package com.smk.alasiyah.perpustakaan.dao;

import com.smk.alasiyah.perpustakaan.config.DatabaseConfig;
import com.smk.alasiyah.perpustakaan.model.Pengembalian;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PengembalianDAO {
    
    public boolean create(Pengembalian pengembalian) {
        String sql = "INSERT INTO pengembalian (id_pinjam, tgl_dikembalikan, denda) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, pengembalian.getIdPinjam());
            stmt.setDate(2, Date.valueOf(pengembalian.getTglDikembalikan()));
            stmt.setDouble(3, pengembalian.getDenda());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Pengembalian> getAll() {
        List<Pengembalian> list = new ArrayList<>();
        String sql = "SELECT peng.*, b.judul as nama_buku, " +
                     "COALESCE(s.nama, g.nama) as nama_anggota " +
                     "FROM pengembalian peng " +
                     "JOIN peminjaman p ON peng.id_pinjam = p.id_pinjam " +
                     "LEFT JOIN buku b ON p.id_buku = b.id_buku " +
                     "LEFT JOIN siswa s ON p.id_siswa = s.id_siswa " +
                     "LEFT JOIN guru g ON p.id_guru = g.id_guru " +
                     "ORDER BY peng.tgl_dikembalikan DESC";
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                list.add(mapResultSetToPengembalian(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    private Pengembalian mapResultSetToPengembalian(ResultSet rs) throws SQLException {
        Pengembalian peng = new Pengembalian();
        peng.setIdKembali(rs.getInt("id_kembali"));
        peng.setIdPinjam(rs.getInt("id_pinjam"));
        Date tgl = rs.getDate("tgl_dikembalikan");
        if (tgl != null) {
            peng.setTglDikembalikan(tgl.toLocalDate());
        }
        peng.setDenda(rs.getDouble("denda"));
        peng.setNamaBuku(rs.getString("nama_buku"));
        peng.setNamaAnggota(rs.getString("nama_anggota"));
        return peng;
    }
}

