build:
	javac src/com/github/shohei36/${CLASS_NAME}.java -sourcepath src/ -d classes/
exec:
	java -classpath classes/ com.github.shohei36.${CLASS_NAME} ${ARGS}
