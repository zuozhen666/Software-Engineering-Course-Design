package com.demo.entity;
/*
    type = 1:
    operand_1:第一个操作数   operand_2:第二个操作数   operand_3:不管
    operator_1:第一个操作符,只能为加或减  operator_2:不管   operator_3:不管   operator_4:不管
    例子：a + b

    type = 2;
    operand_1:第一个操作数   operand_2:第二个操作数   operand_3:不管
    operator_1:第一个操作符,可以为乘或除  operator_2:不管   operator_3:不管   operator_4:不管
    例子：a + b

    type = 3:
    operand_1:第一个操作数   operand_2:第二个操作数   operand_3:第三个操作数
    operator_1:第一个操作符  operator_2:第二个操作符   operator_3:不管   operator_4:不管
    例子：a * b + c

    type = 4:
    operand_1:第一个操作数    operand_2:第二个操作数    operand_3:第三个操作数
    operator_1:"("         operator_2:第一个操作符    operator_3:")"   operator_4:第二个操作符
    例子:(a + b) / c

    type = 5:
    operand_1:第一个操作数     operand_2:第二个操作数   operand_3:第三个操作数
    operator_1:第一个操作符    operator_2:"("         operator_3:第二个操作符   operator_4:")"
    例子:a * （b - c）
 */
import java.text.DecimalFormat;
public class Question{
    //三个操作数
    private double operand_1;
    private double operand_2;
    private double operand_3;
    //四个操作符
    private String operator_1;
    private String operator_2;
    private String operator_3;
    private String operator_4;
    //年级
    private int grade;
    //题目类型
    private int type; //低年级为1,中年级为2,高年级有三种类型为3、4
    //题目答案
    private double ans;
    //题目的字符串
    private String str;
    //构造器
    public Question(double operand_1, double operand_2, double operand_3,
                    String operator_1, String operator_2, String operator_3, String operator_4, int type)
    {
        this.operand_1 = operand_1;
        this.operand_2 = operand_2;
        this.operand_3 = operand_3;
        this.operator_1 = operator_1;
        this.operator_2 = operator_2;
        this.operator_3 = operator_3;
        this.operator_4 = operator_4;
        this.type = type;
        DecimalFormat decimalFormat = new DecimalFormat("#################.#");
        switch(type)
        {
            // a * b
            case 1:
            case 2:
                this.str = decimalFormat.format(operand_1) + " " + operator_1 + " "
                        + decimalFormat.format(operand_2) + " " + "=";
                break;
            // a / b - c
            case 3:
                this.str = decimalFormat.format(operand_1) + " " + operator_1 + " " + decimalFormat.format(operand_2)
                        + " " + operator_2 + " " + decimalFormat.format(operand_3) + " " + "=";
                break;
            // (a + b) * c
            case 4:
                this.str = "(" + decimalFormat.format(operand_1) + " " + operator_2 + " " + decimalFormat.format(operand_2)
                        + ")" + " " + operator_4 + " " + decimalFormat.format(operand_3) + " " + "=";
                break;
            // a * (b + c)
            case 5:
                this.str = decimalFormat.format(operand_1) + " " + operator_1 + " " + "(" + decimalFormat.format(operand_2)
                        + " " + operator_3 + " " + decimalFormat.format(operand_3) + ")" + " " + "=";
        }
    }
    //得到操作数
    public double getOperand_1() { return operand_1; }
    public double getOperand_2() { return operand_2; }
    public double getOperand_3() { return operand_3; }
    //得到操作符
    public String getOperator_1() { return operator_1; }
    public String getOperator_2() { return operator_2; }
    public String getOperator_3() { return operator_3; }
    public String getOperator_4() { return operator_4; }
    //得到答案
    public double getAnswer()
    {
        double a = 1, b = 1, ans = 2;
        String operator = "+";
        switch (type)
        {
            case 1:
                if (operator_1 == "+")  ans = operand_1 + operand_2;
                else if (operator_1 == "-")  ans = operand_1 - operand_2;
                break;
            case 2:
                if (operator_1 == "+")  ans = operand_1 + operand_2;
                else if (operator_1 == "-")  ans = operand_1 - operand_2;
                else if (operator_1 == "×")  ans = operand_1 * operand_2;
                else if (operator_1 == "÷")  ans = operand_1 / operand_2;
                break;
            case 3:
                //无括号，例如：a * b + c
                if (operator_1 == "×" || operator_1 == "÷")
                {
                    if (operator_1 == "×")
                        a = operand_1 * operand_2;
                    else
                        a = operand_1 / operand_2;
                    b = operand_3;
                    operator = operator_2;
                }
                else if (operator_2 == "×" || operator_2 == "÷")
                {
                    if (operator_2 == "*")
                        b = operand_2 * operand_3;
                    else
                        b = operand_2 / operand_3;
                    a = operand_1;
                    operator = operator_1;
                }
                //ch1和ch2都无乘除
                else {
                    if (operator_1 == "+")
                        a = operand_1 + operand_2;
                    else
                        a = operand_1 - operand_2;
                    b = operand_3;
                    operator = operator_2;
                }
                if (operator == "+")  ans = a + b;
                else if (operator == "-")  ans = a - b;
                else if (operator == "×")  ans = a * b;
                else if (operator == "÷")  ans = a / b;
                break;
            case 4:
                //存在括号:(a+b)*c
                if(operator_2 == "+")  a = operand_1 + operand_2;
                else if(operator_2 == "-")  a = operand_1 - operand_2;
                else if(operator_2 == "×")  a = operand_1 * operand_2;
                else if(operator_2 == "÷")  a = operand_1 / operand_2;
                operator = operator_4;
                b = operand_3;
                if (operator == "+")  ans = a + b;
                else if (operator == "-")  ans = a - b;
                else if (operator == "×")  ans = a * b;
                else if (operator == "÷")  ans = a / b;
                break;
            case 5:
                //存在括号：a*(b+c)
                if(operator_3 == "+")  b = operand_2 + operand_3;
                else if(operator_3 == "-")  b = operand_2 - operand_3;
                else if(operator_3 == "×")  b = operand_2 * operand_3;
                else if(operator_3 == "÷")  b = operand_2 / operand_3;
                operator = operator_1;
                a = operand_1;
                if (operator == "+")  ans = a + b;
                else if (operator == "-")  ans = a - b;
                else if (operator == "×")  ans = a * b;
                else if (operator == "÷")  ans = a / b;
        }
        this.ans = ans;
        return ans;
    }
    //得到题目的字符串
    public String getStr(){ return str; }
}