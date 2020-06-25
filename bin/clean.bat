@echo off
rem /**
rem  * Copyright &copy; 2020-now <a href="https://www.qfdmy.com">千锋达摩院</a> All rights reserved.
rem  *
rem  * Author: lee.lusifer@gmail.com
rem  */
echo.
echo [信息] 清理生成路径。
echo.
pause
echo.

cd %~dp0
cd..

call mvn clean install -f qfdmy-dependencies
call mvn clean install -f qfdmy-parent

for /D %%s in (qfdmy*) do (
    call mvn clean -f %%s
)

pause