# 🚀 Panduan NetBeans untuk Aplikasi Perpustakaan

Panduan ini membantu Anda mengimpor, mengonfigurasi, dan menjalankan proyek JavaFX Maven di NetBeans dengan setup yang konsisten dengan dokumentasi modern.

## 📦 Prasyarat
- **JDK 17+** terpasang dan sudah dikonfigurasi di NetBeans (Tools ▸ Java Platforms).
- **Apache NetBeans 18+** (mendukung JavaFX Maven).
- **MySQL 8+** berjalan dan dapat diakses.
- File `database/schema.sql` sudah diimpor ke database target.
- File `.env` di root proyek berisi kredensial database (HOST, PORT, NAME, USER, PASSWORD).

## 🛠️ Impor Proyek ke NetBeans
1. Buka **File ▸ Open Project…** lalu pilih folder root `Java_Perpustakaan` (Maven akan terdeteksi otomatis).
2. Pastikan profil **Maven** aktif dan proyek berhasil diindeks (status bar selesai tanpa error dependency).
3. Jika dependensi belum terunduh, klik kanan proyek ▸ **Build** (setara `mvn clean install`).

## ⚙️ Konfigurasi Run JavaFX
1. Klik kanan proyek ▸ **Properties**.
2. Masuk ke **Run**:
   - **VM Options:** `--module-path "$(JAVA_FX_LIB)" --add-modules javafx.controls,javafx.fxml`
   - **Working Directory:** arahkan ke folder root proyek.
3. Pastikan **Main Class** menunjuk ke kelas launcher JavaFX (mis. `com.alasiyah.library.App`).
4. Simpan konfigurasi, lalu coba **Run**. Jika class tidak ditemukan, lakukan **Clean and Build** dulu.

## 🌐 Konfigurasi Database
1. Perbarui `.env` di root sesuai host MySQL lokal/server.
2. Verifikasi koneksi:
   - Jalankan aplikasi (Run) dan pastikan log tidak menampilkan error koneksi JDBC.
   - Atau gunakan **Services ▸ Databases** di NetBeans untuk membuat koneksi MySQL dengan kredensial yang sama; coba `Test Connection`.
3. Jika port MySQL berbeda, sesuaikan di `.env` dan `database/schema.sql` bila diperlukan.

## ▶️ Menjalankan & Debug
- **Run:** Klik tombol ▶️ atau F6 (menggunakan konfigurasi JavaFX di atas).
- **Debug:** Klik kanan proyek ▸ **Debug**. Set breakpoint pada controller atau service untuk memeriksa alur peminjaman/pengembalian.
- **Arguments:** Gunakan **Project Properties ▸ Run ▸ Arguments** untuk menambahkan argumen runtime jika diperlukan (mis. profil environment).

## 📄 Membuat Artefak
- Klik kanan proyek ▸ **Clean and Build** untuk menghasilkan artefak di folder `target/`.
- File JAR dapat dijalankan dengan: `java -jar target/<nama-artifact>.jar` (pastikan module path JavaFX tersedia atau gunakan jlink bila dikemas).

## 🧭 Troubleshooting
- **Error JavaFX module path:** pastikan variabel `JAVA_FX_LIB` mengarah ke direktori `lib` JavaFX SDK yang kompatibel dengan OS Anda.
- **Dependency tidak terunduh:** cek koneksi internet, lalu ulangi **Build** atau jalankan `mvn dependency:resolve` dari NetBeans Terminal.
- **Gagal koneksi database:** pastikan `.env` benar, layanan MySQL aktif, dan user memiliki hak akses yang cukup.
- **UI blank atau FXML error:** bersihkan cache (`Clean and Build`), lalu periksa path FXML di controller.

## 📌 Tips Produktivitas
- Aktifkan **Save Actions** (Tools ▸ Options ▸ Editor ▸ On Save) untuk auto-format dan organize imports.
- Gunakan **Tasks** (Window ▸ Action Items) untuk melacak TODO/FIXME dalam kode.
- Manfaatkan **Git** bawaan NetBeans untuk commit kecil dengan pesan yang jelas sebelum push.
