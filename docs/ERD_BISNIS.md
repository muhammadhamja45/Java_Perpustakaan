# ERD Bisnis - Sistem Perpustakaan SMK AL-ASIYAH

## Diagram Alur Bisnis

```
┌─────────────────────────────────────────────────────────────────┐
│                    SISTEM PERPUSTAKAAN SMK AL-ASIYAH            │
└─────────────────────────────────────────────────────────────────┘
                              │
                              ▼
                    ┌─────────────────┐
                    │   LOGIN SYSTEM  │
                    │  (Admin/Petugas)│
                    └────────┬────────┘
                              │
                    ┌─────────┴─────────┐
                    │                   │
              ┌─────▼─────┐      ┌─────▼─────┐
              │   ADMIN   │      │  PETUGAS  │
              └─────┬─────┘      └─────┬─────┘
                    │                   │
        ┌───────────┴───────────┐       │
        │                       │       │
┌───────▼────────┐    ┌────────▼──────┐│
│ MANAJEMEN DATA │    │  TRANSAKSI     ││
└───────┬────────┘    └────────┬───────┘│
        │                      │        │
        │                      │        │
┌───────┴────────┐    ┌────────┴───────┐│
│ • Buku         │    │ • Peminjaman   ││
│ • Siswa        │    │ • Pengembalian ││
│ • Guru         │    │ • Riwayat      ││
│ • Users        │    │ • Laporan      ││
└────────────────┘    └────────────────┘│
                              │          │
                              └──────────┘
```

## Proses Bisnis Utama

### 1. Proses Autentikasi
```
User → Login → Validasi Credentials → Set Session → Dashboard
```

### 2. Proses Manajemen Buku
```
Admin/Petugas → Tambah/Edit/Hapus Buku → Validasi Data → Simpan ke Database
```

### 3. Proses Manajemen Anggota
```
Admin/Petugas → Tambah/Edit/Hapus Siswa/Guru → Validasi Data → Simpan ke Database
```

### 4. Proses Peminjaman
```
Petugas → Pilih Anggota → Pilih Buku → Validasi Stok → Set Tanggal Kembali → Simpan Peminjaman → Kurangi Stok
```

### 5. Proses Pengembalian
```
Petugas → Pilih Peminjaman → Hitung Denda (jika terlambat) → Update Status → Tambah Stok → Simpan Pengembalian
```

### 6. Proses Laporan
```
Admin/Petugas → Pilih Periode → Generate Laporan → Export PDF/Excel
```

## Aktor (Actor)

1. **Administrator**
   - Mengelola semua data (Buku, Siswa, Guru, Users)
   - Mengakses semua fitur
   - Generate laporan

2. **Petugas Perpustakaan**
   - Mengelola transaksi (Peminjaman, Pengembalian)
   - Melihat riwayat transaksi
   - Generate laporan

## Use Case Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                        USE CASES                             │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│  ┌──────────┐         ┌──────────────────┐                     │
│  │ Admin    │────────▶│ Manage Books    │                     │
│  └──────────┘         └──────────────────┘                     │
│         │             ┌──────────────────┐                     │
│         │─────────────▶│ Manage Students  │                     │
│         │             └──────────────────┘                     │
│         │             ┌──────────────────┐                     │
│         │─────────────▶│ Manage Teachers │                     │
│         │             └──────────────────┘                     │
│         │             ┌──────────────────┐                     │
│         └─────────────▶│ Manage Users    │                     │
│                       └──────────────────┘                     │
│                                                               │
│  ┌──────────┐         ┌──────────────────┐                     │
│  │ Petugas  │────────▶│ Borrow Book      │                     │
│  └──────────┘         └──────────────────┘                     │
│         │             ┌──────────────────┐                     │
│         │─────────────▶│ Return Book      │                     │
│         │             └──────────────────┘                     │
│         │             ┌──────────────────┐                     │
│         │─────────────▶│ View History     │                     │
│         │             └──────────────────┘                     │
│         │             ┌──────────────────┐                     │
│         └─────────────▶│ Generate Report  │                     │
│                       └──────────────────┘                     │
│                                                               │
└─────────────────────────────────────────────────────────────┘
```

## Business Rules

1. **Aturan Peminjaman:**
   - Stok buku harus > 0 untuk bisa dipinjam
   - Tanggal kembali otomatis +7 hari dari tanggal pinjam
   - Satu anggota hanya bisa meminjam jika tidak ada denda yang belum dibayar

2. **Aturan Pengembalian:**
   - Denda dihitung jika terlambat: Rp 1.000 per hari
   - Stok otomatis bertambah saat pengembalian
   - Status peminjaman berubah menjadi "dikembalikan"

3. **Aturan Akses:**
   - Admin: Full access
   - Petugas: Hanya transaksi dan laporan

4. **Aturan Data:**
   - NIS dan NIP harus unique
   - Kode buku harus unique
   - Status anggota: aktif/nonaktif

