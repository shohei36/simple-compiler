package com.github.shohei36.syntax_tree;

public class ScannerLine {
  private String line;
  private int current;
  private int maxIndex;

  public ScannerLine(String s) {
    line = s;
    current = 0;
    if (line != null)
      maxIndex = line.length();
  }

  public char next() {
    if (!hasNext()) {
      return (char) -1;
    }
    return line.charAt(current++);
  }

  public char peek() {
    if (!hasNext()) {
      return (char) -1;
    }
    return line.charAt(current);
  }

  public int getIndex() {
    return current;
  }

  public boolean hasNext() {
    return (line != null) && (maxIndex != current);
  }

  public void cutWhitespace() {
    char ch = peek();
    while (Character.isWhitespace(ch)) {
      next();
      ch = peek();
    }
  }
}
