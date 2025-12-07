# UML Class Diagram - Sistem Perpustakaan SMK AL-ASIYAH

## Class Diagram Lengkap

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    SISTEM PERPUSTAKAAN SMK AL-ASIYAH                    │
│                         CLASS DIAGRAM                                   │
└─────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────┐
│                           MODEL CLASSES                                 │
└─────────────────────────────────────────────────────────────────────────┘

┌──────────────────────┐
│        User          │
├──────────────────────┤
│ - idUser: int        │
│ - username: String   │
│ - password: String   │
│ - role: String       │
│ - namaLengkap: String│
│ - createdAt: Date    │
├──────────────────────┤
│ + getIdUser(): int   │
│ + getUsername(): Str │
│ + getPassword(): Str │
│ + getRole(): String  │
│ + setRole(String)    │
└──────────────────────┘

┌──────────────────────┐
│        Buku          │
├──────────────────────┤
│ - idBuku: int        │
│ - kodeBuku: String   │
│ - judul: String      │
│ - pengarang: String  │
│ - penerbit: String   │
│ - tahun: int         │
│ - kategori: String   │
│ - stok: int          │
│ - lokasiRak: String  │
│ - createdAt: Date    │
├──────────────────────┤
│ + getIdBuku(): int   │
│ + getKodeBuku(): Str │
│ + getJudul(): String │
│ + getStok(): int     │
│ + setStok(int)       │
│ + decreaseStok()     │
│ + increaseStok()     │
└──────────────────────┘

┌──────────────────────┐
│       Siswa          │
├──────────────────────┤
│ - idSiswa: int       │
│ - nis: String        │
│ - nama: String       │
│ - kelas: String      │
│ - alamat: String     │
│ - noTelp: String     │
│ - status: String     │
│ - createdAt: Date    │
├──────────────────────┤
│ + getIdSiswa(): int  │
│ + getNis(): String   │
│ + getNama(): String   │
│ + getKelas(): String │
│ + getStatus(): String│
└──────────────────────┘

┌──────────────────────┐
│        Guru          │
├──────────────────────┤
│ - idGuru: int        │
│ - nip: String        │
│ - nama: String       │
│ - jabatan: String    │
│ - alamat: String     │
│ - noTelp: String     │
│ - status: String     │
│ - createdAt: Date    │
├──────────────────────┤
│ + getIdGuru(): int   │
│ + getNip(): String   │
│ + getNama(): String   │
│ + getJabatan(): Str │
│ + getStatus(): String│
└──────────────────────┘

┌──────────────────────┐
│     Peminjaman       │
├──────────────────────┤
│ - idPinjam: int      │
│ - idUser: int        │
│ - idBuku: int        │
│ - idSiswa: int       │
│ - idGuru: int        │
│ - tglPinjam: Date    │
│ - tglKembali: Date   │
│ - statusPinjam: Str  │
├──────────────────────┤
│ + getIdPinjam(): int │
│ + getIdBuku(): int   │
│ + getTglPinjam(): Dt │
│ + getTglKembali(): Dt│
│ + getStatus(): String│
│ + setStatus(String)  │
└──────────────────────┘
         │
         │ 1
         │
         │ 1
         │
┌────────▼────────────┐
│   Pengembalian      │
├──────────────────────┤
│ - idKembali: int    │
│ - idPinjam: int     │
│ - tglDikembalikan: D│
│ - denda: double     │
├──────────────────────┤
│ + getIdKembali():int│
│ + getIdPinjam(): int│
│ + getDenda(): double│
│ + calculateDenda(): d│
└──────────────────────┘

┌─────────────────────────────────────────────────────────────────────────┐
│                           DAO CLASSES                                    │
└─────────────────────────────────────────────────────────────────────────┘

┌──────────────────────┐
│      UserDAO         │
├──────────────────────┤
│ - connection: Conn   │
├──────────────────────┤
│ + login(String, Str) │
│ + createUser(User)   │
│ + updateUser(User)   │
│ + deleteUser(int)    │
│ + getUserById(int)   │
│ + getAllUsers()      │
└──────────────────────┘

┌──────────────────────┐
│      BukuDAO         │
├──────────────────────┤
│ - connection: Conn   │
├──────────────────────┤
│ + create(Buku)       │
│ + update(Buku)       │
│ + delete(int)        │
│ + getById(int)       │
│ + getAll()           │
│ + search(String)     │
│ + filterByCategory() │
│ + updateStok(int, int)│
└──────────────────────┘

┌──────────────────────┐
│     SiswaDAO         │
├──────────────────────┤
│ - connection: Conn   │
├──────────────────────┤
│ + create(Siswa)      │
│ + update(Siswa)      │
│ + delete(int)        │
│ + getById(int)       │
│ + getAll()           │
│ + search(String)     │
│ + getAktif()         │
└──────────────────────┘

┌──────────────────────┐
│      GuruDAO         │
├──────────────────────┤
│ - connection: Conn   │
├──────────────────────┤
│ + create(Guru)       │
│ + update(Guru)       │
│ + delete(int)        │
│ + getById(int)       │
│ + getAll()           │
│ + search(String)     │
│ + getAktif()         │
└──────────────────────┘

