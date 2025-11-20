@echo off
setlocal enabledelayedexpansion

REM ================================
REM CONFIGURACIÓN
REM ================================
set BASE_DIR=C:\Users\adaxi\Desktop\AOS_SS\A02\codigo
set DEPLOY_DIR=C:\deploy
set SERVICES=config_server eureka_server mc_general mc_ingredientes mc_menu mc_reservas 
set JAVA_OPTS=-Xms256m -Xmx512m

echo ===============================================
echo COMPILACIÓN DE MICROSERVICIOS
echo ===============================================
echo.

REM Crear carpeta de despliegue si no existe
if not exist "%DEPLOY_DIR%" (
    mkdir "%DEPLOY_DIR%"
)

for %%S in (%SERVICES%) do (
    echo -----------------------------------------------
    echo Compilando microservicio: %%S
    echo -----------------------------------------------

    cd "%BASE_DIR%\%%S"

    REM Compilar con Maven
    mvn clean package -DskipTests

    if %ERRORLEVEL% NEQ 0 (
        echo [ERROR] Falló la compilación de %%S
        pause
        exit /b 1
    )

    REM Copiar el JAR resultante
    for %%F in (target\*.jar) do (
        copy /Y "%%F" "%DEPLOY_DIR%\%%~nxF"
        echo Copiado %%~nxF a %DEPLOY_DIR%
    )
)
exit /b 0
