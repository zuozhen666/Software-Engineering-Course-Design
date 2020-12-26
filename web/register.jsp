<%--
  Created by IntelliJ IDEA.
  User: brave
  Date: 2020/12/14
  Time: 12:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>用户登陆界面</title>
    <style>
        /*将页面所有元素的padding和margin都设为0*/
        *{margin:0;padding:0;box-sizing: border-box;}
        /*设置背景图，字体设置为微软雅黑*/
        body{background: url(image/backgroudimg/register.jpg);font-family: "微软雅黑",sans-serif;}
        /*整个注册框的CSS，并使用绝对定位居中*/
        .regist{
            position: absolute;
            top:50%;
            left:50%;
            margin:-150px 0 0 -150px;
            width: 300px;
            height:300px;
        }
        /*h1标签的css,text-shadow设置阴影使文字更加好看，letter-spcaing设置字符间距*/
        .regist h1{color: black;text-shadow: 0px 10px 8px white;letter-spacing:2px;text-align: center;margin-bottom:20px;}
        /*两个输入框的css，border属性设置边框粗细及颜色，border-radius设置边框的圆角角度*/
        form input{
            padding:10px;
            width:110%;
            color: white;
            margin-bottom:10px;
            background-color: lightblue;
            border:1px solid skyblue;
            border-radius: 4px;
            letter-spacing: 2px;
        }
        /*修改input文本框中字符的样式*/
        input::-webkit-input-placeholder{
            color: #555555;
            font-size: 12px;
            /*居中*/
            text-align: center;
        }
        /*"昵称" "密码" "确认" "年龄"的CSS样式*/
        form span{
            padding: 11px;
            padding-bottom:13%;
            padding-top: 13%;
            margin-top: 0%;
            margin-bottom: 13%;
            width:100%;
            color: white;
            background-color: dimgray;
            border:1px solid gray;
            border-radius: 4px;
            letter-spacing: 2px;
            font-family:"微软雅黑";
            font-weight: 540;
            font-size: small;
            float: left;
        }
        /*重置按钮的CSS样式*/
        strong input{
            position: absolute;
            left:15%;
            width:35%;
            padding:8px;
            background-color:lightskyblue;
            border:1px solid skyblue;
            border-radius: 4px;
            cursor:pointer;
            box-shadow: #000000;
            font-family: "arial black";
            font-size: 16px;
            font-weight: 400;
            float:left;
        }
        /*注册按钮的CSS样式*/
        p button{
            position: absolute;
            left:55%;
            width:35%;
            padding:8px;
            background-color:lightskyblue;
            border:1px solid skyblue;
            border-radius: 4px;
            cursor:pointer;
            box-shadow: #000000;
            font-family: "arial black";
            font-size: 16px;
            font-weight: 400;
        }
        em{
            font-family:"微软雅黑";
            color:red;
        }
    </style>

    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">

        $(function(){
            $("#username").blur(function(){
                var username = $("#username").val();
                //发送异步请求
                $.ajax({
                    url:"regist?username="+username,
                    type:"get",
                    success:function(data){
                        $("#regist_span").html(data);
                    }
                })
            })
            //repassword和password检查一致性
            $("#repassword").blur(function(){
                var password = $("#password").val();
                var repassword = $("#repassword").val();

                if(password == repassword){
                    if(password!="")
                        $("#password_span").html("密码一致");
                    else
                        $("#password_span").html("");
                }else{
                    $("#password_span").html("密码不一致");
                }
            })
        })

        //光标选定，清除提示信息
        function clearUserNameMsg(){
            var spanEle = document.getElementById("regist_span");
            spanEle.innerHTML = "";
        }
        function clearPwdMsg(){
            var spanEle = document.getElementById("password_span");
            spanEle.innerHTML = "";
        }
    </script>

</head>
<body>
<div class="headtop"></div>
<div class="regist">
    <h1>R e g i s t</h1>
    <form action="/regist" method="post">
        <table>
            <tr>
                <td><span>昵称</span></td>
                <td><input type="text" placeholder="起个名字吧" name="username" required="required" id="username" onfocus="clearUserNameMsg();"/><em id="regist_span"></em></td>
            </tr>
            <tr>
                <td><span>密码</span></td>
                <td><input type="password" placeholder="请输入密码" name="password"  required="required" id="password" onfocus="clearPwdMsg();"/></td>
            </tr>
            <tr>
                <td><span>确认</span></td>
                <td><input type="password" placeholder="请确认密码" name="repassword"  required="required" id="repassword"/><em id="password_span"></em></td>
            </tr>
            <tr>
                <td><span>年龄</span></td>
                <td><input type="text" placeholder="你多大了啊" name="age" required="required">
            </tr>
        </table>
        <p><button type="submit">注册</button></p>
        <strong><input type="reset" value="重置"/></strong>
    </form>
</div>
</body>
</html>


