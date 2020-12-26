<%--
  Created by IntelliJ IDEA.
  User: brave
  Date: 2020/12/15
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>排名</title>
    <style>
        *{margin:0;padding:0;box-sizing: border-box;}
        body
        {
            background: url(image/backgroudimg/snow.jpg) no-repeat center center fixed;
            /*兼容浏览器版本*/
            -webkit-backface-size: cover;
            -o-background-size:cover;
            background-size: cover;
            font-family: "微软雅黑", sans-serif;
        }
        #divAll
        {
            position: absolute;
            top:30%;
            left:40%;
            margin:-150px 0 0 -150px;
            width: 600px;
            height:500px;
        }
        #divTop
        {
            list-style-type:none;
            padding:10px;
            width:100%;
            color: white;
            margin-bottom:10px;
            background-color: cadetblue;
            border:1px solid cadetblues ;
            border-radius: 4px;
            letter-spacing: 2px;
            text-align: center;
            font-family: "微软雅黑";
        }
        td
        {
            padding:10px;
            width:100%;
            color: white;
            margin-bottom:10px;
            background-color: gray;
            border:1px solid gray;
            border-radius: 4px;
            letter-spacing: 2px;
            text-align: center;
        }
        /*用户一栏的样式*/
        .yonghu
        {
            width:60%;
            background-color: #555555;
        }

    </style>

</head>
<body>
<h2 align="center">欢迎<font color="aqua">${sessionScope.loginUser.userName}</font>登录</h2>
<div id="divAll">
    <div id="divTop">
        <div id="divOne">排行榜</div>
    </div>
    <div id="Bottom">
        <table>
            <tr>
            <td class="yonghu">用户</td>
            <td class="nianling">年龄</td>
            <td class="defen">得分</td>
            </tr>
            <c:forEach items="${userRankList}" var="user">
                <tr align="center">
                    <td>${user.userName}</td>
                    <td>${user.userAge}</td>
                    <td>${user.userScores}</td>
                </tr>
            </c:forEach>
        </table>
        <p ><font color="black"><b>望再接再厉！</b></font></p>
    </div>
</div>
</div>
<button onclick="window.open('http://localhost:8080/homePage.jsp','_self')">Back to homePage</button>
</body>
</html>
