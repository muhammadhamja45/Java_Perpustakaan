# Manual Pengguna - Aplikasi Perpustakaan SMK AL-ASIYAH

## Daftar Isi

1. [Pengenalan](#pengenalan)
2. [Login dan Logout](#login-dan-logout)
3. [Dashboard](#dashboard)
4. [Manajemen Buku](#manajemen-buku)
5. [Data Siswa](#data-siswa)
6. [Data Guru](#data-guru)
7. [Peminjaman Buku](#peminjaman-buku)
8. [Pengembalian Buku](#pengembalian-buku)
9. [Riwayat Transaksi](#riwayat-transaksi)
10. [Laporan](#laporan)

---

## Pengenalan

Aplikasi Manajemen Perpustakaan SMK AL-ASIYAH adalah sistem desktop untuk mengelola operasional perpustakaan sekolah, termasuk manajemen buku, anggota, peminjaman, pengembalian, dan laporan.

### Fitur Utama:
- Manajemen data buku lengkap
- Manajemen data siswa dan guru
- Peminjaman dan pengembalian buku
- Tracking stok buku otomatis
- Perhitungan denda otomatis
- Laporan harian, mingguan, dan bulanan

---

## Login dan Logout

### Login

1. Buka aplikasi
2. Masukkan **Username** dan **Password**
3. Klik tombol **"Login"**

**Default Login:**
- **Admin:** username: `admin`, password: `admin123`
- **Petugas:** username: `petugas`, password: `petugas123`

> ⚠️ **PENTING:** Ganti password default setelah login pertama kali!

### Logout

1. Klik tombol **"Logout"** di bagian bawah sidebar
2. Anda akan dikembalikan ke halaman login

---

## Dashboard

Dashboard menampilkan ringkasan statistik dan aktivitas perpustakaan.

### Statistik yang Ditampilkan:

1. **Total Buku** - Jumlah total buku di perpustakaan
2. **Buku Tersedia** - Jumlah buku yang tersedia untuk dipinjam
3. **Buku Dipinjam** - Jumlah buku yang sedang dipinjam
4. **Total Anggota** - Jumlah total siswa dan guru aktif

### Grafik:

- **Grafik Peminjaman Harian** - Menampilkan trend peminjaman 7 hari terakhir
- **Grafik Peminjaman Bulanan** - Menampilkan trend peminjaman 3 bulan terakhir

### Activity Log:

Menampilkan 10 aktivitas peminjaman terbaru dengan informasi:
- Tanggal peminjaman
- Nama buku dan anggota
- Status peminjaman

---

## Manajemen Buku

### Menambah Buku Baru

1. Klik menu **"Manajemen Buku"** di sidebar
2. Klik tombol **"Tambah Buku"**
3. Isi form:
   - **Kode Buku** * (wajib, harus unik)
   - **Judul** * (wajib)
   - **Pengarang** * (wajib)
   - **Penerbit** * (wajib)
   - **Tahun** (opsional)
   - **Kategori** * (wajib)
   - **Stok** * (wajib, angka)
   - **Lokasi Rak** * (wajib)
4. Klik **"Simpan"**

### Mengedit Buku

1. Di tabel buku, klik tombol **"Edit"** pada baris buku yang ingin diedit
2. Ubah data yang diperlukan
3. Klik **"Simpan"**

> **Catatan:** Kode buku tidak dapat diubah setelah dibuat

### Menghapus Buku

1. Di tabel buku, klik tombol **"Hapus"** pada baris buku yang ingin dihapus
2. Konfirmasi penghapusan
3. Buku akan dihapus dari database

### Mencari Buku

1. Masukkan kata kunci di **search bar** (judul, pengarang, atau kode buku)
2. Klik **"Cari"** atau tekan Enter

### Filter Kategori

1. Pilih kategori dari **dropdown "Filter Kategori"**
2. Klik **"Cari"**
3. Klik **"Refresh"** untuk menampilkan semua buku lagi

---

## Data Siswa

### Menambah Siswa Baru

1. Klik menu **"Data Siswa"** di sidebar
2. Klik tombol **"Tambah Siswa"**
3. Isi form:
   - **NIS** * (wajib, harus unik)
   - **Nama** * (wajib)
   - **Kelas** * (wajib)
   - **Alamat** (opsional)
   - **No. Telp** (opsional)
   - **Status** * (aktif/nonaktif)
4. Klik **"Simpan"**

### Mengedit Data Siswa

1. Klik tombol **"Edit"** pada baris siswa yang ingin diedit
2. Ubah data yang diperlukan
3. Klik **"Simpan"**

> **Catatan:** NIS tidak dapat diubah setelah dibuat

### Menghapus Siswa

1. Klik tombol **"Hapus"** pada baris siswa yang ingin dihapus
2. Konfirmasi penghapusan

### Mencari dan Filter Siswa

- Gunakan **search bar** untuk mencari berdasarkan nama, NIS, atau kelas
- Gunakan **dropdown "Filter Status"** untuk filter berdasarkan status aktif/nonaktif

---

## Data Guru

### Menambah Guru Baru

1. Klik menu **"Data Guru"** di sidebar
2. Klik tombol **"Tambah Guru"**
3. Isi form:
   - **NIP** * (wajib, harus unik)
   - **Nama** * (wajib)
   - **Jabatan** (opsional)
   - **Alamat** (opsional)
   - **No. Telp** (opsional)
   - **Status** * (aktif/nonaktif)
4. Klik **"Simpan"**

### Mengedit dan Menghapus Guru

Proses sama seperti Data Siswa (lihat bagian Data Siswa)

---

## Peminjaman Buku

### Menambah Peminjaman

1. Klik menu **"Peminjaman"** di sidebar
2. Klik tombol **"Tambah Peminjaman"**
3. Isi form:
   - **Jenis Anggota** * - Pilih "Siswa" atau "Guru"
   - **Anggota** * - Pilih anggota dari dropdown (hanya menampilkan yang aktif)
   - **Buku** * - Pilih buku dari dropdown (hanya menampilkan yang stok > 0)
   - **Tanggal Pinjam** * - Default: hari ini
   - **Tanggal Kembali** * - Default: +7 hari dari tanggal pinjam
4. Klik **"Simpan"**

### Validasi Otomatis:

- Sistem akan mengecek stok buku sebelum menyimpan
- Jika stok habis, peminjaman tidak dapat dilakukan
- Stok buku otomatis berkurang setelah peminjaman berhasil

### Catatan:

- Hanya anggota dengan status "aktif" yang dapat meminjam
- Hanya buku dengan stok > 0 yang dapat dipinjam
- Tanggal kembali dapat diubah sesuai kebutuhan

---

## Pengembalian Buku

### Mengembalikan Buku

1. Klik menu **"Pengembalian"** di sidebar
2. Tabel menampilkan semua buku yang sedang dipinjam
3. Klik tombol **"Kembalikan"** pada baris peminjaman yang ingin dikembalikan
4. Sistem akan menampilkan:
   - Informasi buku dan anggota
   - Jumlah hari terlambat (jika ada)
   - Besar denda (jika ada)
5. Konfirmasi pengembalian
6. Klik **"OK"**

### Perhitungan Denda:

- **Denda:** Rp 1.000 per hari keterlambatan
- Denda dihitung dari tanggal kembali yang ditentukan hingga tanggal pengembalian
- Jika tidak terlambat, denda = Rp 0

### Proses Otomatis:

- Status peminjaman berubah menjadi "dikembalikan"
- Stok buku otomatis bertambah +1
- Data pengembalian tersimpan untuk laporan

---

## Riwayat Transaksi

### Melihat Riwayat

1. Klik menu **"Riwayat Transaksi"** di sidebar
2. Tabel menampilkan semua transaksi peminjaman dan pengembalian

### Filter Riwayat:

1. **Search** - Masukkan kata kunci (nama buku atau anggota)
2. **Filter Status** - Pilih "dipinjam" atau "dikembalikan"
3. **Rentang Tanggal** - Pilih tanggal mulai dan akhir
4. Klik **"Filter"** untuk menerapkan filter
5. Klik **"Refresh"** untuk reset filter

### Informasi yang Ditampilkan:

- Tanggal pinjam
- Nama buku
- Nama anggota
- Jenis anggota (Siswa/Guru)
- Tanggal kembali
- Status (dipinjam/dikembalikan)

---

## Laporan

### Membuat Laporan

1. Klik menu **"Laporan"** di sidebar
2. Pilih **Jenis Laporan:**
   - **Harian** - Laporan untuk 1 hari tertentu
   - **Mingguan** - Laporan untuk 1 minggu (Senin-Minggu)
   - **Bulanan** - Laporan untuk 1 bulan penuh
3. Pilih **Tanggal** (untuk harian) atau tanggal di dalam periode (untuk mingguan/bulanan)
4. Pilih **Format Export:** PDF atau Excel
5. Klik **"Generate Laporan"** untuk preview
6. Klik **"Export"** untuk menyimpan file

### Preview Laporan:

Tabel preview menampilkan:
- No
- Tanggal
- Buku
- Anggota
- Status

### Export Laporan:

1. Setelah generate, klik **"Export"**
2. Pilih lokasi penyimpanan
3. File akan disimpan dengan format:
   - PDF: `laporan_YYYYMMDD.pdf`
   - Excel: `laporan_YYYYMMDD.xlsx`

---

## Tips dan Trik

### Shortcut Keyboard:

- **Enter** pada search field = langsung mencari
- **Tab** = berpindah antar field

### Best Practices:

1. **Backup Database** secara berkala
2. **Update Stok** jika ada buku baru masuk
3. **Cek Riwayat** secara berkala untuk tracking
4. **Generate Laporan** setiap akhir bulan untuk arsip

### Troubleshooting:

**Q: Buku tidak muncul di dropdown peminjaman?**
A: Pastikan stok buku > 0 dan buku tidak sedang dipinjam semua

**Q: Anggota tidak muncul di dropdown?**
A: Pastikan status anggota adalah "aktif"

**Q: Laporan tidak muncul?**
A: Pastikan sudah memilih jenis laporan dan tanggal, lalu klik "Generate Laporan"

---

## Dukungan

Jika mengalami masalah atau pertanyaan, hubungi administrator sistem.

---

**Versi:** 1.0.0  
**Terakhir Diupdate:** 2024  
**SMK AL-ASIYAH**

