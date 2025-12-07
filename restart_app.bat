@echo off
echo ================================================
echo   RESTART APLIKASI PERPUSTAKAAN SMK AL-ASIYAH
echo ================================================
echo.
echo [1/3] Membersihkan build lama...
call mvn clean

echo.
echo [2/3] Kompilasi ulang...
call mvn compile

echo.
echo [3/3] Menjalankan aplikasi...
echo.
echo CATATAN: Jika aplikasi masih berjalan, tutup dulu sebelum menjalankan script ini!
echo.
call mvn javafx:run

