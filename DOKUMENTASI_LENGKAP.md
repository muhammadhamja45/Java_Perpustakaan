# 📚 Dokumentasi Lengkap - Sistem Perpustakaan SMK AL-ASIYAH

## 📋 Daftar Isi

1. [Pengenalan](#pengenalan)
2. [Instalasi](#instalasi)
3. [Konfigurasi](#konfigurasi)
4. [Cara Menggunakan](#cara-menggunakan)
5. [Struktur Database](#struktur-database)
6. [Fitur Aplikasi](#fitur-aplikasi)
7. [Troubleshooting](#troubleshooting)

---

## 🎯 Pengenalan

### Deskripsi Aplikasi

Aplikasi Manajemen Perpustakaan SMK AL-ASIYAH adalah sistem desktop berbasis JavaFX untuk mengelola operasional perpustakaan sekolah. Aplikasi ini dirancang untuk memudahkan petugas perpustakaan dalam mengelola data buku, anggota, transaksi peminjaman dan pengembalian, serta menghasilkan laporan.

### Teknologi yang Digunakan

- **Java 17+** - Bahasa pemrograman
- **JavaFX 21** - Framework untuk UI desktop
- **MySQL 8.0+** - Database management system
- **Maven** - Build tool dan dependency management
- **JasperReports** - Untuk generate laporan PDF
- **Apache POI** - Untuk export ke Excel

### Fitur Utama

✅ **Manajemen Buku** - CRUD lengkap dengan search, filter, dan sorting  
✅ **Manajemen Anggota** - Data siswa dan guru dengan status aktif/nonaktif  
✅ **Peminjaman Buku** - Validasi stok otomatis, tanggal kembali otomatis  
✅ **Pengembalian Buku** - Perhitungan denda otomatis jika terlambat  
✅ **Riwayat Transaksi** - Filter berdasarkan tanggal, status, anggota  
✅ **Dashboard** - Statistik real-time dan grafik peminjaman  
✅ **Laporan** - Generate laporan harian, mingguan, bulanan (PDF/Excel)  
✅ **Multi-User** - Role admin dan petugas dengan akses berbeda  

---

## 🚀 Instalasi

### Prasyarat

1. **Java JDK 17 atau lebih tinggi**
   - Download: https://adoptium.net/
   - Verifikasi: `java -version`

2. **MySQL Server 8.0 atau lebih tinggi**
   - Download: https://dev.mysql.com/downloads/mysql/
   - Pastikan MySQL service berjalan

3. **Maven 3.6+** (Opsional, bisa menggunakan Maven Wrapper)
   - Download: https://maven.apache.org/download.cgi
   - Verifikasi: `mvn -version`

4. **Git** (untuk clone repository)
   - Download: https://git-scm.com/downloads

### Langkah Instalasi

#### 1. Clone Repository

```bash
git clone https://github.com/muhammadhamja45/Java_Perpustakaan.git
cd Java_Perpustakaan
```

#### 2. Setup Database

```bash
# Login ke MySQL
mysql -u root -p

# Import schema database
mysql -u root -p < database/schema.sql

# Atau jika sudah login ke MySQL:
source database/schema.sql
```

#### 3. Konfigurasi Environment

Buat file `.env` di root folder project:

```env
DB_HOST=localhost
DB_PORT=3306
DB_NAME=perpustakaan_al_asiyah
DB_USER=root
DB_PASSWORD=password_anda
```

> **Catatan:** Jika MySQL tidak menggunakan password, biarkan `DB_PASSWORD=` kosong.

#### 4. Build Project

```bash
mvn clean install
```

#### 5. Jalankan Aplikasi

```bash
mvn javafx:run
```

---

## ⚙️ Konfigurasi

### File .env

File `.env` digunakan untuk menyimpan konfigurasi database. Format:

```env
DB_HOST=localhost          # Host MySQL (default: localhost)
DB_PORT=3306              # Port MySQL (default: 3306)
DB_NAME=perpustakaan_al_asiyah  # Nama database
DB_USER=root              # Username MySQL
DB_PASSWORD=              # Password MySQL (kosong jika tidak ada)
```

### Default User

Setelah import database, tersedia user default:

| Username | Password | Role |
|----------|----------|------|
| admin | admin123 | admin |
| petugas | petugas123 | petugas |

> **⚠️ PENTING:** Ganti password default setelah instalasi pertama!

---

## 📖 Cara Menggunakan

### 1. Login

1. Buka aplikasi
2. Masukkan username dan password
3. Klik tombol "Masuk"
4. Aplikasi akan menampilkan dashboard sesuai role

### 2. Dashboard

**Fitur Dashboard:**
- **Statistik Cards:**
  - Total Buku
  - Buku Tersedia
  - Buku Dipinjam
  - Total Anggota (Siswa + Guru)

- **Grafik:**
  - Grafik Peminjaman Harian (7 hari terakhir)
  - Grafik Peminjaman Bulanan (3 bulan terakhir)

- **Aktivitas Terbaru:**
  - 10 transaksi peminjaman terakhir

### 3. Manajemen Buku

**Tambah Buku:**
1. Klik menu "📖 Manajemen Buku"
2. Klik tombol "➕ Tambah Buku"
3. Isi form:
   - Kode Buku* (wajib, harus unique)
   - Judul* (wajib)
   - Pengarang* (wajib)
   - Penerbit* (wajib)
   - Tahun (opsional)
   - Kategori* (wajib)
   - Stok* (wajib, minimal 0)
   - Lokasi Rak* (wajib)
4. Klik "💾 Simpan"

**Edit Buku:**
1. Pilih buku dari tabel
2. Klik tombol "✏️ Edit"
3. Ubah data yang diperlukan
4. Klik "💾 Simpan"

**Hapus Buku:**
1. Pilih buku dari tabel
2. Klik tombol "🗑️ Hapus"
3. Konfirmasi penghapusan
4. Buku akan dihapus (jika tidak ada peminjaman aktif)

**Cari & Filter:**
- Gunakan search box untuk mencari buku
- Gunakan dropdown "Filter Kategori" untuk filter berdasarkan kategori
- Klik "🔄 Refresh" untuk reset filter

### 4. Data Siswa

**Tambah Siswa:**
1. Klik menu "👨‍🎓 Data Siswa"
2. Klik tombol "➕ Tambah Siswa"
3. Isi form:
   - NIS* (wajib, harus unique)
   - Nama* (wajib)
   - Kelas* (wajib)
   - Alamat (opsional)
   - No. Telp (opsional)
   - Status* (aktif/nonaktif)
4. Klik "💾 Simpan"

**Edit/Hapus:** Sama seperti Manajemen Buku

### 5. Data Guru

**Tambah Guru:**
1. Klik menu "👨‍🏫 Data Guru"
2. Klik tombol "➕ Tambah Guru"
3. Isi form:
   - NIP* (wajib, harus unique)
   - Nama* (wajib)
   - Jabatan (opsional)
   - Alamat (opsional)
   - No. Telp (opsional)
   - Status* (aktif/nonaktif)
4. Klik "💾 Simpan"

**Edit/Hapus:** Sama seperti Manajemen Buku

### 6. Peminjaman Buku

**Tambah Peminjaman:**
1. Klik menu "📤 Peminjaman"
2. Klik tombol "➕ Tambah Peminjaman"
3. Isi form:
   - Jenis Anggota* (Siswa/Guru)
   - Anggota* (pilih dari dropdown)
   - Buku* (pilih dari dropdown, hanya yang stok > 0)
   - Tanggal Pinjam* (default: hari ini)
   - Tanggal Kembali* (default: +7 hari)
4. Klik "💾 Simpan"
5. Stok buku otomatis berkurang

**Catatan:**
- Hanya buku dengan stok > 0 yang bisa dipinjam
- Tanggal kembali otomatis +7 hari dari tanggal pinjam
- Status otomatis menjadi "dipinjam"

### 7. Pengembalian Buku

**Kembalikan Buku:**
1. Klik menu "📥 Pengembalian"
2. Tabel menampilkan semua peminjaman dengan status "dipinjam"
3. Klik tombol "📥 Kembalikan" pada baris yang ingin dikembalikan
4. Sistem akan:
   - Menghitung denda jika terlambat (Rp 1.000/hari)
   - Mengubah status menjadi "dikembalikan"
   - Menambah stok buku +1
   - Menyimpan data pengembalian

**Info:**
- Denda dihitung otomatis jika tanggal kembali < tanggal dikembalikan
- Stok buku otomatis bertambah saat pengembalian

### 8. Riwayat Transaksi

**Melihat Riwayat:**
1. Klik menu "📋 Riwayat Transaksi"
2. Gunakan filter:
   - **Search:** Cari berdasarkan nama buku atau anggota
   - **Filter Status:** Pilih status (dipinjam/dikembalikan)
   - **Tanggal Mulai & Akhir:** Filter berdasarkan rentang tanggal
3. Klik "🔍 Filter" untuk menerapkan filter
4. Klik "🔄 Refresh" untuk reset

### 9. Laporan

**Generate Laporan:**
1. Klik menu "📊 Laporan"
2. Pilih:
   - **Jenis Laporan:** Harian, Mingguan, atau Bulanan
   - **Tanggal:** Pilih tanggal/periode
   - **Format Export:** PDF atau Excel
3. Klik "📊 Generate Laporan" untuk preview
4. Klik "💾 Export" untuk menyimpan file

---

## 🗄️ Struktur Database

### Tabel: users
| Kolom | Tipe | Keterangan |
|-------|------|------------|
| id_user | INT | Primary Key, Auto Increment |
| username | VARCHAR(50) | Unique, Not Null |
| password | VARCHAR(255) | Not Null (Plain Text) |
| role | ENUM | 'admin' atau 'petugas' |
| nama_lengkap | VARCHAR(100) | Not Null |
| created_at | TIMESTAMP | Auto |

### Tabel: buku
| Kolom | Tipe | Keterangan |
|-------|------|------------|
| id_buku | INT | Primary Key, Auto Increment |
| kode_buku | VARCHAR(50) | Unique, Not Null |
| judul | VARCHAR(150) | Not Null |
| pengarang | VARCHAR(100) | |
| penerbit | VARCHAR(100) | |
| tahun | YEAR | |
| kategori | VARCHAR(50) | |
| stok | INT | Not Null, Default 1 |
| lokasi_rak | VARCHAR(50) | |
| created_at | TIMESTAMP | Auto |

### Tabel: siswa
| Kolom | Tipe | Keterangan |
|-------|------|------------|
| id_siswa | INT | Primary Key, Auto Increment |
| nis | VARCHAR(20) | Unique, Not Null |
| nama | VARCHAR(100) | Not Null |
| kelas | VARCHAR(50) | Not Null |
| alamat | TEXT | |
| no_telp | VARCHAR(20) | |
| status | ENUM | 'aktif' atau 'nonaktif' |
| created_at | TIMESTAMP | Auto |

### Tabel: guru
| Kolom | Tipe | Keterangan |
|-------|------|------------|
| id_guru | INT | Primary Key, Auto Increment |
| nip | VARCHAR(20) | Unique, Not Null |
| nama | VARCHAR(100) | Not Null |
| jabatan | VARCHAR(100) | |
| alamat | TEXT | |
| no_telp | VARCHAR(20) | |
| status | ENUM | 'aktif' atau 'nonaktif' |
| created_at | TIMESTAMP | Auto |

### Tabel: peminjaman
| Kolom | Tipe | Keterangan |
|-------|------|------------|
| id_pinjam | INT | Primary Key, Auto Increment |
| id_user | INT | Foreign Key → users |
| id_buku | INT | Foreign Key → buku |
| id_siswa | INT | Foreign Key → siswa (NULL jika guru) |
| id_guru | INT | Foreign Key → guru (NULL jika siswa) |
| tgl_pinjam | DATE | Not Null |
| tgl_kembali | DATE | Not Null |
| status_pinjam | ENUM | 'dipinjam' atau 'dikembalikan' |

### Tabel: pengembalian
| Kolom | Tipe | Keterangan |
|-------|------|------------|
| id_kembali | INT | Primary Key, Auto Increment |
| id_pinjam | INT | Foreign Key → peminjaman |
| tgl_dikembalikan | DATE | Not Null |
| denda | DECIMAL(10,2) | Default 0 |

### Tabel: laporan
| Kolom | Tipe | Keterangan |
|-------|------|------------|
| id_laporan | INT | Primary Key, Auto Increment |
| periode | ENUM | 'harian', 'mingguan', 'bulanan' |
| total_pinjam | INT | Default 0 |
| total_kembali | INT | Default 0 |
| total_denda | DECIMAL(10,2) | Default 0 |
| tanggal_laporan | DATE | Not Null |

---

## 🎨 Fitur Aplikasi

### Role Admin
- ✅ Manajemen Buku (CRUD)
- ✅ Data Siswa (CRUD)
- ✅ Data Guru (CRUD)
- ✅ Manajemen User
- ✅ Peminjaman & Pengembalian
- ✅ Riwayat Transaksi
- ✅ Generate Laporan

### Role Petugas
- ✅ Peminjaman & Pengembalian
- ✅ Riwayat Transaksi
- ✅ Generate Laporan
- ❌ Tidak bisa mengelola data buku, siswa, guru, dan user

---

## 🔧 Troubleshooting

### Error: "Gagal koneksi ke database"

**Penyebab:**
- MySQL service tidak berjalan
- Konfigurasi `.env` salah
- Database belum dibuat

**Solusi:**
1. Pastikan MySQL service berjalan:
   ```bash
   # Windows
   net start MySQL80
   
   # Linux/Mac
   sudo systemctl start mysql
   ```

2. Periksa file `.env`:
   - Pastikan file ada di root folder
   - Periksa username, password, host, port
   - Jika tidak ada password, biarkan `DB_PASSWORD=` kosong

3. Pastikan database sudah dibuat:
   ```sql
   mysql -u root -p < database/schema.sql
   ```

### Error: "JavaFX runtime components are missing"

**Penyebab:**
- JavaFX tidak ter-load dengan benar
- Menjalankan langsung dari IDE tanpa Maven

**Solusi:**
1. Gunakan Maven untuk menjalankan:
   ```bash
   mvn javafx:run
   ```

2. Atau build JAR terlebih dahulu:
   ```bash
   mvn clean package
   java -jar target/perpustakaan-app-1.0.0.jar
   ```

### Error: "File .env tidak terbaca"

**Penyebab:**
- File tidak ada di root folder
- Format file salah

**Solusi:**
1. Pastikan file `.env` ada di root folder (sama level dengan `pom.xml`)
2. Format yang benar:
   ```env
   DB_HOST=localhost
   DB_PORT=3306
   DB_NAME=perpustakaan_al_asiyah
   DB_USER=root
   DB_PASSWORD=
   ```
   (tanpa spasi sebelum `=`)

### Data tidak muncul di tabel

**Penyebab:**
- Database kosong
- Query error
- Status data tidak sesuai filter

**Solusi:**
1. Periksa apakah ada data di database:
   ```sql
   SELECT * FROM buku;
   SELECT * FROM peminjaman;
   ```

2. Periksa console untuk error message
3. Pastikan filter status sesuai (misalnya: "dipinjam" bukan "Dipinjam")

### Tombol tidak berfungsi

**Penyebab:**
- Event handler tidak terhubung
- Data null

**Solusi:**
1. Restart aplikasi
2. Pastikan data yang dipilih tidak null
3. Periksa console untuk error message

---

## 📝 Catatan Penting

1. **Password:** Aplikasi menggunakan plain text password (untuk development). Untuk production, disarankan menggunakan BCrypt.

2. **Backup Database:** Lakukan backup database secara berkala:
   ```bash
   mysqldump -u root -p perpustakaan_al_asiyah > backup.sql
   ```

3. **File .env:** File ini tidak di-commit ke repository (sudah di `.gitignore`). Setiap developer harus membuat file `.env` sendiri.

4. **Stok Buku:** Sistem otomatis mengurangi stok saat peminjaman dan menambah saat pengembalian.

5. **Denda:** Dihitung otomatis Rp 1.000 per hari keterlambatan.

---

## 📞 Support

Jika mengalami masalah, silakan:
1. Periksa dokumentasi ini
2. Periksa file troubleshooting
3. Periksa console untuk error message
4. Buat issue di GitHub repository

---

## 📄 Lisensi

Project ini dibuat untuk keperluan pendidikan di SMK AL-ASIYAH.

---

**Selamat menggunakan aplikasi!** 🎉

