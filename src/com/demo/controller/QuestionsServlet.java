package com.demo.controller;

import com.demo.entity.Question;
import com.demo.entity.User;
import com.demo.service.QuestionsService;
import com.demo.service.impl.QuestionsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@WebServlet("/questions")
public class QuestionsServlet extends HttpServlet {

    private QuestionsService questionsService = new QuestionsServiceImpl();
    private List<Question> questions;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String grade = req.getParameter("grade");
        questions = questionsService.getQuestions(Integer.parseInt(grade));
        String path = req.getServletContext().getRealPath("questionFile/test");
        questionsService.buildFile(path, questions);
        for(int i = 0; i < questions.size(); i++) {
            req.setAttribute("question"+Integer.toString(i),questions.get(i));
        }
        for(int i = 0; i < questions.size(); i++) {
            req.setAttribute("answer"+Integer.toString(i),"");
        }
        req.setAttribute("addScores","");
        req.getRequestDispatcher("questions.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loginUser");
        List<String> answers = new ArrayList<>();
        for(int i = 0; i < questions.size(); i++){
            String str = req.getParameter("answer"+Integer.toString(i));
            answers.add(str);
        }

        int addScores = 0;
        for(int i = 0; i < questions.size(); i++) {
            if(Double.parseDouble(answers.get(i)) == questions.get(i).getAnswer())
            {
                addScores += 10;
                req.setAttribute("answer"+Integer.toString(i),answers.get(i)+"√");
            }
            else
            {
                req.setAttribute("answer"+Integer.toString(i),answers.get(i)+"×");
            }
        }

        System.out.println(addScores);
        questionsService.updateScores(user.getUserName(), addScores);

        for(int i = 0; i < questions.size(); i++) {
            req.setAttribute("question"+Integer.toString(i),questions.get(i));
        }
        req.setAttribute("addScores",Integer.toString(addScores));
        req.getRequestDispatcher("questions.jsp").forward(req,resp);
    }
}
