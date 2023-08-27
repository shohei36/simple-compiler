package com.github.shohei36.syntax_tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractExpression {
  private Token value;
  private ArrayList<AbstractExpression> children = new ArrayList<AbstractExpression>();

  public void setValue(Token v) {
    value = v;
  }

  public Token getValue() {
    return value;
  }

  public ArrayList<AbstractExpression> getChildren() {
    return children;
  }

  public void add(AbstractExpression node) {}

  public Iterator<AbstractExpression> iterator() {
    return children.iterator();
  }

  public abstract void compile(List<Byte> objectCodeList) throws Exception;

  private String getTab(int n) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      sb.append("\t");
    }
    return sb.toString();
  }

  public void print(int n) {
    StringBuilder sb = new StringBuilder();
    sb.append(getTab(n));
    sb.append("----\n");
    sb.append(getTab(n));
    sb.append("index  :");
    if (value != null) {
      sb.append(value.getIndexNumber());
    }
    sb.append("\n");
    sb.append(getTab(n));
    sb.append("line   :");
    if (value != null) {
      sb.append(value.getLineNumber());
    }
    sb.append("\n");
    if (value != null) {
      int type = value.getType();
      sb.append(getTab(n));
      sb.append("type   :" + type + "\n");
      sb.append(getTab(n));
      sb.append("value  :");
      switch (type) {
        case TokenUtil.NUMBER:
          sb.append(value.getN() + "\n");
          break;
        default:
          sb.append(value.getS() + "\n");
      }
    } else {
      sb.append(getTab(n));
      sb.append("no-type:\n");
    }
    System.out.println(sb);
    for (AbstractExpression ae : children) {
      ae.print(n + 1);
    }
  }
}
