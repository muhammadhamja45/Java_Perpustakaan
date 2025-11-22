package com.smk.alasiyah.perpustakaan.util;

/**
 * Utility untuk generate password hash BCrypt
 * Jalankan class ini untuk mendapatkan hash password baru
 */
public class PasswordGenerator {
    public static void main(String[] args) {
        // Generate hash untuk password "admin123"
        String hashAdmin = BCryptUtil.hashPassword("admin123");
        System.out.println("Hash untuk 'admin123':");
        System.out.println(hashAdmin);
        System.out.println();
        
        // Generate hash untuk password "petugas123"
        String hashPetugas = BCryptUtil.hashPassword("petugas123");
        System.out.println("Hash untuk 'petugas123':");
        System.out.println(hashPetugas);
        System.out.println();
        
        // Test verify
        System.out.println("Test verify:");
        System.out.println("admin123 matches: " + BCryptUtil.checkPassword("admin123", hashAdmin));
        System.out.println("petugas123 matches: " + BCryptUtil.checkPassword("petugas123", hashPetugas));
    }
}

