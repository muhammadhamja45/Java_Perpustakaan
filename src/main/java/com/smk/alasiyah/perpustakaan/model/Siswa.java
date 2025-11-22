package com.smk.alasiyah.perpustakaan.model;

import java.time.LocalDateTime;

public class Siswa {
    private int idSiswa;
    private String nis;
    private String nama;
    private String kelas;
    private String alamat;
    private String noTelp;
    private String status;
    private LocalDateTime createdAt;
    
    public Siswa() {}
    
    public Siswa(String nis, String nama, String kelas, String alamat, String noTelp) {
        this.nis = nis;
        this.nama = nama;
        this.kelas = kelas;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.status = "aktif";
    }
    
    // Getters and Setters
    public int getIdSiswa() { return idSiswa; }
    public void setIdSiswa(int idSiswa) { this.idSiswa = idSiswa; }
    
    public String getNis() { return nis; }
    public void setNis(String nis) { this.nis = nis; }
    
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    
    public String getKelas() { return kelas; }
    public void setKelas(String kelas) { this.kelas = kelas; }
    
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    
    public String getNoTelp() { return noTelp; }
    public void setNoTelp(String noTelp) { this.noTelp = noTelp; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

