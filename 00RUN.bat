javac *.java
jar --no-compress --create --file FCBTool.jar --main-class=Terminal *.class *.png
title FCBTool
cls
java -jar FCBTool.jar
del *.class

pause
