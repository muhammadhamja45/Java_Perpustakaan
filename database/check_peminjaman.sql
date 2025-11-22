-- Script untuk memeriksa data peminjaman di database
USE perpustakaan_al_asiyah;

-- Tampilkan semua data peminjaman dengan detail
SELECT 
    p.id_pinjam,
    p.tgl_pinjam,
    p.tgl_kembali,
    p.status_pinjam,
    b.judul as nama_buku,
    COALESCE(s.nama, g.nama) as nama_anggota,
    CASE WHEN p.id_siswa IS NOT NULL THEN 'Siswa' ELSE 'Guru' END as jenis_anggota,
    u.nama_lengkap as petugas
FROM peminjaman p
LEFT JOIN buku b ON p.id_buku = b.id_buku
LEFT JOIN users u ON p.id_user = u.id_user
LEFT JOIN siswa s ON p.id_siswa = s.id_siswa
LEFT JOIN guru g ON p.id_guru = g.id_guru
ORDER BY p.tgl_pinjam DESC;

-- Hitung jumlah peminjaman per status
SELECT 
    status_pinjam,
    COUNT(*) as jumlah
FROM peminjaman
GROUP BY status_pinjam;

-- Tampilkan hanya yang status "dipinjam"
SELECT 
    p.id_pinjam,
    p.tgl_pinjam,
    p.tgl_kembali,
    p.status_pinjam,
    b.judul as nama_buku,
    COALESCE(s.nama, g.nama) as nama_anggota
FROM peminjaman p
LEFT JOIN buku b ON p.id_buku = b.id_buku
LEFT JOIN siswa s ON p.id_siswa = s.id_siswa
LEFT JOIN guru g ON p.id_guru = g.id_guru
WHERE p.status_pinjam = 'dipinjam'
ORDER BY p.tgl_pinjam DESC;

