#!/usr/bin/env python
# -*- coding: utf-8 -*-
# coding:UTF-8

from flask import Blueprint, current_app,make_response
from flask_wtf import csrf
# 提供静态文件的蓝图
html = Blueprint("web_html", __name__)


# 用户可以正常访问
# 127.0.0.1:5000/()
# 127.0.0.1:5000/(login.html)
# 127.0.0.1:5000/(register.html)
# 127.0.0.1:5000/(favico.ico)  # 浏览器认为的网站标示，浏览器自己会访问这个资源

# 加转化器，使得用户可以自己匹配：str，int，
@html.route("/<re(r'.*'):html_file_name>")
def get_html(html_file_name):
    """提供html文件"""
    # 如果html_file_name为空，表示访问的路径为/ , 请求的是主页
    if not html_file_name:
        html_file_name = 'login.html'

    # 如果html_file_name不是favicon.ico
    if html_file_name != 'favicon.ico':
        html_file_name = 'html/' + html_file_name

    # 创建一个csrf_token的值
    #csrf_token = csrf.generate_csrf()

    # flask 提供的返回静态文件的方法
    resp = make_response(current_app.send_static_file(html_file_name))

    # 设置cookie值,临时有效，仅在本次浏览器有效时缓存
    #resp.set_cookie('csrf_token', csrf_token)

    # flask 提供的返回静态文件的方法
    return resp
