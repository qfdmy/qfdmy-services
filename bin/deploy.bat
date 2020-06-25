@echo off
rem /**
rem  * Copyright &copy; 2020-now <a href="https://www.qfdmy.com">千锋达摩院</a> All rights reserved.
rem  *
rem  * Author: lee.lusifer@gmail.com
rem  */
echo.
echo [信息] 发布工程到仓库。
echo [注意] 如果发生异常，运行多几次即可。
echo.
pause
echo.

cd %~dp0
cd..

for /D %%s in (qfdmy*) do (
    call mvn deploy -f %%s
)

pause