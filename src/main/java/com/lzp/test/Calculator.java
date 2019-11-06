package com.lzp.test;

public class Calculator {
    public static int result = 0 ;

    public void add(int n) {
        if (n % 2 == 0){
            result = result + n;
        }else if (n % 2 != 0){
            System.out.println("n不是偶数");
        }
    }

    public void divide(int n) {
         result = result / n;
    }

    public void square(int n) {
         result = n * n;
    }

    public void clear() {
        result = 0;
    }
    public int getResult() {
        return result;
    }
}