cd ..
call mvn release:clean release:prepare -DignoreSnapshots=true release:perform
call mvn deploy