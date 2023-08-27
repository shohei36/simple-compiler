package com.github.shohei36.syntax_tree;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Compiler {
  public static final Byte IADD = Byte.valueOf(String.valueOf(96));
  public static final Byte ISUB = Byte.valueOf(String.valueOf(100));
  public static final Byte IMUL = Byte.valueOf(String.valueOf(104));
  public static final Byte IDIV = Byte.valueOf(String.valueOf(108));
  public static final Byte BIPUSH = Byte.valueOf(String.valueOf(16));
  public static final Byte PRINT = Byte.valueOf(String.valueOf(-48));

  public void compile(String fileName) throws Exception {
    Scanner scanner = new Scanner(fileName);
    List<Token> tokenList = scanner.createTokenList();
    Parser parser = new Parser();
    AbstractExpression ae = parser.execute(tokenList);
    List<Byte> objectCodeList = new ArrayList<Byte>();
    ae.print(0);
    ae.compile(objectCodeList);
    objectCodeList.add(PRINT);
    byte[] code = new byte[objectCodeList.size()];
    System.out.println("--");
    for (int i = 0; i < code.length; i++) {
      code[i] = objectCodeList.get(i).byteValue();
      System.out.print(code[i] + "|");
    }
    System.out.println("\n--");
    try {
      write("a.svm", code);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void write(String fileName, byte[] code) throws IOException {
    FileOutputStream fo = null;
    try {
      fo = new FileOutputStream(new File(fileName));
      fo.write(code);
    } finally {
      if (fo != null) {
        fo.close();
      }
    }
  }
}
