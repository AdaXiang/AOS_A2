@echo off
setlocal enabledelayedexpansion

REM ================================
REM CONFIGURACIÓN
REM ================================
set BASE_DIR=C:\Users\adaxi\Desktop\A2\AOS_A2
set DEPLOY_DIR=C:\deploy
set SERVICES=mc_general mc_ingredientes mc_menu mc_reservas 
set JAVA_OPTS=-Xms256m -Xmx512m

echo ===============================================
echo INICIANDO INFRAESTRUCTURA BASICA
echo ===============================================

echo Iniciando config_server...
start "config_server" cmd /k "cd %BASE_DIR%\config_server && mvn spring-boot:run"
echo Esperando 15 segundos a que arranque Config Server...
timeout /t 15

echo Iniciando eureka_server...
start "eureka_server" cmd /k "cd %BASE_DIR%\eureka_server && mvn spring-boot:run"
echo Esperando 15 segundos a que arranque Eureka...
timeout /t 15

echo ===============================================
echo INICIANDO MICROSERVICIOS DE NEGOCIO
echo ===============================================

set SERVICES=mc_ingredientes mc_menu mc_reservas mc_general

for %%S in (%SERVICES%) do (
    echo Iniciando microservicio: %%S
    start "%%S" cmd /k "cd %BASE_DIR%\%%S && mvn spring-boot:run"
    timeout /t 5
)

echo Todos los servicios iniciados.
exit /b 0