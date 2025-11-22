# UML Deployment Diagram - Sistem Perpustakaan SMK AL-ASIYAH

## 1. Deployment Diagram - Arsitektur Sistem

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    DEPLOYMENT DIAGRAM - OVERVIEW                        │
└─────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────┐
│                         CLIENT WORKSTATION                              │
│                    <<device: Windows/Linux/macOS>>                      │
│                                                                          │
│  ┌────────────────────────────────────────────────────────────────┐    │
│  │                     JavaFX Runtime                              │    │
│  │                   <<execution environment>>                     │    │
│  │                        (JRE 17+)                                │    │
│  │                                                                 │    │
│  │  ┌──────────────────────────────────────────────────────┐      │    │
│  │  │         perpustakaan.jar                             │      │    │
│  │  │         <<artifact>>                                 │      │    │
│  │  │                                                       │      │    │
│  │  │  ┌────────────────────────────────────────┐          │      │    │
│  │  │  │   Presentation Layer                   │          │      │    │
│  │  │  │   <<component>>                        │          │      │    │
│  │  │  │                                        │          │      │    │
│  │  │  │   - LoginController.class              │          │      │    │
│  │  │  │   - DashboardController.class          │          │      │    │
│  │  │  │   - BukuController.class               │          │      │    │
│  │  │  │   - PeminjamanController.class         │          │      │    │
│  │  │  │   - PengembalianController.class       │          │      │    │
│  │  │  │   - LaporanController.class            │          │      │    │
│  │  │  │                                        │          │      │    │
│  │  │  │   FXML Files:                          │          │      │    │
│  │  │  │   - login.fxml                         │          │      │    │
│  │  │  │   - dashboard.fxml                     │          │      │    │
│  │  │  │   - buku.fxml                          │          │      │    │
│  │  │  │   - peminjaman.fxml                    │          │      │    │
│  │  │  │   - styles.css                         │          │      │    │
│  │  │  └────────────────────────────────────────┘          │      │    │
│  │  │                      │                                │      │    │
│  │  │                      │ uses                           │      │    │
│  │  │                      ▼                                │      │    │
│  │  │  ┌────────────────────────────────────────┐          │      │    │
│  │  │  │   Business Logic Layer                 │          │      │    │
│  │  │  │   <<component>>                        │          │      │    │
│  │  │  │                                        │          │      │    │
│  │  │  │   DAO Classes:                         │          │      │    │
│  │  │  │   - UserDAO.class                      │          │      │    │
│  │  │  │   - BukuDAO.class                      │          │      │    │
│  │  │  │   - SiswaDAO.class                     │          │      │    │
│  │  │  │   - GuruDAO.class                      │          │      │    │
│  │  │  │   - PeminjamanDAO.class                │          │      │    │
│  │  │  │   - PengembalianDAO.class              │          │      │    │
│  │  │  │   - StatistikDAO.class                 │          │      │    │
│  │  │  │                                        │          │      │    │
│  │  │  │   Model Classes:                       │          │      │    │
│  │  │  │   - User.class                         │          │      │    │
│  │  │  │   - Buku.class                         │          │      │    │
│  │  │  │   - Siswa.class                        │          │      │    │
│  │  │  │   - Guru.class                         │          │      │    │
│  │  │  │   - Peminjaman.class                   │          │      │    │
│  │  │  │   - Pengembalian.class                 │          │      │    │
│  │  │  └────────────────────────────────────────┘          │      │    │
│  │  │                      │                                │      │    │
│  │  │                      │ uses                           │      │    │
│  │  │                      ▼                                │      │    │
│  │  │  ┌────────────────────────────────────────┐          │      │    │
│  │  │  │   Utility Layer                        │          │      │    │
│  │  │  │   <<component>>                        │          │      │    │
│  │  │  │                                        │          │      │    │
│  │  │  │   - DatabaseConfig.class               │          │      │    │
│  │  │  │   - SessionManager.class               │          │      │    │
│  │  │  │   - BCryptUtil.class                   │          │      │    │
│  │  │  │   - EnvConfig.class                    │          │      │    │
│  │  │  └────────────────────────────────────────┘          │      │    │
│  │  └──────────────────────────────────────────────────────┘      │    │
│  │                                                                 │    │
│  │  ┌──────────────────────────────────────────────────────┐      │    │
│  │  │         External Libraries                           │      │    │
│  │  │         <<artifact>>                                 │      │    │
│  │  │                                                       │      │    │
│  │  │   - mysql-connector-java-8.0.33.jar                  │      │    │
│  │  │   - bcrypt-0.10.2.jar                                │      │    │
│  │  │   - jasperreports-6.20.0.jar                         │      │    │
│  │  │   - javafx-sdk-17.jar                                │      │    │
│  │  └──────────────────────────────────────────────────────┘      │    │
│  └─────────────────────────────────────────────────────────────────┘    │
└──────────────────────────────┬───────────────────────────────────────────┘
                               │
                               │ JDBC Connection
                               │ <<protocol: TCP/IP>>
                               │ Port: 3306
                               │
