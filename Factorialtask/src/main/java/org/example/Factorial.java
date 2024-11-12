package org.example;

import java.util.concurrent.Callable;

public class Factorial implements Callable<Integer> {
    private final int number;

    public Factorial(int number){
        this.number = number;
    }
    @Override
    public Integer call(){
        return calculateFactorial(number);
    }

    private Integer calculateFactorial(int num){
        int result = 1;
        for (int i = 1; i <= num; i++){
            result *= i;
        }
        return result;
    }
}
