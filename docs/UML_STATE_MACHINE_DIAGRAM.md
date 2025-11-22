# UML State Machine Diagram - Sistem Perpustakaan SMK AL-ASIYAH

## 1. State Machine Diagram - User Session

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    STATE MACHINE - USER SESSION                         │
└─────────────────────────────────────────────────────────────────────────┘

                        [Application Start]
                               │
                               ▼
                       ┌───────────────┐
                       │ Initial State │
                       └───────┬───────┘
                               │
                               │ launch app
                               │
                               ▼
                       ┌───────────────┐
                    ┌──┤  Not Logged   │
                    │  │     In        │
                    │  └───────┬───────┘
                    │          │
                    │          │ display login form
                    │          │
                    │          ▼
                    │  ┌───────────────┐
                    │  │  Authenticating│◄──────┐
                    │  └───────┬───────┘       │
                    │          │               │
                    │          │ enter          │
                    │          │ credentials    │
                    │          │               │
                    │          ▼               │
                    │  ┌───────────────┐       │
                    │  │  Validating   │       │
                    │  └───────┬───────┘       │
                    │          │               │
                    │          │               │
                    │     ┌────┴─────┐         │
                    │     │          │         │
     [Invalid]      │     │ valid?   │         │ retry
     credentials    │     │          │         │
                    │     └────┬─────┘         │
                    │          │               │
                    │          │ invalid        │
                    │          │               │
                    └──────────┴───────────────┘
                               │
                               │ valid
                               │
                               ▼
                       ┌───────────────┐
                       │  Logged In    │
                       └───────┬───────┘
                               │
                               │ create session
                               │
                               ▼
                       ┌───────────────┐
                       │    Active     │◄────────────────┐
                       │   Session     │                 │
                       └───────┬───────┘                 │
                               │                         │
                         ┌─────┴─────┐                   │
                         │           │                   │
                         │ performing │                   │
                         │ actions   │                   │
                         │           │                   │
                    ┌────▼────┐ ┌────▼────┐              │
                    │ CRUD    │ │ View    │              │
                    │ Operations│ │ Reports │              │
                    └────┬────┘ └────┬────┘              │
                         │           │                   │
                         └─────┬─────┘                   │
                               │                         │
                               │ continue                │
                               │                         │
                               └─────────────────────────┘
                               │
                               │ logout
                               │
                               ▼
                       ┌───────────────┐
                       │  Logging Out  │
                       └───────┬───────┘
                               │
                               │ clear session
                               │
                               ▼
                       ┌───────────────┐
                       │ Session Ended │
                       └───────┬───────┘
                               │
                               ▼
                         [End State]
```

## 2. State Machine Diagram - Peminjaman Buku

```
┌─────────────────────────────────────────────────────────────────────────┐
│                STATE MACHINE - PEMINJAMAN BUKU                          │
└─────────────────────────────────────────────────────────────────────────┘

                        [Create Peminjaman]
                               │
                               ▼
                       ┌───────────────┐
                       │   Initial     │
                       └───────┬───────┘
                               │
                               │ user clicks "Tambah Peminjaman"
                               │
                               ▼
                       ┌───────────────┐
                       │  Selecting    │
                       │   Member      │
                       └───────┬───────┘
                               │
                               │ select member (Siswa/Guru)
                               │
                               ▼
                       ┌───────────────┐
                       │  Selecting    │
                       │    Book       │
                       └───────┬───────┘
                               │
                               │ select book
                               │
                               ▼
                       ┌───────────────┐
                       │  Validating   │◄───────────┐
                       │    Stock      │            │
                       └───────┬───────┘            │
                               │                    │
                          ┌────┴─────┐              │
                          │          │              │
                          │ stok > 0?│              │
                          │          │              │
                     ┌────┴────┐     └──────┐       │
                     │         │            │       │
                  [Stok = 0]   │ valid      │       │ retry
                     │         │            │       │
                     │         ▼            │       │
                     │  ┌───────────────┐   │       │
                     │  │   Creating    │   │       │
                     │  │  Peminjaman   │   │       │
                     │  └───────┬───────┘   │       │
                     │          │           │       │
                     │          │ save data  │       │
                     │          │           │       │
                     │          ▼           │       │
                     │  ┌───────────────┐   │       │
                     │  │  Dipinjam     │   │       │
                     │  │               │   │       │
                     │  │ - tglPinjam   │   │       │
                     │  │ - tglKembali  │   │       │
                     │  │ - status:     │   │       │
                     │  │   "dipinjam"  │   │       │
                     │  └───────┬───────┘   │       │
                     │          │           │       │
                     │          │ update stok│      │
                     │          │ (stok-1)  │      │
                     │          │           │       │
                     │          ▼           │       │
                     │  ┌───────────────┐   │       │
                     │  │   Waiting     │   │       │
                     │  │   Return      │   │       │
                     │  └───────┬───────┘   │       │
                     │          │           │       │
                     │          │           │       │
                     │     ┌────┴─────┐     │       │
                     │     │          │     │       │
                     │     │ waiting  │     │       │
                     │     │ for      │     │       │
                     │     │ return   │     │       │
                     │     │          │     │       │
                     │     └────┬─────┘     │       │
                     │          │           │       │
                     │          │ process return    │
                     │          │           │       │
                     │          ▼           │       │
                     │  ┌───────────────┐   │       │
                     │  │  Processing   │   │       │
                     │  │    Return     │   │       │
                     │  └───────┬───────┘   │       │
                     │          │           │       │
                     │          │ calculate denda   │
                     │          │           │       │
                     │          ▼           │       │
                     │  ┌───────────────┐   │       │
                     │  │ Dikembalikan  │   │       │
                     │  │               │   │       │
                     │  │ - status:     │   │       │
                     │  │  "dikembalikan"│  │       │
                     │  │ - denda: Rp   │   │       │
                     │  └───────┬───────┘   │       │
                     │          │           │       │
                     │          │ update stok│      │
                     │          │ (stok+1)  │      │
                     │          │           │       │
                     │          ▼           │       │
                     │  ┌───────────────┐   │       │
                     │  │   Completed   │   │       │
                     │  └───────┬───────┘   │       │
                     │          │           │       │
                     │          ▼           │       │
                     │      [End State]     │       │
                     │                      │       │
                     ▼                      │       │
              ┌───────────────┐             │       │
              │   Cancelled   │             │       │
              │ (Stok Habis)  │◄────────────┘       │
              └───────┬───────┘                     │
                      │                             │
                      │ notify user                 │
                      │                             │
                      ▼                             │
                 [End State]                        │
                                                    │
              [Error State] ◄─────────────────────┘
                      │
                      │ show error message
                      │
                      ▼
                 [End State]
