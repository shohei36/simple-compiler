package com.github.shohei36.syntax_tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

public class Scanner {
  private String fileName;
  private int current;
  private BufferedReader reader;

  public Scanner(String fileName) throws IOException {
    this.fileName = fileName;
    reader = new BufferedReader(new FileReader(this.fileName));
    current = 0;
  }

  public List<Token> createTokenList() throws IOException {
    List<Token> tokens = null;
    try {
      tokens = readLines();
    } finally {
      reader.close();
    }
    return tokens;
  }

  private List<Token> readLines() throws IOException {
    List<Token> tokenList = new LinkedList<Token>();
    String s = null;
    while ((s = reader.readLine()) != null) {
      current++;
      if (s.length() == 0) {
        continue;
      } else {
        Iterator<Token> tokenizer = new S1sTokenizer(s);
        while (tokenizer.hasNext()) {
          Token token = tokenizer.next();
          token.setLineNumber(current);
          tokenList.add(token);
        }
      }
    }
    return tokenList;
  }
}
