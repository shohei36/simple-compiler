package com.github.shohei36.syntax_tree;

import java.util.Arrays;
import java.util.List;

public class TokenUtil {
  public static final int ERROR = 0;
  public static final int NUMBER = 1;
  public static final int OPE_AS = 2; // + -
  public static final int OPE_MD = 3; // * /
  public static final int L_PAREN = 4; // (
  public static final int R_PAREN = 5; // )
  public static final int L_BRACE = 6; // {
  public static final int R_BRACE = 7; // }
  public static final int KEYWORD = 8; // 予約語
  public static final int IDENTIFIER = 9; // 識別子
  private static final String[] symbolsArray = {"+", "-", "*", "/", "(", ")", "{", "}",};
  private static final String[] keywordsArray = {"main",};
  public static final List<String> symbols = Arrays.asList(symbolsArray);
  public static final List<String> keywords = Arrays.asList(keywordsArray);

  public static boolean isKeyword(String s) {
    return keywords.contains(s);
  }

  public static boolean isSymbol(String s) {
    return symbols.contains(s);
  }

  public static String toPrintFormat(Token t) {
    StringBuilder sb = new StringBuilder();
    sb.append("\tline :" + t.getLineNumber() + "\n");
    sb.append("\tindex:" + t.getIndexNumber() + "\n");
    int type = t.getType();
    sb.append("\ttype :" + type + "\n");
    sb.append("\tvalue:");
    switch (type) {
      case TokenUtil.NUMBER:
        sb.append(t.getN());
        break;
      default:
        sb.append(t.getS());
    }
    return sb.toString();
  }
}
