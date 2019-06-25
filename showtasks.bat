call runcrud.bat
if "%ERRORLEVEL%" == "0" goto runchrome
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:runchrome
start chrome http://localhost:8080/crud/v1/task/getTasks/
if "%ERRORLEVEL%" == "0" goto end
echo Cannot run chrome

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.