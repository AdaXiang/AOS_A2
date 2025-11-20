@echo off
setlocal enabledelayedexpansion

REM ================================
REM CONFIGURACIÓN
REM ================================
set BASE_DIR=C:\Users\adaxi\Desktop\AOS_SS\A02\codigo
set DEPLOY_DIR=C:\deploy
set SERVICES=config_server eureka_server mc_general mc_ingredientes mc_menu mc_reservas 
set JAVA_OPTS=-Xms256m -Xmx512m

echo.
echo ===============================================
echo INICIANDO SERVIDORES
echo ===============================================
echo.

REM arrancar cada microservicio en consola nueva
cd "%DEPLOY_DIR%"

for %%S in (%SERVICES%) do (
    echo Iniciando microservicio: %%S
    start "%%S" cmd /k "cd %BASE_DIR%\%%S && mvn spring-boot:run"
)

echo.
echo Todos los microservicios han sido desplegados e iniciados.
exit /b 0