┌──────────────────────────────▼───────────────────────────────────────────┐
│                         DATABASE SERVER                                  │
│                    <<device: Server/Localhost>>                          │
│                                                                          │
│  ┌────────────────────────────────────────────────────────────────┐    │
│  │                     MySQL Server 8.0                            │    │
│  │                   <<execution environment>>                     │    │
│  │                                                                 │    │
│  │  ┌──────────────────────────────────────────────────────┐      │    │
│  │  │         db_perpustakaan                              │      │    │
│  │  │         <<database>>                                 │      │    │
│  │  │                                                       │      │    │
│  │  │  Tables:                                             │      │    │
│  │  │  ┌────────────────────────────────────┐              │      │    │
│  │  │  │   - users                          │              │      │    │
│  │  │  │   - buku                           │              │      │    │
│  │  │  │   - siswa                          │              │      │    │
│  │  │  │   - guru                           │              │      │    │
│  │  │  │   - peminjaman                     │              │      │    │
│  │  │  │   - pengembalian                   │              │      │    │
│  │  │  │   - laporan                        │              │      │    │
│  │  │  └────────────────────────────────────┘              │      │    │
│  │  │                                                       │      │    │
│  │  │  Stored Procedures:                                  │      │    │
│  │  │  ┌────────────────────────────────────┐              │      │    │
│  │  │  │   - sp_calculate_denda()           │              │      │    │
│  │  │  │   - sp_update_stok()               │              │      │    │
│  │  │  └────────────────────────────────────┘              │      │    │
│  │  │                                                       │      │    │
│  │  │  Views:                                              │      │    │
│  │  │  ┌────────────────────────────────────┐              │      │    │
│  │  │  │   - v_peminjaman_detail            │              │      │    │
│  │  │  │   - v_statistik_buku               │              │      │    │
│  │  │  └────────────────────────────────────┘              │      │    │
│  │  └──────────────────────────────────────────────────────┘      │    │
│  │                                                                 │    │
│  │  ┌──────────────────────────────────────────────────────┐      │    │
│  │  │         Database Storage                             │      │    │
│  │  │         <<artifact>>                                 │      │    │
│  │  │                                                       │      │    │
│  │  │   - Data Files (*.ibd)                               │      │    │
│  │  │   - Index Files                                      │      │    │
│  │  │   - Transaction Logs (redo/undo)                     │      │    │
│  │  │   - Binary Logs                                      │      │    │
│  │  └──────────────────────────────────────────────────────┘      │    │
│  └─────────────────────────────────────────────────────────────────┘    │
└──────────────────────────────────────────────────────────────────────────┘
```

## 2. Deployment Diagram - Network Architecture

```
┌─────────────────────────────────────────────────────────────────────────┐
│                  NETWORK DEPLOYMENT ARCHITECTURE                        │
└─────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────┐
│                         LOCAL AREA NETWORK (LAN)                        │
│                      <<network: Ethernet/WiFi>>                         │
│                                                                          │
│  ┌───────────────────┐      ┌───────────────────┐                      │
│  │   Client PC 1     │      │   Client PC 2     │                      │
│  │   <<device>>      │      │   <<device>>      │                      │
│  │                   │      │                   │                      │
│  │ IP: 192.168.1.10  │      │ IP: 192.168.1.11  │                      │
│  │ OS: Windows 10    │      │ OS: Windows 10    │                      │
│  │                   │      │                   │                      │
│  │ ┌───────────────┐ │      │ ┌───────────────┐ │                      │
│  │ │ JavaFX App    │ │      │ │ JavaFX App    │ │                      │
│  │ │ (JRE 17)      │ │      │ │ (JRE 17)      │ │                      │
│  │ └───────────────┘ │      │ └───────────────┘ │                      │
│  └─────────┬─────────┘      └─────────┬─────────┘                      │
│            │                          │                                 │
│            │ TCP/IP (JDBC)            │ TCP/IP (JDBC)                   │
│            │ Port: 3306               │ Port: 3306                      │
│            │                          │                                 │
│            └──────────┬───────────────┘                                 │
│                       │                                                 │
│                       │                                                 │
│  ┌────────────────────▼────────────────────┐                           │
│  │         Network Switch/Router           │                           │
│  │         <<device>>                      │                           │
│  └────────────────────┬────────────────────┘                           │
│                       │                                                 │
│  ┌───────────────────┐│┌───────────────────┐                           │
│  │   Client PC 3     │││   Client PC 4     │                           │
│  │   <<device>>      │││   <<device>>      │                           │
│  │                   │││                   │                           │
│  │ IP: 192.168.1.12  │││ IP: 192.168.1.13  │                           │
│  │ OS: Windows 11    │││ OS: Linux Ubuntu  │                           │
│  │                   │││                   │                           │
│  │ ┌───────────────┐ │││ ┌───────────────┐ │                           │
│  │ │ JavaFX App    │ │││ │ JavaFX App    │ │                           │
│  │ │ (JRE 17)      │ │││ │ (JRE 17)      │ │                           │
│  │ └───────────────┘ │││ └───────────────┘ │                           │
│  └─────────┬─────────┘│└─────────┬─────────┘                           │
│            │           │          │                                     │
│            └───────────┼──────────┘                                     │
│                        │                                                │
│                        │ TCP/IP (JDBC)                                  │
│                        │ Port: 3306                                     │
│                        │                                                │
│  ┌─────────────────────▼────────────────────┐                          │
│  │       Database Server                    │                          │
│  │       <<device>>                         │                          │
│  │                                           │                          │
│  │ IP: 192.168.1.100                         │                          │
│  │ OS: Ubuntu Server 20.04                   │                          │
│  │                                           │                          │
│  │ ┌───────────────────────────────────┐    │                          │
│  │ │       MySQL Server 8.0            │    │                          │
│  │ │       Port: 3306                  │    │                          │
│  │ │                                   │    │                          │
│  │ │   - db_perpustakaan               │    │                          │
│  │ │   - User: root/perpus_user        │    │                          │
│  │ │   - Max Connections: 100          │    │                          │
│  │ └───────────────────────────────────┘    │                          │
│  └──────────────────────────────────────────┘                          │
│                                                                          │
└─────────────────────────────────────────────────────────────────────────┘
```

## 3. Deployment Diagram - Standalone Mode

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    STANDALONE DEPLOYMENT MODE                           │
│                   (Single PC - Development/Testing)                     │
└─────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────┐
│                      SINGLE WORKSTATION                                 │
│                    <<device: Windows/Linux>>                            │
│                      IP: 127.0.0.1 (localhost)                          │
│                                                                          │
│  ┌───────────────────────────────────────────────────────────────┐     │
│  │                Operating System Layer                          │     │
│  │                                                                │     │
│  │  ┌─────────────────────────────────────────────────────┐      │     │
│  │  │         Java Runtime Environment (JRE 17+)          │      │     │
│  │  │         <<execution environment>>                   │      │     │
│  │  │                                                      │      │     │
│  │  │  ┌────────────────────────────────────────┐         │      │     │
│  │  │  │   Application Process                  │         │      │     │
│  │  │  │   PID: 1234                            │         │      │     │
│  │  │  │                                        │         │      │     │
│  │  │  │   perpustakaan.jar                     │         │      │     │
│  │  │  │   - Controllers                        │         │      │     │
│  │  │  │   - DAO Layer                          │         │      │     │
│  │  │  │   - Models                             │         │      │     │
│  │  │  │   - Utils                              │         │      │     │
│  │  │  │                                        │         │      │     │
│  │  │  │   RAM Usage: ~200MB                    │         │      │     │
│  │  │  └────────────────┬───────────────────────┘         │      │     │
│  │  │                   │                                 │      │     │
│  │  │                   │ JDBC Connection                 │      │     │
│  │  │                   │ localhost:3306                  │      │     │
│  │  │                   │                                 │      │     │
│  │  └───────────────────┼─────────────────────────────────┘      │     │
│  │                      │                                        │     │
│  │  ┌───────────────────▼─────────────────────────────────┐      │     │
│  │  │         MySQL Server 8.0                            │      │     │
│  │  │         <<execution environment>>                   │      │     │
│  │  │         PID: 5678                                   │      │     │
│  │  │                                                      │      │     │
│  │  │  ┌────────────────────────────────────────┐         │      │     │
│  │  │  │   Database Instance                    │         │      │     │
│  │  │  │                                        │         │      │     │
│  │  │  │   db_perpustakaan                      │         │      │     │
│  │  │  │   - Tables                             │         │      │     │
│  │  │  │   - Stored Procedures                  │         │      │     │
│  │  │  │   - Views                              │         │      │     │
│  │  │  │                                        │         │      │     │
│  │  │  │   Storage: ~50MB                       │         │      │     │
│  │  │  │   Location: C:\xampp\mysql\data\       │         │      │     │
│  │  │  └────────────────────────────────────────┘         │      │     │
│  │  └────────────────────────────────────────────────────┘      │     │
│  └───────────────────────────────────────────────────────────────┘     │
│                                                                          │
│  Storage Devices:                                                       │
│  ┌─────────────────────────────────────────────────┐                   │
│  │ C:\kuliah\kkp_app\app_perpustkaan\              │                   │
│  │   - src\                                        │                   │
│  │   - target\perpustakaan.jar                     │                   │
│  │   - database\schema.sql                         │                   │
│  │   - .env (configuration)                        │                   │
│  └─────────────────────────────────────────────────┘                   │
└──────────────────────────────────────────────────────────────────────────┘
```