```

## 3. State Machine Diagram - Buku

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    STATE MACHINE - BUKU                                 │
└─────────────────────────────────────────────────────────────────────────┘

                        [Create Book]
                               │
                               ▼
                       ┌───────────────┐
                       │   New Book    │
                       │  (Stok = 0)   │
                       └───────┬───────┘
                               │
                               │ add book
                               │ set initial stock
                               │
                               ▼
                       ┌───────────────┐
                       │   Available   │
                       │  (Stok > 0)   │◄────────────┐
                       └───────┬───────┘             │
                               │                     │
                          ┌────┴─────┐               │
                          │          │               │
                     borrowed        │ returned      │
                          │          │               │
                          ▼          │               │
                       ┌───────────────┐             │
                       │   Borrowed    │             │
                       │  (Stok -= 1)  │─────────────┘
                       └───────┬───────┘
                               │
                               │
                          ┌────┴─────┐
                          │          │
                     all borrowed    │ some available
                          │          │
                          ▼          ▼
                       ┌───────────────┐
                       │  Out of Stock │
                       │  (Stok = 0)   │
                       └───────┬───────┘
                               │
                               │ returned
                               │
                               ▼
                       ┌───────────────┐
                       │ Restocking    │
                       │  (Stok += 1)  │
                       └───────┬───────┘
                               │
                               ▼
                       ┌───────────────┐
                       │   Available   │
                       │  (Stok > 0)   │
                       └───────┬───────┘
                               │
                               │ delete book
                               │
                               ▼
                       ┌───────────────┐
                       │   Archived    │
                       └───────┬───────┘
                               │
                               ▼
                         [End State]
```

## 4. State Machine Diagram - User Account

```
┌─────────────────────────────────────────────────────────────────────────┐
│                  STATE MACHINE - USER ACCOUNT                           │
└─────────────────────────────────────────────────────────────────────────┘

                        [Create User]
                               │
                               ▼
                       ┌───────────────┐
                       │   Creating    │
                       └───────┬───────┘
                               │
                               │ admin creates user
                               │ set username & password
                               │
                               ▼
                       ┌───────────────┐
                       │    Active     │◄────────────┐
                       └───────┬───────┘             │
                               │                     │
                          ┌────┴─────┐               │
                          │          │               │
                     login failed    │ login success │
                    (3 attempts)     │               │
                          │          │               │
                          ▼          ▼               │
                       ┌───────────────┐             │
                       │   Locked      │             │
                       └───────┬───────┘             │
                               │                     │
                               │ admin unlock        │
                               │                     │
                               └─────────────────────┘
                               │
                               │ admin deactivate
                               │
                               ▼
                       ┌───────────────┐
                       │  Inactive     │
                       └───────┬───────┘
                               │
                               │ admin reactivate
                               │
                               ▼
                       ┌───────────────┐
                       │    Active     │
                       └───────┬───────┘
                               │
                               │ admin delete
                               │
                               ▼
                       ┌───────────────┐
                       │   Deleted     │
                       └───────┬───────┘
                               │
                               ▼
                         [End State]
```

## 5. State Machine Diagram - Laporan (Report)

