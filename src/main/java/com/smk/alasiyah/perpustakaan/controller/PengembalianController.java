package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.dao.BukuDAO;
import com.smk.alasiyah.perpustakaan.dao.PeminjamanDAO;
import com.smk.alasiyah.perpustakaan.dao.PengembalianDAO;
import com.smk.alasiyah.perpustakaan.model.Peminjaman;
import com.smk.alasiyah.perpustakaan.model.Pengembalian;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class PengembalianController {
    
    @FXML
    private TableView<Peminjaman> peminjamanTable;
    
    @FXML
    private TableColumn<Peminjaman, LocalDate> colTanggal, colKembali;
    
    @FXML
    private TableColumn<Peminjaman, String> colBuku, colAnggota, colStatus;
    
    @FXML
    private TableColumn<Peminjaman, Void> colAksi;
    
    private PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
    private PengembalianDAO pengembalianDAO = new PengembalianDAO();
    private BukuDAO bukuDAO = new BukuDAO();
    private ObservableList<Peminjaman> peminjamanList = FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
        setupTable();
        loadData();
        // Refresh setelah sedikit delay untuk memastikan UI sudah siap
        javafx.application.Platform.runLater(() -> {
            peminjamanTable.refresh();
        });
    }
    
    @FXML
    private void handleRefresh() {
        loadData();
    }
    
    private void setupTable() {
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tglPinjam"));
        colBuku.setCellValueFactory(new PropertyValueFactory<>("namaBuku"));
        colAnggota.setCellValueFactory(new PropertyValueFactory<>("namaAnggota"));
        colKembali.setCellValueFactory(new PropertyValueFactory<>("tglKembali"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("statusPinjam"));
        
        // Custom cell factory for status column
        colStatus.setCellFactory(column -> new TableCell<Peminjaman, String>() {
            @Override
            protected void updateItem(String status, boolean empty) {
                super.updateItem(status, empty);
                if (empty) {
                    setText(null);
                    setStyle("");
                } else {
                    // Jika status null, tampilkan "dipinjam"
                    if (status == null || status.trim().isEmpty()) {
                        setText("dipinjam");
                        setStyle("-fx-text-fill: #e67e22; -fx-font-weight: bold;");
                    } else {
                        setText(status);
                        if (status.toLowerCase().equals("dipinjam")) {
                            setStyle("-fx-text-fill: #e67e22; -fx-font-weight: bold;");
                        } else if (status.toLowerCase().equals("dikembalikan")) {
                            setStyle("-fx-text-fill: #27ae60; -fx-font-weight: bold;");
                        } else {
                            setStyle("-fx-text-fill: #7f8c8d;");
                        }
                    }
                }
            }
        });
        
        colAksi.setCellFactory(param -> new TableCell<Peminjaman, Void>() {
            private final Button btnKembalikan = new Button("📥 Kembalikan");
            
            {
                javafx.scene.control.Tooltip tooltipKembalikan = new javafx.scene.control.Tooltip("Klik untuk mengembalikan buku dan mengubah status menjadi 'dikembalikan'");
                tooltipKembalikan.setStyle("-fx-font-size: 12px; -fx-background-color: #2c3e50; -fx-text-fill: white; -fx-padding: 8 12 8 12;");
                tooltipKembalikan.setShowDelay(javafx.util.Duration.millis(300));
                btnKembalikan.setTooltip(tooltipKembalikan);
                btnKembalikan.setStyle("-fx-background-color: linear-gradient(to right, #27ae60 0%, #229954 100%); " +
                                "-fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                "-fx-padding: 8 14 8 14; -fx-font-size: 12px; -fx-cursor: hand; " +
                                "-fx-effect: dropshadow(gaussian, rgba(39, 174, 96, 0.3), 8, 0, 0, 2);");
                btnKembalikan.setOnMouseEntered(e -> btnKembalikan.setStyle("-fx-background-color: linear-gradient(to right, #229954 0%, #27ae60 100%); " +
                                "-fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                "-fx-padding: 8 14 8 14; -fx-font-size: 12px; -fx-cursor: hand; " +
                                "-fx-effect: dropshadow(gaussian, rgba(39, 174, 96, 0.5), 12, 0, 0, 4); " +
                                "-fx-scale-x: 1.05; -fx-scale-y: 1.05;"));
                btnKembalikan.setOnMouseExited(e -> btnKembalikan.setStyle("-fx-background-color: linear-gradient(to right, #27ae60 0%, #229954 100%); " +
                                "-fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                "-fx-padding: 8 14 8 14; -fx-font-size: 12px; -fx-cursor: hand; " +
                                "-fx-effect: dropshadow(gaussian, rgba(39, 174, 96, 0.3), 8, 0, 0, 2); " +
                                "-fx-scale-x: 1.0; -fx-scale-y: 1.0;"));
                btnKembalikan.setOnAction(event -> {
                    Peminjaman p = getTableView().getItems().get(getIndex());
                    if (p != null) {
                        handleKembalikan(p);
                    }
                });
            }
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getTableRow() == null || getTableRow().getItem() == null) {
                    setGraphic(null);
                } else {
                    Peminjaman p = getTableRow().getItem();
                    // Tampilkan tombol jika status null (belum dikembalikan) atau "dipinjam"
                    String status = p.getStatusPinjam();
                    if (status == null || status.trim().isEmpty() || status.toLowerCase().equals("dipinjam")) {
                        HBox hbox = new HBox(btnKembalikan);
                        hbox.setAlignment(javafx.geometry.Pos.CENTER);
                        hbox.setSpacing(5);
                        setGraphic(hbox);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });
        
        peminjamanTable.setItems(peminjamanList);
    }
    
    private void loadData() {
        try {
            System.out.println("\n🔄 Memuat data pengembalian...");
            peminjamanList.clear();
            
            // Coba beberapa variasi status
            List<Peminjaman> data = peminjamanDAO.filterByStatus("dipinjam");
            
            // Jika tidak ada dengan "dipinjam", coba "Dipinjam" atau variasi lain
            if ((data == null || data.isEmpty())) {
                System.out.println("⚠️ Tidak ada dengan status 'dipinjam', mencoba variasi lain...");
                data = peminjamanDAO.filterByStatus("Dipinjam");
            }
            
            // Jika masih kosong, tampilkan semua untuk debugging
            if (data == null || data.isEmpty()) {
                System.out.println("⚠️ Masih kosong, menampilkan semua data untuk debugging...");
                List<Peminjaman> allData = peminjamanDAO.getAll();
                if (allData != null && !allData.isEmpty()) {
                    System.out.println("📊 Total peminjaman di database: " + allData.size());
                    // Inisialisasi list jika null
                    if (data == null) {
                        data = new java.util.ArrayList<>();
                    }
                    // Filter manual untuk status yang mengandung "dipinjam" atau NULL
                    for (Peminjaman p : allData) {
                        String status = p.getStatusPinjam();
                        System.out.println("  - ID: " + p.getIdPinjam() + 
                                        ", Status: '" + (status != null ? status : "NULL") + "'" + 
                                        ", Buku: " + (p.getNamaBuku() != null ? p.getNamaBuku() : "NULL") +
                                        ", Anggota: " + (p.getNamaAnggota() != null ? p.getNamaAnggota() : "NULL"));
                        // Tambahkan jika status null (belum dikembalikan) atau "dipinjam"
                        if (status == null || status.trim().isEmpty() || status.toLowerCase().contains("dipinjam")) {
                            data.add(p);
                        }
                    }
                    System.out.println("✅ Setelah filter manual: " + data.size() + " record dengan status 'dipinjam'");
                } else {
                    System.out.println("📊 Tidak ada data peminjaman sama sekali di database");
                    data = new java.util.ArrayList<>(); // Inisialisasi list kosong
                }
            }
            
            if (data != null && !data.isEmpty()) {
                peminjamanList.addAll(data);
                System.out.println("✅ Data peminjaman dimuat ke tabel: " + data.size() + " record");
            } else {
                System.out.println("⚠️ Tidak ada data peminjaman dengan status 'dipinjam'");
            }
            
            // Refresh tabel
            javafx.application.Platform.runLater(() -> {
                peminjamanTable.refresh();
                System.out.println("🔄 Tabel di-refresh");
            });
        } catch (Exception e) {
            System.err.println("❌ Error saat loadData: " + e.getMessage());
            e.printStackTrace();
            showAlert("Error", "Gagal memuat data: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    private void handleKembalikan(Peminjaman peminjaman) {
        if (peminjaman == null) {
            showAlert("Error", "Data peminjaman tidak valid!", Alert.AlertType.ERROR);
            return;
        }
        
        LocalDate tglKembali = peminjaman.getTglKembali();
        LocalDate tglSekarang = LocalDate.now();
        
        long hariTerlambat = 0;
        if (tglKembali != null) {
            hariTerlambat = ChronoUnit.DAYS.between(tglKembali, tglSekarang);
        }
        double denda = 0;
        
        if (hariTerlambat > 0) {
            denda = hariTerlambat * 1000; // Rp 1000 per hari
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi Pengembalian");
        alert.setHeaderText("Ubah Status Peminjaman Menjadi Dikembalikan");
        String message = "Buku: " + (peminjaman.getNamaBuku() != null ? peminjaman.getNamaBuku() : "-") + "\n";
        message += "Anggota: " + (peminjaman.getNamaAnggota() != null ? peminjaman.getNamaAnggota() : "-") + "\n";
        message += "Tanggal Kembali: " + (tglKembali != null ? tglKembali.toString() : "-") + "\n";
        message += "Tanggal Dikembalikan: " + tglSekarang.toString() + "\n\n";
        if (hariTerlambat > 0) {
            message += "⚠️ Terlambat: " + hariTerlambat + " hari\n";
            message += "💰 Denda: Rp " + String.format("%.0f", denda) + "\n\n";
        } else {
            message += "✅ Tidak ada denda\n\n";
        }
        message += "Status akan diubah menjadi 'dikembalikan' dan stok buku akan bertambah.";
        alert.setContentText(message);
        
        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            try {
                // 1. Buat record pengembalian
                Pengembalian pengembalian = new Pengembalian();
                pengembalian.setIdPinjam(peminjaman.getIdPinjam());
                pengembalian.setTglDikembalikan(tglSekarang);
                pengembalian.setDenda(denda);
                
                // 2. Update status peminjaman menjadi "dikembalikan"
                boolean statusUpdated = peminjamanDAO.updateStatus(peminjaman.getIdPinjam(), "dikembalikan");
                
                // 3. Tambah stok buku
                boolean stokUpdated = bukuDAO.updateStok(peminjaman.getIdBuku(), 1);
                
                // 4. Simpan data pengembalian
                boolean pengembalianCreated = pengembalianDAO.create(pengembalian);
                
                if (statusUpdated && stokUpdated && pengembalianCreated) {
                    showAlert("Sukses", "✅ Status peminjaman berhasil diubah menjadi 'dikembalikan'!\n\n" +
                              "• Status: dipinjam → dikembalikan\n" +
                              "• Stok buku bertambah +1\n" +
                              "• Data pengembalian tersimpan", Alert.AlertType.INFORMATION);
                    loadData(); // Refresh tabel
                } else {
                    showAlert("Error", "Gagal mengubah status! Periksa koneksi database.", Alert.AlertType.ERROR);
                }
            } catch (Exception e) {
                e.printStackTrace();
                showAlert("Error", "Terjadi kesalahan: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }
    
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

