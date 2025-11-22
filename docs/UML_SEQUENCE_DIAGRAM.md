# UML Sequence Diagram - Sistem Perpustakaan SMK AL-ASIYAH

## 1. Sequence Diagram - Login

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    SEQUENCE DIAGRAM - LOGIN                              │
└─────────────────────────────────────────────────────────────────────────┘

 User        LoginController    UserDAO      DatabaseConfig   SessionManager  MainController
  │                │              │                │                │              │
  │  Input         │              │                │                │              │
  │  Credentials   │              │                │                │              │
  ├───────────────►│              │                │                │              │
  │                │              │                │                │              │
  │                │  login()     │                │                │              │
  │                ├─────────────►│                │                │              │
  │                │              │ getConnection()│                │              │
  │                │              ├───────────────►│                │              │
  │                │              │                │                │              │
  │                │              │ ◄──────────────┤                │              │
  │                │              │  Connection    │                │              │
  │                │              │                │                │              │
  │                │              │  SELECT * FROM users           │              │
  │                │              │  WHERE username=?              │              │
  │                │              │                │                │              │
  │                │              │                │                │              │
  │                │              │  BCrypt.checkpw()              │              │
  │                │              │                │                │              │
  │                │  ◄──────────┤                │                │              │
  │                │  User Object │                │                │              │
  │                │              │                │                │              │
  │                │  setCurrentUser()             │                │              │
  │                ├──────────────────────────────────────────────►│              │
  │                │              │                │                │              │
  │                │              │                │  User Stored   │              │
  │                │              │                │ ◄──────────────┤              │
  │                │              │                │                │              │
  │                │  loadMainView()               │                │              │
  │                ├─────────────────────────────────────────────────────────────►│
  │                │              │                │                │              │
  │                │              │                │                │   initialize()│
  │                │              │                │                │   loadDashboard()│
  │                │              │                │                │              │
  │ ◄──────────────────────────────────────────────────────────────────────────────┤
  │  Dashboard View               │                │                │              │
  │                │              │                │                │              │
```

## 2. Sequence Diagram - Tambah Peminjaman

```
┌─────────────────────────────────────────────────────────────────────────┐
│                SEQUENCE DIAGRAM - TAMBAH PEMINJAMAN                      │
└─────────────────────────────────────────────────────────────────────────┘

 User    PeminjamanDialog  PeminjamanDAO  BukuDAO  SiswaDAO  DatabaseConfig
  │            │              │            │         │            │
  │ Klik       │              │            │         │            │
  │ Tambah     │              │            │         │            │
  ├───────────►│              │            │         │            │
  │            │              │            │         │            │
  │            │  getAll()    │            │         │            │
  │            ├──────────────────────────────────►  │            │
  │            │              │            │         │            │
  │            │              │            │  ◄──────┤            │
  │            │              │            │  List<Siswa>         │
  │            │              │            │         │            │
  │            │  getAll()    │            │         │            │
  │            ├──────────────────────────►│         │            │
  │            │              │            │         │            │
  │            │              │     ◄──────┤         │            │
  │            │              │  List<Buku>│         │            │
  │            │              │            │         │            │
  │  Select    │              │            │         │            │
  │  Member &  │              │            │         │            │
  │  Book      │              │            │         │            │
  ├───────────►│              │            │         │            │
  │            │              │            │         │            │
  │            │  getById()   │            │         │            │
  │            ├──────────────────────────►│         │            │
  │            │              │            │         │            │
  │            │              │            │         │            │
  │            │              │  ◄─────────┤         │            │
  │            │              │  Buku Object          │            │
  │            │              │  (Validate Stok)      │            │
  │            │              │            │         │            │
  │  Klik      │              │            │         │            │
  │  Simpan    │              │            │         │            │
  ├───────────►│              │            │         │            │
  │            │              │            │         │            │
  │            │  create()    │            │         │            │
  │            ├─────────────►│            │         │            │
  │            │              │ getConnection()      │            │
  │            │              ├──────────────────────────────────►│
  │            │              │            │         │            │
  │            │              │ INSERT INTO peminjaman            │
  │            │              │            │         │            │
  │            │              │            │         │            │
  │            │  updateStok()│            │         │            │
  │            ├──────────────────────────►│         │            │
  │            │              │            │         │            │
  │            │              │  UPDATE buku SET stok = stok - 1  │
  │            │              │            │         │            │
  │            │              │            │         │            │
  │            │  ◄───────────┤            │         │            │
  │            │  Success     │            │         │            │
  │            │              │            │         │            │
  │ ◄──────────┤              │            │         │            │
  │  Success   │              │            │         │            │
  │  Message   │              │            │         │            │
  │            │              │            │         │            │
