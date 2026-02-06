# 📚 PANDUAN STRUKTUR PROJECT - Aplikasi Perpustakaan SMK AL-ASIYAH

## 🎯 Penjelasan untuk Pemula

Dokumen ini menjelaskan **struktur folder** dan **fungsi setiap file** dalam aplikasi perpustakaan ini dengan bahasa yang mudah dipahami, bahkan untuk yang baru belajar programming.

---

## 📂 STRUKTUR FOLDER UTAMA

```
app_perpustakaan/
├── 📁 database/          → Berisi file SQL untuk database
├── 📁 src/               → Kode program utama
├── 📁 target/            → Hasil kompilasi (dibuat otomatis)
├── 📁 docs/              → Dokumentasi teknis (diagram, ERD, dll)
├── 📁 documentation/     → Dokumentasi user-friendly
├── 📄 pom.xml           → File konfigurasi Maven (pengelola library)
├── 📄 .env              → File konfigurasi database (harus dibuat sendiri)
└── 📄 README.md         → Panduan instalasi dan penggunaan
```

---

## 🗄️ FOLDER: `database/`

Folder ini berisi file-file SQL untuk membuat dan mengelola database.

### 📄 File-file SQL:

| File | Fungsi | Kapan Digunakan |
|------|--------|-----------------|
| `schema.sql` | **Membuat struktur database** lengkap dengan tabel users, buku, siswa, guru, peminjaman, pengembalian, dan data awal (admin & petugas) | Saat **pertama kali install** aplikasi |
| `check_peminjaman.sql` | **Query untuk mengecek** data peminjaman yang aktif | Untuk **debugging** atau melihat data peminjaman |
| `fix_password.sql` | **Memperbaiki password** user yang lupa atau bermasalah | Jika ada masalah login karena password |
| `fix_status_null.sql` | **Memperbaiki data status** yang NULL/kosong di database | Jika terjadi error karena status kosong |

### 💡 Cara Pakai:
```bash
# Import database pertama kali
mysql -u root -p < database/schema.sql

# Jika butuh fix password
mysql -u root -p nama_database < database/fix_password.sql
```

---

## 📦 FOLDER: `src/` (Source Code)

Folder ini berisi **semua kode program** aplikasi. Struktur folder mengikuti pola MVC (Model-View-Controller).

```
src/
├── main/
│   ├── java/                    → Kode Java
│   │   └── com/smk/alasiyah/perpustakaan/
│   │       ├── 📁 config/       → Konfigurasi aplikasi
│   │       ├── 📁 controller/   → Logika kontrol tampilan
│   │       ├── 📁 dao/          → Akses ke database
│   │       ├── 📁 model/        → Model data (class objek)
│   │       ├── 📁 util/         → Fungsi bantuan (helper)
│   │       └── 📄 Main.java    → File utama untuk menjalankan aplikasi
│   │
│   └── resources/              → File tampilan & gambar
│       └── com/smk/alasiyah/perpustakaan/
│           └── view/           → File FXML (desain tampilan)
│
└── test/                       → Folder untuk testing (belum ada)
```

---

## 🎛️ FOLDER: `src/main/java/.../config/`

Folder ini berisi **konfigurasi koneksi database**.

### 📄 `DatabaseConfig.java`
**Fungsi:** Mengatur dan mengelola koneksi ke database MySQL

**Apa yang dilakukan:**
- 🔌 Membaca konfigurasi dari file `.env` (host, port, username, password)
- 🔗 Membuat koneksi ke database MySQL
- ♻️ Mengelola koneksi agar tidak membuat koneksi baru terus-menerus
- ❌ Menutup koneksi saat aplikasi ditutup

**Analogi:** Seperti **operator telepon** yang menghubungkan aplikasi dengan database. Setiap kali aplikasi butuh data, file ini yang membuat "sambungan telepon" ke database.

---

## 🎮 FOLDER: `src/main/java/.../controller/`

Folder ini berisi **logika kontrol** untuk setiap halaman aplikasi. Controller adalah "otak" yang mengatur apa yang terjadi saat user klik tombol atau input data.

### 📄 Penjelasan Setiap Controller:

#### 1️⃣ `LoginController.java`
**Fungsi:** Mengelola halaman login
- ✅ Memvalidasi username dan password
- 🔐 Mengecek data user di database
- 🚪 Membuka halaman utama jika login berhasil
- ⚠️ Menampilkan pesan error jika login gagal

**Analogi:** Seperti **satpam** yang mengecek kartu ID sebelum membiarkan Anda masuk gedung.

---

#### 2️⃣ `MainController.java`
**Fungsi:** Mengelola halaman utama (menu navigasi)
- 🧭 Mengatur perpindahan antar halaman (Dashboard, Buku, Siswa, dll)
- 👤 Menampilkan nama user yang sedang login
- 🚪 Menangani proses logout
- 🎨 Mengatur menu sidebar

