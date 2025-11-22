package com.smk.alasiyah.perpustakaan.dao;

import com.smk.alasiyah.perpustakaan.config.DatabaseConfig;
import com.smk.alasiyah.perpustakaan.model.Peminjaman;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PeminjamanDAO {
    
    public List<Peminjaman> getAll() {
        List<Peminjaman> list = new ArrayList<>();
        String sql = "SELECT p.*, b.judul as nama_buku, u.nama_lengkap as nama_petugas, " +
                     "COALESCE(s.nama, g.nama) as nama_anggota, " +
                     "CASE WHEN p.id_siswa IS NOT NULL THEN 'Siswa' ELSE 'Guru' END as jenis_anggota " +
                     "FROM peminjaman p " +
                     "LEFT JOIN buku b ON p.id_buku = b.id_buku " +
                     "LEFT JOIN users u ON p.id_user = u.id_user " +
                     "LEFT JOIN siswa s ON p.id_siswa = s.id_siswa " +
                     "LEFT JOIN guru g ON p.id_guru = g.id_guru " +
                     "ORDER BY p.tgl_pinjam DESC";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(mapResultSetToPeminjaman(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Peminjaman> search(String keyword) {
        List<Peminjaman> list = new ArrayList<>();
        String sql = "SELECT p.*, b.judul as nama_buku, u.nama_lengkap as nama_petugas, " +
                     "COALESCE(s.nama, g.nama) as nama_anggota, " +
                     "CASE WHEN p.id_siswa IS NOT NULL THEN 'Siswa' ELSE 'Guru' END as jenis_anggota " +
                     "FROM peminjaman p " +
                     "LEFT JOIN buku b ON p.id_buku = b.id_buku " +
                     "LEFT JOIN users u ON p.id_user = u.id_user " +
                     "LEFT JOIN siswa s ON p.id_siswa = s.id_siswa " +
                     "LEFT JOIN guru g ON p.id_guru = g.id_guru " +
                     "WHERE b.judul LIKE ? OR COALESCE(s.nama, g.nama) LIKE ? " +
                     "ORDER BY p.tgl_pinjam DESC";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToPeminjaman(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Peminjaman> filterByStatus(String status) {
        List<Peminjaman> list = new ArrayList<>();
        // Query yang menangani NULL sebagai "dipinjam" (karena NULL berarti belum dikembalikan)
        String sql = "SELECT p.*, b.judul as nama_buku, u.nama_lengkap as nama_petugas, " +
                     "COALESCE(s.nama, g.nama) as nama_anggota, " +
                     "CASE WHEN p.id_siswa IS NOT NULL THEN 'Siswa' ELSE 'Guru' END as jenis_anggota " +
                     "FROM peminjaman p " +
                     "LEFT JOIN buku b ON p.id_buku = b.id_buku " +
                     "LEFT JOIN users u ON p.id_user = u.id_user " +
                     "LEFT JOIN siswa s ON p.id_siswa = s.id_siswa " +
                     "LEFT JOIN guru g ON p.id_guru = g.id_guru " +
                     "WHERE (LOWER(COALESCE(p.status_pinjam, 'dipinjam')) = LOWER(?) OR " +
                     "       (p.status_pinjam IS NULL AND LOWER(?) = 'dipinjam')) " +
                     "ORDER BY p.tgl_pinjam DESC";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            System.out.println("🔍 Mencari peminjaman dengan status: '" + status + "' (termasuk NULL)");
            stmt.setString(1, status);
            stmt.setString(2, status);
            ResultSet rs = stmt.executeQuery();
            
            int count = 0;
            while (rs.next()) {
                Peminjaman p = mapResultSetToPeminjaman(rs);
                list.add(p);
                count++;
                System.out.println("  ✓ Ditemukan: ID=" + p.getIdPinjam() + ", Buku=" + p.getNamaBuku() + ", Status=" + (p.getStatusPinjam() != null ? p.getStatusPinjam() : "NULL (dianggap dipinjam)"));
            }
            System.out.println("📊 Total data ditemukan: " + count);
        } catch (SQLException e) {
            System.err.println("❌ Error saat filter by status: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Peminjaman> filterByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Peminjaman> list = new ArrayList<>();
        String sql = "SELECT p.*, b.judul as nama_buku, u.nama_lengkap as nama_petugas, " +
                     "COALESCE(s.nama, g.nama) as nama_anggota, " +
                     "CASE WHEN p.id_siswa IS NOT NULL THEN 'Siswa' ELSE 'Guru' END as jenis_anggota " +
                     "FROM peminjaman p " +
                     "LEFT JOIN buku b ON p.id_buku = b.id_buku " +
                     "LEFT JOIN users u ON p.id_user = u.id_user " +
                     "LEFT JOIN siswa s ON p.id_siswa = s.id_siswa " +
                     "LEFT JOIN guru g ON p.id_guru = g.id_guru " +
                     "WHERE p.tgl_pinjam BETWEEN ? AND ? " +
                     "ORDER BY p.tgl_pinjam DESC";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToPeminjaman(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Peminjaman getById(int id) {
        String sql = "SELECT p.*, b.judul as nama_buku, u.nama_lengkap as nama_petugas, " +
                     "COALESCE(s.nama, g.nama) as nama_anggota, " +
                     "CASE WHEN p.id_siswa IS NOT NULL THEN 'Siswa' ELSE 'Guru' END as jenis_anggota " +
                     "FROM peminjaman p " +
                     "LEFT JOIN buku b ON p.id_buku = b.id_buku " +
                     "LEFT JOIN users u ON p.id_user = u.id_user " +
                     "LEFT JOIN siswa s ON p.id_siswa = s.id_siswa " +
                     "LEFT JOIN guru g ON p.id_guru = g.id_guru " +
                     "WHERE p.id_pinjam = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToPeminjaman(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean create(Peminjaman peminjaman) {
        String sql = "INSERT INTO peminjaman (id_user, id_buku, id_siswa, id_guru, tgl_pinjam, tgl_kembali, status_pinjam) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, peminjaman.getIdUser());
            stmt.setInt(2, peminjaman.getIdBuku());
            if (peminjaman.getIdSiswa() != null) {
                stmt.setInt(3, peminjaman.getIdSiswa());
            } else {
                stmt.setNull(3, Types.INTEGER);
            }
            if (peminjaman.getIdGuru() != null) {
                stmt.setInt(4, peminjaman.getIdGuru());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.setDate(5, Date.valueOf(peminjaman.getTglPinjam()));
            stmt.setDate(6, Date.valueOf(peminjaman.getTglKembali()));
            // Pastikan status tidak null, default ke "dipinjam"
            String status = peminjaman.getStatusPinjam();
            if (status == null || status.trim().isEmpty()) {
                status = "dipinjam";
            }
            stmt.setString(7, status);
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateStatus(int idPinjam, String status) {
        String sql = "UPDATE peminjaman SET status_pinjam = ? WHERE id_pinjam = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status);
            stmt.setInt(2, idPinjam);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private Peminjaman mapResultSetToPeminjaman(ResultSet rs) throws SQLException {
        Peminjaman p = new Peminjaman();
        p.setIdPinjam(rs.getInt("id_pinjam"));
        p.setIdUser(rs.getInt("id_user"));
        p.setIdBuku(rs.getInt("id_buku"));
        int idSiswa = rs.getInt("id_siswa");
        if (!rs.wasNull()) {
            p.setIdSiswa(idSiswa);
        }
        int idGuru = rs.getInt("id_guru");
        if (!rs.wasNull()) {
            p.setIdGuru(idGuru);
        }
        Date tglPinjam = rs.getDate("tgl_pinjam");
        if (tglPinjam != null) {
            p.setTglPinjam(tglPinjam.toLocalDate());
        }
        Date tglKembali = rs.getDate("tgl_kembali");
        if (tglKembali != null) {
            p.setTglKembali(tglKembali.toLocalDate());
        }
        p.setStatusPinjam(rs.getString("status_pinjam"));
        p.setNamaBuku(rs.getString("nama_buku"));
        p.setNamaAnggota(rs.getString("nama_anggota"));
        p.setJenisAnggota(rs.getString("jenis_anggota"));
        p.setNamaPetugas(rs.getString("nama_petugas"));
        return p;
    }
}

