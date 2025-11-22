# UML Use Case Diagram - Sistem Perpustakaan SMK AL-ASIYAH

## Use Case Diagram Lengkap

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    SISTEM PERPUSTAKAAN SMK AL-ASIYAH                    │
│                         USE CASE DIAGRAM                                │
└─────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────┐
│                                                                          │
│  ┌──────────────┐                                                       │
│  │   ADMIN      │                                                       │
│  └──────┬───────┘                                                       │
│         │                                                               │
│         │  ┌──────────────────────────────────────────────┐            │
│         │  │         MANAJEMEN DATA                       │            │
│         │  │                                              │            │
│         ├──▶│  • Kelola Buku (CRUD)                       │            │
│         │  │  • Kelola Data Siswa (CRUD)                  │            │
│         │  │  • Kelola Data Guru (CRUD)                   │            │
│         │  │  • Kelola User (CRUD)                        │            │
│         │  └──────────────────────────────────────────────┘            │
│         │                                                               │
│         │  ┌──────────────────────────────────────────────┐            │
│         │  │         TRANSAKSI                            │            │
│         │  │                                              │            │
│         ├──▶│  • Tambah Peminjaman                         │            │
│         │  │  • Proses Pengembalian                       │            │
│         │  │  • Lihat Riwayat Transaksi                   │            │
│         │  └──────────────────────────────────────────────┘            │
│         │                                                               │
│         │  ┌──────────────────────────────────────────────┐            │
│         │  │         LAPORAN & STATISTIK                 │            │
│         │  │                                              │            │
│         └──▶│  • Lihat Dashboard                          │            │
│            │  • Generate Laporan Harian                  │            │
│            │  • Generate Laporan Mingguan                 │            │
│            │  • Generate Laporan Bulanan                  │            │
│            │  • Export Laporan (PDF/Excel)                │            │
│            └──────────────────────────────────────────────┘            │
│                                                                          │
│  ┌──────────────┐                                                       │
│  │   PETUGAS    │                                                       │
│  └──────┬───────┘                                                       │
│         │                                                               │
│         │  ┌──────────────────────────────────────────────┐            │
│         │  │         TRANSAKSI                            │            │
│         │  │                                              │            │
│         ├──▶│  • Tambah Peminjaman                         │            │
│         │  │  • Proses Pengembalian                       │            │
│         │  │  • Lihat Riwayat Transaksi                   │            │
│         │  └──────────────────────────────────────────────┘            │
│         │                                                               │
│         │  ┌──────────────────────────────────────────────┐            │
│         │  │         LAPORAN & STATISTIK                 │            │
│         │  │                                              │            │
│         └──▶│  • Lihat Dashboard                          │            │
│            │  • Generate Laporan Harian                  │            │
│            │  • Generate Laporan Mingguan                 │            │
│            │  • Generate Laporan Bulanan                  │            │
│            │  • Export Laporan (PDF/Excel)                │            │
│            └──────────────────────────────────────────────┘            │
│                                                                          │
│  ┌──────────────┐                                                       │
│  │   SISTEM     │                                                       │
│  └──────┬───────┘                                                       │
│         │                                                               │
│         │  ┌──────────────────────────────────────────────┐            │
│         └──▶│  • Autentikasi User                          │            │
│            │  • Validasi Stok Buku                        │            │
│            │  • Hitung Denda Otomatis                     │            │
│            │  • Update Stok Otomatis                      │            │
│            │  • Generate Laporan                          │            │
│            └──────────────────────────────────────────────┘            │
│                                                                          │
└─────────────────────────────────────────────────────────────────────────┘
```

## Detail Use Case

### 1. Autentikasi User
- **Actor:** Admin, Petugas
- **Deskripsi:** User melakukan login ke sistem
- **Precondition:** User memiliki akun yang valid
- **Postcondition:** User berhasil login dan session dibuat
- **Flow:**
  1. User memasukkan username dan password
  2. Sistem memvalidasi credentials
  3. Sistem membuat session
  4. User diarahkan ke dashboard

### 2. Kelola Buku (CRUD)
- **Actor:** Admin
- **Deskripsi:** Admin dapat menambah, mengedit, menghapus, dan melihat data buku
- **Use Cases:**
  - Tambah Buku
  - Edit Buku
  - Hapus Buku
  - Lihat Daftar Buku
  - Cari Buku
  - Filter Buku

### 3. Kelola Data Siswa (CRUD)
- **Actor:** Admin
- **Deskripsi:** Admin dapat mengelola data siswa
- **Use Cases:**
  - Tambah Siswa
  - Edit Siswa
  - Hapus Siswa
  - Lihat Daftar Siswa
  - Cari Siswa

### 4. Kelola Data Guru (CRUD)
- **Actor:** Admin
- **Deskripsi:** Admin dapat mengelola data guru
- **Use Cases:**
  - Tambah Guru
  - Edit Guru
  - Hapus Guru
  - Lihat Daftar Guru
  - Cari Guru

### 5. Tambah Peminjaman
- **Actor:** Admin, Petugas
- **Deskripsi:** Menambahkan transaksi peminjaman buku
- **Precondition:** 
  - Buku tersedia (stok > 0)
  - Anggota aktif
- **Postcondition:** 
  - Peminjaman tersimpan
  - Stok buku berkurang
- **Flow:**
  1. Pilih jenis anggota (Siswa/Guru)
  2. Pilih anggota
  3. Pilih buku
  4. Validasi stok
  5. Set tanggal pinjam dan kembali
  6. Simpan peminjaman
  7. Kurangi stok buku

### 6. Proses Pengembalian
- **Actor:** Admin, Petugas
- **Deskripsi:** Memproses pengembalian buku
- **Precondition:** Ada peminjaman dengan status "dipinjam"
- **Postcondition:** 
  - Status peminjaman menjadi "dikembalikan"
  - Stok buku bertambah
  - Denda dihitung jika terlambat
- **Flow:**
  1. Pilih peminjaman
  2. Hitung denda (jika terlambat)
  3. Simpan pengembalian
  4. Update status peminjaman
  5. Tambah stok buku

### 7. Lihat Riwayat Transaksi
- **Actor:** Admin, Petugas
- **Deskripsi:** Melihat riwayat semua transaksi
- **Use Cases:**
  - Filter berdasarkan tanggal
  - Filter berdasarkan status
  - Filter berdasarkan anggota
  - Filter berdasarkan buku

### 8. Lihat Dashboard
- **Actor:** Admin, Petugas
- **Deskripsi:** Melihat statistik dan grafik peminjaman
- **Informasi:**
  - Total buku
  - Buku tersedia
  - Buku dipinjam
  - Total anggota
  - Grafik harian
  - Grafik bulanan
  - Aktivitas terbaru

### 9. Generate Laporan
- **Actor:** Admin, Petugas
- **Deskripsi:** Generate laporan peminjaman
- **Use Cases:**
  - Laporan Harian
  - Laporan Mingguan
  - Laporan Bulanan
  - Export PDF
  - Export Excel

## Relasi Use Case

### Include Relationship
- **Autentikasi User** ←include─ **Semua Use Case**
  - Semua use case memerlukan autentikasi

### Extend Relationship
- **Hitung Denda** ←extend─ **Proses Pengembalian**
  - Denda hanya dihitung jika terlambat

### Generalization
- **Kelola Buku** (general)
  - Tambah Buku (specific)
  - Edit Buku (specific)
  - Hapus Buku (specific)

## Tabel Use Case

| Use Case ID | Use Case Name | Actor | Priority | Complexity |
|-------------|---------------|-------|----------|------------|
| UC-01 | Autentikasi User | Admin, Petugas | High | Low |
| UC-02 | Kelola Buku | Admin | High | Medium |
| UC-03 | Kelola Data Siswa | Admin | High | Medium |
| UC-04 | Kelola Data Guru | Admin | High | Medium |
| UC-05 | Tambah Peminjaman | Admin, Petugas | High | Medium |
| UC-06 | Proses Pengembalian | Admin, Petugas | High | Medium |
| UC-07 | Lihat Riwayat Transaksi | Admin, Petugas | Medium | Low |
| UC-08 | Lihat Dashboard | Admin, Petugas | High | Low |
| UC-09 | Generate Laporan | Admin, Petugas | Medium | High |


