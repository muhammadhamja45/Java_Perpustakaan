# ERD Database - Sistem Perpustakaan SMK AL-ASIYAH

## Entity Relationship Diagram

```
┌─────────────────────────────────────────────────────────────────┐
│                      DATABASE SCHEMA                             │
└─────────────────────────────────────────────────────────────────┘

┌──────────────┐
│    USERS     │
├──────────────┤
│ id_user (PK) │
│ username     │
│ password     │
│ role         │
│ nama_lengkap │
│ created_at   │
└──────┬───────┘
       │
       │ 1
       │
       │ N
       │
┌──────▼──────────────┐
│   PEMINJAMAN        │
├─────────────────────┤
│ id_pinjam (PK)      │
│ id_user (FK) ───────┼──┐
│ id_buku (FK) ───────┼──┼──┐
│ id_siswa (FK) ──────┼──┼──┼──┐
│ id_guru (FK) ───────┼──┼──┼──┼──┐
│ tgl_pinjam          │  │  │  │  │
│ tgl_kembali         │  │  │  │  │
│ status_pinjam       │  │  │  │  │
└──────┬──────────────┘  │  │  │  │
       │                 │  │  │  │
       │ 1               │  │  │  │
       │                 │  │  │  │
       │ N               │  │  │  │
       │                 │  │  │  │
┌──────▼──────────────┐  │  │  │  │
│  PENGEMBALIAN       │  │  │  │  │
├─────────────────────┤  │  │  │  │
│ id_kembali (PK)     │  │  │  │  │
│ id_pinjam (FK) ─────┘  │  │  │  │
│ tgl_dikembalikan       │  │  │  │
│ denda                  │  │  │  │
└───────────────────────┘  │  │  │  │
                            │  │  │  │
┌──────────────┐            │  │  │  │
│    BUKU      │            │  │  │  │
├──────────────┤            │  │  │  │
│ id_buku (PK) │◄───────────┘  │  │  │
│ kode_buku    │               │  │  │
│ judul        │               │  │  │
│ pengarang    │               │  │  │
│ penerbit     │               │  │  │
│ tahun        │               │  │  │
│ kategori     │               │  │  │
│ stok         │               │  │  │
│ lokasi_rak   │               │  │  │
│ created_at   │               │  │  │
└──────────────┘               │  │  │
                                │  │  │
┌──────────────┐                │  │  │
│    SISWA     │                │  │  │
├──────────────┤                │  │  │
│ id_siswa(PK) │◄───────────────┘  │  │
│ nis          │                   │  │
│ nama         │                   │  │
│ kelas        │                   │  │
│ alamat       │                   │  │
│ no_telp      │                   │  │
│ status       │                   │  │
│ created_at   │                   │  │
└──────────────┘                   │  │
                                    │  │
┌──────────────┐                    │  │
│     GURU     │                    │  │
├──────────────┤                    │  │
│ id_guru (PK) │◄───────────────────┘  │
│ nip          │                      │
│ nama         │                      │
│ jabatan      │                      │
│ alamat       │                      │
│ no_telp      │                      │
│ status       │                      │
│ created_at   │                      │
└──────────────┘                      │
                                       │
┌──────────────┐                       │
│   LAPORAN    │                       │
├──────────────┤                       │
│ id_laporan(PK)                       │
│ periode                              │
│ total_pinjam                         │
│ total_kembali                       │
│ total_denda                          │
│ tanggal_laporan                      │
└──────────────────────────────────────┘
```

## Relasi Antar Tabel

### 1. USERS → PEMINJAMAN
- **Relasi:** One-to-Many
- **Deskripsi:** Satu user (petugas) dapat membuat banyak peminjaman
- **Foreign Key:** `peminjaman.id_user` → `users.id_user`

### 2. BUKU → PEMINJAMAN
- **Relasi:** One-to-Many
- **Deskripsi:** Satu buku dapat dipinjam berkali-kali
- **Foreign Key:** `peminjaman.id_buku` → `buku.id_buku`

### 3. SISWA → PEMINJAMAN
- **Relasi:** One-to-Many (Optional)
- **Deskripsi:** Satu siswa dapat meminjam banyak buku
- **Foreign Key:** `peminjaman.id_siswa` → `siswa.id_siswa` (NULL jika peminjam adalah guru)

### 4. GURU → PEMINJAMAN
- **Relasi:** One-to-Many (Optional)
- **Deskripsi:** Satu guru dapat meminjam banyak buku
- **Foreign Key:** `peminjaman.id_guru` → `guru.id_guru` (NULL jika peminjam adalah siswa)

### 5. PEMINJAMAN → PENGEMBALIAN
- **Relasi:** One-to-One
- **Deskripsi:** Satu peminjaman memiliki satu record pengembalian
- **Foreign Key:** `pengembalian.id_pinjam` → `peminjaman.id_pinjam`

## Atribut Utama

### Tabel USERS
- **Primary Key:** `id_user`
- **Unique:** `username`
- **Constraints:** `role` ENUM('admin', 'petugas')

### Tabel BUKU
- **Primary Key:** `id_buku`
- **Unique:** `kode_buku`
- **Constraints:** `stok` >= 0

### Tabel SISWA
- **Primary Key:** `id_siswa`
- **Unique:** `nis`
- **Constraints:** `status` ENUM('aktif', 'nonaktif')

### Tabel GURU
- **Primary Key:** `id_guru`
- **Unique:** `nip`
- **Constraints:** `status` ENUM('aktif', 'nonaktif')

### Tabel PEMINJAMAN
- **Primary Key:** `id_pinjam`
- **Foreign Keys:** `id_user`, `id_buku`, `id_siswa`, `id_guru`
- **Constraints:** 
  - `id_siswa` dan `id_guru` tidak boleh keduanya NULL
  - `status_pinjam` ENUM('dipinjam', 'dikembalikan')

### Tabel PENGEMBALIAN
- **Primary Key:** `id_kembali`
- **Foreign Key:** `id_pinjam`
- **Constraints:** `denda` >= 0

### Tabel LAPORAN
- **Primary Key:** `id_laporan`
- **Constraints:** `periode` ENUM('harian', 'mingguan', 'bulanan')