**Analogi:** Seperti **resepsionis hotel** yang mengarahkan Anda ke ruangan yang Anda tuju.

---

#### 3️⃣ `DashboardController.java`
**Fungsi:** Mengelola halaman dashboard (statistik)
- 📊 Menampilkan jumlah total buku, siswa, guru
- 📈 Menampilkan statistik peminjaman dan pengembalian
- 📉 Menampilkan grafik peminjaman per bulan
- 🔄 Refresh data secara otomatis

**Analogi:** Seperti **papan informasi** di stasiun yang menampilkan informasi penting.

---

#### 4️⃣ `BukuController.java`
**Fungsi:** Mengelola halaman daftar buku
- 📋 Menampilkan semua data buku dalam tabel
- 🔍 Fitur pencarian buku (judul, pengarang, penerbit)
- ➕ Tombol tambah buku baru
- ✏️ Tombol edit buku
- 🗑️ Tombol hapus buku
- 🔄 Refresh data

**Analogi:** Seperti **katalog perpustakaan** yang bisa Anda cari dan kelola.

---

#### 5️⃣ `BukuDialogController.java`
**Fungsi:** Mengelola popup form tambah/edit buku
- 📝 Form input untuk data buku (judul, pengarang, penerbit, tahun, stok)
- ✅ Validasi input (tidak boleh kosong, tahun harus angka, stok harus positif)
- 💾 Menyimpan data baru atau update data lama
- ❌ Tombol batal

**Analogi:** Seperti **formulir** yang harus Anda isi saat mendaftar.

---

#### 6️⃣ `SiswaController.java`
**Fungsi:** Mengelola halaman daftar siswa
- 📋 Menampilkan semua data siswa (NIS, nama, kelas, alamat, telepon)
- 🔍 Pencarian siswa
- ➕ Tambah siswa baru
- ✏️ Edit data siswa
- 🗑️ Hapus siswa

**Analogi:** Seperti **buku induk siswa** di sekolah.

---

#### 7️⃣ `SiswaDialogController.java`
**Fungsi:** Mengelola popup form tambah/edit siswa
- 📝 Form input NIS, nama, kelas, alamat, telepon
- ✅ Validasi (NIS tidak boleh duplikat)
- 💾 Simpan data siswa

---

#### 8️⃣ `GuruController.java`
**Fungsi:** Mengelola halaman daftar guru
- 📋 Menampilkan semua data guru (NIP, nama, mata pelajaran, alamat, telepon)
- 🔍 Pencarian guru
- ➕ Tambah guru baru
- ✏️ Edit data guru
- 🗑️ Hapus guru

**Analogi:** Seperti **daftar kepegawaian guru** di sekolah.

---

#### 9️⃣ `GuruDialogController.java`
**Fungsi:** Mengelola popup form tambah/edit guru
- 📝 Form input NIP, nama, mata pelajaran, alamat, telepon
- ✅ Validasi data
- 💾 Simpan data guru

---

#### 🔟 `PeminjamanController.java`
**Fungsi:** Mengelola halaman peminjaman buku
- 📋 Menampilkan daftar peminjaman aktif
- ➕ Proses peminjaman baru
- 🔍 Pencarian peminjaman
- ⏰ Menampilkan tanggal pinjam dan tanggal harus kembali
- ⚠️ Validasi stok buku (tidak bisa pinjam jika stok habis)

**Analogi:** Seperti **petugas sirkulasi** yang mencatat siapa yang pinjam buku apa.

---

#### 1️⃣1️⃣ `PeminjamanDialogController.java`
**Fungsi:** Mengelola popup form peminjaman baru
- 👤 Pilih peminjam (siswa atau guru)
- 📚 Pilih buku yang akan dipinjam
- 📅 Set tanggal pinjam dan tanggal harus kembali
- ✅ Validasi (stok, tanggal, peminjam)
- 💾 Proses peminjaman (kurangi stok buku otomatis)

---

#### 1️⃣2️⃣ `PengembalianController.java`
**Fungsi:** Mengelola halaman pengembalian buku
- 📋 Menampilkan daftar peminjaman yang belum dikembalikan
- ✅ Proses pengembalian
- 💰 Hitung denda otomatis jika terlambat (Rp 1.000/hari)
- 🔍 Pencarian data peminjaman
- 🔄 Update stok buku otomatis saat dikembalikan

**Analogi:** Seperti **kasir perpustakaan** yang menerima buku kembali dan menghitung denda.

---

#### 1️⃣3️⃣ `RiwayatController.java`
**Fungsi:** Mengelola halaman riwayat transaksi
- 📜 Menampilkan semua peminjaman dan pengembalian yang sudah selesai
- 🔍 Filter berdasarkan tanggal
- 🔍 Filter berdasarkan status (sudah dikembalikan/belum)
- 📊 Melihat detail transaksi
- 📄 Export ke PDF/Excel

**Analogi:** Seperti **arsip transaksi** yang bisa Anda lihat kembali.

