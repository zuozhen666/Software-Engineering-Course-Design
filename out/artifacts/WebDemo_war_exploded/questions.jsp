<%--
  Created by IntelliJ IDEA.
  User: brave
  Date: 2020/12/18
  Time: 8:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>口算题生成器</title>
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
        /*整个做题框的css，并使用绝对定位居中*/
        #divAll
        {
            position: absolute;
            top:25%;
            left:50%;
            margin:-150px 0 0 -150px;
            width: 300px;
            height:300px;
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
        #li1
        {
            position: absolute;
            left:-20%;
            width:50%;

        }
        #li2
        {
            position: absolute;
            left:65%;
            width:50%;
        }
        /*提交按钮的CSS样式*/
        p button{
            position: absolute;
            left:0%;
            width:40%;
            padding:10px;
            background-color:cadetblue;
            border:1px solid cadetblue;
            border-radius: 4px;
            cursor:pointer;
            box-shadow: hotpink;
            font-family: "arial black";
            font-size: 16px;
            font-weight: 400;
        }
        /*换卷按钮的CSS样式*/
        span input{
            position: absolute;
            left:60%;
            width:40%;
            padding:10px;
            background-color:cadetblue;
            border:1px solid cadetblue;
            border-radius: 4px;
            cursor:pointer;
            box-shadow: hotpink;
            font-family: "arial black";
            font-size: 16px;
            font-weight: 400;
        }
    </style>
</head>
<body>
<h2 align="center">欢迎<font color="#7fff00">${sessionScope.loginUser.userName}</font>登录</h2>
<div id="divAll">
    <!--定义无序列表-->
    <ul id="jst_biaoti_shezhi">
        <li id="li1">耗时：<span id="jst_haoshi">103s</span></li>
        <li id="li2">得分：<span id="jst_defen">${addScores}</span></li>
    </ul>
    <br/><br/><br/>
    <!--答题框-->
    <form action="/questions" method="post">
    <div id="neirong_html">
        <ul id="neirong_ul">
            <li>
                <span class="question_show">${question0.str}</span>
                <input type="text" name="answer0" size="2" zqda="" onkeyup="quanjiao_zhuan_banjiao(this);" required="required"/>
                <span class="answer_check">${answer0}</span>
                <i class="s_jieguo"></i>
            </li>
            <li>
                <span class="question_show">${question1.str}</span>
                <input type="text" name="answer1" size="2" zqda="" onkeyup="quanjiao_zhuan_banjiao(this);" required="required"/>
                <span class="answer_check">${answer1}</span>
                <i class="s_jieguo"></i>
            </li>
            <li>
                <span class="question_show">${question2.str}</span>
                <input type="text" name="answer2" size="2" zqda="" onkeyup="quanjiao_zhuan_banjiao(this);" required="required"/>
                <span class="answer_check">${answer2}</span>
                <i class="s_jieguo"></i>
            </li>
            <li>
                <span class="question_show">${question3.str}</span>
                <input type="text" name="answer3" size="2" zqda="" onkeyup="quanjiao_zhuan_banjiao(this);" required="required"/>
                <span class="answer_check">${answer3}</span>
                <i class="s_jieguo"></i>
            </li>
            <li>
                <span class="question_show">${question4.str}</span>
                <input type="text" name="answer4" size="2" zqda="" onkeyup="quanjiao_zhuan_banjiao(this);" required="required"/>
                <span class="answer_check">${answer4}</span>
                <i class="s_jieguo"></i>
            </li>
            <li>
                <span class="question_show">${question5.str}</span>
                <input type="text" name="answer5" size="2" zqda="" onkeyup="quanjiao_zhuan_banjiao(this);" required="required"/>
                <span class="answer_check">${answer5}</span>
                <i class="s_jieguo"></i>
            </li>
            <li>
                <span class="question_show">${question6.str}</span>
                <input type="text" name="answer6" size="2" zqda="" onkeyup="quanjiao_zhuan_banjiao(this);" required="required"/>
                <span class="answer_check">${answer6}</span>
                <i class="s_jieguo"></i>
            </li>
            <li>
                <span class="question_show">${question7.str}</span>
                <input type="text" name="answer7" size="2" zqda="" onkeyup="quanjiao_zhuan_banjiao(this);" required="required"/>
                <span class="answer_check">${answer7}</span>
                <i class="s_jieguo"></i>
            </li>
            <li>
                <span class="question_show">${question8.str}</span>
                <input type="text" name="answer8" size="2" zqda="" onkeyup="quanjiao_zhuan_banjiao(this);" required="required"/>
                <span class="answer_check">${answer8}</span>
                <i class="s_jieguo"></i>
            </li>
            <li>
                <span class="question_show">${question9.str}</span>
                <input type="text" name="answer9" size="2" zqda="" onkeyup="quanjiao_zhuan_banjiao(this);" required="required"/>
                <span class="answer_check">${answer9}</span>
                <i class="s_jieguo"></i>
            </li>
        </ul>
    </div>
    <!--答题框结束-->
    <p><button type="submit">提交</button></p>
    </form>
    <span><input type="button" onclick="window.open('http://localhost:8080/questions?grade=1','_self')" value="换卷" class="all_an_1" /></span>
</div>
    <button onclick="window.open('http://localhost:8080/download','_self')">Download</button>
    <br/>
    <br/>
    <br/>
    <button onclick="window.open('http://localhost:8080/homePage.jsp','_self')">Back to homePage</button>
</body>
</html>

