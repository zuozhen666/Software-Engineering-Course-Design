<%--
  Created by IntelliJ IDEA.
  User: brave
  Date: 2020/12/18
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <style>
        body
        {
            background: url(image/backgroudimg/snow.jpg) no-repeat center center fixed;
            /*兼容浏览器版本*/
            -webkit-backface-size: cover;
            -o-background-size:cover;
            background-size: cover;
            font-family: "微软雅黑", sans-serif;
        }
        #jiemian
        {
            position: absolute;
            top:30%;
            left:50%;
            margin:-150px 0 0 -150px;
            width: 300px;
            height:300px;
        }
        p
        {
            padding:10px;
            width:110%;
            color: white;
            margin-bottom:10px;
            background-color: cadetblue;
            border:1px solid cadetblue;
            border-radius: 4px;
            letter-spacing: 2px;
        }
        /*去掉无序列表前的符号*/
        li{
            list-style-type:none;
            padding:10px;
            width:100%;
            color: white;
            margin-bottom:10px;
            background-color: dimgrey;
            border:1px solid dimgrey;
            border-radius: 4px;
            letter-spacing: 2px;
        }
        /*去掉超链接的下划线*/
        a {
            text-decoration: none;
            color:white;
            font-weight: 500;
        }
    </style>
</head>
<body>
<div align="center" id="jiemian">
    <p>请选择年级：</p>
    <ul>
        <li><span class="all_an2_0"><a onclick="window.open('http://localhost:8080/questions?grade=1','_self')">一年级</a></span></li>
        <li><span class="all_an2_1"><a onclick="window.open('http://localhost:8080/questions?grade=2','_self')">二年级</a></span></li>
        <li><span class="all_an2_1"><a onclick="window.open('http://localhost:8080/questions?grade=3','_self')">三年级</a></span></li>
        <li><span class="all_an2_1"><a onclick="window.open('http://localhost:8080/questions?grade=4','_self')">四年级</a></span></li>
        <li><span class="all_an2_1"><a onclick="window.open('http://localhost:8080/questions?grade=5','_self')">五年级</a></span></li>
        <li><span class="all_an2_1"><a onclick="window.open('http://localhost:8080/questions?grade=6','_self')">六年级</a></span></li>
    </ul>

</div>

</body>
</html>

