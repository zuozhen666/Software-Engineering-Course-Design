package com.demo.controller;

import com.demo.entity.User;
import com.demo.service.RankService;
import com.demo.service.impl.RankServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/rank")
public class rankServlet extends HttpServlet {

    private RankService rankService = new RankServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userRankList = rankService.rank();
        req.setAttribute("userRankList",userRankList);
        req.getRequestDispatcher("rank.jsp").forward(req,resp);
    }
}
