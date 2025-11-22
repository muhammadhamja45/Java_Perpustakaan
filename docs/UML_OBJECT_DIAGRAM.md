# UML Object Diagram - Sistem Perpustakaan SMK AL-ASIYAH

## 1. Object Diagram - Peminjaman Buku

```
┌─────────────────────────────────────────────────────────────────────────┐
│              OBJECT DIAGRAM - PEMINJAMAN BUKU (SISWA)                   │
└─────────────────────────────────────────────────────────────────────────┘

┌────────────────────────┐
│   admin1: User         │
├────────────────────────┤
│ idUser = 1             │
│ username = "admin"     │
│ password = "$2a$..."   │
│ role = "admin"         │
│ namaLengkap =          │
│   "Admin Perpus"       │
└────────┬───────────────┘
         │
         │ melakukan
         │
         ▼
┌────────────────────────┐
│ peminjaman1:           │
│   Peminjaman           │
├────────────────────────┤
│ idPinjam = 1           │
│ idUser = 1             │
│ idBuku = 101           │
│ idSiswa = 201          │
│ idGuru = NULL          │
│ tglPinjam =            │
│   "2024-11-20"         │
│ tglKembali =           │
│   "2024-11-27"         │
│ statusPinjam =         │
│   "dipinjam"           │
└────┬───────────┬───────┘
     │           │
     │           │ meminjam
     │ dipinjam  │
     │ oleh      ▼
     │       ┌────────────────────┐
     │       │ siswa1: Siswa      │
     │       ├────────────────────┤
     │       │ idSiswa = 201      │
     │       │ nis = "2024001"    │
     │       │ nama = "Ahmad"     │
     │       │ kelas = "X RPL 1"  │
     │       │ status = "Aktif"   │
     │       │ noTelp =           │
     │       │   "081234567890"   │
     │       └────────────────────┘
     │
     ▼
┌────────────────────────┐
│ buku1: Buku            │
├────────────────────────┤
│ idBuku = 101           │
│ kodeBuku = "B-001"     │
│ judul = "Pemrograman   │
│   Java"                │
│ pengarang =            │
│   "Budi Raharjo"       │
│ penerbit =             │
│   "Informatika"        │
│ tahun = 2023           │
│ kategori =             │
│   "Teknologi"          │
│ stok = 4               │
│   (awalnya 5)          │
│ lokasiRak = "A-01"     │
└────────────────────────┘
```

## 2. Object Diagram - Peminjaman Buku (Guru)

```
┌─────────────────────────────────────────────────────────────────────────┐
│              OBJECT DIAGRAM - PEMINJAMAN BUKU (GURU)                    │
└─────────────────────────────────────────────────────────────────────────┘

┌────────────────────────┐
│   petugas1: User       │
├────────────────────────┤
│ idUser = 2             │
│ username = "petugas1"  │
│ password = "$2a$..."   │
│ role = "petugas"       │
│ namaLengkap =          │
│   "Petugas 1"          │
└────────┬───────────────┘
         │
         │ melakukan
         │
         ▼
┌────────────────────────┐
│ peminjaman2:           │
│   Peminjaman           │
├────────────────────────┤
│ idPinjam = 2           │
│ idUser = 2             │
│ idBuku = 102           │
│ idSiswa = NULL         │
│ idGuru = 301           │
│ tglPinjam =            │
│   "2024-11-21"         │
│ tglKembali =           │
│   "2024-11-28"         │
│ statusPinjam =         │
│   "dipinjam"           │
└────┬───────────┬───────┘
     │           │
     │           │ meminjam
     │ dipinjam  │
     │ oleh      ▼
     │       ┌────────────────────┐
     │       │ guru1: Guru        │
     │       ├────────────────────┤
     │       │ idGuru = 301       │
     │       │ nip = "199001012"  │
     │       │ nama = "Siti R."   │
     │       │ jabatan =          │
     │       │   "Guru Produktif" │
     │       │ status = "Aktif"   │
     │       │ noTelp =           │
     │       │   "081987654321"   │
     │       └────────────────────┘
     │
     ▼
┌────────────────────────┐
│ buku2: Buku            │
├────────────────────────┤
│ idBuku = 102           │
│ kodeBuku = "B-002"     │
│ judul = "Database      │
│   MySQL"               │
│ pengarang =            │
│   "Abdul Kadir"        │
│ penerbit = "Andi"      │
│ tahun = 2022           │
│ kategori =             │
│   "Teknologi"          │
│ stok = 2               │
│   (awalnya 3)          │
│ lokasiRak = "A-02"     │
└────────────────────────┘
```

