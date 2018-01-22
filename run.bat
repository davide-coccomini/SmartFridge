@echo off
mkdir build\classes
mkdir cache
cd src
@echo Sto compilando...
javac -classpath ..\dist\lib\xstream-1.4.7.jar;..\dist\lib\xmlpull-1.1.3.1.jar; frontend\AvvioSmartFridge.java -d ..\build\classes\
cd ..\build\classes
@echo Sto eseguendo...
color 4F
java -classpath ..\..\dist\lib\xstream-1.4.7.jar;..\..\dist\lib\xmlpull-1.1.3.1.jar;..\..\dist\lib\xpp3_min-1.1.4c.jar;..\..\dist\lib\mysql-connector-java-5.1.34-bin.jar; frontend.AvvioSmartFridge
pause
color
cd ..\..