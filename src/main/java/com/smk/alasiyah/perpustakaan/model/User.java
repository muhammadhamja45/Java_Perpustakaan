package com.smk.alasiyah.perpustakaan.model;

import java.time.LocalDateTime;

public class User {
    private int idUser;
    private String username;
    private String password;
    private String role;
    private String namaLengkap;
    private LocalDateTime createdAt;
    
    public User() {}
    
    public User(int idUser, String username, String password, String role, String namaLengkap) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.role = role;
        this.namaLengkap = namaLengkap;
    }
    
    // Getters and Setters
    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }
    
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

