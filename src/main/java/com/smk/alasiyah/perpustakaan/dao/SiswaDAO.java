package com.smk.alasiyah.perpustakaan.dao;

import com.smk.alasiyah.perpustakaan.config.DatabaseConfig;
import com.smk.alasiyah.perpustakaan.model.Siswa;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SiswaDAO {
    
    public List<Siswa> getAll() {
        List<Siswa> list = new ArrayList<>();
        String sql = "SELECT * FROM siswa ORDER BY nama";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(mapResultSetToSiswa(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Siswa> search(String keyword) {
        List<Siswa> list = new ArrayList<>();
        String sql = "SELECT * FROM siswa WHERE nama LIKE ? OR nis LIKE ? OR kelas LIKE ? ORDER BY nama";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToSiswa(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Siswa> filterByStatus(String status) {
        List<Siswa> list = new ArrayList<>();
        String sql = "SELECT * FROM siswa WHERE status = ? ORDER BY nama";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToSiswa(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Siswa getById(int id) {
        String sql = "SELECT * FROM siswa WHERE id_siswa = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToSiswa(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Siswa getByNis(String nis) {
        String sql = "SELECT * FROM siswa WHERE nis = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, nis);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToSiswa(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean create(Siswa siswa) {
        String sql = "INSERT INTO siswa (nis, nama, kelas, alamat, no_telp, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, siswa.getNis());
            stmt.setString(2, siswa.getNama());
            stmt.setString(3, siswa.getKelas());
            stmt.setString(4, siswa.getAlamat());
            stmt.setString(5, siswa.getNoTelp());
            stmt.setString(6, siswa.getStatus());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(Siswa siswa) {
        String sql = "UPDATE siswa SET nama = ?, kelas = ?, alamat = ?, no_telp = ?, status = ? WHERE id_siswa = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, siswa.getNama());
            stmt.setString(2, siswa.getKelas());
            stmt.setString(3, siswa.getAlamat());
            stmt.setString(4, siswa.getNoTelp());
            stmt.setString(5, siswa.getStatus());
            stmt.setInt(6, siswa.getIdSiswa());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(int id) {
        String sql = "DELETE FROM siswa WHERE id_siswa = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private Siswa mapResultSetToSiswa(ResultSet rs) throws SQLException {
        Siswa siswa = new Siswa();
        siswa.setIdSiswa(rs.getInt("id_siswa"));
        siswa.setNis(rs.getString("nis"));
        siswa.setNama(rs.getString("nama"));
        siswa.setKelas(rs.getString("kelas"));
        siswa.setAlamat(rs.getString("alamat"));
        siswa.setNoTelp(rs.getString("no_telp"));
        siswa.setStatus(rs.getString("status"));
        Timestamp ts = rs.getTimestamp("created_at");
        if (ts != null) {
            siswa.setCreatedAt(ts.toLocalDateTime());
        }
        return siswa;
    }
}

