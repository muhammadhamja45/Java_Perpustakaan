package com.smk.alasiyah.perpustakaan.dao;

import com.smk.alasiyah.perpustakaan.config.DatabaseConfig;
import com.smk.alasiyah.perpustakaan.model.Buku;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BukuDAO {
    
    public List<Buku> getAll() {
        List<Buku> list = new ArrayList<>();
        String sql = "SELECT * FROM buku ORDER BY judul";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(mapResultSetToBuku(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Buku> search(String keyword) {
        List<Buku> list = new ArrayList<>();
        String sql = "SELECT * FROM buku WHERE judul LIKE ? OR pengarang LIKE ? OR kode_buku LIKE ? ORDER BY judul";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToBuku(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Buku> filterByKategori(String kategori) {
        List<Buku> list = new ArrayList<>();
        String sql = "SELECT * FROM buku WHERE kategori = ? ORDER BY judul";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, kategori);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToBuku(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<String> getAllKategori() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT DISTINCT kategori FROM buku WHERE kategori IS NOT NULL ORDER BY kategori";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(rs.getString("kategori"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Buku getById(int id) {
        String sql = "SELECT * FROM buku WHERE id_buku = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToBuku(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Buku getByKode(String kodeBuku) {
        String sql = "SELECT * FROM buku WHERE kode_buku = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, kodeBuku);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToBuku(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean create(Buku buku) {
        String sql = "INSERT INTO buku (kode_buku, judul, pengarang, penerbit, tahun, kategori, stok, lokasi_rak) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, buku.getKodeBuku());
            stmt.setString(2, buku.getJudul());
            stmt.setString(3, buku.getPengarang());
            stmt.setString(4, buku.getPenerbit());
            if (buku.getTahun() != null) {
                stmt.setInt(5, buku.getTahun());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }
            stmt.setString(6, buku.getKategori());
            stmt.setInt(7, buku.getStok());
            stmt.setString(8, buku.getLokasiRak());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean update(Buku buku) {
        String sql = "UPDATE buku SET judul = ?, pengarang = ?, penerbit = ?, tahun = ?, kategori = ?, stok = ?, lokasi_rak = ? WHERE id_buku = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, buku.getJudul());
            stmt.setString(2, buku.getPengarang());
            stmt.setString(3, buku.getPenerbit());
            if (buku.getTahun() != null) {
                stmt.setInt(4, buku.getTahun());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }
            stmt.setString(5, buku.getKategori());
            stmt.setInt(6, buku.getStok());
            stmt.setString(7, buku.getLokasiRak());
            stmt.setInt(8, buku.getIdBuku());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean delete(int id) {
        String sql = "DELETE FROM buku WHERE id_buku = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean updateStok(int idBuku, int perubahan) {
        String sql = "UPDATE buku SET stok = stok + ? WHERE id_buku = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, perubahan);
            stmt.setInt(2, idBuku);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private Buku mapResultSetToBuku(ResultSet rs) throws SQLException {
        Buku buku = new Buku();
        buku.setIdBuku(rs.getInt("id_buku"));
        buku.setKodeBuku(rs.getString("kode_buku"));
        buku.setJudul(rs.getString("judul"));
        buku.setPengarang(rs.getString("pengarang"));
        buku.setPenerbit(rs.getString("penerbit"));
        int tahun = rs.getInt("tahun");
        if (!rs.wasNull()) {
            buku.setTahun(tahun);
        }
        buku.setKategori(rs.getString("kategori"));
        buku.setStok(rs.getInt("stok"));
        buku.setLokasiRak(rs.getString("lokasi_rak"));
        Timestamp ts = rs.getTimestamp("created_at");
        if (ts != null) {
            buku.setCreatedAt(ts.toLocalDateTime());
        }
        return buku;
    }
}

