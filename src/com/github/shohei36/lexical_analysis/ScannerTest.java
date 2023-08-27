package com.github.shohei36.lexical_analysis;


import java.io.IOException;
import java.util.List;

public class ScannerTest {
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("java ScannerTest <filename>");
			return;
		}
		try {
			Scanner scanner = new Scanner(args[0]);
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
