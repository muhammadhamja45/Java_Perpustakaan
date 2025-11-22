CREATE DATABASE IF NOT EXISTS perpustakaan_al_asiyah;
USE perpustakaan_al_asiyah;

CREATE TABLE users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('admin', 'petugas') NOT NULL DEFAULT 'petugas',
    nama_lengkap VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE siswa (
    id_siswa INT AUTO_INCREMENT PRIMARY KEY,
    nis VARCHAR(20) UNIQUE NOT NULL,
    nama VARCHAR(100) NOT NULL,
    kelas VARCHAR(50) NOT NULL,
    alamat TEXT,
    no_telp VARCHAR(20),
    status ENUM('aktif','nonaktif') DEFAULT 'aktif',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE guru (
    id_guru INT AUTO_INCREMENT PRIMARY KEY,
    nip VARCHAR(20) UNIQUE NOT NULL,
    nama VARCHAR(100) NOT NULL,
    jabatan VARCHAR(100),
    alamat TEXT,
    no_telp VARCHAR(20),
    status ENUM('aktif','nonaktif') DEFAULT 'aktif',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE buku (
    id_buku INT AUTO_INCREMENT PRIMARY KEY,
    kode_buku VARCHAR(50) UNIQUE NOT NULL,
    judul VARCHAR(150) NOT NULL,
    pengarang VARCHAR(100),
    penerbit VARCHAR(100),
    tahun YEAR,
    kategori VARCHAR(50),
    stok INT NOT NULL DEFAULT 1,
    lokasi_rak VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE peminjaman (
    id_pinjam INT AUTO_INCREMENT PRIMARY KEY,
    id_user INT NOT NULL,
    id_buku INT NOT NULL,
    id_siswa INT DEFAULT NULL,
    id_guru INT DEFAULT NULL,
    tgl_pinjam DATE NOT NULL,
    tgl_kembali DATE NOT NULL,
    status_pinjam ENUM('dipinjam','dikembalikan') DEFAULT 'dipinjam',
    FOREIGN KEY (id_user) REFERENCES users(id_user),
    FOREIGN KEY (id_buku) REFERENCES buku(id_buku),
    FOREIGN KEY (id_siswa) REFERENCES siswa(id_siswa),
    FOREIGN KEY (id_guru) REFERENCES guru(id_guru)
);

CREATE TABLE pengembalian (
    id_kembali INT AUTO_INCREMENT PRIMARY KEY,
    id_pinjam INT NOT NULL,
    tgl_dikembalikan DATE NOT NULL,
    denda DECIMAL(10,2) DEFAULT 0,
    FOREIGN KEY (id_pinjam) REFERENCES peminjaman(id_pinjam)
);

CREATE TABLE laporan (
    id_laporan INT AUTO_INCREMENT PRIMARY KEY,
    periode ENUM('harian','mingguan','bulanan') NOT NULL,
    total_pinjam INT DEFAULT 0,
    total_kembali INT DEFAULT 0,
    total_denda DECIMAL(10,2) DEFAULT 0,
    tanggal_laporan DATE NOT NULL
);

-- Insert default admin user (password: admin123) - PLAIN TEXT
INSERT INTO users (username, password, role, nama_lengkap) 
VALUES ('admin', 'admin123', 'admin', 'Administrator');

-- Insert default petugas user (password: petugas123) - PLAIN TEXT
INSERT INTO users (username, password, role, nama_lengkap) 
VALUES ('petugas', 'petugas123', 'petugas', 'Petugas Perpustakaan');

