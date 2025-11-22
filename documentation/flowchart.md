# 🗺️ Flowchart Sistem - Aplikasi Perpustakaan

Dokumen ini menyajikan alur proses utama secara detail untuk memastikan tim pengembang, QA, dan operasional memiliki referensi visual yang jelas.

## 1) Alur Login & Otorisasi
![Diagram alur login](images/login.svg)

```mermaid
flowchart TD
    A[Mulai] --> B[Halaman Login]
    B --> C{Input username & password?}
    C -- Kosong --> X1[Info: lengkapi kredensial]
    C -- Terisi --> D[BCrypt verifikasi password]
    D --> E{Valid?}
    E -- Tidak --> X2[Error: kredensial salah]
    E -- Ya --> F[Ambil role & preferensi pengguna]
    F --> G{Role?}
    G -- Admin --> H[Load Dashboard Admin]
    G -- Petugas --> I[Load Dashboard Petugas]
    H --> J[Gunakan fitur sesuai role]
    I --> J
    J --> Z[Monitoring aktivitas & audit trail]
```

## 2) Alur Peminjaman Buku
![Diagram alur peminjaman](images/peminjaman.svg)

```mermaid
flowchart TD
    A[Mulai Peminjaman] --> B[Input anggota & buku]
    B --> C{Anggota aktif?}
    C -- Tidak --> X1[Blokir & minta aktivasi]
    C -- Ya --> D{Stok buku tersedia?}
    D -- Tidak --> X2[Informasikan stok habis]
    D -- Ya --> E[Hitung batas pinjam & tanggal kembali]
    E --> F[Catat transaksi pinjam]
    F --> G[Kurangi stok buku]
    G --> H{Butuh bukti cetak?}
    H -- Ya --> I[Generate bukti peminjaman]
    H -- Tidak --> J[Update dashboard & riwayat]
    I --> J
    J --> Z[Selesai]
```

## 3) Alur Pengembalian & Perhitungan Denda
![Diagram alur pengembalian](images/pengembalian.svg)

```mermaid
flowchart TD
    A[Mulai Pengembalian] --> B[Pilih transaksi aktif]
    B --> C[Ambil tanggal jatuh tempo]
    C --> D[Hitung keterlambatan]
    D --> E{Terlambat?}
    E -- Tidak --> F[Set denda = 0]
    E -- Ya --> G[Hitung denda otomatis]
    F --> H[Update status transaksi]
    G --> H
    H --> I[Kembalikan stok buku]
    I --> J{Terima pembayaran denda?}
    J -- Ya --> K[Catat pembayaran & resi]
    J -- Tidak --> L[Tagih kemudian / simpan piutang]
    K --> M[Perbarui dashboard & laporan]
    L --> M
    M --> Z[Selesai]
```

## 4) Alur Generasi Laporan (PDF/Excel)
![Diagram alur laporan](images/laporan.svg)

```mermaid
flowchart TD
    A[Mulai Laporan] --> B[Pilih rentang tanggal & tipe laporan]
    B --> C[Validasi filter & akses role]
    C --> D[Query data transaksi]
    D --> E{Data ditemukan?}
    E -- Tidak --> X1[Info: tidak ada data]
    E -- Ya --> F{Format?}
    F -- PDF --> G[JasperReports render PDF]
    F -- Excel --> H[Apache POI generate XLSX]
    G --> I[Simbolkan nama file & lokasi arsip]
    H --> I
    I --> J[Audit trail (pengunduh, waktu, filter)]
    J --> Z[Selesai]
```

## Catatan Implementasi
- **Validasi stok & status anggota** berada di layer service untuk menjaga integritas data sebelum akses database.
- **Perhitungan denda** menggunakan tanggal server agar konsisten dan anti-manipulasi waktu klien.
- **Audit trail** disarankan untuk login, generate laporan, dan transaksi pembayaran denda.
- **Cetak bukti** opsional namun direkomendasikan untuk transaksi fisik (tersimpan juga sebagai arsip digital).
