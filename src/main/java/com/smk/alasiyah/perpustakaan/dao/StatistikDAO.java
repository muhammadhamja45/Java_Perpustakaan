package com.smk.alasiyah.perpustakaan.dao;

import com.smk.alasiyah.perpustakaan.config.DatabaseConfig;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class StatistikDAO {
    
    public int getTotalBuku() {
        String sql = "SELECT COUNT(*) as total FROM buku";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int getBukuTersedia() {
        String sql = "SELECT SUM(stok) as total FROM buku WHERE stok > 0";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int getBukuDipinjam() {
        String sql = "SELECT COUNT(*) as total FROM peminjaman WHERE status_pinjam = 'dipinjam'";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int getTotalSiswa() {
        String sql = "SELECT COUNT(*) as total FROM siswa WHERE status = 'aktif'";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int getTotalGuru() {
        String sql = "SELECT COUNT(*) as total FROM guru WHERE status = 'aktif'";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public Map<String, Integer> getPeminjamanHarian(LocalDate startDate, LocalDate endDate) {
        Map<String, Integer> data = new HashMap<>();
        String sql = "SELECT DATE(tgl_pinjam) as tanggal, COUNT(*) as jumlah " +
                     "FROM peminjaman " +
                     "WHERE tgl_pinjam BETWEEN ? AND ? " +
                     "GROUP BY DATE(tgl_pinjam) " +
                     "ORDER BY tanggal";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                data.put(rs.getDate("tanggal").toString(), rs.getInt("jumlah"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public Map<String, Integer> getPeminjamanMingguan(LocalDate startDate, LocalDate endDate) {
        Map<String, Integer> data = new HashMap<>();
        String sql = "SELECT YEARWEEK(tgl_pinjam) as minggu, COUNT(*) as jumlah " +
                     "FROM peminjaman " +
                     "WHERE tgl_pinjam BETWEEN ? AND ? " +
                     "GROUP BY YEARWEEK(tgl_pinjam) " +
                     "ORDER BY minggu";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                data.put("Minggu " + rs.getInt("minggu"), rs.getInt("jumlah"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public Map<String, Integer> getPeminjamanBulanan(LocalDate startDate, LocalDate endDate) {
        Map<String, Integer> data = new HashMap<>();
        String sql = "SELECT DATE_FORMAT(tgl_pinjam, '%Y-%m') as bulan, COUNT(*) as jumlah " +
                     "FROM peminjaman " +
                     "WHERE tgl_pinjam BETWEEN ? AND ? " +
                     "GROUP BY DATE_FORMAT(tgl_pinjam, '%Y-%m') " +
                     "ORDER BY bulan";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                data.put(rs.getString("bulan"), rs.getInt("jumlah"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}

