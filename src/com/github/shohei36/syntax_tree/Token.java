package com.github.shohei36.syntax_tree;

public class Token {
  private int type;
  private String s;
  private double n;
  private int lineNumber;
  private int indexNumber;

  public Token(int t, char c) {
    type = t;
    this.s = Character.toString(c);
  }

  public Token(int t, String s) {
    type = t;
    this.s = s;
  }

  public Token(int t, double n) {
    type = t;
    this.n = n;
  }

  public int getType() {
    return type;
  }

  public String getS() {
    return s;
  }

  public double getN() {
    return n;
  }

  public void setLineNumber(int n) {
    lineNumber = n;
  }

  public void setIndexNumber(int n) {
    indexNumber = n;
  }

  public int getLineNumber() {
    return lineNumber;
  }

  public int getIndexNumber() {
    return indexNumber;
  }
}
