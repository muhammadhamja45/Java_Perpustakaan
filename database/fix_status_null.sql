-- Script untuk memperbaiki status NULL menjadi "dipinjam"
USE perpustakaan_al_asiyah;

-- Update semua peminjaman yang statusnya NULL menjadi "dipinjam"
UPDATE peminjaman 
SET status_pinjam = 'dipinjam' 
WHERE status_pinjam IS NULL;

-- Verifikasi hasil
SELECT 
    id_pinjam,
    tgl_pinjam,
    status_pinjam,
    CASE 
        WHEN status_pinjam IS NULL THEN 'NULL (perlu diperbaiki)'
        WHEN status_pinjam = 'dipinjam' THEN '✓ Dipinjam'
        WHEN status_pinjam = 'dikembalikan' THEN '✓ Dikembalikan'
        ELSE status_pinjam
    END as status_info
FROM peminjaman
ORDER BY tgl_pinjam DESC;