## 3. Object Diagram - Pengembalian Buku (Tanpa Denda)

```
┌─────────────────────────────────────────────────────────────────────────┐
│          OBJECT DIAGRAM - PENGEMBALIAN BUKU (TANPA DENDA)              │
└─────────────────────────────────────────────────────────────────────────┘

┌────────────────────────┐
│   admin1: User         │
├────────────────────────┤
│ idUser = 1             │
│ username = "admin"     │
│ role = "admin"         │
└────────┬───────────────┘
         │
         │ memproses
         │
         ▼
┌────────────────────────┐
│ peminjaman1:           │
│   Peminjaman           │
├────────────────────────┤
│ idPinjam = 1           │
│ idUser = 1             │
│ idBuku = 101           │
│ idSiswa = 201          │
│ tglPinjam =            │
│   "2024-11-20"         │
│ tglKembali =           │
│   "2024-11-27"         │
│ statusPinjam =         │
│   "dikembalikan"       │
│   (updated)            │
└────────┬───────────────┘
         │
         │ memiliki
         │
         ▼
┌────────────────────────┐
│ pengembalian1:         │
│   Pengembalian         │
├────────────────────────┤
│ idKembali = 1          │
│ idPinjam = 1           │
│ tglDikembalikan =      │
│   "2024-11-26"         │
│ denda = 0.0            │
│   (tepat waktu)        │
└────────────────────────┘

         ┌────────────────┐
         │ buku1: Buku    │
         ├────────────────┤
         │ idBuku = 101   │
         │ stok = 5       │
         │   (kembali     │
         │    normal)     │
         └────────────────┘
```

## 4. Object Diagram - Pengembalian Buku (Dengan Denda)

```
┌─────────────────────────────────────────────────────────────────────────┐
│          OBJECT DIAGRAM - PENGEMBALIAN BUKU (DENGAN DENDA)             │
└─────────────────────────────────────────────────────────────────────────┘

┌────────────────────────┐
│   petugas1: User       │
├────────────────────────┤
│ idUser = 2             │
│ username = "petugas1"  │
│ role = "petugas"       │
└────────┬───────────────┘
         │
         │ memproses
         │
         ▼
┌────────────────────────┐
│ peminjaman3:           │
│   Peminjaman           │
├────────────────────────┤
│ idPinjam = 3           │
│ idUser = 2             │
│ idBuku = 103           │
│ idSiswa = 202          │
│ tglPinjam =            │
│   "2024-11-10"         │
│ tglKembali =           │
│   "2024-11-17"         │
│ statusPinjam =         │
│   "dikembalikan"       │
│   (updated)            │
└────────┬───────────────┘
         │
         │ memiliki
         │
         ▼
┌────────────────────────┐
│ pengembalian2:         │
│   Pengembalian         │
├────────────────────────┤
│ idKembali = 2          │
│ idPinjam = 3           │
│ tglDikembalikan =      │
│   "2024-11-22"         │
│   (5 hari terlambat)   │
│ denda = 5000.0         │
│   (5 hari × 1000)      │
└────────────────────────┘

         ┌────────────────┐
         │ buku3: Buku    │
         ├────────────────┤
         │ idBuku = 103   │
         │ stok = 8       │
         │   (bertambah)  │
         └────────────────┘
         
         ┌────────────────────┐
         │ siswa2: Siswa      │
         ├────────────────────┤
         │ idSiswa = 202      │
         │ nis = "2024002"    │
         │ nama = "Rina"      │
         │ status = "Aktif"   │
         └────────────────────┘
```

## 5. Object Diagram - Dashboard Statistics