┌──────────────────────┐
│   PeminjamanDAO      │
├──────────────────────┤
│ - connection: Conn   │
├──────────────────────┤
│ + create(Peminjaman) │
│ + update(Peminjaman) │
│ + getById(int)      │
│ + getAll()           │
│ + filterByStatus()   │
│ + filterByDate()     │
│ + getByAnggota()     │
└──────────────────────┘

┌──────────────────────┐
│  PengembalianDAO     │
├──────────────────────┤
│ - connection: Conn   │
├──────────────────────┤
│ + create(Pengembalian)│
│ + getById(int)       │
│ + getAll()           │
│ + getByPeminjaman()  │
└──────────────────────┘

┌──────────────────────┐
│    StatistikDAO      │
├──────────────────────┤
│ - connection: Conn   │
├──────────────────────┤
│ + getTotalBuku()     │
│ + getBukuTersedia()  │
│ + getBukuDipinjam()  │
│ + getTotalAnggota()  │
│ + getPeminjamanHarian()│
│ + getPeminjamanBulanan()│
└──────────────────────┘

┌─────────────────────────────────────────────────────────────────────────┐
│                        CONTROLLER CLASSES                               │
└─────────────────────────────────────────────────────────────────────────┘

┌──────────────────────┐
│  LoginController     │
├──────────────────────┤
│ - userDAO: UserDAO   │
│ - usernameField      │
│ - passwordField      │
├──────────────────────┤
│ + initialize()       │
│ + handleLogin()      │
│ + loadMainView()     │
└──────────────────────┘

┌──────────────────────┐
│  MainController      │
├──────────────────────┤
│ - contentArea        │
├──────────────────────┤
│ + initialize()       │
│ + loadView(String)   │
│ + handleLogout()     │
└──────────────────────┘

┌──────────────────────┐
│  DashboardController │
├──────────────────────┤
│ - statistikDAO       │
│ - peminjamanDAO      │
│ - chart components   │
├──────────────────────┤
│ + initialize()       │
│ + loadStatistics()    │
│ + loadCharts()       │
│ + loadRecentActivity()│
└──────────────────────┘

┌──────────────────────┐
│   BukuController     │
├──────────────────────┤
│ - bukuDAO: BukuDAO   │
│ - tableView          │
│ - searchField        │
├──────────────────────┤
│ + initialize()       │
│ + loadData()         │
│ + handleTambah()     │
│ + handleEdit()       │
│ + handleHapus()      │
│ + handleSearch()     │
└──────────────────────┘

┌──────────────────────┐
│ PeminjamanController │
├──────────────────────┤
│ - peminjamanDAO      │
│ - bukuDAO            │
│ - siswaDAO           │
│ - guruDAO            │
│ - tableView          │
├──────────────────────┤
│ + initialize()       │
│ + loadData()         │
│ + handleTambah()     │
│ + validateStok()     │
└──────────────────────┘

┌──────────────────────┐
│PengembalianController│
├──────────────────────┤
│ - pengembalianDAO    │
│ - peminjamanDAO      │
│ - bukuDAO            │
│ - tableView          │
├──────────────────────┤
│ + initialize()       │
│ + loadData()         │
│ + handleKembalikan() │
│ + calculateDenda()   │
└──────────────────────┘

┌─────────────────────────────────────────────────────────────────────────┐
│                         UTILITY CLASSES                                 │
└─────────────────────────────────────────────────────────────────────────┘

┌──────────────────────┐
│  DatabaseConfig      │
├──────────────────────┤
│ - host: String       │
│ - port: int          │
│ - database: String   │
│ - user: String       │
│ - password: String   │
├──────────────────────┤
│ + getConnection():   │
│   Connection         │
│ + closeConnection()  │
└──────────────────────┘

┌──────────────────────┐
│   SessionManager     │
├──────────────────────┤
│ - currentUser: User  │
│ + INSTANCE            │
├──────────────────────┤
│ + getInstance()      │
│ + setCurrentUser(User)│
│ + getCurrentUser()   │
│ + clearSession()     │
└──────────────────────┘

┌──────────────────────┐
│     EnvConfig        │
├──────────────────────┤
│ + get(String): String│
│ + loadEnvFile()      │
└──────────────────────┘
```

## Relasi Antar Class

### Association
- **UserDAO** ──uses──> **User**
- **BukuDAO** ──uses──> **Buku**
- **PeminjamanDAO** ──uses──> **Peminjaman**, **Buku**, **Siswa**, **Guru**
- **Controller** ──uses──> **DAO**

### Composition
- **Peminjaman** ◄─── **Pengembalian** (1:1)
- **Peminjaman** ──uses──> **Buku** (Many:1)
- **Peminjaman** ──uses──> **User** (Many:1)

### Dependency
- **Controller** ──depends on──> **DAO**
- **DAO** ──depends on──> **DatabaseConfig**

## Stereotype

- **<<Model>>** - Model classes (User, Buku, Siswa, Guru, Peminjaman, Pengembalian)
- **<<DAO>>** - Data Access Object classes
- **<<Controller>>** - Controller classes
- **<<Utility>>** - Utility classes




