# coding:UTF-8
from . import api
from ihome.utils.captcha.captcha import captcha
from ihome import redis_store, constants, db
from flask import current_app, jsonify, make_response, request
from ihome.utils.response_code import RET
from ihome.models import User
import random
from ihome.libs.yuntongxun.sms import CCP


# GET 127.0.0.1:5000/api/v1.0/image_codes/<image_code_id>
@api.route("/image_codes/<image_code_id>")
def get_image_code(image_code_id):
    """
    获取图片验证码
    ;return; 验证码图片
    """
    # 获取参数
    # 检验参数
    # 业务逻辑参数
    # 返回值

    # 业务逻辑处理
    # 生成验证码图片
    # 名字、真实文本，图片数据
    name, text, image_data = captcha.generate_captcha()
    # 将验证码真实值与编号保存到redis中,设置有效期
    # redis:字符串、列表、哈希、set
    # "key":xxx 使用哈希最为有效值只能整体设置
    # "image_code":["id1":"真实文本"，"id2":"真实文本"] 哈希类型 hset("image_codes")

    try:  # 上述两条指令可以一步完成  记录名                         有效期                           记录值
        redis_store.set("image_code_%s" % image_code_id, text)
        redis_store.expire("image_code_%s" % image_code_id, constants.IMAGE_CODE_REDIS_EXPIRES)
    except Exception as e:
        # 记录日志
        current_app.logger.error(e)
        # return jsonify(errno=RET.DBERR, errmsg="save image code failed")
        # 出现异常，返回json格式的提示
        return jsonify(errno=RET.DBERR, errmsg="保存图片验证码失败")
    # 返回图片
    resp = make_response(image_data)
    resp.headers["Content-Type"] = "image/jpg"
    return resp


# GET /api/v1.0/sms_codes/<mobiles>?image_code=xxxx&image_code_id=xxxx
@api.route("/sms_codes/<mobile>")
def get_sms_code(mobile):
    """获取短信验证码"""
    # 获取参数
    image_code = request.args.get('image_code')
    image_code_id = request.args.get('image_code_id')

    # 检验参数
    if not all([image_code, image_code_id]):
        # 参数不完整
        return jsonify(errno=RET.PARAMERR, errmsg='参数不完整')

    # 逻辑处理
    # 从redis中取出验证码图片的真实值
    try:
        real_image_code = redis_store.get('image_code_%s' % image_code_id)
        print real_image_code
    except Exception as e:
        current_app.logger.error(e)
        return jsonify(errno=RET.DBERR, errmsg='数据库异常')

    # 判断真实值是否过期,redis中如果过期则返回None
    if real_image_code is None:
        return jsonify(errno=RET.NODATA, errmsg='图片验证码失效')

    # 删除redis中的图片验证码，防止用户使用同一个图片使用多次
    try:
        redis_store.delete("image_code_%s" % image_code_id)
    except Exception as e:
        current_app.logger.error(e)

    # 验证用户填写的验证码与redis中的真实值是否相等
    if image_code.lower() != real_image_code.decode('utf-8').lower():
        return jsonify(errno=RET.DATAERR, errmssg='图片验证码错误')

    # 判断对于这个手机号的操作，在60秒内有没有之前的纪录，如果有，则不允许用户处理
    try:
        send_flag = redis_store.get("send_sms_code_%s" % mobile)
    except Exception as e:
        current_app.logger.error(e)
    else:
        if send_flag is not None:
            # 表示在60s之前有发送的纪录，直接返回
            return jsonify(errno=RET.REQERR, errmsg="请求过于频繁，请60s后重试")

    # 判断手机号是否已注册
    try:
        user = User.query.filter_by(mobile=mobile).first()
    except Exception as e:
        current_app.logger.error(e)
    else:
        if user is not None:
            return jsonify(errno=RET.DATAEXIST, errmsg='手机号已注册')

    # 生成短信验证码 6 位
    # import random
    sms_code = "%06d" % random.randint(0, 999999)  # %06d  表示生成6位整数，不够的前边补0 ，如029541

    # 保存短信验证码到redis中
    try:
        redis_store.setex("sms_code_%s" % mobile, constants.SMS_CODE_REDIS_EXPIRES, sms_code)
        # 保存发送给这个手机号的纪录，防止用户再次发送短信的操作
        # redis_store.setex("send_sms_code_%s" % mobile, constants.SEND_SMS_CODE_INTERVAL,1)
    except Exception as e:
        current_app.logger.error(e)
        return jsonify(errno=RET.DBERR, errmsg='短信验证码保存异常')
    # 发送短信验证码
    # 返回异步任务的结果
    try:
        ccp = CCP()
        result = ccp.send_template_sms(mobile, [sms_code, int(constants.SMS_CODE_REDIS_EXPIRES / 60)], 1)
    except Exception as e:
        current_app.logger.error(e)
    if result == 0:
        #发送成功
        return jsonify(error=RET.OK, errmsg="发送成功")
    else:
        return jsonify(error=RET.THIRDERR, errmsg="发送失败")

