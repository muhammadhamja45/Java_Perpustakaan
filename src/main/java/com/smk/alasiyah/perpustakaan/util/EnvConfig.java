package com.smk.alasiyah.perpustakaan.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EnvConfig {
    private static Map<String, String> envMap = new HashMap<>();
    private static boolean loaded = false;
    
    public static void loadEnv() {
        if (loaded) return;
        
        try {
            // Coba baca dari .env di root project
            File envFile = new File(".env");
            if (!envFile.exists()) {
                // Coba dari parent directory (jika di target/)
                envFile = new File("../.env");
            }
            
            if (envFile.exists()) {
                Scanner scanner = new Scanner(envFile);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine().trim();
                    // Skip komentar dan baris kosong
                    if (line.isEmpty() || line.startsWith("#")) {
                        continue;
                    }
                    // Parse key=value
                    if (line.contains("=")) {
                        String[] parts = line.split("=", 2);
                        if (parts.length >= 1) {
                            String key = parts[0].trim();
                            String value = parts.length == 2 ? parts[1].trim() : "";
                            envMap.put(key, value);
                        }
                    }
                }
                scanner.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File .env tidak ditemukan, menggunakan default values");
        }
        
        loaded = true;
    }
    
    public static String get(String key) {
        if (!loaded) {
            loadEnv();
        }
        return envMap.get(key);
    }
    
    public static String get(String key, String defaultValue) {
        String value = get(key);
        return value != null ? value : defaultValue;
    }
}

