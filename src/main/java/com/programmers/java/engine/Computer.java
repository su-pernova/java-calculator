package com.programmers.java.engine;

import java.util.ArrayList;
import java.util.List;

import com.programmers.java.engine.io.Input;
import com.programmers.java.engine.io.Output;

public class Computer implements Runnable{
    Input input;
    Output output;
    Calculator calculator = new Calculator();
    List<String> db = new ArrayList<>();
    private final String MESSAGE = "1. 조회\n2. 계산\n\n선택 : ";

    public Computer(Input input, Output output){
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        while(true){
            String option = input.chooseOpt(MESSAGE);
            int optNum = parseOption(option);
            if(optNum == -1){
                output.inputError();
                continue;
            }
            mainJob(optNum);
        }
    }

    private void mainJob(int optNum) {
        if(optNum == 1){
            select();
        }
        else if(optNum == 2){
            String inputStr = input.input();
            int answer = calculator.calculate(inputStr);
            output.output(String.valueOf(answer));
            db.add(String.format("%s = %s", inputStr, answer));
        }
    }

    private void select() {
        for(String s: db){
            output.output(s);
        }
        output.output("");
    }

    private int parseOption(String option){
        return Integer.parseInt(option);
    }

}