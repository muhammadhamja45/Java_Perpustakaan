# Cara Mengubah Status Peminjaman Menjadi Dikembalikan

## 📍 Lokasi Fitur

**Menu:** Pengembalian Buku (di sidebar)

## 🔄 Langkah-langkah

### 1. Buka Halaman Pengembalian
- Klik menu **"📥 Pengembalian"** di sidebar kiri
- Halaman akan menampilkan semua buku yang sedang dipinjam (status = "dipinjam")

### 2. Klik Tombol "Kembalikan"
- Di tabel, cari baris peminjaman yang ingin dikembalikan
- Di kolom **"Aksi"** (kolom paling kanan), klik tombol **"📥 Kembalikan"** (tombol hijau)

### 3. Konfirmasi Pengembalian
- Akan muncul dialog konfirmasi yang menampilkan:
  - Nama buku
  - Nama anggota
  - Tanggal kembali yang ditentukan
  - Tanggal dikembalikan (hari ini)
  - Informasi denda (jika terlambat)
- Klik **"OK"** untuk konfirmasi

### 4. Proses Otomatis
Setelah dikonfirmasi, sistem akan otomatis:
1. ✅ **Mengubah status** peminjaman dari "dipinjam" → "dikembalikan"
2. ✅ **Menambah stok** buku +1
3. ✅ **Menyimpan data** pengembalian (termasuk denda jika ada)
4. ✅ **Refresh tabel** - buku yang sudah dikembalikan akan hilang dari daftar

## 💡 Catatan Penting

- **Tombol "Kembalikan"** hanya muncul di halaman **Pengembalian**
- Hanya buku dengan status **"dipinjam"** yang muncul di halaman ini
- Setelah dikembalikan, buku akan hilang dari daftar pengembalian
- Untuk melihat semua transaksi (termasuk yang sudah dikembalikan), gunakan menu **"Riwayat Transaksi"**

## 🔍 Verifikasi

Setelah mengembalikan buku:
1. Buka menu **"Riwayat Transaksi"**
2. Cari peminjaman yang baru saja dikembalikan
3. Status akan berubah menjadi **"dikembalikan"**

---

**Tombol "Kembalikan" ada di kolom "Aksi" di halaman Pengembalian!** 📥