---

#### 1️⃣4️⃣ `LaporanController.java`
**Fungsi:** Mengelola halaman laporan
- 📅 Pilih periode laporan (Harian, Mingguan, Bulanan)
- 📊 Tampilkan preview laporan
- 📄 Export ke PDF
- 📊 Export ke Excel
- 📈 Statistik per periode

**Analogi:** Seperti **mesin pembuat laporan** untuk kepala perpustakaan.

---

#### 1️⃣5️⃣ `LaporanDetailController.java`
**Fungsi:** Mengelola popup detail laporan
- 📋 Menampilkan detail transaksi per periode
- 📊 Grafik dan statistik
- 🖨️ Print preview

---

## 🗃️ FOLDER: `src/main/java/.../dao/`

DAO = **Data Access Object** → Class yang **berinteraksi langsung dengan database**.

**Analogi sederhana:** DAO adalah seperti **pegawai administrasi** yang tugasnya adalah mengambil, menyimpan, mengupdate, dan menghapus data di lemari arsip (database).

### 📄 Penjelasan Setiap DAO:

#### 1️⃣ `UserDAO.java`
**Fungsi:** Mengelola data user (admin & petugas)
- ✅ Login (cek username & password)
- ➕ Tambah user baru
- 🔍 Cari user berdasarkan username
- ✏️ Update data user
- 🗑️ Hapus user
- 🔐 Hash password dengan BCrypt

**Query SQL yang digunakan:**
```sql
SELECT * FROM users WHERE username = ? AND password = ?
INSERT INTO users (username, password, role, nama_lengkap) VALUES (?, ?, ?, ?)
```

---

#### 2️⃣ `BukuDAO.java`
**Fungsi:** Mengelola data buku
- 📋 Ambil semua data buku
- 🔍 Cari buku (judul, pengarang, penerbit)
- ➕ Tambah buku baru
- ✏️ Update data buku
- 🗑️ Hapus buku
- 📊 Update stok buku (saat peminjaman/pengembalian)

**Query SQL contoh:**
```sql
SELECT * FROM buku
INSERT INTO buku (kode_buku, judul, pengarang, penerbit, tahun_terbit, stok) VALUES (?, ?, ?, ?, ?, ?)
UPDATE buku SET stok = stok - 1 WHERE id = ?
```

---

#### 3️⃣ `SiswaDAO.java`
**Fungsi:** Mengelola data siswa
- 📋 Ambil semua data siswa
- 🔍 Cari siswa berdasarkan NIS atau nama
- ➕ Tambah siswa baru
- ✏️ Update data siswa
- 🗑️ Hapus siswa
- ✅ Validasi NIS tidak duplikat

---

#### 4️⃣ `GuruDAO.java`
**Fungsi:** Mengelola data guru
- 📋 Ambil semua data guru
- 🔍 Cari guru berdasarkan NIP atau nama
- ➕ Tambah guru baru
- ✏️ Update data guru
- 🗑️ Hapus guru
- ✅ Validasi NIP tidak duplikat

---

#### 5️⃣ `PeminjamanDAO.java`
**Fungsi:** Mengelola data peminjaman
- 📋 Ambil semua peminjaman aktif
- ➕ Tambah peminjaman baru
- 🔍 Cari peminjaman berdasarkan peminjam atau buku
- ✅ Validasi stok buku sebelum pinjam
- 📊 Update stok buku otomatis (kurangi 1)
- 📅 Set tanggal pinjam dan tanggal kembali

**Query SQL contoh:**
```sql
SELECT p.*, b.judul, s.nama_siswa, g.nama_guru 
FROM peminjaman p
LEFT JOIN buku b ON p.id_buku = b.id
LEFT JOIN siswa s ON p.id_peminjam = s.id
LEFT JOIN guru g ON p.id_peminjam = g.id
WHERE p.status = 'dipinjam'
```

---

#### 6️⃣ `PengembalianDAO.java`
**Fungsi:** Mengelola data pengembalian
- ✅ Proses pengembalian buku
- 💰 Hitung denda otomatis (Rp 1.000/hari keterlambatan)
- 📊 Update status peminjaman (dari "dipinjam" → "dikembalikan")
- 📊 Update stok buku otomatis (tambah 1)
- 📅 Simpan tanggal pengembalian aktual

**Logika denda:**
```
Jika tanggal kembali > tanggal harus kembali:
  Denda = (jumlah hari terlambat) × Rp 1.000
Contoh: Terlambat 3 hari = Rp 3.000
```

---

#### 7️⃣ `StatistikDAO.java`
**Fungsi:** Mengambil data statistik untuk dashboard
- 📊 Hitung total buku
- 📊 Hitung total siswa
- 📊 Hitung total guru
- 📊 Hitung total peminjaman aktif
- 📊 Hitung total pengembalian hari ini
- 📈 Data peminjaman per bulan (untuk grafik)