```
┌─────────────────────────────────────────────────────────────────────────┐
│                    STATE MACHINE - LAPORAN                              │
└─────────────────────────────────────────────────────────────────────────┘

                        [Request Report]
                               │
                               ▼
                       ┌───────────────┐
                       │  Configuring  │
                       │               │
                       │ - select type │
                       │ - set period  │
                       │ - set format  │
                       └───────┬───────┘
                               │
                               │ submit request
                               │
                               ▼
                       ┌───────────────┐
                       │  Validating   │
                       │  Parameters   │
                       └───────┬───────┘
                               │
                          ┌────┴─────┐
                          │          │
                     invalid        │ valid
                          │          │
                          ▼          ▼
                   ┌───────────────┐
              ┌────│   Error       │
              │    └───────────────┘
              │            │
              │            ▼
              │      [End State]
              │
              │    ┌───────────────┐
              └───►│ Querying Data │
                   └───────┬───────┘
                           │
                           │ fetch from database
                           │
                           ▼
                   ┌───────────────┐
                   │ Processing    │
                   │ Data          │
                   └───────┬───────┘
                           │
                           │ format data
                           │ apply filters
                           │
                           ▼
                   ┌───────────────┐
                   │  Generating   │
                   │  Report       │
                   └───────┬───────┘
                           │
                      ┌────┴─────┐
                      │          │
                 PDF format      │ Excel format
                      │          │
                      ▼          ▼
                   ┌───────────────┐
                   │   Generated   │
                   │               │
                   │ - report.pdf  │
                   │ - report.xlsx │
                   └───────┬───────┘
                           │
                           │ save to database
                           │
                           ▼
                   ┌───────────────┐
                   │    Stored     │
                   └───────┬───────┘
                           │
                           │ display/download
                           │
                           ▼
                   ┌───────────────┐
                   │   Delivered   │
                   └───────┬───────┘
                           │
                           │ user downloads
                           │
                           ▼
                   ┌───────────────┐
                   │   Completed   │
                   └───────┬───────┘
                           │
                           ▼
                      [End State]
```

## 6. State Machine Diagram - Pengembalian Buku

```
┌─────────────────────────────────────────────────────────────────────────┐
│              STATE MACHINE - PENGEMBALIAN BUKU                          │
└─────────────────────────────────────────────────────────────────────────┘

                        [Peminjaman Status: "dipinjam"]
                               │
                               ▼
                       ┌───────────────┐
                       │   Pending     │
                       │   Return      │
                       │               │
                       │ - waiting for │
                       │   return      │
                       └───────┬───────┘
                               │
                               │ petugas selects
                               │ peminjaman to return
                               │
                               ▼
                       ┌───────────────┐
                       │  Checking     │
                       │  Due Date     │
                       └───────┬───────┘
                               │
                          ┌────┴─────┐
                          │          │
                    on time         │ late
                          │          │
                          ▼          ▼
                   ┌───────────────┐
                   │ Calculating   │
                   │ Denda = 0     │
                   └───────┬───────┘
                           │
                           │
                   ┌───────────────┐
                   │ Calculating   │
                   │ Denda = days  │
                   │  × Rp 1000    │
                   └───────┬───────┘
                           │
                           └────┬─────┘
                                │
                                │ confirm return
                                │
                                ▼
                       ┌───────────────┐
                       │  Processing   │
                       │  Return       │
                       └───────┬───────┘
                               │
                               │ create pengembalian record
                               │ update peminjaman status
                               │
                               ▼
                       ┌───────────────┐
                       │   Returned    │
                       │               │
                       │ - status:     │
                       │  "dikembalikan"│
                       │ - denda: Rp   │
                       └───────┬───────┘
                               │
                               │ update book stock
                               │
                               ▼
                       ┌───────────────┐
                       │  Stock Updated│
                       │  (stok += 1)  │
                       └───────┬───────┘
                               │
                          ┌────┴─────┐
                          │          │
                     denda > 0      │ no denda
                          │          │
                          ▼          ▼
                   ┌───────────────┐
                   │  Waiting      │
                   │  Payment      │
                   └───────┬───────┘
                           │
                           │ payment received
                           │
                           ▼
                   ┌───────────────┐
                   │    Paid       │
                   └───────┬───────┘
                           │
                           └────┬─────┘
                                │
                                ▼
                       ┌───────────────┐
                       │   Completed   │
                       └───────┬───────┘
                               │
                               ▼
                         [End State]
```

## Catatan Penting

### State vs Activity
- **State** - Kondisi object pada waktu tertentu
- **Activity** - Proses/aksi yang dilakukan

### Komponen State Machine
1. **Initial State** - Titik awal (●)
2. **Final State** - Titik akhir (◉)
3. **State** - Kondisi object
4. **Transition** - Perpindahan antar state
5. **Event** - Trigger transition
6. **Guard Condition** - Kondisi untuk transition

### Karakteristik
- **Sequential** - State berurutan
- **Conditional** - Transition berdasarkan kondisi
- **Event-driven** - Digerakkan oleh event
- **Single State** - Object hanya di 1 state pada satu waktu

