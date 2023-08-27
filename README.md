# simple-compiler
@ITにあった[簡単なコンパイラを作る記事](https://atmarkit.itmedia.co.jp/ait/articles/0612/02/news016.html)をやってみる

# Build & Execution
Build
```bash
$ make build REL_PATH=simple_calculator CLASS_NAME=Main
```

Execution
```bash
$ make exec REL_PACKAGE=simple_calculator CLASS_NAME=Main ARGS=code0.svm
```

# Class Diagram
```mermaid
classDiagram
    class Svm1 {
        - code: byte[]
        - codeLength: int[]
        - operandStack: Stack
        - pc: int
        + load(fileName: String): void
        + execute(): void
        - executeCommand(command: byte): void
    }
    class Alu {
        + add(a: byte, b: byte): byte
        + multiply(a: byte, b: byte): byte
    }
    Svm1 --> Alu: - alu
```

# 簡単な四則演算の計算を実現できる程度のプログラミング言語のBNF
```
<program> ::= main '{' <expression> '}'    // "main {"ではじまり"}"で終わる
<expression> ::= <term>{ <opeas> <term> }  // 式
<term> ::= <factor>{ <opemd> <factor> }    // 
<factor> ::= <number>|( <expression> )     // 要素
<number> ::= <digit>{<digit>}
<opeas> ::= + | -
<opemd> ::= * | /
<digit> ::= 0|1|2|3|4|5|6|7|8|9
```

# コンパイラの基本構成
```mermaid
flowchart TD
    SC[ソースコード] --> A[1.字句解析]
    A --> B[2.構文解析]
    B --> C[3.意味解析]
    C --> D[4.コード最適化]
    D --> E[5.コード生成]
    E --> OC[オブジェクトコード]
```