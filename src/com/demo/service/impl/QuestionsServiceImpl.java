package com.demo.service.impl;

import com.demo.entity.Question;
import com.demo.repository.UserRepository;
import com.demo.repository.impl.UserRepositoryImpl;
import com.demo.service.QuestionsService;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionsServiceImpl implements QuestionsService {

    private UserRepository userRepository = new UserRepositoryImpl();

    public List<Question> getQuestions(int grade)
    {
        List<Question> questions = new ArrayList<Question>();
        Random rand = new Random();
        int t;
        String ch1 = "null", ch2 = "null", ch3 = "null", ch4 = "null";  //两个操作符
        double a = 1, b = 1, c = 1;   //三个操作数
        int type = 0;
        for(int i = 1; i <= 10; i++)
        {
            t = rand.nextInt(4);
            //低年级只有加减法
            if(grade == 1 || grade == 2)
            {
                type = 1;
                if(t == 0 || t == 1)
                    ch1 = "+";
                else
                    ch1 = "-";
                a = rand.nextInt(100) + 1;
                b = rand.nextInt(100) + 1;
            }
            //中高年级有乘除法
            else
            {
                if (t == 0) ch1 = "+";
                if (t == 1) ch1 = "-";
                if (t == 2) ch1 = "×";
                if (t == 3) ch1 = "÷";
                //中年级有两个操作数，一个操作符
                if(grade == 3 || grade == 4)
                {
                    type = 2;
                    a = rand.nextInt(300) + 1;
                    b = rand.nextInt(300) + 1;
                }
                //只有高年级才有两个操作符
                else if(grade == 5 || grade == 6)
                {
                    a = (rand.nextInt(1000) + 1) * 0.01;
                    b = (rand.nextInt(1000) + 1) * 0.01;
                    c = (rand.nextInt(1000) + 1) * 0.01;
                    //出无括号的题目：a * b + c型
                    if(rand.nextInt(2) == 0)
                    {
                        type = 3;
                        t = rand.nextInt(4);
                        if (t == 0) ch2 = "+";
                        if (t == 1) ch2 = "-";
                        if (t == 2) ch2 = "*";
                        if (t == 3) ch2 = "/";
                    }
                    //出有括号的题目
                    else
                    {
                        // (a+b)/c型
                        if(rand.nextInt(2) == 0)
                        {
                            a = rand.nextInt(30) + 1;
                            b = rand.nextInt(30) + 1;
                            c = rand.nextInt(10) +1;
                            type = 4;
                            ch2 = ch1;
                            ch1 = "(";
                            ch3 = ")";
                            t = rand.nextInt(4);
                            if (t == 0)  ch4 = "+";
                            if (t == 1)  ch4 = "-";
                            if (t == 2)  ch4 = "×";
                            if (t == 3)  ch4 = "÷";
                        }
                        // a*(b+c)型
                        else
                        {

                            a = rand.nextInt(10) + 1;
                            b = rand.nextInt(30) + 1;
                            c = rand.nextInt(30) +1;
                            type = 5;
                            ch2 = "(";
                            ch4 = ")";
                            t = rand.nextInt(4);
                            if (t == 0)  ch3 = "+";
                            if (t == 1)  ch3 = "-";
                            if (t == 2)  ch3 = "×";
                            if (t == 3)  ch3 = "÷";
                        }
                    }
                }
            }
            //得到一个对象
            questions.add(new Question(a, b, c, ch1, ch2, ch3, ch4, type));
        }
        return questions;
    }
    //写入文件
    public void buildFile(String path, List<Question> questions) throws IOException {
        File f= new File(path);    // 声明File对象
        Writer out = null;         // 准备好一个输出的对象
        out = new FileWriter(f);
        String str = "null";
        for(int i = 0; i < questions.size(); i++)
        {
            Question q = questions.get(i);
            str = q.getStr();
            out.write(str + "\n");                          // 将内容输出，保存文件
        }
        out.write("\n\n答案\n");
        double ans;
        DecimalFormat decimalFormat = new DecimalFormat("#################.##");
        for(int i = 0; i < questions.size(); i++)
        {
            Question q = questions.get(i);
            ans = q.getAnswer();
            out.write(decimalFormat.format(ans) + "\n");
        }
        out.close();
    }

    @Override
    public void updateScores(String userName, Integer addScores) {
        userRepository.updateScores(userName, addScores);
    }
}
