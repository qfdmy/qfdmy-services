@echo off
rem /**
rem  * Copyright &copy; 2020-now <a href="https://www.qfdmy.com">千锋达摩院</a> All rights reserved.
rem  *
rem  * Author: lee.lusifer@gmail.com
rem  */
echo.
echo [信息] 清理 JRebel 生成的 .bak 文件
echo.
pause
echo.

cd %~dp0
cd..

for /f "delims=" %%i in ('dir /b /s ".rebel.xml.bak"') do (
    del /s /q %%i
)

pause