## 4. Component Distribution

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    COMPONENT DISTRIBUTION DETAILS                       │
└─────────────────────────────────────────────────────────────────────────┘

CLIENT SIDE:
┌─────────────────────────────────────────────────────┐
│ perpustakaan.jar (~15 MB)                           │
├─────────────────────────────────────────────────────┤
│ com/smk/alasiyah/perpustakaan/                      │
│   ├── Main.class                                    │
│   ├── controller/                                   │
│   │   ├── LoginController.class                     │
│   │   ├── MainController.class                      │
│   │   ├── DashboardController.class                 │
│   │   ├── BukuController.class                      │
│   │   ├── SiswaController.class                     │
│   │   ├── GuruController.class                      │
│   │   ├── PeminjamanController.class                │
│   │   ├── PengembalianController.class              │
│   │   └── LaporanController.class                   │
│   ├── dao/                                          │
│   │   ├── UserDAO.class                             │
│   │   ├── BukuDAO.class                             │
│   │   ├── SiswaDAO.class                            │
│   │   ├── GuruDAO.class                             │
│   │   ├── PeminjamanDAO.class                       │
│   │   ├── PengembalianDAO.class                     │
│   │   └── StatistikDAO.class                        │
│   ├── model/                                        │
│   │   ├── User.class                                │
│   │   ├── Buku.class                                │
│   │   ├── Siswa.class                               │
│   │   ├── Guru.class                                │
│   │   ├── Peminjaman.class                          │
│   │   └── Pengembalian.class                        │
│   ├── util/                                         │
│   │   ├── DatabaseConfig.class                      │
│   │   ├── SessionManager.class                      │
│   │   ├── BCryptUtil.class                          │
│   │   └── EnvConfig.class                           │
│   └── view/                                         │
│       ├── login.fxml                                │
│       ├── main.fxml                                 │
│       ├── dashboard.fxml                            │
│       ├── buku.fxml                                 │
│       └── styles.css                                │
│                                                      │
│ External Dependencies:                              │
│   ├── mysql-connector-java-8.0.33.jar (~2.5 MB)    │
│   ├── bcrypt-0.10.2.jar (~60 KB)                    │
│   ├── jasperreports-6.20.0.jar (~5 MB)             │
│   └── javafx-sdk-17 (provided by JRE)              │
└─────────────────────────────────────────────────────┘

