package com.smk.alasiyah.perpustakaan.util;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptUtil {
    private static final int ROUNDS = 10;
    
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(ROUNDS));
    }
    
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        try {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        } catch (Exception e) {
            return false;
        }
    }
}

