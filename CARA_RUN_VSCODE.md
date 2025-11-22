# Cara Menjalankan Aplikasi di VSCode

## ⚠️ PENTING: Jangan Klik Run Button Langsung!

VSCode Java Extension akan mencoba menjalankan aplikasi langsung tanpa Maven, yang menyebabkan error **"JavaFX runtime components are missing"**.

## ✅ Cara Benar: Gunakan Maven

### Metode 1: Terminal VSCode (PALING MUDAH)

1. Buka terminal di VSCode: `Ctrl+`` (backtick)
2. Pastikan di root folder project
3. Jalankan:
   ```bash
   mvn javafx:run
   ```

**Ini adalah cara yang PALING AMAN dan DIREKOMENDASIKAN!**

---

### Metode 2: Task Runner di VSCode

1. Tekan `Ctrl+Shift+P`
2. Ketik: **"Tasks: Run Task"**
3. Pilih: **"maven-javafx-run"**

---

### Metode 3: Debug/Run Configuration

1. Tekan `F5` atau klik "Run and Debug" di sidebar
2. Pilih: **"Launch Perpustakaan App (via Maven)"**
3. Klik Run

---

## 🚫 JANGAN Gunakan:

- ❌ Klik tombol "Run" langsung di `Main.java`
- ❌ Run via "Run Java" di Command Palette
- ❌ Menjalankan langsung dengan `java` command

Semua cara di atas akan error karena JavaFX modules tidak ditemukan!

---

## ✅ Quick Start

```bash
# Di terminal VSCode, cukup jalankan:
mvn javafx:run
```

**Selesai!** Aplikasi akan otomatis:
1. Compile source code
2. Download dependencies (jika belum)
3. Menjalankan aplikasi JavaFX

---

## 🔧 Troubleshooting

### Error: "mvn is not recognized"
- Install Maven dan tambahkan ke PATH
- Atau gunakan Java Extension Pack (akan handle Maven otomatis)

### Error: "JavaFX runtime components are missing"
- **JANGAN** run langsung dengan Java
- **GUNAKAN** `mvn javafx:run` di terminal

### Aplikasi tidak muncul
- Periksa file `.env` sudah dibuat
- Pastikan database MySQL sudah di-import
- Periksa koneksi database di `DatabaseConfig.java`

---

## 📝 Checklist

Sebelum menjalankan, pastikan:
- [ ] File `.env` sudah dibuat dengan konfigurasi database
- [ ] Database MySQL sudah di-import
- [ ] Maven sudah terinstall atau Java Extension Pack terinstall
- [ ] Menggunakan `mvn javafx:run` di terminal (BUKAN run langsung)

---

**Ingat: Selalu gunakan `mvn javafx:run` di terminal!** 🚀