SERVER SIDE:
┌─────────────────────────────────────────────────────┐
│ MySQL Database Server                               │
├─────────────────────────────────────────────────────┤
│ Database: db_perpustakaan                           │
│                                                      │
│ Tables (7):                                         │
│   ├── users (authentication & authorization)        │
│   ├── buku (book catalog)                           │
│   ├── siswa (student records)                       │
│   ├── guru (teacher records)                        │
│   ├── peminjaman (borrowing transactions)           │
│   ├── pengembalian (return transactions)            │
│   └── laporan (report history)                      │
│                                                      │
│ Stored Procedures (2):                              │
│   ├── sp_calculate_denda()                          │
│   └── sp_update_stok()                              │
│                                                      │
│ Views (2):                                          │
│   ├── v_peminjaman_detail                           │
│   └── v_statistik_buku                              │
│                                                      │
│ Estimated Size: ~50-100 MB                          │
└─────────────────────────────────────────────────────┘
```

## 5. System Requirements

```
┌─────────────────────────────────────────────────────────────────────────┐
│                        SYSTEM REQUIREMENTS                              │
└─────────────────────────────────────────────────────────────────────────┘

CLIENT REQUIREMENTS:
├── Hardware:
│   ├── Processor: Intel Core i3 or equivalent (minimum)
│   ├── RAM: 4 GB (minimum), 8 GB (recommended)
│   ├── Storage: 500 MB free space
│   └── Display: 1366 x 768 resolution (minimum)
│
├── Software:
│   ├── Operating System:
│   │   ├── Windows 10/11
│   │   ├── Linux (Ubuntu 20.04+)
│   │   └── macOS 10.14+
│   │
│   ├── Java Runtime Environment:
│   │   └── JRE 17 or higher
│   │
│   └── Network:
│       └── TCP/IP connectivity to database server

