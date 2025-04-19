@echo off
setlocal

REM ======= CONFIGURACIÓN =======
set SRC_DIR=src
set BIN_DIR=bin
set OUT_DIR=Image_out
set MAIN_CLASS=App

REM ======= MOSTRAR ENCABEZADO =======
echo =======================================
echo Compilador y ejecutor Java automático
echo =======================================

REM ======= VERIFICAR ARGUMENTO ===========
if "%~1"=="" (
    echo Uso: run.bat src\nombre_imagen.png
    echo.
    exit /b
)

REM ======= CREAR CARPETAS SI NO EXISTEN ==
if not exist %BIN_DIR% (
    echo Creando carpeta de compilación: %BIN_DIR%
    mkdir %BIN_DIR%
)

if not exist %OUT_DIR% (
    echo Creando carpeta de salida de imágenes: %OUT_DIR%
    mkdir %OUT_DIR%
)

REM ======= COMPILAR JAVA ==================
echo Compilando archivos fuente Java...
javac -d %BIN_DIR% %SRC_DIR%\*.java

if errorlevel 1 (
    echo ❌ Error al compilar. Verifica que no haya errores de sintaxis.
    exit /b
)

echo ✅ Compilación exitosa.

REM ======= EJECUTAR EL PROGRAMA ===========
echo Ejecutando el programa con la imagen: %1
java -cp %BIN_DIR% %MAIN_CLASS% %1

REM ======= FINAL ==========================
echo.
echo ✅ Proceso finalizado. Archivos generados en: %OUT_DIR%
endlocal
pause
