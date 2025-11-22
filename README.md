# Aplikasi Manajemen Perpustakaan SMK AL-ASIYAH

Aplikasi desktop modern untuk sistem manajemen perpustakaan sekolah menggunakan JavaFX dan MySQL.

## 📋 Fitur Utama

- ✅ **Login & Logout** dengan role admin dan petugas (password di-hash dengan BCrypt)
- ✅ **Dashboard** dengan statistik lengkap dan grafik peminjaman
- ✅ **Manajemen Buku** (CRUD lengkap dengan search, filter, sorting)
- ✅ **Data Anggota** (Siswa & Guru dengan CRUD)
- ✅ **Peminjaman Buku** dengan validasi stok otomatis
- ✅ **Pengembalian Buku** dengan perhitungan denda otomatis
- ✅ **Riwayat Transaksi** dengan filter lengkap
- ✅ **Laporan** (Harian, Mingguan, Bulanan) dengan export PDF/Excel

## 🛠️ Teknologi

- **Java 17+**
- **JavaFX 21**
- **MySQL 8.0+**
- **Maven**
- **BCrypt** untuk password hashing
- **JasperReports** untuk laporan PDF
- **Apache POI** untuk export Excel

## 📦 Instalasi Cepat

### 1. Setup File .env

Buat file `.env` di root folder project (copy dari `.env.example`):

```env
DB_HOST=localhost
DB_PORT=3306
DB_NAME=perpustakaan_al_asiyah
DB_USER=root
DB_PASSWORD=password_anda
```

### 2. Setup Database

```bash
mysql -u root -p < database/schema.sql
```

### 3. Build dan Jalankan

```bash
# Build project
mvn clean install

# Jalankan aplikasi
mvn javafx:run
```

**Atau untuk VSCode:** Lihat [PANDUAN_VSCODE.md](PANDUAN_VSCODE.md)

## 🔐 Default Login

Setelah import database:

- **Admin:** username: `admin`, password: `admin123`
- **Petugas:** username: `petugas`, password: `petugas123`

> **PENTING:** Ganti password default setelah instalasi pertama!

## 📖 Dokumentasi Lengkap

- **[DOKUMENTASI_LENGKAP.md](DOKUMENTASI_LENGKAP.md)** - Dokumentasi lengkap dan panduan penggunaan
- **[docs/ERD_BISNIS.md](docs/ERD_BISNIS.md)** - ERD Bisnis dan Use Case Diagram
- **[docs/ERD_DATABASE.md](docs/ERD_DATABASE.md)** - ERD Database dan Relasi Tabel
- **[docs/ALU_PROGRAM.md](docs/ALU_PROGRAM.md)** - Alur Program dan Pseudocode
- **[docs/FLOWCHART.md](docs/FLOWCHART.md)** - Flowchart Sistem
- **[INSTALASI.md](INSTALASI.md)** - Panduan instalasi detail
- **[MANUAL_PENGGUNA.md](MANUAL_PENGGUNA.md)** - Manual penggunaan aplikasi
- **[PANDUAN_VSCODE.md](PANDUAN_VSCODE.md)** - Panduan khusus untuk VSCode

## 🗂️ Struktur Project

```
app_perpustakaan/
├── .env                    # Konfigurasi database (buat sendiri)
├── .env.example            # Template konfigurasi
├── database/
│   └── schema.sql         # SQL schema database
├── src/
│   ├── main/
│   │   ├── java/          # Source code Java
│   │   └── resources/      # FXML dan resources
│   └── test/              # Unit tests
├── pom.xml                # Maven configuration
└── README.md              # Dokumentasi ini
```

## 🔧 Konfigurasi

Edit file `.env` untuk mengubah konfigurasi database:

```env
DB_HOST=localhost      # Host MySQL
DB_PORT=3306          # Port MySQL
DB_NAME=perpustakaan_al_asiyah  # Nama database
DB_USER=root          # Username MySQL
DB_PASSWORD=          # Password MySQL
```

## 🚀 Menjalankan Aplikasi

### Command Line (Maven)

```bash
mvn javafx:run
```

### Build JAR

```bash
mvn clean package
java -jar target/perpustakaan-app-1.0.0.jar
```

### VSCode

1. Install "Extension Pack for Java"
2. Buat file `.env` dengan konfigurasi database
3. Klik Run di `Main.java` atau gunakan terminal: `mvn javafx:run`

## 🔧 Troubleshooting

### Error: "Gagal koneksi ke database"
- Pastikan MySQL service berjalan
- Periksa file `.env` (username, password, host, port)
- Pastikan database sudah dibuat

### Error: "JavaFX runtime components are missing"
- Pastikan menggunakan Java 17+ dengan JavaFX
- Jalankan: `mvn clean install`

### File .env tidak terbaca
- Pastikan file `.env` ada di **root folder** (sama level dengan `pom.xml`)
- Format: `KEY=value` (tanpa spasi sebelum `=`)

## 📝 Catatan

- Password default menggunakan BCrypt hash yang sudah disediakan di schema.sql
- File `.env` tidak di-commit ke repository (sudah di .gitignore)
- Pastikan backup database secara berkala

## 👥 Kontributor

Dibuat untuk SMK AL-ASIYAH

## 📄 Lisensi

Project ini dibuat untuk keperluan pendidikan.

---

**Selamat menggunakan aplikasi!** 🎉
