@echo off
rem /**
rem  * Copyright &copy; 2020-now <a href="https://www.qfdmy.com">千锋达摩院</a> All rights reserved.
rem  *
rem  * Author: lee.lusifer@gmail.com
rem  */
echo.
echo [信息] 清理 Maven 本地仓库中下载失败的包。
echo.
pause
echo.

set REPOSITORY_PATH=D:\apache-maven-3.6.1\repo\com\qfdmy
rd /s /q %REPOSITORY_PATH%

set REPOSITORY_PATH=D:\apache-maven-3.6.1\repo
rem 正在搜索...
for /f "delims=" %%i in ('dir /b /s "%REPOSITORY_PATH%\*lastUpdated*"') do (
    del /s /q %%i
)
rem 搜索完毕

pause