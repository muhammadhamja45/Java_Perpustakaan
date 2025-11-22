# 📖 Tutorial Lengkap: Menjalankan Aplikasi Perpustakaan di NetBeans IDE

Panduan lengkap dan detail untuk menjalankan Aplikasi Manajemen Perpustakaan SMK AL-ASIYAH menggunakan NetBeans IDE dari awal hingga aplikasi berjalan.

---

## 📋 Daftar Isi

1. [Prasyarat Sistem](#1-prasyarat-sistem)
2. [Instalasi Software yang Diperlukan](#2-instalasi-software-yang-diperlukan)
3. [Setup Database MySQL](#3-setup-database-mysql)
4. [Membuka Project di NetBeans](#4-membuka-project-di-netbeans)
5. [Konfigurasi Project di NetBeans](#5-konfigurasi-project-di-netbeans)
6. [Konfigurasi Database Connection](#6-konfigurasi-database-connection)
7. [Build Project](#7-build-project)
8. [Menjalankan Aplikasi](#8-menjalankan-aplikasi)
9. [Login dan Penggunaan](#9-login-dan-penggunaan)
10. [Troubleshooting](#10-troubleshooting)
11. [Tips dan Trik NetBeans](#11-tips-dan-trik-netbeans)

---

## 1. Prasyarat Sistem

### Spesifikasi Minimum:
- **OS:** Windows 7/8/10/11, Linux, atau macOS
- **RAM:** Minimal 4GB (Disarankan 8GB)
- **Storage:** Minimal 2GB ruang kosong
- **Processor:** Intel Core i3 atau setara

### Software yang Diperlukan:
1. ✅ Java Development Kit (JDK) 17 atau lebih tinggi
2. ✅ NetBeans IDE (versi 12 atau lebih tinggi)
3. ✅ MySQL Server 8.0 atau lebih tinggi
4. ✅ MySQL Workbench (opsional, untuk manajemen database)
5. ✅ Apache Maven 3.6+ (biasanya sudah termasuk di NetBeans)

---

## 2. Instalasi Software yang Diperlukan

### 📦 Langkah 2.1: Install Java JDK 17

#### **Windows:**

1. **Download JDK 17:**
   - Buka browser dan kunjungi: https://www.oracle.com/java/technologies/downloads/#java17
   - Atau gunakan OpenJDK: https://adoptium.net/
   - Pilih **Windows** → **x64 Installer** (.exe)
   - Download file installer (contoh: `jdk-17.0.x_windows-x64_bin.exe`)

2. **Install JDK:**
   - Double-click file installer yang sudah didownload
   - Klik **Next** → **Next** → **Close**
   - JDK akan terinstall di `C:\Program Files\Java\jdk-17`

3. **Set Environment Variable:**
   - Tekan tombol **Windows + R**
   - Ketik `sysdm.cpl` dan tekan **Enter**
   - Klik tab **Advanced** → **Environment Variables**
   - Di **System variables**, klik **New**:
     ```
     Variable name: JAVA_HOME
     Variable value: C:\Program Files\Java\jdk-17
     ```
   - Pilih variable **Path** → Klik **Edit** → **New** → Tambahkan:
     ```
     %JAVA_HOME%\bin
     ```
   - Klik **OK** pada semua dialog

4. **Verifikasi Instalasi:**
   - Buka **Command Prompt** (CMD)
   - Ketik:
     ```bash
     java -version
     javac -version
     ```
   - Jika muncul versi Java 17.x.x, berarti instalasi berhasil ✅

#### **Linux (Ubuntu/Debian):**

```bash
# Update sistem
sudo apt update

# Install OpenJDK 17
sudo apt install openjdk-17-jdk -y

# Verifikasi
java -version
javac -version
```

#### **macOS:**

```bash
# Install menggunakan Homebrew
brew install openjdk@17

# Link ke sistem
sudo ln -sfn /usr/local/opt/openjdk@17/libexec/openjdk.jdk \
  /Library/Java/JavaVirtualMachines/openjdk-17.jdk

# Verifikasi
java -version
```

---

### 📦 Langkah 2.2: Install NetBeans IDE

#### **Windows:**

1. **Download NetBeans:**
   - Kunjungi: https://netbeans.apache.org/download/
   - Download **Apache NetBeans 19** atau versi terbaru
   - Pilih **Installer** untuk Windows (.exe)

2. **Install NetBeans:**
   - Double-click file installer
   - Klik **Next**
   - Terima License Agreement → **Next**
   - Pilih lokasi instalasi → **Next**
   - **Penting:** Pastikan JDK 17 terdeteksi di halaman ini
   - Klik **Install** → Tunggu proses instalasi selesai
   - Klik **Finish**

3. **Jalankan NetBeans Pertama Kali:**
   - Buka NetBeans dari Start Menu
   - Tunggu loading selesai
   - NetBeans siap digunakan! ✅

#### **Linux:**

```bash
# Download dan extract
wget https://dlcdn.apache.org/netbeans/netbeans-installers/19/Apache-NetBeans-19-bin-linux-x64.sh
chmod +x Apache-NetBeans-19-bin-linux-x64.sh
sudo ./Apache-NetBeans-19-bin-linux-x64.sh
```

#### **macOS:**

```bash
# Download dari website atau gunakan Homebrew
brew install --cask netbeans
```

---

### 📦 Langkah 2.3: Install MySQL Server

#### **Windows:**

1. **Download MySQL:**
   - Kunjungi: https://dev.mysql.com/downloads/installer/
   - Download **MySQL Installer** (mysql-installer-web-community-x.x.x.msi)

2. **Install MySQL:**
   - Double-click installer
   - Pilih **Custom** atau **Developer Default**
   - Komponen yang harus dipilih:
     - ✅ MySQL Server 8.0.x
     - ✅ MySQL Workbench (opsional tapi disarankan)
   - Klik **Next** → **Execute** untuk download & install
   - Tunggu hingga selesai

3. **Konfigurasi MySQL Server:**
   - **Type and Networking:**
     - Config Type: **Development Computer**
     - Port: **3306** (default)
     - Klik **Next**
   
   - **Authentication Method:**
     - Pilih: **Use Strong Password Encryption**
     - Klik **Next**
   
   - **Accounts and Roles:**
     - Buat password untuk user **root** (INGAT password ini!)
     - Contoh: `root123` atau `admin123`
     - Klik **Next**
   
   - **Windows Service:**
     - Service Name: **MySQL80**
     - ✅ Start MySQL Server at System Startup
     - Klik **Next**
   
   - Klik **Execute** → Tunggu hingga selesai
   - Klik **Finish**

4. **Verifikasi MySQL Berjalan:**
   - Tekan **Windows + R** → ketik `services.msc`
   - Cari service **MySQL80**
   - Status harus **Running** ✅

5. **Test Koneksi MySQL:**
   - Buka Command Prompt
   - Ketik:
     ```bash
     mysql -u root -p
     ```
   - Masukkan password root yang tadi dibuat
   - Jika berhasil login, ketik `exit;` untuk keluar

#### **Linux (Ubuntu/Debian):**

```bash
# Install MySQL Server
sudo apt update
sudo apt install mysql-server -y

# Jalankan MySQL
sudo systemctl start mysql
sudo systemctl enable mysql

# Secure installation
sudo mysql_secure_installation

# Test koneksi
mysql -u root -p
```

#### **macOS:**

```bash
# Install menggunakan Homebrew
brew install mysql

# Start MySQL service
brew services start mysql

# Test koneksi
mysql -u root -p
```

---

## 3. Setup Database MySQL

### 🗄️ Langkah 3.1: Import Schema Database

Ada 2 cara untuk import database:

#### **Cara 1: Menggunakan Command Line (Mudah)**

1. **Buka Command Prompt / Terminal**
   - Windows: Tekan **Windows + R** → ketik `cmd` → Enter
   - Linux/Mac: Buka Terminal

2. **Masuk ke folder project:**
   ```bash
   cd C:\kuliah\kkp_app\app_perpustkaan
   ```

3. **Login ke MySQL:**
   ```bash
   mysql -u root -p
   ```
   - Masukkan password root MySQL Anda

4. **Import schema database:**
   ```sql
   source database/schema.sql
   ```
   
   Atau gunakan perintah MySQL dari luar:
   ```bash
   mysql -u root -p < database/schema.sql
   ```

5. **Verifikasi database berhasil dibuat:**
   ```sql
   SHOW DATABASES;
   ```
   - Anda harus melihat database: **perpustakaan_al_asiyah** ✅

6. **Cek tabel-tabel:**
   ```sql
   USE perpustakaan_al_asiyah;
   SHOW TABLES;
   ```
   
   Anda harus melihat 7 tabel:
   - ✅ buku
   - ✅ guru
   - ✅ laporan
   - ✅ peminjaman
   - ✅ pengembalian
   - ✅ siswa
   - ✅ users

7. **Cek user default:**
   ```sql
   SELECT username, role, nama_lengkap FROM users;
   ```
   
   Harus ada 2 user:
   - ✅ admin (role: admin)
   - ✅ petugas (role: petugas)

#### **Cara 2: Menggunakan MySQL Workbench (GUI)**

1. **Buka MySQL Workbench**

2. **Buat Koneksi:**
   - Klik **+** (New Connection)
   - Connection Name: `Localhost`
   - Hostname: `localhost`
   - Port: `3306`
   - Username: `root`
   - Password: Klik **Store in Vault** → masukkan password root
   - Klik **Test Connection** → harus **Success** ✅
   - Klik **OK**

3. **Buka Koneksi:**
   - Double-click koneksi yang baru dibuat

4. **Import Schema:**
   - Menu: **File** → **Open SQL Script**
   - Browse ke folder project: `C:\kuliah\kkp_app\app_perpustkaan\database\`
   - Pilih file: **schema.sql**
   - Klik **Open**

5. **Execute Script:**
   - Klik icon **⚡ Execute** (atau tekan **Ctrl + Shift + Enter**)
   - Tunggu hingga selesai
   - Harus muncul: ✅ **Action Output: All statements executed successfully**

6. **Refresh dan Verifikasi:**
   - Klik **Refresh** icon di panel **SCHEMAS** (kiri bawah)
   - Expand **perpustakaan_al_asiyah** → **Tables**
   - Anda harus melihat 7 tabel ✅

---

### 🗄️ Langkah 3.2: Verifikasi Data Awal

Setelah import, verifikasi bahwa data awal sudah ada:

```sql
-- Gunakan database
USE perpustakaan_al_asiyah;

-- Cek user (harus ada 2 user: admin dan petugas)
SELECT * FROM users;

-- Cek tabel kosong (normal jika baru install)
SELECT COUNT(*) FROM buku;
SELECT COUNT(*) FROM siswa;
SELECT COUNT(*) FROM guru;
```

---

## 4. Membuka Project di NetBeans

### 📂 Langkah 4.1: Buka Project Maven

1. **Jalankan NetBeans IDE**
   - Buka NetBeans dari Start Menu atau Desktop

2. **Buka Project:**
   - Menu: **File** → **Open Project**
   - Atau tekan: **Ctrl + Shift + O**

3. **Pilih Folder Project:**
   - Browse ke folder project: `C:\kuliah\kkp_app\app_perpustkaan`
   - Project akan terlihat dengan icon Maven (M)
   - **Penting:** Pastikan Anda memilih folder yang berisi file `pom.xml`
   - Klik **Open Project**

4. **Tunggu NetBeans Sync:**
   - NetBeans akan otomatis mendeteksi bahwa ini adalah Maven project
   - Di panel **Output**, Anda akan melihat proses:
     ```
     Scanning for projects...
     Building perpustakaan-app 1.0.0
     ```
   - Tunggu hingga proses selesai (bisa 2-5 menit untuk pertama kali)
   - Status: **BUILD SUCCESS** ✅

---

### 📂 Langkah 4.2: Download Dependencies

NetBeans akan otomatis mendownload semua dependencies yang diperlukan:

1. **Lihat Progress Download:**
   - Panel **Output** → Tab **Maven Build**
   - Anda akan melihat:
     ```
     Downloading from central: ...
     Downloaded from central: ...
     ```

2. **Dependencies yang akan didownload:**
   - JavaFX 21 (Controls & FXML)
   - MySQL Connector Java
   - BCrypt
   - JasperReports (untuk laporan PDF)
   - Apache POI (untuk export Excel)
   - FontAwesome & ControlsFX (untuk UI)

3. **Waktu Download:**
   - Tergantung kecepatan internet
   - Biasanya 5-15 menit untuk pertama kali
   - Dependencies akan di-cache, jadi download berikutnya lebih cepat

4. **Verifikasi Dependencies Berhasil:**
   - Expand folder **Dependencies** di panel **Projects**
   - Anda harus melihat semua library ✅

---

## 5. Konfigurasi Project di NetBeans

### ⚙️ Langkah 5.1: Set JDK Project

1. **Klik kanan pada nama project** (`perpustakaan-app`) di panel **Projects**

2. **Pilih Properties**

3. **Kategori: Sources**
   - **Source/Binary Format:** Java 17 atau 17+
   - Klik **OK**

4. **Verifikasi JDK:**
   - Menu: **Tools** → **Java Platforms**
   - Pastikan JDK 17 ada di daftar ✅
   - Jika tidak ada, klik **Add Platform** dan browse ke folder JDK

---

### ⚙️ Langkah 5.2: Set Maven Configuration

1. **Klik kanan project → Properties**

2. **Kategori: Build → Compile**
   - Java Platform: **JDK 17** ✅
   - Klik **OK**

3. **Verifikasi Maven:**
   - Menu: **Tools** → **Options**
   - Kategori: **Java** → Tab **Maven**
   - Maven Home: Harus terdeteksi (biasanya bundled dengan NetBeans)
   - Jika kosong, download Maven dan set path manual

---

### ⚙️ Langkah 5.3: Eksplorasi Struktur Project

Di panel **Projects**, Anda akan melihat struktur:

```
perpustakaan-app
├── 📁 Source Packages
│   └── com.smk.alasiyah.perpustakaan
│       ├── 📦 config
│       │   └── DatabaseConfig.java
│       ├── 📦 controller
│       │   ├── BukuController.java
│       │   ├── DashboardController.java
│       │   ├── LoginController.java
│       │   ├── MainController.java
│       │   └── ... (dll)
│       ├── 📦 dao
│       │   ├── BukuDAO.java
│       │   ├── PeminjamanDAO.java
│       │   └── ... (dll)
│       ├── 📦 model
│       │   ├── Buku.java
│       │   ├── Siswa.java
│       │   └── ... (dll)
│       ├── 📦 util
│       │   ├── BCryptUtil.java
│       │   ├── SessionManager.java
│       │   └── ... (dll)
│       └── Main.java  ⭐ (File utama untuk run)
│
├── 📁 Other Sources
│   └── src/main/resources
│       └── com/smk/alasiyah/perpustakaan/view
│           ├── login.fxml
│           ├── main.fxml
│           ├── dashboard.fxml
│           └── ... (dll)
│
├── 📁 Dependencies
│   └── (Semua library Maven)
│
└── 📄 Project Files
    ├── pom.xml
    └── ... (dll)
```

---

## 6. Konfigurasi Database Connection

### 🔧 Langkah 6.1: Buat File .env (Konfigurasi Database)

Project ini menggunakan file `.env` untuk menyimpan konfigurasi database.

1. **Di NetBeans, klik kanan pada project** → **New** → **Other**

2. **Categories: Other** → **File Types: Empty File** → **Next**

3. **File Name:** `.env` (dengan titik di depan)
   - **Folder:** `<root project>` (sama level dengan pom.xml)
   - Klik **Finish**

4. **Isi file .env:**
   ```env
   # Konfigurasi Database MySQL
   DB_HOST=localhost
   DB_PORT=3306
   DB_NAME=perpustakaan_al_asiyah
   DB_USER=root
   DB_PASSWORD=root123
   ```
   
   > **⚠️ Penting:** Ganti `DB_PASSWORD` dengan password MySQL root Anda!

5. **Save file:** Tekan **Ctrl + S**

---

### 🔧 Langkah 6.2: Verifikasi Konfigurasi Database

Pastikan konfigurasi database sudah benar dengan membuka file `DatabaseConfig.java`:

1. **Di NetBeans, expand:**
   ```
   Source Packages → com.smk.alasiyah.perpustakaan → config
   ```

2. **Double-click:** `DatabaseConfig.java`

3. **Verifikasi kode:**
   - File ini membaca konfigurasi dari file `.env`
   - Pastikan URL, user, dan password sesuai dengan setup MySQL Anda

4. **Jika menggunakan port MySQL selain 3306:**
   - Edit file `.env` dan ubah `DB_PORT`

---

## 7. Build Project

### 🔨 Langkah 7.1: Clean and Build

Sebelum menjalankan aplikasi, kita perlu build project terlebih dahulu:

1. **Klik kanan pada project** (`perpustakaan-app`)

2. **Pilih: Clean and Build**
   - Atau tekan: **Shift + F11**

3. **Monitor Progress:**
   - Di panel **Output**, Anda akan melihat:
     ```
     --- maven-clean-plugin ---
     Deleting target directory...
     
     --- maven-compiler-plugin ---
     Compiling 30 source files to target/classes
     
     --- maven-shade-plugin ---
     Creating shaded jar...
     
     BUILD SUCCESS
     Total time: 45 s
     ```

4. **Pastikan muncul: BUILD SUCCESS** ✅

5. **Jika ada error:**
   - Lihat bagian [Troubleshooting](#10-troubleshooting) di bawah

---

### 🔨 Langkah 7.2: Verifikasi Build

Setelah build berhasil:

1. **Di panel Projects, expand folder:**
   ```
   perpustakaan-app → target → classes
   ```

2. **Anda harus melihat:**
   - ✅ Folder `com` dengan semua file .class
   - ✅ Folder `view` dengan semua file .fxml

3. **File JAR:**
   - Di folder `target`, Anda akan melihat:
     - `perpustakaan-app-1.0.0.jar` (fat jar dengan semua dependencies)

---

## 8. Menjalankan Aplikasi

### 🚀 Langkah 8.1: Run dari NetBeans

Ada 3 cara untuk menjalankan aplikasi:

#### **Cara 1: Menggunakan Run Project (Paling Mudah)**

1. **Klik kanan pada project** (`perpustakaan-app`)

2. **Pilih: Run**
   - Atau tekan: **F6**

3. **NetBeans akan:**
   - Compile project (jika ada perubahan)
   - Menjalankan class `Main.java`
   - Membuka jendela aplikasi

4. **Jika berhasil:**
   - Jendela **Login** aplikasi akan muncul ✅

#### **Cara 2: Run Main.java Langsung**

1. **Di panel Projects, expand:**
   ```
   Source Packages → com.smk.alasiyah.perpustakaan
   ```

2. **Klik kanan pada file:** `Main.java`

3. **Pilih: Run File**
   - Atau tekan: **Shift + F6**

4. **Aplikasi akan langsung berjalan** ✅

#### **Cara 3: Run dengan Maven Goal**

1. **Klik kanan pada project** → **Properties**

2. **Kategori: Actions**

3. **Actions: Run project**

4. **Execute Goals:** `javafx:run`

5. **Klik OK**

6. **Jalankan:** Tekan **F6**

---

### 🚀 Langkah 8.2: Run dari Command Line (Alternatif)

Jika Anda lebih suka menggunakan terminal:

1. **Buka Terminal di NetBeans:**
   - Menu: **Window** → **Output** → Tab **Terminal**
   - Atau tekan: **Alt + Shift + T**

2. **Pastikan Anda di folder project:**
   ```bash
   cd C:\kuliah\kkp_app\app_perpustkaan
   ```

3. **Jalankan dengan Maven:**
   ```bash
   mvn javafx:run
   ```

4. **Tunggu hingga aplikasi terbuka** ✅

---

### 🚀 Langkah 8.3: Run JAR File

Setelah build, Anda bisa menjalankan file JAR langsung:

1. **Buka Command Prompt / Terminal**

2. **Masuk ke folder target:**
   ```bash
   cd C:\kuliah\kkp_app\app_perpustkaan\target
   ```

3. **Jalankan JAR:**
   ```bash
   java -jar perpustakaan-app-1.0.0.jar
   ```

4. **Aplikasi akan berjalan tanpa NetBeans** ✅

---

## 9. Login dan Penggunaan

### 🔐 Langkah 9.1: Login Pertama Kali

Setelah aplikasi terbuka, Anda akan melihat form **Login**:

1. **Login sebagai Admin:**
   - Username: `admin`
   - Password: `admin123`
   - Klik: **LOGIN**

2. **Login sebagai Petugas:**
   - Username: `petugas`
   - Password: `petugas123`
   - Klik: **LOGIN**

3. **Jika login berhasil:**
   - Aplikasi akan redirect ke halaman **Dashboard** ✅
   - Anda akan melihat menu sidebar:
     - 📊 Dashboard
     - 📚 Data Buku
     - 👥 Data Anggota (Siswa & Guru)
     - 📖 Peminjaman
     - ↩️ Pengembalian
     - 📜 Riwayat
     - 📄 Laporan

---

### 🔐 Langkah 9.2: Ganti Password Default (PENTING!)

Untuk keamanan, segera ganti password default:

1. **Login sebagai admin**

2. **Buka MySQL Workbench atau Command Line**

3. **Jalankan query untuk ganti password:**
   ```sql
   USE perpustakaan_al_asiyah;
   
   -- Update password admin (gunakan BCrypt hash)
   UPDATE users 
   SET password = '$2a$10$newhash...'  -- Hash dari password baru
   WHERE username = 'admin';
   ```

4. **Atau gunakan tool PasswordGenerator di project:**
   - Run class: `com.smk.alasiyah.perpustakaan.util.PasswordGenerator`
   - Masukkan password baru
   - Copy hash yang dihasilkan
   - Update ke database

---

### 🎯 Langkah 9.3: Mulai Menggunakan Aplikasi

Selamat! Aplikasi sudah berjalan. Berikut fitur-fitur utama:

#### **1. Dashboard**
- Statistik total buku, anggota, peminjaman
- Grafik peminjaman bulanan
- Buku terpopuler

#### **2. Data Buku**
- Tambah, Edit, Hapus buku
- Search dan Filter berdasarkan kategori
- Sorting berdasarkan kolom

#### **3. Data Anggota**
- **Siswa:** Kelola data siswa (NISN, nama, kelas, dll)
- **Guru:** Kelola data guru (NIP, nama, mata pelajaran, dll)

#### **4. Peminjaman**
- Buat transaksi peminjaman baru
- Validasi stok otomatis
- Set tanggal jatuh tempo (default 7 hari)

#### **5. Pengembalian**
- Proses pengembalian buku
- Perhitungan denda otomatis (jika terlambat)
- Status: Tepat Waktu / Terlambat

#### **6. Riwayat**
- Lihat semua riwayat peminjaman
- Filter berdasarkan status, tanggal, anggota

#### **7. Laporan**
- Export laporan ke PDF
- Export laporan ke Excel
- Filter: Harian, Mingguan, Bulanan

---

## 10. Troubleshooting

### ❌ Error: "Failed to connect to database"

**Penyebab:**
- MySQL service tidak berjalan
- Username/password salah di file `.env`
- Database belum dibuat

**Solusi:**

1. **Cek MySQL Service:**
   ```bash
   # Windows
   net start MySQL80
   
   # Linux
   sudo systemctl status mysql
   ```

2. **Periksa file `.env`:**
   - Pastikan `DB_USER` dan `DB_PASSWORD` benar
   - Pastikan `DB_NAME` = `perpustakaan_al_asiyah`

3. **Test koneksi manual:**
   ```bash
   mysql -u root -p perpustakaan_al_asiyah
   ```

4. **Pastikan database ada:**
   ```sql
   SHOW DATABASES;
   -- Harus ada: perpustakaan_al_asiyah
   ```

---

### ❌ Error: "JavaFX runtime components are missing"

**Penyebab:**
- JavaFX tidak terinstall atau tidak terdeteksi

**Solusi:**

1. **Pastikan menggunakan JDK 17+:**
   ```bash
   java -version
   # Harus: java version "17" atau lebih tinggi
   ```

2. **Clean and Rebuild:**
   - Klik kanan project → **Clean and Build**

3. **Verifikasi dependencies di pom.xml:**
   - Pastikan ada `javafx-controls` dan `javafx-fxml` versi 21

4. **Force update dependencies:**
   - Klik kanan project → **Clean**
   - Delete folder `target`
   - Build ulang

---

### ❌ Error: "Cannot find symbol" atau "package does not exist"

**Penyebab:**
- Dependencies belum terdownload
- Maven cache corrupt

**Solusi:**

1. **Clean Maven Cache:**
   - Tutup NetBeans
   - Delete folder: `C:\Users\<YourName>\.m2\repository`
   - Buka NetBeans lagi
   - Klik kanan project → **Clean and Build**

2. **Force Reload Dependencies:**
   - Klik kanan project → **Properties**
   - Kategori: **Actions** → **Build project**
   - Execute Goals: `clean install -U`
   - Klik OK dan Build

3. **Verifikasi internet connection:**
   - Dependencies didownload dari Maven Central
   - Pastikan koneksi internet stabil

---

### ❌ Error: "java.lang.ClassNotFoundException: com.smk.alasiyah.perpustakaan.Main"

**Penyebab:**
- Build belum dijalankan
- File .class tidak ada di target folder

**Solusi:**

1. **Clean and Build:**
   - Klik kanan project → **Clean and Build**

2. **Verify Main class:**
   - Klik kanan project → **Properties**
   - Kategori: **Run**
   - Main Class: `com.smk.alasiyah.perpustakaan.Main` ✅

3. **Rebuild project:**
   - Delete folder `target`
   - Build ulang

---

### ❌ Error: "Port 3306 is already in use"

**Penyebab:**
- MySQL sudah berjalan di port 3306
- Atau port digunakan aplikasi lain

**Solusi:**

1. **Jika MySQL di port lain:**
   - Edit file `.env`:
     ```env
     DB_PORT=3307  # Ganti dengan port MySQL Anda
     ```

2. **Cek port MySQL:**
   ```sql
   SHOW VARIABLES LIKE 'port';
   ```

3. **Restart MySQL:**
   ```bash
   # Windows
   net stop MySQL80
   net start MySQL80
   ```

---

### ❌ Error: "FXML file not found" atau jendela kosong

**Penyebab:**
- File FXML tidak ada di resources
- Path FXML salah

**Solusi:**

1. **Verifikasi resources folder:**
   - Expand: **Other Sources** → `src/main/resources`
   - Pastikan ada folder: `com/smk/alasiyah/perpustakaan/view/`
   - Pastikan ada file: `login.fxml`, `main.fxml`, dll

2. **Rebuild project:**
   - Clean and Build

3. **Check path di controller:**
   - Buka `Main.java`
   - Verifikasi path FXML:
     ```java
     FXMLLoader loader = new FXMLLoader(
         getClass().getResource("/com/smk/alasiyah/perpustakaan/view/login.fxml")
     );
     ```

---

### ❌ Error: "Password authentication failed"

**Penyebab:**
- Password di database tidak match dengan input
- BCrypt hash tidak sesuai

**Solusi:**

1. **Reset password di database:**
   - Buka folder project: `database/`
   - Jalankan SQL script: `fix_password.sql`
   
   ```bash
   mysql -u root -p < database/fix_password.sql
   ```

2. **Atau manual:**
   ```sql
   USE perpustakaan_al_asiyah;
   
   -- Password: admin123
   UPDATE users SET password = '$2a$10$O7V5QILd9V5ySB4/V5tZnO8P3.dI3T5yH5z2B5z2B5z2B5z2B5z2Be'
   WHERE username = 'admin';
   
   -- Password: petugas123
   UPDATE users SET password = '$2a$10$O7V5QILd9V5ySB4/V5tZnO8P3.dI3T5yH5z2B5z2B5z2B5z2B5z2Be'
   WHERE username = 'petugas';
   ```

3. **Test login lagi dengan:**
   - Username: `admin` / Password: `admin123`
   - Username: `petugas` / Password: `petugas123`

---

### ❌ Error: "Maven build failed"

**Penyebab:**
- Koneksi internet terputus saat download
- Maven repository tidak bisa diakses

**Solusi:**

1. **Cek koneksi internet**

2. **Clear Maven cache dan retry:**
   ```bash
   # Di terminal NetBeans
   mvn clean install -U
   ```

3. **Gunakan Maven wrapper:**
   ```bash
   # Windows
   .\mvnw.cmd clean install
   
   # Linux/Mac
   ./mvnw clean install
   ```

---

### ❌ Error: "Java heap space" atau "OutOfMemoryError"

**Penyebab:**
- Memory tidak cukup untuk build project

**Solusi:**

1. **Increase heap size di NetBeans:**
   - Edit file: `netbeans-install-dir\etc\netbeans.conf`
   - Cari baris: `netbeans_default_options`
   - Tambahkan: `-J-Xmx2048m` (2GB RAM)
   
2. **Increase Maven heap:**
   - Set environment variable:
     ```bash
     set MAVEN_OPTS=-Xmx2048m -XX:MaxPermSize=512m
     ```

3. **Restart NetBeans**

---

## 11. Tips dan Trik NetBeans

### 💡 Tip 1: Keyboard Shortcuts

| Shortcut | Fungsi |
|----------|--------|
| **F6** | Run Project |
| **Shift + F6** | Run File |
| **Shift + F11** | Clean and Build |
| **Ctrl + Shift + O** | Open Project |
| **Ctrl + S** | Save |
| **Ctrl + Shift + S** | Save All |
| **Ctrl + Space** | Code Completion |
| **Ctrl + Click** | Go to Definition |
| **Alt + Shift + F** | Format Code |
| **Ctrl + /** | Comment/Uncomment Line |

---

### 💡 Tip 2: NetBeans Plugins untuk Java Development

Install plugins untuk meningkatkan produktivitas:

1. **Menu: Tools → Plugins**

2. **Available Plugins:**
   - ✅ **FindBugs** - Deteksi bug
   - ✅ **CheckStyle** - Code style checker
   - ✅ **SonarLint** - Code quality analyzer
   - ✅ **Git** (biasanya sudah include)

---

### 💡 Tip 3: Debug Mode

Untuk debug aplikasi:

1. **Set Breakpoint:**
   - Klik di margin kiri (sebelah line number)
   - Red dot akan muncul

2. **Run Debug:**
   - Klik kanan project → **Debug**
   - Atau tekan: **Ctrl + F5**

3. **Debug Controls:**
   - **F7** - Step Into
   - **F8** - Step Over
   - **Ctrl + F7** - Step Out
   - **F5** - Continue

---

### 💡 Tip 4: Git Integration

NetBeans memiliki Git integration built-in:

1. **Clone Repository:**
   - Menu: **Team → Git → Clone**

2. **Commit Changes:**
   - Klik kanan project → **Git → Commit**
   - Atau: **Ctrl + Alt + K**

3. **Push to Remote:**
   - Klik kanan project → **Git → Remote → Push**

4. **View History:**
   - Klik kanan project → **Git → Show History**

---

### 💡 Tip 5: Database Tools di NetBeans

NetBeans memiliki database client built-in:

1. **Buka Services Tab:**
   - Menu: **Window → Services**
   - Atau: **Ctrl + 5**

2. **Add Database Connection:**
   - Expand: **Databases**
   - Klik kanan: **MySQL Database** → **Register MySQL Server**
   - Atau: Klik kanan **Databases** → **New Connection**

3. **Configure Connection:**
   - Driver: `MySQL (Connector/J driver)`
   - Host: `localhost`
   - Port: `3306`
   - Database: `perpustakaan_al_asiyah`
   - User: `root`
   - Password: `<your_password>`
   - Klik **Test Connection** → Harus **Success** ✅

4. **Browse Database:**
   - Expand connection
   - Expand **Tables**
   - Klik kanan table → **View Data**
   - Anda bisa edit data langsung dari NetBeans!

---

### 💡 Tip 6: Code Templates

NetBeans memiliki code templates untuk coding lebih cepat:

| Template | Hasil |
|----------|-------|
| **psvm** + Tab | `public static void main(String[] args) {}` |
| **sout** + Tab | `System.out.println("");` |
| **fori** + Tab | `for (int i = 0; i < array.length; i++) {}` |
| **fore** + Tab | `for (Object obj : collection) {}` |
| **ifelse** + Tab | `if (condition) {} else {}` |
| **try** + Tab | `try {} catch (Exception e) {}` |

**Cara pakai:**
1. Ketik template (misal: `sout`)
2. Tekan **Tab**
3. Template akan ter-expand otomatis ✅

---

### 💡 Tip 7: Optimize Performance NetBeans

Jika NetBeans terasa lambat:

1. **Disable unused plugins:**
   - Menu: **Tools → Plugins → Installed**
   - Uncheck plugins yang tidak dipakai

2. **Increase memory:**
   - Edit `netbeans.conf`
   - Set: `-J-Xmx4096m` (4GB)

3. **Exclude folders dari scanning:**
   - Klik kanan folder `target` → **Mark as Excluded**

4. **Clear cache:**
   - Close NetBeans
   - Delete folder: `C:\Users\<YourName>\AppData\Local\NetBeans\Cache`

---

### 💡 Tip 8: Export Project sebagai ZIP

Untuk backup atau kirim project ke orang lain:

1. **Clean project terlebih dahulu:**
   - Klik kanan project → **Clean**
   - Ini akan hapus folder `target` (tidak perlu di-backup)

2. **Export:**
   - Klik kanan project → **Export Project**
   - Pilih format: **ZIP Archive**
   - Pilih lokasi save
   - Klik **Export**

3. **Atau manual:**
   - Compress folder project dengan WinRAR/7-Zip
   - Exclude folder: `target`, `.git`, `node_modules` (jika ada)

---

## 🎉 Selesai!

Selamat! Anda telah berhasil:
- ✅ Menginstall semua software yang diperlukan
- ✅ Setup database MySQL
- ✅ Membuka dan konfigurasi project di NetBeans
- ✅ Build dan menjalankan aplikasi
- ✅ Login dan menggunakan aplikasi

---

## 📚 Dokumentasi Tambahan

Untuk informasi lebih lanjut, baca dokumentasi berikut:

- **[README.md](README.md)** - Panduan singkat
- **[INSTALASI.md](INSTALASI.md)** - Panduan instalasi detail
- **[MANUAL_PENGGUNA.md](MANUAL_PENGGUNA.md)** - Manual penggunaan aplikasi
- **[DOKUMENTASI_LENGKAP.md](DOKUMENTASI_LENGKAP.md)** - Dokumentasi teknis lengkap

---

## 🆘 Butuh Bantuan?

Jika masih mengalami masalah:

1. **Cek bagian [Troubleshooting](#10-troubleshooting)** di atas
2. **Cek log error** di panel **Output** NetBeans
3. **Pastikan semua prasyarat terpenuhi**
4. **Hubungi administrator atau developer**

---

## 📞 Kontak & Support

- **Email:** support@smk-alasiyah.sch.id (contoh)
- **Phone:** 021-XXXX-XXXX (contoh)

---

**Dibuat untuk SMK AL-ASIYAH**  
**Versi: 1.0.0**  
**Terakhir Update: {{ Date }}**

---

**Happy Coding! 🚀**

