# coding:utf-8
import redis
import logging
from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from config import config_map
from flask_session import Session
from flask_wtf import CSRFProtect
from logging.handlers import RotatingFileHandler
#import pymysql
#pymysql.install_as_MySQLdb()
from ihome.utils.commons import ReConverter

# 数据库
db = SQLAlchemy()

# 创建redis连接对象
redis_store = None

# 为flask补充csrf防护机制
csrf = CSRFProtect()

# 设置日志的纪录等级
logging.basicConfig(level=logging.DEBUG) # 调试debu等级
# 创建日志记录器、指明日志保存的路径、每个日志文件的最大大小、保存的日志文件个数的上限
file_log_handler = RotatingFileHandler("logs/log", maxBytes=1024*1024*100, backupCount=10) #保存到这个文件，最大字节数确定每个文件<100M
# 创建日志记录的格式                 日志等级    输入日志信息的文件名 行数   日志信息
formatter = logging.Formatter('%(levelname)s %(filename)s:%(lineno)d %(message)s')
# 为刚创建的日记记录设置日志记录格式
file_log_handler.setFormatter(formatter)
# 为全局的日志工具对象（flask app使用的）添加日记录器
logging.getLogger().addHandler(file_log_handler)

# app对象做成工厂模式
def create_app(config_name):
    """
    创建flask的应用对象
    ;parm congfig_name; str 配置模式名字（"develop","product"）

    :return:
    """
    app = Flask(__name__)  #创建app


    #根据配置模式的名字获取配置参数的类
    config_class = config_map.get(config_name) #config类
    app.config.from_object(config_class) #导入配置信息，将类传入

    #使用app初始化db
    db.init_app(app)

    #初始化redis工具
    global redis_store
    redis_store = redis.StrictRedis(host=config_class.REDIS_HOST, port=config_class.REDIS_PORT)

    # 利用flask-session，将session数据保存到redis中
    Session(app)  # 修改flask默认机制

    # 为flask补充csrf防护
    # csrf验证机制：从cookie中获取一个csrf_token的值；从请求体中获取一个csrf_token的值，如果两个值相同，则检验通过可以进入到视图函数中执行，如果两个值不同，则检验失败，返回前端状态码400的错误
    # 防护的是hacker伪造行为向服务器发送请求行为，所以需要有csrf_token的值
    CSRFProtect(app)

    # 为flask添加自定义的转换器
    app.url_map.converters['re'] = ReConverter

    # 注册蓝图(避免循环导入)
    from ihome import api_1_0
    app.register_blueprint(api_1_0.api, url_prefix="/api/v1.0")

    # 注册提供静态文件的蓝图
    from ihome import web_html
    app.register_blueprint(web_html.html)


    return app
