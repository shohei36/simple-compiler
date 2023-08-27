package com.github.shohei36.simple_calculator;

import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;

public class SampleSvm1CodeGenerator {
  final static byte[][] codes = {{16, 1, 16, 2, 96, -48}, // 中間記法: 1 + 2 後置記法: 1 2 +
      {16, 1, 16, 2, 16, 3, 104, 96, -48}, // 中間記法: 1 + 2 * 3 後置記法: 1 2 3 * +
      {16, 1, 16, 2, 96, 16, 3, 104, -48}, // 中間記法: (1 + 2) * 3 後置記法: 1 2 + 3 *
  };

  public void generate() {
    for (int i = 0; i < codes.length; i++) {
      byte[] code = codes[i];
      try {
        write("code" + i + ".svm", code);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void write(String fileName, byte[] code) throws IOException {
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

  public static void main(String[] args) {
    SampleSvm1CodeGenerator g = new SampleSvm1CodeGenerator();
    g.generate();
  }
}
