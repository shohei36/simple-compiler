package com.github.shohei36.syntax_tree;

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
        System.out.print(operandStack.peek());
        break;
    }
  }

  public void load(String fileName) throws IOException {
    FileInputStream is = null;
    try {
      is = new FileInputStream(new File(fileName));
      int len = 0;
      while ((len = is.read(code, len, 8)) != -1) {
        codeLength += len;
      }
    } finally {
      if (is != null) {
        is.close();
      }
    }
  }

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
