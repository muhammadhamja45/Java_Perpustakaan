package com.smk.alasiyah.perpustakaan.dao;

import com.smk.alasiyah.perpustakaan.config.DatabaseConfig;
import com.smk.alasiyah.perpustakaan.model.User;
import com.smk.alasiyah.perpustakaan.util.BCryptUtil;

import java.sql.*;
import java.time.LocalDateTime;

public class UserDAO {
    
    public User login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                User user = new User();
                user.setIdUser(rs.getInt("id_user"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));
                user.setNamaLengkap(rs.getString("nama_lengkap"));
                Timestamp ts = rs.getTimestamp("created_at");
                if (ts != null) {
                    user.setCreatedAt(ts.toLocalDateTime());
                }
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean createUser(User user) {
        String sql = "INSERT INTO users (username, password, role, nama_lengkap) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword()); // Simpan password plain text
            stmt.setString(3, user.getRole());
            stmt.setString(4, user.getNamaLengkap());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