---

## 📦 FOLDER: `src/main/java/.../model/`

Model adalah **class yang merepresentasikan data** (seperti blueprint atau cetakan).

**Analogi:** Seperti **formulir kosong** yang punya kolom-kolom tertentu. Setiap kali ada data baru, kita isi formulir itu.

### 📄 Penjelasan Setiap Model:

#### 1️⃣ `User.java`
**Representasi:** Data pengguna aplikasi (admin/petugas)

**Atribut/Field:**
```java
- id             → ID user (primary key)
- username       → Username untuk login
- password       → Password (di-hash)
- role           → Peran (admin / petugas)
- nama_lengkap   → Nama lengkap user
```

---

#### 2️⃣ `Buku.java`
**Representasi:** Data buku di perpustakaan

**Atribut/Field:**
```java
- id             → ID buku (auto increment)
- kode_buku      → Kode unik buku (contoh: BK001)
- judul          → Judul buku
- pengarang      → Nama pengarang
- penerbit       → Nama penerbit
- tahun_terbit   → Tahun terbit (contoh: 2020)
- stok           → Jumlah stok buku
```

---

#### 3️⃣ `Siswa.java`
**Representasi:** Data siswa yang jadi anggota perpustakaan

**Atribut/Field:**
```java
- id             → ID siswa (auto increment)
- nis            → Nomor Induk Siswa (unik)
- nama_siswa     → Nama lengkap siswa
- kelas          → Kelas siswa (contoh: XII RPL 1)
- alamat         → Alamat rumah
- telepon        → Nomor telepon
```

---

#### 4️⃣ `Guru.java`
**Representasi:** Data guru yang jadi anggota perpustakaan

**Atribut/Field:**
```java
- id             → ID guru (auto increment)
- nip            → Nomor Induk Pegawai (unik)
- nama_guru      → Nama lengkap guru
- mata_pelajaran → Mata pelajaran yang diajar
- alamat         → Alamat rumah
- telepon        → Nomor telepon
```

---

#### 5️⃣ `Peminjaman.java`
**Representasi:** Data transaksi peminjaman buku

**Atribut/Field:**
```java
- id                  → ID peminjaman
- id_buku             → ID buku yang dipinjam
- id_peminjam         → ID siswa/guru yang pinjam
- jenis_peminjam      → "siswa" atau "guru"
- tanggal_pinjam      → Tanggal pinjam
- tanggal_harus_kembali → Batas waktu pengembalian
- status              → "dipinjam" atau "dikembalikan"

// Field tambahan untuk tampilan
- judulBuku          → Nama buku (dari JOIN)
- namaPeminjam       → Nama siswa/guru (dari JOIN)
```

---

#### 6️⃣ `Pengembalian.java`
**Representasi:** Data transaksi pengembalian buku

**Atribut/Field:**
```java
- id                     → ID pengembalian
- id_peminjaman          → ID peminjaman terkait
- tanggal_kembali        → Tanggal pengembalian aktual
- denda                  → Jumlah denda (Rp)
- keterangan             → Catatan (contoh: "Terlambat 2 hari")

// Field tambahan dari JOIN
- judulBuku              → Nama buku
- namaPeminjam           → Nama peminjam
- tanggalPinjam          → Kapan dipinjam
```

---

## 🛠️ FOLDER: `src/main/java/.../util/`

Folder ini berisi **class pembantu** (utility) yang dipakai di berbagai tempat.

### 📄 Penjelasan Setiap Utility:

#### 1️⃣ `SessionManager.java`
**Fungsi:** Menyimpan data user yang sedang login

**Cara Kerja:**
- 💾 Saat login berhasil → simpan data user di memori
- 🔍 Controller bisa cek siapa yang login
- 🚪 Saat logout → hapus data user dari memori

**Analogi:** Seperti **gelang VIP** di konser. Selama pakai gelang, Anda bebas keluar-masuk. Kalau gelang dilepas (logout), harus login lagi.

**Kode contoh:**
```java
// Simpan user saat login
SessionManager.setCurrentUser(user);

// Cek user yang login
User currentUser = SessionManager.getCurrentUser();

// Logout
SessionManager.logout();
```

---

#### 2️⃣ `BCryptUtil.java`
**Fungsi:** Enkripsi password dengan algoritma BCrypt

**Mengapa perlu enkripsi?**
- 🔐 Password tidak disimpan dalam bentuk teks biasa (plain text)
- ✅ Lebih aman dari hacker
- 🔒 Tidak bisa dikembalikan ke bentuk asli (one-way encryption)

**Contoh:**
```
Password asli:   "admin123"
Setelah di-hash: "$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy"
```

**Fungsi utama:**
```java
// Hash password saat register/tambah user
String hashed = BCryptUtil.hashPassword("admin123");

// Cek password saat login
boolean valid = BCryptUtil.checkPassword("admin123", hashed);
```

---

