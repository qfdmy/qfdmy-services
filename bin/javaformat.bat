@echo off
rem /**
rem  * Copyright &copy; 2020-now <a href="https://www.qfdmy.com">千锋达摩院</a> All rights reserved.
rem  *
rem  * Author: lee.lusifer@gmail.com
rem  */
echo.
echo [信息] Spring 代码美化。
echo.
pause
echo.

cd %~dp0
cd..

for /D %%s in (qfdmy*) do (
    call mvn spring-javaformat:apply -f %%s
)

pause