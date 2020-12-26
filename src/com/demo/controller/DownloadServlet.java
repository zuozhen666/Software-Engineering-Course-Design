package com.demo.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应方式
        resp.setContentType("application/x-msdownload");
        String fileName = "Questions.txt";
        //设置下载后的文件名
        resp.setHeader("Content-Disposition","attachement;filename="+fileName);
        //获取输出流
        OutputStream outputStream = resp.getOutputStream();
        String path = req.getServletContext().getRealPath("questionFile/test");
        InputStream inputStream = new FileInputStream(path);
        int temp = 0;
        while((temp=inputStream.read())!=-1){
            outputStream.write(temp);
        }
        inputStream.close();
        outputStream.close();
    }
}
