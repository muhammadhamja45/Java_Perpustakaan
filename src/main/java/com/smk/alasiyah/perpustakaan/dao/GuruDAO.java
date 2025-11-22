package com.smk.alasiyah.perpustakaan.dao;

import com.smk.alasiyah.perpustakaan.config.DatabaseConfig;
import com.smk.alasiyah.perpustakaan.model.Guru;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GuruDAO {
    
    public List<Guru> getAll() {
        List<Guru> list = new ArrayList<>();
        String sql = "SELECT * FROM guru ORDER BY nama";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(mapResultSetToGuru(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Guru> search(String keyword) {
        List<Guru> list = new ArrayList<>();
        String sql = "SELECT * FROM guru WHERE nama LIKE ? OR nip LIKE ? OR jabatan LIKE ? ORDER BY nama";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToGuru(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Guru> filterByStatus(String status) {
        List<Guru> list = new ArrayList<>();
        String sql = "SELECT * FROM guru WHERE status = ? ORDER BY nama";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToGuru(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Guru getById(int id) {
        String sql = "SELECT * FROM guru WHERE id_guru = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToGuru(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Guru getByNip(String nip) {
        String sql = "SELECT * FROM guru WHERE nip = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nip);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToGuru(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean create(Guru guru) {
        String sql = "INSERT INTO guru (nip, nama, jabatan, alamat, no_telp, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, guru.getNip());
            stmt.setString(2, guru.getNama());
            stmt.setString(3, guru.getJabatan());
            stmt.setString(4, guru.getAlamat());
            stmt.setString(5, guru.getNoTelp());
            stmt.setString(6, guru.getStatus());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(Guru guru) {
        String sql = "UPDATE guru SET nama = ?, jabatan = ?, alamat = ?, no_telp = ?, status = ? WHERE id_guru = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, guru.getNama());
            stmt.setString(2, guru.getJabatan());
            stmt.setString(3, guru.getAlamat());
            stmt.setString(4, guru.getNoTelp());
            stmt.setString(5, guru.getStatus());
            stmt.setInt(6, guru.getIdGuru());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(int id) {
        String sql = "DELETE FROM guru WHERE id_guru = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private Guru mapResultSetToGuru(ResultSet rs) throws SQLException {
        Guru guru = new Guru();
        guru.setIdGuru(rs.getInt("id_guru"));
        guru.setNip(rs.getString("nip"));
        guru.setNama(rs.getString("nama"));
        guru.setJabatan(rs.getString("jabatan"));
        guru.setAlamat(rs.getString("alamat"));
        guru.setNoTelp(rs.getString("no_telp"));
        guru.setStatus(rs.getString("status"));
        Timestamp ts = rs.getTimestamp("created_at");
        if (ts != null) {
            guru.setCreatedAt(ts.toLocalDateTime());
        }
        return guru;
    }
}

