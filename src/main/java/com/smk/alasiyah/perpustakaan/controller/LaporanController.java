package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.dao.PeminjamanDAO;
import com.smk.alasiyah.perpustakaan.model.Peminjaman;
import com.smk.alasiyah.perpustakaan.util.PDFReportGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LaporanController {
    
    @FXML
    private ComboBox<String> jenisCombo, formatCombo;
    
    @FXML
    private DatePicker tanggalPicker;
    
    @FXML
    private TableView<Peminjaman> laporanTable;
    
    @FXML
    private TableColumn<Peminjaman, Integer> colNo;
    
    @FXML
    private TableColumn<Peminjaman, LocalDate> colTanggal;
    
    @FXML
    private TableColumn<Peminjaman, String> colBuku, colAnggota, colStatus;
    
    private PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
    private ObservableList<Peminjaman> laporanList = FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
        jenisCombo.getItems().addAll("Semua Data", "Harian", "Mingguan", "Bulanan");
        formatCombo.getItems().addAll("PDF", "Excel");
        formatCombo.getSelectionModel().select(0);
        
        setupTable();
    }
    
    private void setupTable() {
        colNo.setCellFactory(column -> new TableCell<Peminjaman, Integer>() {
            @Override
            protected void updateItem(Integer item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(String.valueOf(getIndex() + 1));
                }
            }
        });
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tglPinjam"));
        colBuku.setCellValueFactory(new PropertyValueFactory<>("namaBuku"));
        colAnggota.setCellValueFactory(new PropertyValueFactory<>("namaAnggota"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("statusPinjam"));
        
        laporanTable.setItems(laporanList);
    }
    
    @FXML
    private void handleGenerate() {
        String jenis = jenisCombo.getSelectionModel().getSelectedItem();
        
        if (jenis == null) {
            showAlert("Error", "Pilih jenis laporan!", Alert.AlertType.ERROR);
            return;
        }
        
        List<Peminjaman> data;
        
        // Jika "Semua Data", tidak perlu tanggal
        if ("Semua Data".equals(jenis)) {
            data = peminjamanDAO.getAll();
            laporanList.clear();
            laporanList.addAll(data);
            
            if (data.isEmpty()) {
                showAlert("Info", "Tidak ada data transaksi di database.", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Sukses", "Berhasil memuat " + data.size() + " transaksi dari database.", Alert.AlertType.INFORMATION);
            }
            return;
        }
        
        // Untuk jenis lainnya, tanggal wajib
        LocalDate tanggal = tanggalPicker.getValue();
        if (tanggal == null) {
            showAlert("Error", "Pilih tanggal untuk laporan " + jenis + "!", Alert.AlertType.ERROR);
            return;
        }
        
        LocalDate startDate, endDate;
        
        switch (jenis) {
            case "Harian":
                startDate = tanggal;
                endDate = tanggal;
                break;
            case "Mingguan":
                startDate = tanggal.with(java.time.DayOfWeek.MONDAY);
                endDate = startDate.plusDays(6);
                break;
            case "Bulanan":
                startDate = tanggal.withDayOfMonth(1);
                endDate = tanggal.withDayOfMonth(tanggal.lengthOfMonth());
                break;
            default:
                startDate = tanggal;
                endDate = tanggal;
        }
        
        data = peminjamanDAO.filterByDateRange(startDate, endDate);
        laporanList.clear();
        laporanList.addAll(data);
        
        if (data.isEmpty()) {
            showAlert("Info", "Tidak ada data pada periode yang dipilih.", Alert.AlertType.INFORMATION);
        } else {
            showAlert("Sukses", "Berhasil memuat " + data.size() + " transaksi.", Alert.AlertType.INFORMATION);
        }
    }
    
    @FXML
    private void handleExport() {
        if (laporanList.isEmpty()) {
            showAlert("Error", "Generate laporan terlebih dahulu!", Alert.AlertType.ERROR);
            return;
        }
        
        String jenis = jenisCombo.getSelectionModel().getSelectedItem();
        LocalDate tanggal = tanggalPicker.getValue();
        String format = formatCombo.getSelectionModel().getSelectedItem();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Simpan Laporan");
        
        if ("PDF".equals(format)) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            fileChooser.setInitialFileName("laporan_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".pdf");
        } else {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
            fileChooser.setInitialFileName("laporan_" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".xlsx");
        }
        
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                // Jika "Semua Data" dan tanggal tidak dipilih, gunakan tanggal hari ini
                LocalDate reportDate = (tanggal != null) ? tanggal : LocalDate.now();
                
                if ("PDF".equals(format)) {
                    PDFReportGenerator.generateLaporanPDF(laporanList, file, jenis, reportDate);
                    showAlert("Sukses", "Laporan PDF berhasil disimpan!\n\nFile: " + file.getAbsolutePath(), Alert.AlertType.INFORMATION);
                } else {
                    PDFReportGenerator.generateExcelReport(laporanList, file, jenis, reportDate);
                    showAlert("Sukses", "Laporan Excel berhasil disimpan!\n\nFile: " + file.getAbsolutePath(), Alert.AlertType.INFORMATION);
                }
            } catch (Exception e) {
                showAlert("Error", "Gagal membuat laporan: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
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

