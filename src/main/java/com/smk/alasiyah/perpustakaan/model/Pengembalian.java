package com.smk.alasiyah.perpustakaan.model;

import java.time.LocalDate;

public class Pengembalian {
    private int idKembali;
    private int idPinjam;
    private LocalDate tglDikembalikan;
    private double denda;
    
    // Additional fields for display
    private String namaBuku;
    private String namaAnggota;
    
    public Pengembalian() {}
    
    public Pengembalian(int idPinjam, LocalDate tglDikembalikan, double denda) {
        this.idPinjam = idPinjam;
        this.tglDikembalikan = tglDikembalikan;
        this.denda = denda;
    }
    
    // Getters and Setters
    public int getIdKembali() { return idKembali; }
    public void setIdKembali(int idKembali) { this.idKembali = idKembali; }
    
    public int getIdPinjam() { return idPinjam; }
    public void setIdPinjam(int idPinjam) { this.idPinjam = idPinjam; }
    
    public LocalDate getTglDikembalikan() { return tglDikembalikan; }
    public void setTglDikembalikan(LocalDate tglDikembalikan) { this.tglDikembalikan = tglDikembalikan; }
    
    public double getDenda() { return denda; }
    public void setDenda(double denda) { this.denda = denda; }
    
    public String getNamaBuku() { return namaBuku; }
    public void setNamaBuku(String namaBuku) { this.namaBuku = namaBuku; }
    
    public String getNamaAnggota() { return namaAnggota; }
    public void setNamaAnggota(String namaAnggota) { this.namaAnggota = namaAnggota; }
}

