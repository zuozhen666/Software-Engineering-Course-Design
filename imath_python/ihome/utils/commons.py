# coding:UTF-8
# 配置静态文件，浏览器访问页面时需要输入“127.0.0.1:5000/static/html/index.html”，这样有点太不友好了，我们需要的是输入“127.0.0.1:5000”或者“127.0.0.1:5000/index.html”，就可以访问主页面，因此，配置静态文件还是需要的
# 自定义蓝图，用来提供相关资源
# !/usr/bin/env python
# -*- coding: utf-8 -*-

from werkzeug.routing import BaseConverter


# 定义正则转换器
class ReConverter(BaseConverter):
    def __init__(self, url_map, regex):
        # 调用父类的初始化方法
        super(ReConverter, self).__init__(url_map)
        # 保存正则表达式
        self.regex = regex