DATABASE SERVER REQUIREMENTS:
├── Hardware:
│   ├── Processor: Intel Core i5 or equivalent
│   ├── RAM: 8 GB (minimum), 16 GB (recommended)
│   ├── Storage: 10 GB free space (for database + logs)
│   └── Network: Ethernet 100 Mbps (minimum)
│
├── Software:
│   ├── Operating System:
│   │   ├── Windows Server 2016+
│   │   ├── Linux (Ubuntu Server 20.04+)
│   │   └── CentOS 7+
│   │
│   ├── Database:
│   │   ├── MySQL Server 8.0+
│   │   └── MariaDB 10.5+ (alternative)
│   │
│   └── Network:
│       ├── Static IP address (recommended)
│       └── Port 3306 open (MySQL default)

NETWORK REQUIREMENTS:
├── LAN Setup:
│   ├── Bandwidth: 100 Mbps (minimum)
│   ├── Latency: < 50ms
│   └── Protocol: TCP/IP
│
└── Firewall Rules:
    ├── Allow port 3306 (MySQL)
    └── Allow client IP ranges
```

## Catatan Deployment

### Mode Deployment
1. **Standalone Mode** - Satu PC untuk development/testing
2. **LAN Mode** - Multiple clients dengan database server terpisah
3. **Cloud Mode** - Database di cloud (AWS RDS, Azure, etc.)

### Keamanan
- Database menggunakan password terenkripsi (BCrypt)
- Koneksi JDBC menggunakan SSL (production)
- User role-based access control
- Prepared statements untuk mencegah SQL injection

### Backup & Recovery
- Database backup otomatis (daily)
- Transaction logs untuk recovery
- Export data ke Excel/PDF

### Skalabilitas
- Support multiple concurrent users (100+)
- Connection pooling untuk optimasi
- Indexing pada tabel utama

