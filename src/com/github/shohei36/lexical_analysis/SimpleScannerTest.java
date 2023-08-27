package com.github.shohei36.lexical_analysis;


import java.io.IOException;
import java.util.List;

public class SimpleScannerTest {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("java SimpleScannerTest <filename>");
			return;
		}
		try {
			SimpleScanner scanner = new SimpleScanner(args[0]);
			List<Token> tokenList = scanner.createTokenList();
			for (Token t : tokenList) {
				System.out.println("--------");
				System.out.println(TokenUtil.toPrintFormat(t));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
