@echo off
setlocal enabledelayedexpansion

REM ================================
REM CONFIGURACIÓN
REM ================================
set BASE_DIR=C:\Users\adaxi\Desktop\A2\AOS_A2
set DEPLOY_DIR=C:\deploy
set JAVA_OPTS=-Xms256m -Xmx512m

REM Microservicios
set SERVICES=mc_ingredientes mc_menu mc_reservas 

echo ===============================================
echo INICIANDO INFRAESTRUCTURA BASICA
echo ===============================================

echo Iniciando config_server y eureka_server...
start "config_server" cmd /k "cd %BASE_DIR%\config_server && mvn spring-boot:run"
start "eureka_server" cmd /k "cd %BASE_DIR%\eureka_server && mvn spring-boot:run"

echo Esperando 15 segundos a que arranquen...
timeout /t 15

echo ===============================================
echo INICIANDO MICROSERVICIOS DE NEGOCIO (2 instancias)
echo ===============================================

for %%S in (%SERVICES%) do (
    echo -----------------------------------------------
    echo Iniciando INSTANCIA 1 del microservicio: %%S
    start "%%S-1" cmd /k "cd %BASE_DIR%\%%S && mvn spring-boot:run"
    timeout /t 10

    @REM echo Iniciando INSTANCIA 2 del microservicio: %%S
    @REM start "%%S-2" cmd /k "cd %BASE_DIR%\%%S && mvn spring-boot:run"
    @REM timeout /t 10
)

echo Iniciando mc_general...
start "mc_general" cmd /k "cd %BASE_DIR%\mc_general && mvn spring-boot:run"


echo ===============================================
echo Todas las instancias levantadas.
exit /b 0
