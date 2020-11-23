package com.example.calculate;

import android.widget.Toast;

public class Calculate {
     String expression, operator;
     double num1,num2;
    public Calculate(String expression) {
        this.expression = expression;
    }
    public void convert(){
        String[] calSymbol = {"+", "-", "*", "/","^","√","n","%"};
        for (int i = 0; i < calSymbol.length; i++){
            //indexOf():返回字符中indexof（string）中字符串string在父串中首次出现的位置，从0开始，没有返回-1。
            //如果不是有效字符，就会返回-1，不能做运算
            int n=expression.indexOf(calSymbol[i]);
            if( n!=-1){
                operator =calSymbol[i];
                //substring(x,y) 包括x位置，但是不包括y位置
                // num1=Double.valueOf(expression.substring(0,n));
                // num2=Double.valueOf(expression.substring(n+1));
                //第一个操作数就是表达式的第一个字符开始到操作符的前一个字符为止，第二个操作数是从操作符的下一个位置开始直到表达式的结束
                num1=Double.valueOf(expression.substring(0, n));
                if(n==expression.length()-1)
                    num2=-1;
                else num2=Double.valueOf(expression.substring(n+1));
                break; } } }//获取操作数后可以直接跳出循环
    public boolean isLing(){
        if(operator=="√" && num1<=0){
            return true;//表示根号下面的数小于0
        }
        return false;
    }
    public double jiecheng(double a) {
        double sum=1.0;
        for(int j=1;j<=a;j++) {
            sum=sum*j;
        }
        return sum;
    }
    /**
     * 计算 a@b 的简单方法
     * @return
     */
    public String singleEval(){
        convert();
        double result=0.0;
        switch(operator){
            case "+":
                result =num1+num2; break;
            case "-":
                result =num1-num2;break;
            case "*":
                result =num1*num2;break;
            case "/":
                result =num1/num2; break;
            case "^":
                result =Math.pow(num1,num2);break;
            case "n":
                result=jiecheng(num1);
                break;
            case "√":
                result =Math.sqrt(num1);break;
            case "%":
                result =num1/100;break;
        }
        if(num2==0 && operator=="/")
            return "错误！除数不能为0";
        return result+""; }
    @Override
    public String toString() {
        return expression + "="+ singleEval();
    } }