# Alur Program (Algorithm) - Sistem Perpustakaan SMK AL-ASIYAH

## 1. Alur Login

```
START
  │
  ▼
[Input Username & Password]
  │
  ▼
[Validasi Input Kosong?]
  │
  ├─ Ya → [Tampilkan Error] → END
  │
  └─ Tidak
      │
      ▼
[Query Database: SELECT * FROM users WHERE username = ?]
  │
  ▼
[Password Match?]
  │
  ├─ Tidak → [Tampilkan Error] → END
  │
  └─ Ya
      │
      ▼
[Set Session User]
  │
  ▼
[Load Main View]
  │
  ▼
END
```

## 2. Alur Peminjaman Buku

```
START
  │
  ▼
[Pilih Jenis Anggota (Siswa/Guru)]
  │
  ▼
[Load Daftar Anggota Aktif]
  │
  ▼
[Pilih Anggota]
  │
  ▼
[Pilih Buku]
  │
  ▼
[Validasi Stok > 0?]
  │
  ├─ Tidak → [Tampilkan Error: Stok Habis] → END
  │
  └─ Ya
      │
      ▼
[Set Tanggal Pinjam = Hari Ini]
  │
  ▼
[Set Tanggal Kembali = Hari Ini + 7 Hari]
  │
  ▼
[Insert ke tabel PEMINJAMAN]
  │
  ▼
[Update Stok Buku: stok = stok - 1]
  │
  ▼
[Refresh Tabel Peminjaman]
  │
  ▼
END
```

## 3. Alur Pengembalian Buku

```
START
  │
  ▼
[Load Daftar Peminjaman dengan Status 'dipinjam']
  │
  ▼
[Pilih Peminjaman]
  │
  ▼
[Hitung Selisih Hari: tgl_kembali - tgl_sekarang]
  │
  ▼
[Terlambat?]
  │
  ├─ Ya → [Hitung Denda: hari_terlambat × 1000]
  │
  └─ Tidak → [Denda = 0]
      │
      ▼
[Konfirmasi Pengembalian]
  │
  ├─ Batal → END
  │
  └─ OK
      │
      ▼
[Insert ke tabel PENGEMBALIAN]
  │
  ▼
[Update Status PEMINJAMAN: 'dipinjam' → 'dikembalikan']
  │
  ▼
[Update Stok Buku: stok = stok + 1]
  │
  ▼
[Refresh Tabel]
  │
  ▼
END
```

## 4. Alur CRUD Buku

### Tambah Buku
```
START
  │
  ▼
[Input Data Buku]
  │
  ▼
[Validasi: Kode Buku Unique?]
  │
  ├─ Tidak → [Error: Kode sudah ada] → END
  │
  └─ Ya
      │
      ▼
[Validasi: Semua Field Wajib Terisi?]
  │
  ├─ Tidak → [Error: Field wajib kosong] → END
  │
  └─ Ya
      │
      ▼
[INSERT INTO buku]
  │
  ▼
[Refresh Tabel]
  │
  ▼
END
```

### Edit Buku
```
START
  │
  ▼
[Pilih Buku dari Tabel]
  │
  ▼
[Load Data ke Form]
  │
  ▼
[Edit Data]
  │
  ▼
[Validasi Data]
  │
  ▼
[UPDATE buku SET ... WHERE id_buku = ?]
  │
  ▼
[Refresh Tabel]
  │
  ▼
END
```

### Hapus Buku
```
START
  │
  ▼
[Pilih Buku dari Tabel]
  │
  ▼
[Konfirmasi Hapus]
  │
  ├─ Batal → END
  │
  └─ OK
      │
      ▼
[Validasi: Ada Peminjaman Aktif?]
  │
  ├─ Ya → [Error: Buku sedang dipinjam] → END
  │
  └─ Tidak
      │
      ▼
[DELETE FROM buku WHERE id_buku = ?]
  │
  ▼
[Refresh Tabel]
  │
  ▼
END
```

