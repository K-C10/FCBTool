@echo off
color 06
Title Compilling...
javac *.java

jar -0 --create --file FCBTool.jar --main-class=Terminal *.class *.png

del *.class