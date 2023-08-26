package com.github.shohei36;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Stack;

public class Svm1 {
    private byte[] code = new byte[256];
    private int codeLength = 0;
    private Stack<Byte> operandStack = new Stack<Byte>();
    private Alu alu = new Alu();
    private int pc;

    /**
     * プログラムファイルからプログラムを読み込む
     * 
     * @param fileName
     * @throws IOException
     */
    public void load(String fileName) throws IOException {
        FileInputStream is = null;
        try {
            is = new FileInputStream(new File(fileName));
            int len = 0;
            while ((len = is.read(code, codeLength, 8)) != -1) {
                codeLength += len;
            }
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public void execute() {
        for (pc = 0; pc < codeLength; pc++) {
            executeCommand(code[pc]);
        }
    }

    public void executeCommand(byte command) {
        byte a;
        byte b;
        switch (command) {
            case 16:
                operandStack.push(code[++pc]);
                break;
            case 96:
                b = operandStack.pop();
                a = operandStack.pop();
                operandStack.push(alu.add(a, b));
                break;
            case 104:
                b = operandStack.pop();
                a = operandStack.pop();
                operandStack.push(alu.multiply(a, b));
                break;
            case -48:
                System.out.println(operandStack.peek());
                break;
        }
    }
}
