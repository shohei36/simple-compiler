package com.github.shohei36.syntax_tree;

import java.util.List;

public class NumberExpression extends AbstractExpression {
  public void compile(List<Byte> objectCodeList) throws Exception {
    double value = getValue().getN();
    byte b = (byte) value;
    objectCodeList.add(Compiler.BIPUSH);
    objectCodeList.add(Byte.valueOf(b));
  }
}
