package com.smk.alasiyah.perpustakaan.util;

import com.smk.alasiyah.perpustakaan.model.User;

public class SessionManager {
    private static User currentUser;
    
    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    
    public static User getCurrentUser() {
        return currentUser;
    }
    
    public static void logout() {
        currentUser = null;
    }
    
    public static boolean isLoggedIn() {
        return currentUser != null;
    }
    
    public static boolean isAdmin() {
        return currentUser != null && "admin".equals(currentUser.getRole());
    }
}

