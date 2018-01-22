@echo off
mkdir build\classes
cd src
@echo Sto compilando...
javac -classpath ..\dist\lib\xstream-1.4.7.jar;..\dist\lib\xmlpull-1.1.3.1.jar; backend\ServerLogEventoXML.java -d ..\build\classes\
cd ..\build\classes
@echo Sto eseguendo...
color 1F
java -classpath ..\..\dist\lib\xstream-1.4.7.jar;..\..\dist\lib\xmlpull-1.1.3.1.jar;..\..\dist\lib\xpp3_min-1.1.4c.jar; backend.ServerLogEventoXML 8080
pause