```

## 3. Sequence Diagram - Proses Pengembalian

```
┌─────────────────────────────────────────────────────────────────────────┐
│                SEQUENCE DIAGRAM - PROSES PENGEMBALIAN                   │
└─────────────────────────────────────────────────────────────────────────┘

 User    PengembalianCtrl PengembalianDAO PeminjamanDAO BukuDAO DatabaseConfig
  │            │              │               │           │          │
  │ Load       │              │               │           │          │
  │ View       │              │               │           │          │
  ├───────────►│              │               │           │          │
  │            │              │               │           │          │
  │            │  getByStatus("dipinjam")     │           │          │
  │            ├──────────────────────────────►│           │          │
  │            │              │               │           │          │
  │            │              │        ◄──────┤           │          │
  │            │              │  List<Peminjaman>         │          │
  │            │              │               │           │          │
  │  Select    │              │               │           │          │
  │  Peminjaman│              │               │           │          │
  ├───────────►│              │               │           │          │
  │            │              │               │           │          │
  │            │  calculateDenda()            │           │          │
  │            │  (tglSekarang - tglKembali)  │           │          │
  │            │              │               │           │          │
  │  Confirm   │              │               │           │          │
  │  Return    │              │               │           │          │
  ├───────────►│              │               │           │          │
  │            │              │               │           │          │
  │            │  create()    │               │           │          │
  │            ├─────────────►│               │           │          │
  │            │              │  getConnection()          │          │
  │            │              ├──────────────────────────────────────►│
  │            │              │               │           │          │
  │            │              │  INSERT INTO pengembalian │          │
  │            │              │  (id_pinjam, tgl_dikembalikan, denda)│
  │            │              │               │           │          │
  │            │              │               │           │          │
  │            │  updateStatus(id, "dikembalikan")       │          │
  │            ├──────────────────────────────►│           │          │
  │            │              │               │           │          │
  │            │              │  UPDATE peminjaman       │          │
  │            │              │  SET status_pinjam = "dikembalikan" │
  │            │              │               │           │          │
  │            │              │               │           │          │
  │            │  increaseStok()              │           │          │
  │            ├──────────────────────────────────────────►│          │
  │            │              │               │           │          │
  │            │              │  UPDATE buku SET stok = stok + 1    │
  │            │              │               │           │          │
  │            │              │               │           │          │
  │            │  ◄───────────┤               │           │          │
  │            │  Success     │               │           │          │
  │            │              │               │           │          │
  │ ◄──────────┤              │               │           │          │
  │  Success   │              │               │           │          │
  │  Message   │              │               │           │          │
  │  + Denda   │              │               │           │          │
  │  Info      │              │               │           │          │
  │            │              │               │           │          │
```

## 4. Sequence Diagram - Generate Laporan

```
┌─────────────────────────────────────────────────────────────────────────┐
│                SEQUENCE DIAGRAM - GENERATE LAPORAN                      │
└─────────────────────────────────────────────────────────────────────────┘

 User      LaporanController  PeminjamanDAO  DatabaseConfig  JasperReports
  │              │                 │               │               │
  │ Select       │                 │               │               │
  │ Period &     │                 │               │               │
  │ Format       │                 │               │               │
  ├─────────────►│                 │               │               │
  │              │                 │               │               │
  │              │  filterByDate() │               │               │
  │              ├────────────────►│               │               │
  │              │                 │ getConnection()               │
  │              │                 ├──────────────►│               │
  │              │                 │               │               │
  │              │                 │  SELECT * FROM peminjaman    │
  │              │                 │  LEFT JOIN buku, siswa, guru │
  │              │                 │  WHERE tgl_pinjam BETWEEN    │
  │              │                 │               │               │
  │              │                 │               │               │
  │              │  ◄──────────────┤               │               │
  │              │  List<Peminjaman>               │               │
  │              │                 │               │               │
  │              │  prepareData()  │               │               │
  │              │  (format data)  │               │               │
  │              │                 │               │               │
  │              │  compile()      │               │               │
  │              ├────────────────────────────────────────────────►│
  │              │                 │               │               │
  │              │                 │               │  fillReport() │
  │              │                 │               │  (jasper.jrxml)│
  │              │                 │               │               │
  │              │  ◄──────────────────────────────────────────────┤
  │              │  JasperPrint    │               │               │
  │              │                 │               │               │
  │              │  exportToPDF()  │               │               │
  │              ├────────────────────────────────────────────────►│
  │              │                 │               │               │
  │              │  ◄──────────────────────────────────────────────┤
  │              │  PDF File       │               │               │
  │              │                 │               │               │
  │ ◄────────────┤                 │               │               │
  │  Download    │                 │               │               │
  │  PDF File    │                 │               │               │
  │              │                 │               │               │
