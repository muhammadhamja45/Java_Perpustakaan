package com.smk.alasiyah.perpustakaan.model;

import java.time.LocalDate;

public class Peminjaman {
    private int idPinjam;
    private int idUser;
    private int idBuku;
    private Integer idSiswa;
    private Integer idGuru;
    private LocalDate tglPinjam;
    private LocalDate tglKembali;
    private String statusPinjam;
    
    // Additional fields for display
    private String namaBuku;
    private String namaAnggota;
    private String jenisAnggota;
    private String namaPetugas;
    
    public Peminjaman() {}
    
    public Peminjaman(int idUser, int idBuku, Integer idSiswa, Integer idGuru, 
                     LocalDate tglPinjam, LocalDate tglKembali) {
        this.idUser = idUser;
        this.idBuku = idBuku;
        this.idSiswa = idSiswa;
        this.idGuru = idGuru;
        this.tglPinjam = tglPinjam;
        this.tglKembali = tglKembali;
        this.statusPinjam = "dipinjam";
    }
    
    // Getters and Setters
    public int getIdPinjam() { return idPinjam; }
    public void setIdPinjam(int idPinjam) { this.idPinjam = idPinjam; }
    
    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }
    
    public int getIdBuku() { return idBuku; }
    public void setIdBuku(int idBuku) { this.idBuku = idBuku; }
    
    public Integer getIdSiswa() { return idSiswa; }
    public void setIdSiswa(Integer idSiswa) { this.idSiswa = idSiswa; }
    
    public Integer getIdGuru() { return idGuru; }
    public void setIdGuru(Integer idGuru) { this.idGuru = idGuru; }
    
    public LocalDate getTglPinjam() { return tglPinjam; }
    public void setTglPinjam(LocalDate tglPinjam) { this.tglPinjam = tglPinjam; }
    
    public LocalDate getTglKembali() { return tglKembali; }
    public void setTglKembali(LocalDate tglKembali) { this.tglKembali = tglKembali; }
    
    public String getStatusPinjam() { return statusPinjam; }
    public void setStatusPinjam(String statusPinjam) { this.statusPinjam = statusPinjam; }
    
    public String getNamaBuku() { return namaBuku; }
    public void setNamaBuku(String namaBuku) { this.namaBuku = namaBuku; }
    
    public String getNamaAnggota() { return namaAnggota; }
    public void setNamaAnggota(String namaAnggota) { this.namaAnggota = namaAnggota; }
    
    public String getJenisAnggota() { return jenisAnggota; }
    public void setJenisAnggota(String jenisAnggota) { this.jenisAnggota = jenisAnggota; }
    
    public String getNamaPetugas() { return namaPetugas; }
    public void setNamaPetugas(String namaPetugas) { this.namaPetugas = namaPetugas; }
}

