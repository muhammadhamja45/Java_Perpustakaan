# Panduan Instalasi - Aplikasi Perpustakaan SMK AL-ASIYAH

## Daftar Isi

1. [Prasyarat Sistem](#prasyarat-sistem)
2. [Instalasi Database](#instalasi-database)
3. [Instalasi Aplikasi](#instalasi-aplikasi)
4. [Konfigurasi](#konfigurasi)
5. [Menjalankan Aplikasi](#menjalankan-aplikasi)
6. [Build Executable JAR](#build-executable-jar)
7. [Troubleshooting](#troubleshooting)

---

## Prasyarat Sistem

### 1. Java Development Kit (JDK) 17 atau lebih tinggi

**Windows:**
1. Download JDK 17 dari [Oracle](https://www.oracle.com/java/technologies/downloads/#java17) atau [OpenJDK](https://adoptium.net/)
2. Install JDK
3. Set environment variable `JAVA_HOME`:
   ```
   JAVA_HOME=C:\Program Files\Java\jdk-17
   ```
4. Tambahkan ke PATH:
   ```
   %JAVA_HOME%\bin
   ```
5. Verifikasi instalasi:
   ```bash
   java -version
   javac -version
   ```

**Linux/Mac:**
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install openjdk-17-jdk

# Mac (dengan Homebrew)
brew install openjdk@17

# Verifikasi
java -version
```

### 2. MySQL Server 8.0 atau lebih tinggi

**Windows:**
1. Download dari [MySQL Downloads](https://dev.mysql.com/downloads/mysql/)
2. Install MySQL Server
3. Catat root password yang dibuat saat instalasi
4. Pastikan MySQL service berjalan:
   - Buka Services (services.msc)
   - Cari "MySQL80" dan pastikan status "Running"

**Linux:**
```bash
# Ubuntu/Debian
sudo apt update
sudo apt install mysql-server
sudo systemctl start mysql
sudo systemctl enable mysql
```

**Mac:**
```bash
brew install mysql
brew services start mysql
```

**Verifikasi:**
```bash
mysql --version
mysql -u root -p
```

### 3. Maven 3.6 atau lebih tinggi

**Windows:**
1. Download dari [Maven Downloads](https://maven.apache.org/download.cgi)
2. Extract ke folder (misal: `C:\Program Files\Apache\maven`)
3. Set environment variable `MAVEN_HOME`:
   ```
   MAVEN_HOME=C:\Program Files\Apache\maven
   ```
4. Tambahkan ke PATH:
   ```
   %MAVEN_HOME%\bin
   ```
5. Verifikasi:
   ```bash
   mvn -version
   ```

**Linux/Mac:**
```bash
# Ubuntu/Debian
sudo apt install maven

# Mac
brew install maven

# Verifikasi
mvn -version
```

### 4. IDE (Opsional tapi Direkomendasikan)

- **IntelliJ IDEA** - [Download](https://www.jetbrains.com/idea/download/)
- **Eclipse** - [Download](https://www.eclipse.org/downloads/)
- **NetBeans** - [Download](https://netbeans.apache.org/download/)

---

## Instalasi Database

### Langkah 1: Buat Database

1. Buka MySQL Command Line atau MySQL Workbench
2. Login sebagai root:
   ```bash
   mysql -u root -p
   ```
3. Import schema:
   ```sql
   source database/schema.sql
   ```
   
   Atau copy-paste isi file `database/schema.sql` dan jalankan di MySQL.

### Langkah 2: Verifikasi Database

```sql
USE perpustakaan_al_asiyah;
SHOW TABLES;
```

Anda harus melihat tabel-tabel berikut:
- users
- siswa
- guru
- buku
- peminjaman
- pengembalian
- laporan

### Langkah 3: Cek User Default

```sql
SELECT username, role, nama_lengkap FROM users;
```

Harus ada 2 user:
- admin (role: admin)
- petugas (role: petugas)

---

## Instalasi Aplikasi

### Metode 1: Menggunakan Maven (Command Line)

1. **Clone atau extract project** ke folder lokal

2. **Buka terminal/command prompt** di folder project

3. **Download dependencies:**
   ```bash
   mvn clean install
   ```
   
   Proses ini akan:
   - Download semua dependencies (JavaFX, MySQL Connector, dll)
   - Compile source code
   - Run tests (jika ada)
   - Package aplikasi

4. **Verifikasi build:**
   ```bash
   ls target/perpustakaan-app-1.0.0.jar
   ```

### Metode 2: Menggunakan IDE

**IntelliJ IDEA:**
1. Buka IntelliJ IDEA
2. File → Open → Pilih folder project
3. IntelliJ akan otomatis detect Maven project
4. Tunggu Maven sync selesai
5. Klik kanan pada `pom.xml` → Maven → Reload Project

**Eclipse:**
1. File → Import → Maven → Existing Maven Projects
2. Pilih folder project
3. Klik Finish
4. Tunggu Maven dependencies download

---

## Konfigurasi

### 1. Konfigurasi Database Connection

Edit file: `src/main/java/com/smk/alasiyah/perpustakaan/config/DatabaseConfig.java`

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/perpustakaan_al_asiyah";
private static final String DB_USER = "root";  // Ganti dengan username MySQL Anda
private static final String DB_PASSWORD = "";  // Ganti dengan password MySQL Anda
```

**Contoh konfigurasi:**
- Jika MySQL di port 3307: `jdbc:mysql://localhost:3307/perpustakaan_al_asiyah`
- Jika menggunakan user lain: ubah `DB_USER` dan `DB_PASSWORD`

### 2. Verifikasi Koneksi

Jalankan aplikasi dan coba login. Jika ada error koneksi, periksa:
- MySQL service berjalan
- Username dan password benar
- Database sudah dibuat
- Port MySQL benar (default: 3306)

---

## Menjalankan Aplikasi

### Metode 1: Maven (Command Line)

```bash
mvn javafx:run
```

### Metode 2: JAR File

```bash
java -jar target/perpustakaan-app-1.0.0.jar
```

### Metode 3: IDE

**IntelliJ IDEA:**
1. Klik kanan pada `Main.java`
2. Run → Run 'Main.main()'

**Eclipse:**
1. Klik kanan pada `Main.java`
2. Run As → Java Application

### Login Pertama Kali

Gunakan kredensial default:
- **Admin:** username: `admin`, password: `admin123`
- **Petugas:** username: `petugas`, password: `petugas123`

> ⚠️ **PENTING:** Ganti password setelah login pertama!

---

## Build Executable JAR

### Build dengan Maven Shade Plugin

```bash
mvn clean package
```

File JAR akan dibuat di: `target/perpustakaan-app-1.0.0.jar`

### Membuat JAR dengan Dependencies

File JAR yang dihasilkan sudah include semua dependencies (fat JAR).

### Menjalankan JAR

```bash
java -jar target/perpustakaan-app-1.0.0.jar
```

### Membuat Executable untuk Windows (.exe)

Gunakan tool seperti:
- **Launch4j** - [Download](http://launch4j.sourceforge.net/)
- **jpackage** (Java 14+):
  ```bash
  jpackage --input target --name PerpustakaanApp --main-jar perpustakaan-app-1.0.0.jar --main-class com.smk.alasiyah.perpustakaan.Main
  ```

---

## Troubleshooting

### Error: "Gagal koneksi ke database"

**Penyebab:**
- MySQL service tidak berjalan
- Username/password salah
- Database belum dibuat
- Port MySQL salah

**Solusi:**
1. Pastikan MySQL service berjalan:
   ```bash
   # Windows
   net start MySQL80
   
   # Linux
   sudo systemctl start mysql
   ```
2. Periksa konfigurasi di `DatabaseConfig.java`
3. Test koneksi manual:
   ```bash
   mysql -u root -p
   ```
4. Pastikan database sudah dibuat:
   ```sql
   SHOW DATABASES;
   ```

### Error: "JavaFX runtime components are missing"

**Penyebab:**
- JavaFX tidak terinstall atau tidak terdeteksi

**Solusi:**
1. Pastikan menggunakan Java 17+ dengan JavaFX
2. Atau download JavaFX SDK dan tambahkan ke classpath
3. Untuk Java 11+, JavaFX perlu di-download terpisah

### Error: "ClassNotFoundException"

**Penyebab:**
- Dependencies belum terdownload
- Maven build belum dijalankan

**Solusi:**
```bash
mvn clean install
```

### Error: "Port already in use"

**Penyebab:**
- Port MySQL (3306) sudah digunakan aplikasi lain

**Solusi:**
1. Ubah port MySQL di `my.ini` (Windows) atau `my.cnf` (Linux)
2. Atau ubah port di `DatabaseConfig.java`

### Aplikasi tidak muncul/jendela kosong

**Penyebab:**
- FXML file tidak ditemukan
- Resource path salah

**Solusi:**
1. Pastikan folder `src/main/resources` ada
2. Pastikan struktur folder sesuai
3. Rebuild project:
   ```bash
   mvn clean install
   ```

### Password default tidak bisa login

**Penyebab:**
- Hash password di database tidak sesuai

**Solusi:**
1. Hapus user lama:
   ```sql
   DELETE FROM users WHERE username = 'admin';
   ```
2. Insert ulang dengan hash yang benar (lihat `schema.sql`)
3. Atau reset password menggunakan BCryptUtil

---

## Verifikasi Instalasi

Setelah instalasi selesai, verifikasi dengan:

1. ✅ Database terhubung
2. ✅ Login berhasil
3. ✅ Dashboard menampilkan data
4. ✅ Semua menu dapat diakses
5. ✅ CRUD buku berfungsi
6. ✅ Peminjaman dan pengembalian berfungsi

---

## Support

Jika mengalami masalah instalasi:
1. Periksa log error di console
2. Pastikan semua prasyarat terpenuhi
3. Periksa konfigurasi database
4. Hubungi administrator sistem

---

**Selamat menggunakan aplikasi!** 🎉