## 5. Alur Generate Laporan

```
START
  │
  ▼
[Pilih Jenis Laporan (Harian/Mingguan/Bulanan)]
  │
  ▼
[Pilih Tanggal/Periode]
  │
  ▼
[Pilih Format Export (PDF/Excel)]
  │
  ▼
[Query Data Berdasarkan Periode]
  │
  ▼
[Generate Laporan]
  │
  ▼
[Export ke File]
  │
  ▼
[Simpan ke tabel LAPORAN]
  │
  ▼
END
```

## 6. Alur Dashboard

```
START
  │
  ▼
[Load Statistik: Total Buku]
  │
  ▼
[Load Statistik: Buku Tersedia]
  │
  ▼
[Load Statistik: Buku Dipinjam]
  │
  ▼
[Load Statistik: Total Anggota]
  │
  ▼
[Load Data Grafik Harian (7 hari terakhir)]
  │
  ▼
[Load Data Grafik Bulanan (3 bulan terakhir)]
  │
  ▼
[Load Aktivitas Terbaru (10 transaksi terakhir)]
  │
  ▼
[Render Dashboard]
  │
  ▼
END
```

## 7. Alur Pencarian & Filter

```
START
  │
  ▼
[Input Keyword/Filter]
  │
  ▼
[Validasi Input?]
  │
  ├─ Kosong → [Load Semua Data]
  │
  └─ Ada Input
      │
      ▼
[Query dengan WHERE clause]
  │
  ▼
[Filter Data]
  │
  ▼
[Update Tabel]
  │
  ▼
END
```

## Pseudocode Utama

### Login
```pseudocode
FUNCTION login(username, password):
    IF username IS EMPTY OR password IS EMPTY:
        RETURN error("Username dan password harus diisi")
    
    user = queryDatabase("SELECT * FROM users WHERE username = ?", username)
    
    IF user IS NULL:
        RETURN error("Username tidak ditemukan")
    
    IF password != user.password:  // Plain text comparison
        RETURN error("Password salah")
    
    SET session.currentUser = user
    LOAD mainView()
    RETURN success
END FUNCTION
```

### Peminjaman
```pseudocode
FUNCTION createPeminjaman(anggotaId, bukuId, jenisAnggota):
    buku = getBukuById(bukuId)
    
    IF buku.stok <= 0:
        RETURN error("Stok buku habis")
    
    tglPinjam = TODAY()
    tglKembali = TODAY() + 7 DAYS
    
    INSERT INTO peminjaman (
        id_user, id_buku, id_siswa, id_guru,
        tgl_pinjam, tgl_kembali, status_pinjam
    ) VALUES (
        session.userId, bukuId, 
        IF jenisAnggota == 'Siswa' THEN anggotaId ELSE NULL,
        IF jenisAnggota == 'Guru' THEN anggotaId ELSE NULL,
        tglPinjam, tglKembali, 'dipinjam'
    )
    
    UPDATE buku SET stok = stok - 1 WHERE id_buku = bukuId
    
    RETURN success
END FUNCTION
```

### Pengembalian
```pseudocode
FUNCTION returnBook(peminjamanId):
    peminjaman = getPeminjamanById(peminjamanId)
    
    tglSekarang = TODAY()
    hariTerlambat = MAX(0, tglSekarang - peminjaman.tglKembali)
    denda = hariTerlambat * 1000
    
    INSERT INTO pengembalian (
        id_pinjam, tgl_dikembalikan, denda
    ) VALUES (peminjamanId, tglSekarang, denda)
    
    UPDATE peminjaman 
    SET status_pinjam = 'dikembalikan' 
    WHERE id_pinjam = peminjamanId
    
    UPDATE buku 
    SET stok = stok + 1 
    WHERE id_buku = peminjaman.idBuku
    
    RETURN success
END FUNCTION
```