```
┌─────────────────────────────────────────────────────────────────────────┐
│              OBJECT DIAGRAM - DASHBOARD STATISTICS                      │
└─────────────────────────────────────────────────────────────────────────┘

┌────────────────────────────────┐
│ dashboardCtrl:                 │
│   DashboardController          │
├────────────────────────────────┤
│ totalBukuLabel = "150"         │
│ bukuTersediaLabel = "120"      │
│ bukuDipinjamLabel = "30"       │
│ totalAnggotaLabel = "500"      │
└────────┬───────────────────────┘
         │
         │ menggunakan
         │
         ▼
┌────────────────────────────────┐
│ statistikDAO:                  │
│   StatistikDAO                 │
├────────────────────────────────┤
│ (no instance variables)        │
└────────┬───────────────────────┘
         │
         │ query
         │
         ▼
┌────────────────────────────────┐
│ connection:                    │
│   Connection                   │
├────────────────────────────────┤
│ url = "jdbc:mysql://          │
│   localhost:3306/db_perpus"    │
│ user = "root"                  │
│ status = "connected"           │
└────────────────────────────────┘

┌────────────────────────────────┐
│ buku1: Buku                    │
├────────────────────────────────┤
│ idBuku = 1, stok = 5           │
├────────────────────────────────┤
│ buku2: Buku                    │
├────────────────────────────────┤
│ idBuku = 2, stok = 0           │
├────────────────────────────────┤
│ buku3: Buku                    │
├────────────────────────────────┤
│ idBuku = 3, stok = 10          │
└────────────────────────────────┘
  ... (total 150 buku objects)

┌────────────────────────────────┐
│ peminjaman1: Peminjaman        │
├────────────────────────────────┤
│ statusPinjam = "dipinjam"      │
├────────────────────────────────┤
│ peminjaman2: Peminjaman        │
├────────────────────────────────┤
│ statusPinjam = "dipinjam"      │
└────────────────────────────────┘
  ... (total 30 peminjaman aktif)
```

## 6. Object Diagram - Session Management

```
┌─────────────────────────────────────────────────────────────────────────┐
│              OBJECT DIAGRAM - SESSION MANAGEMENT                        │
└─────────────────────────────────────────────────────────────────────────┘

┌────────────────────────────────┐
│ sessionManager:                │
│   SessionManager               │
│   <<Singleton>>                │
├────────────────────────────────┤
│ INSTANCE = sessionManager      │
└────────┬───────────────────────┘
         │
         │ holds
         │
         ▼
┌────────────────────────────────┐
│ currentUser: User              │
├────────────────────────────────┤
│ idUser = 1                     │
│ username = "admin"             │
│ password = "$2a$10$..."        │
│ role = "admin"                 │
│ namaLengkap = "Admin Perpus"   │
│ createdAt = "2024-01-01"       │
└────────────────────────────────┘

         ↓ used by ↓

┌────────────────────────────────┐
│ loginCtrl:                     │
│   LoginController              │
├────────────────────────────────┤
│ usernameField = [TextField]    │
│ passwordField = [PasswordField]│
└────────────────────────────────┘

┌────────────────────────────────┐
│ mainCtrl:                      │
│   MainController               │
├────────────────────────────────┤
│ userLabel = "Admin Perpus"     │
│ roleLabel = "admin"            │
└────────────────────────────────┘

┌────────────────────────────────┐
│ peminjamanCtrl:                │
│   PeminjamanController         │
├────────────────────────────────┤
│ currentUser = [from Session]   │
└────────────────────────────────┘
```

## Catatan Penting

### Karakteristik Object Diagram
1. **Instance Notation** - Setiap object menggunakan format `objectName: ClassName`
2. **Attribute Values** - Menampilkan nilai aktual dari attribute
3. **Relationships** - Menunjukkan hubungan antar object pada waktu tertentu
4. **State** - Menggambarkan state sistem pada momen tertentu

### Perbedaan dengan Class Diagram
- **Class Diagram** - Struktur statis (template)
- **Object Diagram** - Instance spesifik dengan nilai aktual

### Use Cases
- Memahami hubungan antar object saat runtime
- Debugging dan testing
- Dokumentasi state sistem
- Validasi desain class diagram

