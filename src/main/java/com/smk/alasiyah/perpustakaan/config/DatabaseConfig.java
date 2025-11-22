package com.smk.alasiyah.perpustakaan.config;

import com.smk.alasiyah.perpustakaan.util.EnvConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static String DB_HOST;
    private static String DB_PORT;
    private static String DB_NAME;
    private static String DB_USER;
    private static String DB_PASSWORD;
    
    private static Connection connection;
    
    static {
        // Load konfigurasi dari .env
        EnvConfig.loadEnv();
        DB_HOST = EnvConfig.get("DB_HOST", "localhost");
        DB_PORT = EnvConfig.get("DB_PORT", "3306");
        DB_NAME = EnvConfig.get("DB_NAME", "perpustakaan_al_asiyah");
        DB_USER = EnvConfig.get("DB_USER", "root");
        DB_PASSWORD = EnvConfig.get("DB_PASSWORD", "");
    }
    
    private static String getDBUrl() {
        return "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;
    }
    
    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(getDBUrl(), DB_USER, DB_PASSWORD);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal koneksi ke database: " + e.getMessage());
        }
        return connection;
    }
    
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

