@echo off
rem /**
rem  * Copyright &copy; 2020-now <a href="https://www.qfdmy.com">千锋达摩院</a> All rights reserved.
rem  *
rem  * Author: lee.lusifer@gmail.com
rem  */
echo.
echo [信息] 打包工程，生成 JAR 包文件。
echo [注意] 如果发生异常，运行多几次即可。
echo.
pause
echo.

REM set REPOSITORY_PATH=D:\apache-maven-3.6.1\repo\com\qfdmy
REM rd /s /q %REPOSITORY_PATH%

cd %~dp0
cd..

for /D %%s in (qfdmy*) do (
    call mvn clean install -Dmaven.test.skip=true -f %%s
)

pause