#### 3️⃣ `EnvConfig.java`
**Fungsi:** Membaca file `.env` untuk konfigurasi database

**Cara Kerja:**
- 📄 Baca file `.env` di root folder
- 📝 Parse format `KEY=value`
- 💾 Simpan di memori
- 🔍 Bisa diakses dengan `EnvConfig.get("DB_HOST")`

**Kenapa pakai `.env`?**
- 🔐 Tidak perlu hardcode password di kode
- 🔄 Mudah ganti konfigurasi tanpa ubah kode
- 🚫 File `.env` tidak di-commit ke Git (aman)

**Contoh file `.env`:**
```
DB_HOST=localhost
DB_PORT=3306
DB_NAME=perpustakaan_al_asiyah
DB_USER=root
DB_PASSWORD=rahasia123
```

---

#### 4️⃣ `PasswordGenerator.java`
**Fungsi:** Generate password random untuk user baru

**Cara Kerja:**
- 🎲 Buat kombinasi random huruf, angka, dan simbol
- 📏 Panjang password bisa diatur (default 8 karakter)
- 🔐 Password yang dihasilkan aman dan sulit ditebak

**Contoh:**
```java
String newPassword = PasswordGenerator.generate(10);
// Output: "aB3$dK9@zX"
```

---

#### 5️⃣ `PDFReportGenerator.java`
**Fungsi:** Generate laporan dalam format PDF

**Fitur:**
- 📄 Buat dokumen PDF
- 📊 Tambahkan tabel dengan data
- 🎨 Format header, footer, dan styling
- 💾 Export ke file PDF

**Library yang dipakai:** iText PDF

**Contoh penggunaan:**
```java
PDFReportGenerator.generateLaporan(
    dataPeminjaman,
    "Laporan Peminjaman Bulan Januari 2026",
    "laporan_januari.pdf"
);
```

---

#### 6️⃣ `PDFReportGeneratorExtended.java`
**Fungsi:** Versi extended dari PDF generator dengan fitur tambahan

**Fitur Tambahan:**
- 📊 Export ke Excel (format .xlsx)
- 📈 Grafik dan chart
- 🎨 Styling lebih kompleks
- 📋 Multiple sheets (untuk Excel)

**Library yang dipakai:** Apache POI (untuk Excel)

---

#### 7️⃣ `TestConnection.java`
**Fungsi:** Test koneksi ke database

**Kapan dipakai:**
- 🔧 Saat troubleshooting koneksi database
- ✅ Cek apakah konfigurasi `.env` sudah benar
- 🧪 Testing saat development

**Cara pakai:**
```bash
java com.smk.alasiyah.perpustakaan.util.TestConnection
# Output: "Koneksi berhasil!" atau "Koneksi gagal: [error message]"
```

---

## 🖼️ FOLDER: `src/main/resources/.../view/`

Folder ini berisi **file tampilan** (UI) aplikasi menggunakan FXML.

**FXML = File XML untuk mendefinisikan layout tampilan JavaFX**

### 📄 Penjelasan Setiap File View:

#### 1️⃣ `login.fxml`
**Tampilan:** Halaman login
- 📝 Form username
- 🔐 Form password
- 🔘 Tombol login
- ⚠️ Label error message

---

#### 2️⃣ `main.fxml`
**Tampilan:** Halaman utama dengan sidebar menu
- 🧭 Sidebar navigasi (Dashboard, Buku, Siswa, Guru, Peminjaman, dll)
- 👤 Info user yang login
- 🚪 Tombol logout
- 📱 Container untuk load halaman lain

---

#### 3️⃣ `dashboard.fxml`
**Tampilan:** Halaman dashboard
- 📊 Card statistik (total buku, siswa, guru)
- 📈 Grafik peminjaman per bulan
- 📋 Summary transaksi hari ini
- 🔄 Tombol refresh

---

#### 4️⃣ `buku.fxml`
**Tampilan:** Halaman daftar buku
- 📋 Tabel daftar buku (kode, judul, pengarang, stok)
- 🔍 Search box
- ➕ Tombol tambah buku
- ✏️ Tombol edit
- 🗑️ Tombol hapus

---

#### 5️⃣ `buku_dialog.fxml`
**Tampilan:** Popup form tambah/edit buku
- 📝 Form input semua field buku
- 💾 Tombol simpan
- ❌ Tombol batal

---

#### 6️⃣ `siswa.fxml`
**Tampilan:** Halaman daftar siswa
- 📋 Tabel data siswa
- 🔍 Search & filter
- ➕ Tambah siswa
- ✏️ Edit
- 🗑️ Hapus

---

#### 7️⃣ `siswa_dialog.fxml`
**Tampilan:** Popup form tambah/edit siswa

---

#### 8️⃣ `guru.fxml`
**Tampilan:** Halaman daftar guru

---

#### 9️⃣ `guru_dialog.fxml`
**Tampilan:** Popup form tambah/edit guru

---

