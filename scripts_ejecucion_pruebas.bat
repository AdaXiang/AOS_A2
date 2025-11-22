@echo off
setlocal enabledelayedexpansion

REM ==========================================
REM CONFIGURACIÓN
REM ==========================================
set HOST=http://localhost:8005/general
set HEADER=-H "Content-Type: application/json"

echo ==========================================
echo  PRUEBAS DE API (MC_GENERAL)
echo ==========================================
echo.
echo.

REM =========================================================
REM [1] GESTIÓN DE INVENTARIO (PROVEEDORES Y SUMINISTROS)
REM =========================================================
echo [1] --- INVENTARIO ---

echo 1.1. Crear un Ingrediente Nuevo: "Harina"
curl -X POST "%HOST%/ingredientes" %HEADER% -d "{\"nombre\": \"Harina\", \"cantidad\": 50, \"cantidadMin\": 10}"
echo.
echo.

echo 1.2. Crear un Proveedor: "Panificadora Central"
curl -X POST "%HOST%/proveedores" %HEADER% -d "{\"nombre\": \"Panificadora Central\", \"correo\": \"pedidos@pan.com\", \"telefono\": \"555-1234\"}"
echo.
echo.

echo 1.3. Crear un Suministro (Vincular Proveedor con Harina)
echo      (Intentamos vincular Proveedor ultimo creado con Ingrediente ultimo creado. Ajusta IDs si es necesario)
curl -X POST "%HOST%/suministros" %HEADER% -d "{\"proveedor\": {\"id\": 10}, \"ingrediente\": {\"id\": 9}, \"precio\": 1.5, \"cantidad\": 500}"
echo.
echo.

echo 1.4. Simular Consumo Masivo (Restar 45 de Harina)
echo      Esto deberia dejar la Harina en 5 unidades.
curl -X POST "%HOST%/ingredientes/9/consumir/45"
echo.
echo.

echo. 1.5. Verificar Estado del Ingrediente (Harina)
curl -X GET "%HOST%/ingredientes/9"
echo.
echo.

REM =========================================================
REM [2] GESTIÓN COMPLETA DE MENÚ (CREACIÓN DE CARTA)
REM =========================================================
echo.
echo [2] --- GESTION DE MENU COMPLETA ---

echo 2.1. Crear un Tipo de Plato: "Postre"
curl -X POST "%HOST%/tipoplatos" %HEADER% -d "{\"nombre\": \"Postre\"}"
echo.
echo.

echo 2.2. Crear un Plato Nuevo: "Tarta de la Casa"
REM Asumimos que el TipoPlato "Postre" recibio el ID 6
curl -X POST "%HOST%/platos" %HEADER% -d "{\"name\": \"Tarta de la Casa\", \"precio\": 6.50, \"tipoPlato\": {\"id\": 6}}"
echo.
echo.

echo 2.3. Asignar Ingredientes al Plato (Tarta lleva Harina)
REM Plato ID: 7. Ingrediente ID: 9 (Harina). Cantidad: 10.
curl -X POST "%HOST%/ingredienteplatos" %HEADER% -d "{\"id\": {\"idIngrediente\": 9, \"idPlato\": 7}, \"cantidad\": 2}"
echo.
echo.

echo 2.4. Crear un Menú Nuevo: "Menu Fin de Semana"
curl -X POST "%HOST%/menus" %HEADER% -d "{\"name\": \"Menu Fin de Semana\", \"precio\": 25.0}"
echo.
echo.

echo 2.5. Asignar Plato al Menú
REM Menu ID: 8. Plato ID: 7.
curl -X POST "%HOST%/menuplatos" %HEADER% -d "{\"id\": {\"idMenu\": 8, \"idPlato\": 7}}"
echo.
echo.

echo 2.6. Consultar el Menú Completo (Deberia salir la relacion con Tarta de la Casa (menu ID 8 y plato ID 7))
curl -X GET "%HOST%/menuplatos"
echo.
echo.

REM =========================================================
REM [3] LOGICA DE NEGOCIO: PEDIDOS Y ACTUALIZACION DE MENU
REM =========================================================
echo.
echo [3] --- LOGICA DE NEGOCIO ---

echo 3.1. Realizar Pedido de "Tarta de la Casa" (ID 7)
echo      Consume 2 de Harina. (Quedaban 5 -> Deberian quedar 3)
curl -X POST "%HOST%/platos/7/pedido"
echo.
echo.

echo 3.2. Provocar Rotura de Stock
echo      Consumimos manualmente toda la harina restante para dejarla en 0.
curl -X POST "%HOST%/ingredientes/9/consumir/3"
echo.
echo.

echo 3.3. Consultar Menús (VERIFICAR LOGICA AUTOMATICA)
echo      Al pedir el menú, el sistema deberia detectar que NO hay Harina (0 stock).
echo      Si la logica "verificarDisponibilidadYActualizar" funciona, la relacion
echo      entre "Tarta de la Casa" y el menú debería eliminarse.
curl -X GET "%HOST%/menuplatos"
echo.
echo.

REM =========================================================
REM [4] CICLO DE VIDA DE RESERVAS (CRUD)
REM =========================================================
echo.
echo [4] --- CICLO DE VIDA DE RESERVAS ---

echo 4.1. Listar Mesas...
curl -X GET "%HOST%/mesas"
echo.
echo.

echo 4.2. Listar Reservas existentes...
curl -X GET "%HOST%/reservas"
echo.
echo.

echo 4.3. Crear una Mesa Nueva (Para 10 personas, en terraza)
curl -X POST "%HOST%/mesas" %HEADER% -d "{\"nPersonas\": 10, \"enTerraza\": true}"
echo.
echo.

echo 4.4. Crear Reserva en esa mesa (ID 7)
curl -X POST "%HOST%/reservas" %HEADER% -d "{\"mesa\": {\"id\": 7}, \"nombreCliente\": \"Grupo Grande\", \"correo\": \"grupo@mail.com\", \"telefono\": \"111222333\", \"fechaReserva\": \"2025-12-31\", \"horaReserva\": \"21:00:00\", \"duracion\": 120, \"nComensales\": 8}"
echo.
echo.

echo 4.5. Modificar la Reserva (PUT) - Cambiar comensales a 10
REM Asumimos que la reserva tiene ID 9 (despues de las del init)
curl -X PUT "%HOST%/reservas/9" %HEADER% -d "{\"nComensales\": 10}"
echo.
echo.

echo 4.6. Verificar la modificación
curl -X GET "%HOST%/reservas/9"
echo.
echo.

echo 4.7. Eliminar la Reserva (DELETE)
curl -X DELETE "%HOST%/reservas/9"
echo.
echo.

echo 4.8. Verificar que ya no existe 
curl -X GET "%HOST%/reservas"
echo.
echo.

echo 4.9. Intentar Crear Reserva SOLAPADA (Caso ERROR 409 Conflict)
REM Intentamos reservar la misma mesa, el mismo dia, a las 14:30 (choca con la de 14:00-15:00)
curl -v -X POST "%HOST%/reservas" %HEADER% -d "{\"mesa\": {\"id\": 1}, \"nombreCliente\": \"Intruso\", \"correo\": \"intruso@test.com\", \"telefono\": \"000000000\", \"fechaReserva\": \"2025-12-25\", \"horaReserva\": \"14:30:00\", \"duracion\": 60, \"nComensales\": 2}"
echo.
echo.

echo ==========================================
echo  PRUEBAS FINALIZADAS
echo ==========================================
pause