package com.smk.alasiyah.perpustakaan.model;

import java.time.LocalDateTime;

public class Guru {
    private int idGuru;
    private String nip;
    private String nama;
    private String jabatan;
    private String alamat;
    private String noTelp;
    private String status;
    private LocalDateTime createdAt;
    
    public Guru() {}
    
    public Guru(String nip, String nama, String jabatan, String alamat, String noTelp) {
        this.nip = nip;
        this.nama = nama;
        this.jabatan = jabatan;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.status = "aktif";
    }
    
    // Getters and Setters
    public int getIdGuru() { return idGuru; }
    public void setIdGuru(int idGuru) { this.idGuru = idGuru; }
    
    public String getNip() { return nip; }
    public void setNip(String nip) { this.nip = nip; }
    
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    
    public String getJabatan() { return jabatan; }
    public void setJabatan(String jabatan) { this.jabatan = jabatan; }
    
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    
    public String getNoTelp() { return noTelp; }
    public void setNoTelp(String noTelp) { this.noTelp = noTelp; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

