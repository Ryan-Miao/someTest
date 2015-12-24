package com.test.arithmetic;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Administrator on 2015/12/24.
 * 求一串算数表达式的值
 * （1-7+（1*4））
 */
public class Evaluate {

    public static void main(String[] args) {
        java.util.Stack<String> ops = new java.util.Stack<>();
        Stack<Double> vals = new Stack<>();
        Scanner sc = new Scanner(System.in);
        while(true){
            String s = sc.next();
            if(s.equals("wq")){
                break;
            }
            if(s.equals("("))
                ;
            else if (s.equals("+")) ops.push(s);
            else if (s.equals("-")) ops.push(s);
            else if (s.equals("*")) ops.push(s);
            else if (s.equals("/")) ops.push(s);
            else if (s.equals("sqrt")) ops.push(s);
            else if (s.equals(")")){
                String op = ops.pop();
                System.out.println(op);
                double v = vals.pop();
                if(op.equals("+")) v = vals.pop()+v;
                if(op.equals("-")) v = vals.pop()-v;
                if(op.equals("*")) v = vals.pop()*v;
                if(op.equals("/")) v = vals.pop()/v;
                if(op.equals("sqrt")) v = Math.sqrt(v);
                vals.push(v);
            }
            else vals.push(Double.parseDouble(s));
            System.out.println("此时vals:"+vals);
            System.out.println("此时ops:"+ops);
        }
        System.out.println(vals);
    }

}
