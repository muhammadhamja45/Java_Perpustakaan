package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.Main;
import com.smk.alasiyah.perpustakaan.dao.PeminjamanDAO;
import com.smk.alasiyah.perpustakaan.model.Peminjaman;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class PeminjamanController {
    
    @FXML
    private TableView<Peminjaman> peminjamanTable;
    
    @FXML
    private TableColumn<Peminjaman, LocalDate> colTanggal, colKembali;
    
    @FXML
    private TableColumn<Peminjaman, String> colBuku, colAnggota, colJenis, colStatus;
    
    private PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
    private ObservableList<Peminjaman> peminjamanList = FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
        setupTable();
        loadData();
    }
    
    private void setupTable() {
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tglPinjam"));
        colBuku.setCellValueFactory(new PropertyValueFactory<>("namaBuku"));
        colAnggota.setCellValueFactory(new PropertyValueFactory<>("namaAnggota"));
        colJenis.setCellValueFactory(new PropertyValueFactory<>("jenisAnggota"));
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
        
        peminjamanTable.setItems(peminjamanList);
    }
    
    private void loadData() {
        try {
            System.out.println("\n🔄 Memuat data peminjaman...");
            peminjamanList.clear();
            // Tampilkan semua data peminjaman (bukan hanya yang status "dipinjam")
            List<Peminjaman> data = peminjamanDAO.getAll();
            if (data != null && !data.isEmpty()) {
                peminjamanList.addAll(data);
                System.out.println("✅ Data peminjaman dimuat: " + data.size() + " record");
            } else {
                System.out.println("⚠️ Tidak ada data peminjaman di database");
            }
        } catch (Exception e) {
            System.err.println("❌ Error saat loadData: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleTambah() {
        showPeminjamanDialog();
    }
    
    private void showPeminjamanDialog() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/peminjaman_dialog.fxml"));
            Scene scene = new Scene(loader.load());
            PeminjamanDialogController controller = loader.getController();
            controller.setPeminjamanController(this);
            
            Stage stage = new Stage();
            stage.setTitle("Tambah Peminjaman");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void refreshTable() {
        loadData();
    }
}

