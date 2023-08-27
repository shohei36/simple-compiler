package com.github.shohei36.syntax_tree;

import java.util.List;
import java.util.ListIterator;

public class Parser {
  public AbstractExpression execute(List<Token> list) throws Exception {
    ListIterator<Token> tokens = list.listIterator();
    AbstractExpression programTree = createProgram(tokens);
    return programTree;
  }

  private AbstractExpression createProgram(ListIterator<Token> tokens) throws Exception {
    Token t = tokens.next();
    if (t.getType() != TokenUtil.KEYWORD) {
      throw new Exception();
    }
    if (!"main".equals(t.getS())) {
      throw new Exception();
    }
    if (!check(tokens, TokenUtil.L_BRACE))
      throw new Exception();
    tokens.next(); // L_BRACE
    AbstractExpression e = createExpression(tokens);
    if (!check(tokens, TokenUtil.R_BRACE))
      throw new Exception();
    tokens.next(); // R_BRACE
    Program p = new Program();
    p.setValue(t);
    p.add(e);
    return p;
  }

  private AbstractExpression createExpression(ListIterator<Token> tokens) throws Exception {
    AbstractExpression t = createTerm(tokens);
    while (tokens.hasNext()) {
      if (!check(tokens, TokenUtil.OPE_AS)) {
        break;
      }
      Token op = tokens.next();
      AbstractExpression t2 = createTerm(tokens);
      AbstractExpression e = new Expression();
      e.setValue(op);
      e.add(t);
      e.add(t2);
      t = e;
    }
    return t;
  }

  private AbstractExpression createTerm(ListIterator<Token> tokens) throws Exception {
    AbstractExpression f = createFactor(tokens);
    while (tokens.hasNext()) {
      if (!check(tokens, TokenUtil.OPE_MD)) {
        break;
      }
      Token op = tokens.next();
      AbstractExpression f2 = createFactor(tokens);
      AbstractExpression e = new Term();
      e.setValue(op);
      e.add(f);
      e.add(f2);
      f = e;
    }
    return f;
  }

  private AbstractExpression createFactor(ListIterator<Token> tokens) throws Exception {
    // if (isNumber(tokens)) {
    if (check(tokens, TokenUtil.NUMBER)) {
      AbstractExpression n = createNumber(tokens);
      return n;
    } else {
      if (!check(tokens, TokenUtil.L_PAREN))
        throw new Exception();
      tokens.next(); // L_PAREN
      AbstractExpression e = createExpression(tokens);
      if (!check(tokens, TokenUtil.R_PAREN))
        throw new Exception();
      tokens.next(); // R_PAREN
      return e;
    }
  }

  private AbstractExpression createNumber(ListIterator<Token> tokens) throws Exception {
    Token t = tokens.next();
    AbstractExpression e = new NumberExpression();
    e.setValue(t);
    return e;
  }

  private boolean check(ListIterator<Token> tokens, int type) {
    Token t = tokens.next();
    tokens.previous();
    return (t.getType() == type);
  }
}
