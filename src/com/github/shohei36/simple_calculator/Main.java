package com.github.shohei36.simple_calculator;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Svm1 <svm file>");
        } else {
            String fileName = args[0];
            Svm1 svm1 = new Svm1();
            try {
                svm1.load(fileName);
                svm1.execute();
            } catch (IOException e) {
                System.out.println("error");
            }
        }
    }
}
