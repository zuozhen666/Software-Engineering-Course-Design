package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.RegistService;
import com.demo.service.impl.RegistServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/regist")
public class RegistServlet extends HttpServlet{

    private RegistService registService = new RegistServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 异步检验处理
         * 1.check用户名是否已存在
         * 2.返回提示信息
         */
        String username = req.getParameter("username");
        User user = registService.registCheck(username);
        String msg = "";
        if(user==null){
            if(username!="")
                msg="用户名可用";
        }else{
            msg="用户名已存在";
        }
        System.out.println(msg);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(msg);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 注册处理
         * 1.获取页面信息
         * 2.数据写入数据库
         * 3.注册成功去往登录页面
         */
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String age = req.getParameter("age");
        Integer tmp_age = Integer.parseInt(age);
        registService.insert(username, password, tmp_age);
        resp.sendRedirect("login.jsp");
        System.out.println("regist sucess!");
    }
}
