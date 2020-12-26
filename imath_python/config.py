# coding:utf-8
#使用类的继承
import redis

class Config(object):
    """配置信息"""
    SECRET_KEY = "abcd"

    #数据库
    SQLALCHEMY_DATABASE_URI = "mysql://root:mysql@127.0.0.1:3306/ihome_python04"
    SQLALCHEMY_TRACK_MODIFICATIONS = True

    # redis
    REDIS_HOST = "127.0.0.1"
    REDIS_PORT = 6379

    # flask-session
    SESSION_TYPE = redis
    Session_REDIS = redis.StrictRedis(host=REDIS_HOST,port = REDIS_PORT)
    SESSION_USE_SIGNER = True  # 对cookie中的sessionID进行隐藏
    PERMANENT_SESSION_LIFETIME = 86400  #session数据的有效期，s

class DevelopmentConfig(Config):
    """开发模式的配置信息"""
    DEBUG = True

class ProductConfig(Config):
    """生产环境的配置信息"""
    pass

config_map = {
    "develop": DevelopmentConfig,
    "product": ProductConfig
}