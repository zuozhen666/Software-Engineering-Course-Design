package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.LoginService;
import com.demo.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();

    /**
     * 登录处理
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = loginService.login(username, password);
        if (user != null){
            HttpSession session = req.getSession();
            session.setAttribute("loginUser", user);
            //跳转到主页
            resp.sendRedirect("homePage.jsp");
        }else{//登陆失败
            //绑定数据
            req.setAttribute("loginMsg","用户名或者密码错误!");
            //转发
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
