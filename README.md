# simple-compiler
@ITにあった[簡単なコンパイラを作る記事](https://atmarkit.itmedia.co.jp/ait/articles/0612/02/news016.html)をやってみる

# Build & Execution
Build
```bash
$ make build CLASS_NAME=Main
```

Execution
```bash
$ make exec CLASS_NAME=Main ARGS=code0.svm
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
