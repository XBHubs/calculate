package com.example.calculate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //数字 Number 输入
    private int[] idNum = {R.id.txt0, R.id.txt1, R.id.txt2, R.id.txt3, R.id.txt4, R.id.txt5,
            R.id.txt6,
            R.id.txt7, R.id.txt8, R.id.txt9};
//运算符
private int[] idCal = {R.id.plus, R.id.minus, R.id.txtMul, R.id.div, R.id.dot,R.id.pow,
        R.id.sqr,R.id.jc,R.id.per};
    private Button[] buttonsCal = new Button[idCal.length];
    private Button[] buttonsNum = new Button[idNum.length];
    private Button buttonEqu; //=
    private Button buttonClear; // AC
    private Button buttonDel;
    private TextView textView;
    private String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate);
        //显示文本框，无内容
        textView = (TextView)findViewById(R.id.txtView);
        textView.setText("");

        buttonEqu = (Button)findViewById(R.id.equ);
        //设置‘=’鼠标点击事件，点击之后，把输入的表达式显示在TextView里
        buttonEqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=textView.getText().toString();
                Calculate c=new Calculate(str);
                textView.setText(c.singleEval()+"");
            }
        });
        //设置“清空”鼠标点击事件，点击之后，直接使文本框显示内容清空
        buttonClear = (Button) findViewById(R.id.clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("");
                text=""; }
        });
        //设置“回退”鼠标点击事件
        buttonDel = (Button) findViewById(R.id.del);
        buttonDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果现在文本框内容不空，把text设为文本框输入的字符串，并重新设为从开始到倒数第二个组成的字符串
                if (!textView.getText().toString().isEmpty() ) {
                    text = textView.getText().toString();
                    text = text.substring(0, text.length() - 1);
                    textView.setText(text);
                } }
        });
// 给数字键注册单击事件
        for (int i = 0; i < idNum.length; i++) {
            buttonsNum[i] = (Button) findViewById(idNum[i]);//根据点击的按钮id找到对应的数字
            final String msg=buttonsNum[i].getText().toString();
            buttonsNum[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.append(msg);
                }
            });//把输入的内容加入到文本框内
        }
// 给运算符和点注册单击事件
        for (int idcal = 0; idcal < idCal.length; idcal++) {
            buttonsCal[idcal] = (Button) findViewById(idCal[idcal]);
            buttonsCal[idcal].setOnClickListener(new
                    CalOnClick(buttonsCal[idcal].getText().toString()) ) ;
        } }
//内部类
    /**
     * @param msg 点击按钮传入字符
     */
    private class CalOnClick implements View.OnClickListener{
        String msg;
        String[] calSymbol = {"+", "-", "*", "/",".","^","√","n","%"};
        public CalOnClick(String msg) {
            this.msg = msg;
        }
        @Override
        public void onClick(View v) {
            //文本框上的内容不空
            if (!textView.getText().toString().equals("")) {
                //运算符不重复
                /*
                Calculate cc = new Calculate(textView.getText().toString());
                if(cc.isLing())
                     Toast.makeText(MainActivity.this,"根号里的数不能小于0！",Toast.LENGTH_SHORT).show();*/
                if (!isRepeat()){
                    //把内容显示到文本框
                    textView.append(msg);
                }

            }

        }
        // 检查运算符是否重复输入
        private boolean isRepeat(){
            String[] arr=textView.getText().toString().split("");
            for (int i = 0; i < calSymbol.length; i++)
                if( arr[arr.length - 1].equals(calSymbol[i]))//arr[arr.length - 1]是文本框内容的最后一个字符和以上运算符重复，表示有重复
                    return true;
            return false;//表示没有重复
        }
    }
    //Calculate类
    public class Calculate {
        String expression, operator;
        double num1,num2;
        public Calculate(String expression) {
            this.expression = expression;
        }
        public void convert(){
            String[] calSymbol = {"+", "-", "*", "/","^","√","n","%"};

           /* for (int i = 0; i < calSymbol.length; i++){
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
                    break;
                }
            } */

            if(expression.substring(0, 1).equals("-")) {
                expression=expression.substring(1);
                for (int i = 0; i < calSymbol.length; i++){
                    //indexOf():返回字符中indexof（string）中字符串string在父串中首次出现的位置，从0开始，没有返回-1。
                    //如果不是有效字符，就会返回-1，不能做运算
                    int n=expression.indexOf(calSymbol[i]);
                    if( n!=-1){
                        operator =calSymbol[i];
                        num1=Double.valueOf(expression.substring(0, n));
                        num1=0-num1;
                        if(n==expression.length()-1)
                            num2=-1;
                        else num2=Double.valueOf(expression.substring(n+1));
                        break;
                    }
                }
            }
            else {
                for (int i = 0; i < calSymbol.length; i++){
                    int n=expression.indexOf(calSymbol[i]);
                    if( n!=-1){
                        operator =calSymbol[i];
                        num1=Double.valueOf(expression.substring(0, n));
                        if(n==expression.length()-1)
                            num2=0;
                        else num2=Double.valueOf(expression.substring(n+1));
                        break;
                    }
                }
            }
        }//获取操作数后可以直接跳出循环
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
                    if(num1<0){
                        Toast.makeText(MainActivity.this, "请输入正整数！", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else{
                        result=jiecheng(num1);
                        break;
                    }
                case "√":
                    if(num1<0) {
                        Toast.makeText(MainActivity.this, "根号里的数不能小于0！", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    else{
                        result =Math.sqrt(num1);
                        break;
                    }
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
}