#### 🔟 `peminjaman.fxml`
**Tampilan:** Halaman peminjaman buku
- 📋 Tabel peminjaman aktif
- 🔍 Search
- ➕ Proses peminjaman baru
- 👁️ Detail peminjaman

---

#### 1️⃣1️⃣ `peminjaman_dialog.fxml`
**Tampilan:** Popup form peminjaman baru
- 👤 Dropdown pilih peminjam
- 📚 Dropdown pilih buku
- 📅 Date picker tanggal pinjam
- 📅 Date picker tanggal kembali
- 💾 Tombol simpan

---

#### 1️⃣2️⃣ `pengembalian.fxml`
**Tampilan:** Halaman pengembalian buku
- 📋 Tabel peminjaman yang belum dikembali
- ✅ Tombol proses pengembalian
- 💰 Kolom denda (otomatis)
- 🔍 Search

---

#### 1️⃣3️⃣ `riwayat.fxml`
**Tampilan:** Halaman riwayat transaksi
- 📜 Tabel semua transaksi (selesai & aktif)
- 🔍 Filter tanggal
- 🔍 Filter status
- 📄 Export PDF/Excel

---

#### 1️⃣4️⃣ `laporan.fxml`
**Tampilan:** Halaman laporan
- 📅 Pilih periode (Harian/Mingguan/Bulanan)
- 📅 Date picker range tanggal
- 🔘 Tombol generate laporan
- 👁️ Preview laporan
- 📄 Export PDF
- 📊 Export Excel

---

#### 1️⃣5️⃣ `laporan_detail.fxml`
**Tampilan:** Popup detail laporan
- 📊 Tabel detail transaksi
- 📈 Grafik statistik
- 🖨️ Print preview

---

#### 1️⃣6️⃣ `styles.css`
**Fungsi:** File styling untuk semua tampilan

**Isi:**
- 🎨 Warna tema aplikasi
- 📏 Ukuran font
- 🔲 Border dan shadow
- 🎯 Hover effects
- 📱 Layout spacing

---

## 📁 FOLDER: `docs/`

Folder ini berisi **dokumentasi teknis** seperti diagram dan desain.

### 📄 File-file Dokumentasi:

| File | Isi |
|------|-----|
| `ERD_BISNIS.md` | Diagram ERD untuk proses bisnis perpustakaan |
| `ERD_DATABASE.md` | Diagram ERD untuk struktur database |
| `ALU_PROGRAM.md` | Alur program dan pseudocode |
| `FLOWCHART.md` | Flowchart sistem |
| `UML_CLASS_DIAGRAM.md` | Diagram class (OOP) |
| `UML_SEQUENCE_DIAGRAM.md` | Diagram sequence (alur interaksi) |
| `UML_STATE_MACHINE_DIAGRAM.md` | Diagram state machine (status user) |
| `UML_USE_CASE.md` | Diagram use case (kebutuhan fungsional) |
| `DESIGN.md` | Dokumentasi desain UI/UX |
| `design/` | Folder berisi screenshot desain halaman |

---

## 📁 FOLDER: `documentation/`

Folder ini berisi **dokumentasi yang lebih user-friendly**.

### 📄 File-file:

| File | Isi |
|------|-----|
| `README.md` | Pusat dokumentasi (overview) |
| `flowchart.md` | Flowchart dalam format Mermaid |
| `netbeans.md` | Panduan menggunakan NetBeans IDE |
| `mermaid/` | File-file diagram Mermaid |
| `images/` | Gambar flowchart dalam format SVG |

---

## 🎯 POLA ARSITEKTUR: MVC (Model-View-Controller)

Aplikasi ini menggunakan pola **MVC** untuk memisahkan logika:

```
┌─────────────────────────────────────────────┐
│            USER INTERFACE (View)            │
│         File: *.fxml + styles.css           │
│  ↕ User klik tombol, input data             │
└─────────────────────────────────────────────┘
                    ↕
┌─────────────────────────────────────────────┐
│          CONTROLLER (Logika)                │
│    File: *Controller.java                   │
│  ↕ Proses input, validasi, panggil DAO      │
└─────────────────────────────────────────────┘
                    ↕
┌─────────────────────────────────────────────┐
│        DAO (Akses Database)                 │
│         File: *DAO.java                     │
│  ↕ Query SQL ke database                    │
└─────────────────────────────────────────────┘
                    ↕
┌─────────────────────────────────────────────┐
│           DATABASE (MySQL)                  │
│    File: database/schema.sql                │
│  💾 Menyimpan semua data                    │
└─────────────────────────────────────────────┘
```

### 🔄 Contoh Alur Lengkap: "Tambah Buku Baru"