```

## 5. Sequence Diagram - Dashboard Load

```
┌─────────────────────────────────────────────────────────────────────────┐
│                SEQUENCE DIAGRAM - DASHBOARD LOAD                        │
└─────────────────────────────────────────────────────────────────────────┘

 User    DashboardCtrl  StatistikDAO  PeminjamanDAO  DatabaseConfig
  │            │            │              │               │
  │ Open       │            │              │               │
  │ Dashboard  │            │              │               │
  ├───────────►│            │              │               │
  │            │            │              │               │
  │            │  getTotalBuku()           │               │
  │            ├───────────►│              │               │
  │            │            │ getConnection()              │
  │            │            ├──────────────────────────────►│
  │            │            │              │               │
  │            │            │ SELECT COUNT(*) FROM buku   │
  │            │            │              │               │
  │            │  ◄─────────┤              │               │
  │            │  Total     │              │               │
  │            │            │              │               │
  │            │  getBukuTersedia()        │               │
  │            ├───────────►│              │               │
  │            │            │ SELECT COUNT(*) FROM buku   │
  │            │            │ WHERE stok > 0              │
  │            │            │              │               │
  │            │  ◄─────────┤              │               │
  │            │  Total     │              │               │
  │            │            │              │               │
  │            │  getBukuDipinjam()        │               │
  │            ├───────────►│              │               │
  │            │            │ SELECT COUNT(*) FROM        │
  │            │            │ peminjaman WHERE            │
  │            │            │ status = 'dipinjam'         │
  │            │            │              │               │
  │            │  ◄─────────┤              │               │
  │            │  Total     │              │               │
  │            │            │              │               │
  │            │  getTotalAnggota()        │               │
  │            ├───────────►│              │               │
  │            │            │ SELECT COUNT(*) FROM        │
  │            │            │ siswa + guru                │
  │            │            │              │               │
  │            │  ◄─────────┤              │               │
  │            │  Total     │              │               │
  │            │            │              │               │
  │            │  getPeminjamanHarian()    │               │
  │            ├──────────────────────────►│               │
  │            │            │              │               │
  │            │            │  SELECT DATE, COUNT(*)      │
  │            │            │  GROUP BY DATE              │
  │            │            │              │               │
  │            │  ◄──────────────────────┤ │               │
  │            │  Chart Data              │               │
  │            │            │              │               │
  │            │  render()  │              │               │
  │            │  (display statistics)    │               │
  │            │  (render charts)          │               │
  │            │            │              │               │
  │ ◄──────────┤            │              │               │
  │  Dashboard │            │              │               │
  │  View      │            │              │               │
  │            │            │              │               │
```

## Catatan Penting

### Pola Umum
Semua sequence diagram mengikuti pola:
1. **User Interaction** - User melakukan aksi
2. **Controller Processing** - Controller menerima dan memproses request
3. **DAO Layer** - Mengakses data melalui DAO
4. **Database Access** - Koneksi dan query database
5. **Business Logic** - Pemrosesan data dan validasi
6. **Response** - Kembali ke user dengan hasil

### Prinsip Desain
- **Separation of Concerns** - Setiap layer memiliki tanggung jawab terpisah
- **MVC Pattern** - Model-View-Controller architecture
- **DAO Pattern** - Data Access Object untuk abstraksi database
- **Singleton Pattern** - SessionManager dan DatabaseConfig
- **Error Handling** - Validasi di setiap layer

