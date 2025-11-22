package com.smk.alasiyah.perpustakaan.model;

import java.time.LocalDateTime;

public class Buku {
    private int idBuku;
    private String kodeBuku;
    private String judul;
    private String pengarang;
    private String penerbit;
    private Integer tahun;
    private String kategori;
    private int stok;
    private String lokasiRak;
    private LocalDateTime createdAt;
    
    public Buku() {}
    
    public Buku(String kodeBuku, String judul, String pengarang, String penerbit, 
                Integer tahun, String kategori, int stok, String lokasiRak) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.tahun = tahun;
        this.kategori = kategori;
        this.stok = stok;
        this.lokasiRak = lokasiRak;
    }
    
    // Getters and Setters
    public int getIdBuku() { return idBuku; }
    public void setIdBuku(int idBuku) { this.idBuku = idBuku; }
    
    public String getKodeBuku() { return kodeBuku; }
    public void setKodeBuku(String kodeBuku) { this.kodeBuku = kodeBuku; }
    
    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }
    
    public String getPengarang() { return pengarang; }
    public void setPengarang(String pengarang) { this.pengarang = pengarang; }
    
    public String getPenerbit() { return penerbit; }
    public void setPenerbit(String penerbit) { this.penerbit = penerbit; }
    
    public Integer getTahun() { return tahun; }
    public void setTahun(Integer tahun) { this.tahun = tahun; }
    
    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    
    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }
    
    public String getLokasiRak() { return lokasiRak; }
    public void setLokasiRak(String lokasiRak) { this.lokasiRak = lokasiRak; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

