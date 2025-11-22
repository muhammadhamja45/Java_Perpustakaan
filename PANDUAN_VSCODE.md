# Panduan Menjalankan Aplikasi di VSCode

## 📋 Prasyarat

1. **Java Extension Pack** untuk VSCode
   - Install extension: "Extension Pack for Java" oleh Microsoft
   - Atau install secara manual:
     - Java Extension Pack
     - Language Support for Java
     - Debugger for Java
     - Test Runner for Java
     - Maven for Java

2. **Java JDK 17+** sudah terinstall
3. **Maven** sudah terinstall
4. **MySQL** sudah terinstall dan berjalan

## 🚀 Langkah-langkah Setup

### 1. Install Extensions VSCode

1. Buka VSCode
2. Tekan `Ctrl+Shift+X` (Windows/Linux) atau `Cmd+Shift+X` (Mac)
3. Cari "Extension Pack for Java"
4. Klik Install

### 2. Setup File .env

1. Di root folder project, buat file `.env` (copy dari `.env.example` jika ada)
2. Edit file `.env` dengan konfigurasi database Anda:

```env
# Konfigurasi Database MySQL
DB_HOST=localhost
DB_PORT=3306
DB_NAME=perpustakaan_al_asiyah
DB_USER=root
DB_PASSWORD=password_anda_disini
```

**Contoh:**
```env
DB_HOST=localhost
DB_PORT=3306
DB_NAME=perpustakaan_al_asiyah
DB_USER=root
DB_PASSWORD=12345
```

### 3. Setup Database MySQL

1. Buka MySQL Command Line atau MySQL Workbench
2. Import schema:
   ```sql
   source database/schema.sql
   ```
   
   Atau copy-paste isi file `database/schema.sql` dan jalankan

### 4. Reload VSCode Window

1. Tekan `Ctrl+Shift+P` (Windows/Linux) atau `Cmd+Shift+P` (Mac)
2. Ketik: "Java: Clean Java Language Server Workspace"
3. Pilih dan tunggu reload selesai

## ▶️ Menjalankan Aplikasi

### Metode 1: Menggunakan Terminal VSCode

1. Buka terminal di VSCode: `Ctrl+`` (backtick) atau View → Terminal
2. Pastikan Anda di root folder project
3. Jalankan:
   ```bash
   mvn clean install
   ```
4. Setelah build selesai, jalankan:
   ```bash
   mvn javafx:run
   ```

### Metode 2: Menggunakan Run Configuration

1. Buka file `src/main/java/com/smk/alasiyah/perpustakaan/Main.java`
2. Klik tombol "Run" di atas method `main()` (ikon play hijau)
3. Atau klik kanan → "Run Java"

### Metode 3: Menggunakan Command Palette

1. Tekan `Ctrl+Shift+P` (Windows/Linux) atau `Cmd+Shift+P` (Mac)
2. Ketik: "Java: Run"
3. Pilih file `Main.java`

## 🔧 Troubleshooting VSCode

### Error: "Java runtime could not be located"

**Solusi:**
1. Tekan `Ctrl+Shift+P` → "Java: Configure Java Runtime"
2. Pilih JDK 17 atau lebih tinggi
3. Atau set di settings.json:
   ```json
   {
       "java.home": "C:/Program Files/Java/jdk-17"
   }
   ```

### Error: "Maven project not found"

**Solusi:**
1. Pastikan file `pom.xml` ada di root folder
2. Tekan `Ctrl+Shift+P` → "Java: Clean Java Language Server Workspace"
3. Reload window

### Error: "Cannot resolve symbol"

**Solusi:**
1. Tekan `Ctrl+Shift+P` → "Java: Rebuild Projects"
2. Tunggu Maven download dependencies
3. Periksa apakah ada error di "Problems" tab

### File .env tidak terbaca

**Solusi:**
1. Pastikan file `.env` ada di **root folder project** (sama level dengan `pom.xml`)
2. Pastikan format file benar (tidak ada spasi sebelum `=`)
3. Restart aplikasi setelah mengubah `.env`

## 📝 Tips VSCode

### Shortcuts Berguna:

- `Ctrl+Shift+P` - Command Palette
- `Ctrl+`` - Toggle Terminal
- `F5` - Debug
- `Ctrl+F5` - Run tanpa debug
- `Ctrl+Shift+F` - Search di semua file

### Extension Tambahan yang Berguna:

- **Maven for Java** - Untuk manage Maven dependencies
- **GitLens** - Untuk version control
- **Error Lens** - Menampilkan error inline
- **Java Test Runner** - Untuk menjalankan unit tests

## 🐛 Debugging

1. Set breakpoint di kode (klik di margin kiri)
2. Tekan `F5` atau klik "Run and Debug"
3. Pilih "Java" configuration
4. Aplikasi akan berhenti di breakpoint

## ✅ Verifikasi Setup

Setelah setup, pastikan:

1. ✅ File `.env` ada dan berisi konfigurasi database
2. ✅ Database MySQL sudah dibuat dan di-import
3. ✅ Maven dependencies terdownload (lihat folder `.m2` atau `target`)
4. ✅ Tidak ada error di "Problems" tab
5. ✅ Aplikasi bisa dijalankan dan login berhasil

## 🎯 Quick Start (Ringkas)

```bash
# 1. Buat file .env di root folder
# 2. Isi dengan konfigurasi database

# 3. Setup database
mysql -u root -p < database/schema.sql

# 4. Build dan jalankan
mvn clean install
mvn javafx:run
```

---

**Selamat coding di VSCode!** 🎉

