package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.dao.PeminjamanDAO;
import com.smk.alasiyah.perpustakaan.model.Peminjaman;
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
        jenisCombo.getItems().addAll("Harian", "Mingguan", "Bulanan");
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
        LocalDate tanggal = tanggalPicker.getValue();
        
        if (jenis == null || tanggal == null) {
            showAlert("Error", "Pilih jenis laporan dan tanggal!", Alert.AlertType.ERROR);
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
        
        List<Peminjaman> data = peminjamanDAO.filterByDateRange(startDate, endDate);
        laporanList.clear();
        laporanList.addAll(data);
    }
    
    @FXML
    private void handleExport() {
        if (laporanList.isEmpty()) {
            showAlert("Error", "Generate laporan terlebih dahulu!", Alert.AlertType.ERROR);
            return;
        }
        
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
            // TODO: Implement PDF/Excel export
            showAlert("Info", "Fitur export akan diimplementasikan menggunakan JasperReports/Apache POI", Alert.AlertType.INFORMATION);
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

