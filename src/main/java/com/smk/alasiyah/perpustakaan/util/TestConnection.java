package com.smk.alasiyah.perpustakaan.util;

import com.smk.alasiyah.perpustakaan.config.DatabaseConfig;
import com.smk.alasiyah.perpustakaan.dao.UserDAO;
import com.smk.alasiyah.perpustakaan.model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Utility untuk test koneksi database dan login
 * Jalankan class ini untuk debug masalah koneksi/login
 */
public class TestConnection {
    public static void main(String[] args) {
        System.out.println("=== Test Koneksi Database ===");
        
        // Test 1: Koneksi Database
        try {
            Connection conn = DatabaseConfig.getConnection();
            System.out.println("✓ Koneksi database berhasil!");
            
            // Test 2: Cek tabel users
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as total FROM users");
            if (rs.next()) {
                System.out.println("✓ Tabel users ditemukan, total rows: " + rs.getInt("total"));
            }
            
            // Test 3: Cek data user
            rs = stmt.executeQuery("SELECT username, password, role FROM users");
            System.out.println("\nData users di database:");
            while (rs.next()) {
                System.out.println("  - Username: " + rs.getString("username"));
                System.out.println("    Password hash: " + rs.getString("password").substring(0, 30) + "...");
                System.out.println("    Role: " + rs.getString("role"));
            }
            
            // Test 4: Test login
            System.out.println("\n=== Test Login ===");
            UserDAO userDAO = new UserDAO();
            
            // Test admin
            User admin = userDAO.login("admin", "admin123");
            if (admin != null) {
                System.out.println("✓ Login admin berhasil!");
                System.out.println("  Nama: " + admin.getNamaLengkap());
                System.out.println("  Role: " + admin.getRole());
            } else {
                System.out.println("✗ Login admin GAGAL!");
            }
            
            // Test petugas
            User petugas = userDAO.login("petugas", "petugas123");
            if (petugas != null) {
                System.out.println("✓ Login petugas berhasil!");
                System.out.println("  Nama: " + petugas.getNamaLengkap());
                System.out.println("  Role: " + petugas.getRole());
            } else {
                System.out.println("✗ Login petugas GAGAL!");
            }
            
            // Test 5: Test BCrypt
            System.out.println("\n=== Test BCrypt ===");
            String testHash = "$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy";
            boolean test1 = BCryptUtil.checkPassword("admin123", testHash);
            boolean test2 = BCryptUtil.checkPassword("petugas123", testHash);
            System.out.println("BCrypt check 'admin123': " + test1);
            System.out.println("BCrypt check 'petugas123': " + test2);
            
            conn.close();
            
        } catch (Exception e) {
            System.err.println("✗ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

