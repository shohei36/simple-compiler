package com.github.shohei36.syntax_tree;

import java.util.ArrayList;
import java.util.List;

public class Term extends AbstractExpression {
  public void add(AbstractExpression node) {
    getChildren().add(node);
  }

  public void compile(List<Byte> objectCodeList) throws Exception {
    ArrayList children = getChildren();
    AbstractExpression leftOperand = (AbstractExpression) children.get(0);
    leftOperand.compile(objectCodeList);
    if (children.size() > 1) {
      AbstractExpression rightOperand = (AbstractExpression) children.get(1);
      rightOperand.compile(objectCodeList);
      if ("*".equals(getValue().getS())) {
        objectCodeList.add(Compiler.IMUL);
      } else if ("/".equals(getValue().getS())) {
        objectCodeList.add(Compiler.IDIV);
      }
    }
  }
}
