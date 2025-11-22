-- Script untuk memperbaiki password di database (PLAIN TEXT)
-- Jalankan script ini di phpMyAdmin atau MySQL Command Line

USE perpustakaan_al_asiyah;

-- Hapus user lama (jika ada)
DELETE FROM users WHERE username IN ('admin', 'petugas');

-- Insert ulang dengan password PLAIN TEXT
-- Password: admin123
INSERT INTO users (username, password, role, nama_lengkap) 
VALUES ('admin', 'admin123', 'admin', 'Administrator');

-- Password: petugas123
INSERT INTO users (username, password, role, nama_lengkap) 
VALUES ('petugas', 'petugas123', 'petugas', 'Petugas Perpustakaan');

-- Verifikasi
SELECT username, role, nama_lengkap FROM users;

