# 📋 Panduan Menambahkan Logo Sekolah ke Laporan

## ✅ Update yang Sudah Dilakukan:

### 1. **Folder images sudah dibuat** ✓
Lokasi: `src/main/resources/images/`

### 2. **Code sudah diupdate** ✓
- Logo akan muncul di header laporan PDF
- Tanggal dihapus dari kolom Admin Perpustakaan
- Tanggal hanya muncul di kolom Kepala Sekolah

---

## 📸 Cara Menambahkan Logo:

### Langkah 1: Simpan Logo
Simpan file logo yang Anda berikan dengan nama:
```
logo_sekolah.png
```

**Lokasi file:**
```
C:\kuliah\kkp_app\app_perpustkaan\src\main\resources\images\logo_sekolah.png
```

### Langkah 2: Copy File Logo
1. Download/simpan gambar logo sekolah
2. Buka folder: `C:\kuliah\kkp_app\app_perpustkaan\src\main\resources\images\`
3. Paste file logo ke folder tersebut
4. Pastikan nama file: `logo_sekolah.png`

### Langkah 3: Rebuild Project
```bash
mvn clean compile
```

### Langkah 4: Test
Generate laporan dan cek apakah logo muncul!

---

## 🎨 Layout PDF Baru:

```
┌────────────────────────────────────────────────────┐
│                                                    │
│              [LOGO SEKOLAH AL-ASIYAH]             │
│                    (80x80 px)                      │
│                                                    │
│                  SMK AL-ASIYAH                     │
│             Perpustakaan SMK AL-ASIYAH             │
│                 Bogor, Jawa Barat                  │
│   _______________________________________________  │
│                                                    │
│        LAPORAN PEMINJAMAN BUKU                     │
│                                                    │
│  [... Tabel Data ...]                             │
│                                                    │
│  Total: XX transaksi                              │
│                                                    │
│  ┌────────────────────┬─────────────────────┐    │
│  │                    │                     │    │
│  │ Admin              │ Bogor, 07 Des 2025  │    │
│  │ Perpustakaan       │ Kepala Sekolah      │    │
│  │                    │                     │    │
│  │                    │                     │    │
│  │ __________________ │ ___________________ │    │
│  │ ( .............. ) │ ( .............. )  │    │
│  │ NIP: ............ │ NIP: ............ │    │
│  └────────────────────┴─────────────────────┘    │
└────────────────────────────────────────────────────┘
```

---

## 🔧 Detail Perubahan:

### PDF Header:
✅ **Logo Sekolah**
- Ukuran: 80x80 pixels (auto-scaled)
- Posisi: Center aligned
- Format: PNG (recommended)
- Fallback: Emoji 📚 (jika logo tidak ditemukan)

### Signature Section:
✅ **Kolom Kiri - Admin Perpustakaan**
- ❌ Tanggal **DIHAPUS**
- ✅ Label "Admin Perpustakaan" (biru bold)
- ✅ Ruang tanda tangan
- ✅ Nama dan NIP

✅ **Kolom Kanan - Kepala Sekolah**
- ✅ Tanggal **TETAP ADA** (Bogor, [tanggal])
- ✅ Label "Kepala Sekolah" (biru bold)
- ✅ Ruang tanda tangan
- ✅ Nama dan NIP

---

## 📝 Format Logo yang Disarankan:

### Spesifikasi File:
- **Format**: PNG (dengan background transparan)
- **Ukuran**: 500x500 px atau lebih (akan di-scale otomatis)
- **Nama File**: `logo_sekolah.png` (huruf kecil semua)
- **Lokasi**: `src/main/resources/images/`

### Alternative Formats:
Jika PNG tidak tersedia, bisa gunakan:
- JPG/JPEG
- GIF

Tapi update nama file di code jika berbeda.

---

## 🐛 Troubleshooting:

### Logo tidak muncul?
**Cek:**
1. File ada di `src/main/resources/images/logo_sekolah.png`
2. Nama file exact: `logo_sekolah.png` (case sensitive)
3. File format PNG
4. Rebuild project: `mvn clean compile`

### Logo terlalu besar/kecil?
**Edit ukuran di code:**
```java
logo.scaleToFit(80, 80); // Ubah angka ini
```
- Angka pertama: width (lebar)
- Angka kedua: height (tinggi)

### Logo terdistorsi?
Gunakan `scaleToFit` bukan `scaleAbsolute` agar proporsi tetap.

---

## ✅ Checklist:

- [x] Folder `images` dibuat
- [x] Code diupdate untuk support logo
- [x] Fallback ke emoji jika logo tidak ada
- [x] Tanggal dihapus dari Admin Perpustakaan
- [x] Tanggal tetap di Kepala Sekolah
- [ ] **User: Copy file logo ke folder**
- [ ] **User: Rebuild project**
- [ ] **User: Test generate PDF**

---

## 🎯 Next Steps:

1. **Download/Simpan logo sekolah**
   - Ambil gambar logo yang sudah diberikan
   - Simpan sebagai PNG

2. **Copy ke folder project**
   ```
   C:\kuliah\kkp_app\app_perpustkaan\src\main\resources\images\logo_sekolah.png
   ```

3. **Rebuild**
   ```bash
   mvn clean compile
   ```

4. **Test**
   - Jalankan aplikasi
   - Generate laporan
   - Export PDF
   - Cek logo muncul di header!

---

## 📊 Status:

```
✅ BUILD SUCCESS
✅ Code Updated
✅ Folder Created
✅ Logo Support Added
✅ Date Removed from Admin
✅ Date Kept for Kepala Sekolah
⏳ Waiting for logo file to be added
```

**Version**: 1.5.0  
**Date**: 7 Desember 2025  
**Status**: Ready (pending logo file)