1. **View** (`buku.fxml`) → User klik tombol "Tambah Buku"
2. **Controller** (`BukuController.java`) → Buka dialog form
3. **View** (`buku_dialog.fxml`) → User isi form (judul, pengarang, stok)
4. **Controller** (`BukuDialogController.java`) → Validasi input
5. **Model** (`Buku.java`) → Buat object Buku baru dari input user
6. **DAO** (`BukuDAO.java`) → Jalankan query SQL `INSERT INTO buku ...`
7. **Database** (MySQL) → Simpan data buku baru
8. **Controller** → Tutup dialog, refresh tabel buku
9. **View** → Tampilkan buku baru di tabel

---

## 🔑 FILE PENTING LAINNYA

### 📄 `pom.xml`
**Fungsi:** File konfigurasi Maven

**Isi:**
- 📦 Daftar library yang dipakai (dependencies)
- ⚙️ Konfigurasi build (cara compile dan package)
- 🏷️ Informasi project (nama, versi, deskripsi)

**Library utama:**
- JavaFX (untuk UI)
- MySQL Connector (untuk database)
- BCrypt (untuk hash password)
- iText (untuk PDF)
- Apache POI (untuk Excel)
- JasperReports (untuk laporan)

---

### 📄 `.env`
**Fungsi:** File konfigurasi database (HARUS DIBUAT SENDIRI)

**Contoh isi:**
```env
DB_HOST=localhost
DB_PORT=3306
DB_NAME=perpustakaan_al_asiyah
DB_USER=root
DB_PASSWORD=
```

**PENTING:**
- ⚠️ File ini TIDAK ada di repository (harus dibuat manual)
- 🔐 Jangan di-commit ke Git (berisi password)
- 📋 Sudah disediakan template `.env.example`

---

### 📄 `Main.java`
**Fungsi:** File utama untuk menjalankan aplikasi

**Apa yang dilakukan:**
1. 🚀 Start aplikasi JavaFX
2. 📄 Load file `login.fxml` sebagai halaman awal
3. 🎨 Load file `styles.css` untuk styling
4. 🪟 Buat window aplikasi
5. 📺 Tampilkan halaman login

**Cara menjalankan:**
```bash
mvn javafx:run
```

Atau dari IDE: Klik kanan `Main.java` → Run

---

## 🗺️ ALUR KERJA APLIKASI

### 1️⃣ Saat Aplikasi Dijalankan:
```
Main.java → Load login.fxml → Tampilkan halaman login
```

### 2️⃣ Saat User Login:
```
User input username & password
  ↓
LoginController.handleLogin()
  ↓
UserDAO.login(username, password)
  ↓
Query: SELECT * FROM users WHERE username = ? AND password = ?
  ↓
Jika valid: SessionManager.setCurrentUser(user)
  ↓
Load main.fxml (halaman utama dengan menu)
```

### 3️⃣ Saat User Navigasi ke Menu "Buku":
```
User klik menu Buku
  ↓
MainController.loadPage("buku.fxml")
  ↓
BukuController.initialize()
  ↓
BukuDAO.getAllBuku()
  ↓
Query: SELECT * FROM buku
  ↓
Tampilkan data di tabel
```

### 4️⃣ Saat User Tambah Buku Baru:
```
User klik tombol "Tambah Buku"
  ↓
BukuController.handleTambah()
  ↓
Buka popup buku_dialog.fxml
  ↓
User isi form dan klik "Simpan"
  ↓
BukuDialogController.handleSimpan()
  ↓
Validasi input (tidak boleh kosong)
  ↓
Buat object Buku baru
  ↓
BukuDAO.insert(buku)
  ↓
Query: INSERT INTO buku (...) VALUES (...)
  ↓
Tutup popup, refresh tabel
```

### 5️⃣ Saat User Proses Peminjaman:
```
User klik "Tambah Peminjaman"
  ↓
Buka dialog, pilih peminjam & buku
  ↓
PeminjamanDialogController.handleSimpan()
  ↓
Validasi stok buku (harus > 0)
  ↓
PeminjamanDAO.insert(peminjaman)
  ↓
BEGIN TRANSACTION:
  INSERT INTO peminjaman (...)
  UPDATE buku SET stok = stok - 1 WHERE id = ?
COMMIT
  ↓
Tutup dialog, refresh tabel
```

### 6️⃣ Saat User Proses Pengembalian:
```
User pilih peminjaman yang mau dikembalikan
  ↓
PengembalianController.handleKembali()
  ↓
Hitung denda (jika terlambat)
  ↓
Denda = (hari terlambat) × Rp 1.000
  ↓
PengembalianDAO.insert(pengembalian)
  ↓
BEGIN TRANSACTION:
  INSERT INTO pengembalian (id_peminjaman, tanggal_kembali, denda)
  UPDATE peminjaman SET status = 'dikembalikan' WHERE id = ?
  UPDATE buku SET stok = stok + 1 WHERE id = ?
COMMIT
  ↓
Tampilkan notifikasi (dengan jumlah denda)
  ↓
Refresh tabel
```

---

## 🎓 TIPS MEMAHAMI KODE

### Untuk Pemula:

