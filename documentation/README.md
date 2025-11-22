# 📚 Dokumentasi Modern - Aplikasi Manajemen Perpustakaan

Repositori ini memiliki folder `documentation/` sebagai pusat dokumentasi baru yang ringkas, modern, dan profesional untuk memandu tim dalam memahami dan mengoperasikan aplikasi.

## 🌟 Ringkasan Proyek
- **Nama Aplikasi:** Sistem Manajemen Perpustakaan SMK AL-ASIYAH
- **Platform:** JavaFX Desktop
- **Database:** MySQL 8+
- **Build Tool:** Maven (Java 17+)
- **Fokus:** Manajemen buku, anggota, peminjaman/pengembalian, laporan, dan keamanan data.

## 🧭 Peta Dokumentasi
- [`flowchart.md`](flowchart.md) — Alur proses end-to-end dan skenario kritis.
- [`netbeans.md`](netbeans.md) — Panduan import, konfigurasi run, dan debug di NetBeans.
- [Dokumentasi Lama](../docs) — Tetap dapat digunakan sebagai referensi detail (ERD, UML, dsb).

## 🔑 Fitur Utama
- Autentikasi multi-peran (Admin & Petugas) dengan BCrypt hashing.
- Dashboard statistik dan grafik peminjaman.
- CRUD Buku & Anggota dengan pencarian, filter, dan sorting.
- Peminjaman & Pengembalian dengan validasi stok dan perhitungan denda otomatis.
- Laporan (harian/mingguan/bulanan) ke PDF/Excel.

## 🏗️ Arsitektur Aplikasi
- **UI Layer:** JavaFX (FXML) + Controller untuk interaksi pengguna.
- **Service Layer:** Validasi bisnis, perhitungan denda, pengelolaan transaksi.
- **Repository/DAO:** Akses MySQL melalui konfigurasi `.env` (DB host, port, nama, user, password).
- **Security:** Hashing password menggunakan BCrypt; otorisasi berbasis role.
- **Reporting:** JasperReports untuk PDF, Apache POI untuk Excel.

## 🚀 Alur Kerja Utama (Ringkasan)
1. **Login:** Pengguna memasukkan kredensial → sistem memverifikasi hash BCrypt → memuat role dan preferensi.
2. **Dashboard:** Menampilkan statistik (stok buku, transaksi aktif, denda) dan grafik tren peminjaman.
3. **Manajemen Data:** CRUD buku & anggota dengan pencarian dan filter.
4. **Peminjaman:** Validasi stok → catat transaksi → kurangi stok → cetak bukti bila diperlukan.
5. **Pengembalian:** Hitung denda (jika terlambat) → update status transaksi → kembalikan stok → simpan histori.
6. **Laporan:** Pilih rentang waktu → generate PDF/Excel → arsip di penyimpanan aman.

## ⚙️ Setup Singkat
1. Pastikan **Java 17+** dan **MySQL 8+** terpasang.
2. Import `database/schema.sql` ke MySQL.
3. Buat `.env` di root project dengan kredensial database.
4. Jalankan: `mvn clean install && mvn javafx:run`.

## 🔒 Keamanan & Kepatuhan
- Gunakan password kuat untuk akun MySQL dan user aplikasi; segera ubah password default.
- Batasi hak akses database sesuai kebutuhan aplikasi.
- Backup database rutin sebelum dan sesudah pembaruan versi.

## 🛠️ Operasional & Maintenance
- **Monitoring:** Pantau log aplikasi dan performa query MySQL.
- **Backup:** Jadwalkan backup harian ke lokasi aman.
- **Recovery:** Simpan skrip `database/schema.sql` dan file konfigurasi `.env` untuk disaster recovery.
- **Update:** Jalankan `mvn clean install` setelah menarik perubahan untuk memastikan dependensi terkini.

## 🧪 Validasi Cepat
- Login dengan role Admin dan Petugas untuk memastikan hak akses.
- Simulasikan peminjaman → pengembalian terlambat untuk memverifikasi perhitungan denda.
- Generate laporan PDF dan Excel untuk mengecek integritas data.

## 📞 Dukungan
Untuk pertanyaan lebih lanjut, hubungi tim pengembang atau lihat dokumentasi lama pada folder `docs/`.
