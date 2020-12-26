<%--
  Created by IntelliJ IDEA.
  User: brave
  Date: 2020/12/13
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--<meta charset="utf-8">--%>
    <title>用户登陆界面</title>
                         <style>
                             /*将页面所有元素的padding和margin都设为0*/
                         *{margin:0;padding:0;box-sizing: border-box;}
        /*设置背景图，字体设置为微软雅黑*/
        body{background: url(image/backgroudimg/pink.jpg);font-family: "微软雅黑"，sans-serif;}
        /*整个登录框的cxx，并使用绝对定位居中*/
        .login{
            position: absolute;
            top:50%;
            left:50%;
            margin:-150px 0 0 -150px;
            width: 300px;
            height:300px;
        }
        /*h1标签的css,text-shadow设置阴影使文字更加好看，letter-spcaing设置字符间距*/
        .login h1{color: black;text-shadow: 0px 10px 8px white;letter-spacing:2px;text-align: center;margin-bottom:20px;}
        /*两个输入框的css，border属性设置边框粗细及颜色，border-radius设置边框的圆角角度*/
        input{
            padding:10px;
            width:100%;
            color: white;
            margin-bottom:10px;
            background-color: #555555;
            border:1px solid #000000;
            border-radius: 4px;
            letter-spacing: 2px;
        }
        form button{
            width:50%;
            padding:10px;
            background-color:#993333;
            border:1px solid wheat;
            border-radius: 4px;
            cursor:pointer;
            box-shadow: hotpink;
            font-family: "arial black";
            font-size: 16px;
            font-weight: 400;
        }
        a button{
            width:50%;
            padding:10px;
            background-color:#993333;
            border:1px solid wheat;
            border-radius: 4px;
            cursor:pointer;
            box-shadow: hotpink;
            font-family: "arial black";
            font-size: 16px;
            font-weight: 400;
            float: left;
        }
        span {
            font-size: 16px;
            color:  red;
        }
    </style>

    <script type="text/javascript">
        //光标选定，清除提示信息
        function clearLoginMsg(){
            var spanEle = document.getElementById("login_span");
            spanEle.innerHTML = "";
        }
    </script>

</head>
<body>
<div class="headtop"></div>
<div class="login">
    <h1>Login</h1>
    <form action="/login" method="post">
        <input type="text" name="username" placeholder="用户名" required="required" id="username" onfocus="clearLoginMsg();">
        <br/>
        <input type="password" name="password" placeholder="密码" required="required" id="password">
        <a href="">
            <button onclick="window.open('http://localhost:8080/register.jsp','_self')">无账号，先注册</button>
        </a>
        <button type="submit">登录</button>
        <br/>
        <%--提示信息--%>
        <span id = "login_span">${requestScope.loginMsg}</span>
    </form>
</div>
</body>
</html>