1. **Mulai dari Main.java**
   - Lihat bagaimana aplikasi dimulai
   - Trace dari Main → LoginController → MainController

2. **Pahami pola MVC**
   - View (FXML) = Tampilan
   - Controller = Logika
   - DAO = Akses database
   - Model = Struktur data

3. **Baca kode per fitur**
   - Contoh: Fitur "Tambah Buku"
   - Baca: `BukuController.java` → `BukuDialogController.java` → `BukuDAO.java`

4. **Lihat database schema**
   - Buka `database/schema.sql`
   - Pahami struktur tabel dan relasi

5. **Test satu fitur**
   - Jalankan aplikasi
   - Coba tambah buku
   - Lihat data di database (pakai MySQL Workbench/phpMyAdmin)
   - Baca kode yang terkait fitur itu

---

## 🆘 FAQ (Pertanyaan Umum)

### ❓ Di mana data disimpan?
📍 Semua data disimpan di **database MySQL**. File-file Java hanya untuk **logika**, bukan penyimpanan data.

### ❓ Bagaimana data dari database tampil di aplikasi?
1. DAO menjalankan query SQL `SELECT`
2. Hasil query dibuat menjadi object Model
3. Controller memasukkan data ke tabel di View (FXML)

### ❓ Kenapa harus pakai DAO? Kenapa tidak langsung query dari Controller?
✅ **Pemisahan tanggung jawab (Separation of Concerns)**:
- Controller fokus ke logika tampilan
- DAO fokus ke database
- Kode lebih rapi dan mudah di-maintain

### ❓ Apa bedanya Controller dengan DAO?
| Controller | DAO |
|------------|-----|
| Logika tampilan | Akses database |
| Handle event (klik tombol) | Jalankan query SQL |
| Validasi input user | Tidak tau tentang UI |
| Panggil DAO untuk ambil/simpan data | Return data ke Controller |

### ❓ Kenapa password di-hash pakai BCrypt?
🔐 **Keamanan!** Jika database bocor, hacker tidak bisa tahu password asli karena sudah di-enkripsi satu arah (tidak bisa dikembalikan).

### ❓ Bagaimana cara aplikasi tahu user siapa yang login?
💾 Pakai `SessionManager` yang menyimpan data user di **memori** saat login berhasil. Saat logout, data dihapus.

### ❓ File mana yang harus diubah jika mau ganti tampilan?
🎨 Ubah file **FXML** (di folder `resources/view/`) dan file **styles.css**. Bisa pakai Scene Builder untuk edit FXML secara visual.

### ❓ File mana yang harus diubah jika mau tambah fitur baru?
1. 📄 Buat/update file View (FXML)
2. 🎮 Buat/update Controller
3. 🗃️ Buat/update DAO (jika butuh akses database)
4. 📦 Buat/update Model (jika ada data baru)
5. 🗄️ Update database schema (jika ada tabel/kolom baru)

---

## ✅ CHECKLIST UNTUK DEVELOPER BARU

Jika Anda baru join project ini, lakukan hal berikut:

- [ ] Clone/download project
- [ ] Install Java 17+ dan Maven
- [ ] Install MySQL dan buat database
- [ ] Import `database/schema.sql`
- [ ] Buat file `.env` (copy dari `.env.example`)
- [ ] Jalankan `mvn clean install`
- [ ] Jalankan `mvn javafx:run`
- [ ] Test login dengan username `admin` password `admin123`
- [ ] Coba semua menu (Dashboard, Buku, Siswa, Guru, Peminjaman, Pengembalian)
- [ ] Baca file-file dokumentasi di folder `docs/`
- [ ] Baca kode per fitur (mulai dari yang sederhana seperti `BukuController`)
- [ ] Coba ubah sesuatu (contoh: ganti warna di `styles.css`)

---

## 📞 KONTAK & BANTUAN

Jika ada pertanyaan atau butuh bantuan memahami kode:

1. 📖 Baca dokumentasi lengkap di `DOKUMENTASI_LENGKAP.md`
2. 🔍 Lihat diagram di folder `docs/`
3. 💬 Tanya senior developer
4. 🐛 Jika ada bug, catat di issue tracker

---

## 🎉 PENUTUP

Dokumen ini dibuat untuk membantu **siapa saja** memahami struktur dan cara kerja aplikasi perpustakaan ini, bahkan yang baru belajar programming.

**Ingat:**
- 📚 Belajar programming butuh **praktek** dan **kesabaran**
- 🔍 Jangan malu **bertanya** jika ada yang tidak paham
- 💡 **Trial and error** adalah bagian dari belajar
- 🚀 Mulai dari yang **sederhana** dulu, jangan langsung yang kompleks

**Selamat belajar dan happy coding!** 🎊

---

📅 **Terakhir diupdate:** 6 Februari 2026
👨‍💻 **Dibuat untuk:** SMK AL-ASIYAH
📝 **Versi:** 1.0.0

