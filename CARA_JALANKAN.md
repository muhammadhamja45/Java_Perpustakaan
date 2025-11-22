# Cara Menjalankan Aplikasi (Windows)

## ⚠️ Masalah: Maven tidak terinstall

Jika muncul error `'mvn' is not recognized`, pilih salah satu solusi di bawah:

---

## ✅ Solusi 1: Install Maven (Paling Direkomendasikan)

### Download dan Install Maven:

1. **Download Maven:**
   - Buka: https://maven.apache.org/download.cgi
   - Download: `apache-maven-3.9.x-bin.zip` (versi terbaru)

2. **Extract Maven:**
   - Extract ke folder, contoh: `C:\Program Files\Apache\maven`
   - Atau: `C:\apache-maven-3.9.6`

3. **Tambahkan ke PATH:**
   - Tekan `Win + R` → ketik `sysdm.cpl` → Enter
   - Tab **"Advanced"** → Klik **"Environment Variables"**
   - Di **"System variables"**, pilih **"Path"** → Klik **"Edit"**
   - Klik **"New"** → Tambahkan path Maven:
     ```
     C:\Program Files\Apache\maven\bin
     ```
     (Sesuaikan dengan lokasi Maven Anda)
   - Klik **OK** di semua dialog

4. **Restart Terminal:**
   - Tutup semua terminal/command prompt
   - Buka terminal baru

5. **Verifikasi:**
   ```bash
   mvn -version
   ```
   Jika muncul versi Maven, berarti berhasil!

6. **Jalankan Aplikasi:**
   ```bash
   mvn clean install
   mvn javafx:run
   ```

---

## ✅ Solusi 2: Menggunakan VSCode Java Extension (Tidak Perlu Install Maven)

### Langkah 1: Install Extension

1. Di VSCode, tekan `Ctrl+Shift+X`
2. Cari: **"Extension Pack for Java"** (oleh Microsoft)
3. Klik **Install**

### Langkah 2: Setup

1. Tekan `Ctrl+Shift+P`
2. Ketik: **"Java: Clean Java Language Server Workspace"**
3. Pilih dan tunggu reload selesai

### Langkah 3: Jalankan

**Cara A - Run Button:**
1. Buka file: `src/main/java/com/smk/alasiyah/perpustakaan/Main.java`
2. Klik tombol **"Run"** (ikon play hijau) di atas method `main()`

**Cara B - Command Palette:**
1. Tekan `Ctrl+Shift+P`
2. Ketik: **"Java: Run"**
3. Pilih `Main.java`

**Cara C - Terminal VSCode:**
1. Buka terminal: `Ctrl+``
2. VSCode akan otomatis menggunakan Maven yang terintegrasi
3. Jalankan:
   ```bash
   mvn clean install
   mvn javafx:run
   ```

---

## ✅ Solusi 3: Menggunakan Maven Wrapper (Alternatif)

Jika sudah ada file `mvnw.cmd`, gunakan:

```bash
# Build
.\mvnw.cmd clean install

# Jalankan
.\mvnw.cmd javafx:run
```

---

## 📝 Checklist Sebelum Menjalankan

Pastikan sudah:

- [ ] File `.env` sudah dibuat di root folder
- [ ] Database MySQL sudah di-import (`database/schema.sql`)
- [ ] Java Extension Pack sudah terinstall (jika pakai Solusi 2)
- [ ] Maven sudah terinstall dan di PATH (jika pakai Solusi 1)

---

## 🎯 Quick Start (Pilih Salah Satu)

### Opsi A: Dengan Maven (setelah install)
```bash
mvn clean install
mvn javafx:run
```

### Opsi B: Dengan VSCode Extension
1. Install "Extension Pack for Java"
2. Klik Run di `Main.java`

### Opsi C: Dengan Maven Wrapper
```bash
.\mvnw.cmd clean install
.\mvnw.cmd javafx:run
```

---

## 🔧 Troubleshooting

### Error: "JAVA_HOME not found"
- Set JAVA_HOME di Environment Variables:
  ```
  JAVA_HOME=C:\Users\Lenovo\Downloads\jdk-17.0.12_windows-x64_bin\jdk-17.0.12
  ```

### Error: "Maven project not found"
- Pastikan file `pom.xml` ada di root folder
- Reload workspace: `Ctrl+Shift+P` → "Java: Clean Java Language Server Workspace"

### Error: "Cannot resolve dependencies"
- Tunggu Maven download dependencies (bisa beberapa menit)
- Periksa koneksi internet

---

**Pilih solusi yang paling mudah untuk Anda!** 🚀



