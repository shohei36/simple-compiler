build:
	javac src/com/github/shohei36/${REL_PATH}/${CLASS_NAME}.java -sourcepath src/ -d classes/
exec:
	java -classpath classes/ com.github.shohei36.${REL_PACKAGE}.${CLASS_NAME} ${ARGS}
