package com.github.shohei36.lexical_analysis;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class SimpleScanner {
  private String fileName;
  private int current;
  private BufferedReader reader;

  public SimpleScanner(String fileName) throws IOException {
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
        StringTokenizer tokenizer = new StringTokenizer(s);
        while (tokenizer.hasMoreTokens()) {
          String st = tokenizer.nextToken();
          Token token = createToken(st);
          token.setLineNumber(current);
          tokenList.add(token);
        }
      }
    }
    return tokenList;
  }

  private Token createToken(String s) {
    Token token = null;
    if (TokenUtil.isKeyword(s)) {
      token = new Token(TokenUtil.KEYWORD, s);
    } else if ("{".equals(s)) {
      token = new Token(TokenUtil.L_BRACE, s);
    } else if ("}".equals(s)) {
      token = new Token(TokenUtil.R_BRACE, s);
    } else if ("(".equals(s)) {
      token = new Token(TokenUtil.L_PAREN, s);
    } else if (")".equals(s)) {
      token = new Token(TokenUtil.R_PAREN, s);
    } else if ("+".equals(s) || "-".equals(s)) {
      token = new Token(TokenUtil.OPE_AS, s);
    } else if ("*".equals(s) || "/".equals(s)) {
      token = new Token(TokenUtil.OPE_MD, s);
    } else {
      char ch = s.charAt(0);
      if (Character.isDigit(ch)) {
        try {
          double n = Double.parseDouble(s);
          token = new Token(TokenUtil.NUMBER, n);
        } catch (NumberFormatException e) {
          token = new Token(TokenUtil.ERROR, s);
        }
      } else if (Character.isLetter(ch)) {
        token = new Token(TokenUtil.IDENTIFIER, s);
      } else {
        token = new Token(TokenUtil.ERROR, s);
      }
    }
    return token;
  }
}
