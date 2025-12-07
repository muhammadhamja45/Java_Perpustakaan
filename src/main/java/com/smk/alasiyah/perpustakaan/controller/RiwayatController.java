package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.dao.PeminjamanDAO;
import com.smk.alasiyah.perpustakaan.model.Peminjaman;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RiwayatController {
    
    @FXML
    private TableView<Peminjaman> riwayatTable;
    
    @FXML
    private TableColumn<Peminjaman, Integer> colNo;
    
    @FXML
    private TableColumn<Peminjaman, LocalDate> colTanggal, colKembali;
    
    @FXML
    private TableColumn<Peminjaman, String> colBuku, colAnggota, colJenis, colStatus, colDurasi;
    
    @FXML
    private TextField searchField;
    
    @FXML
    private ComboBox<String> statusCombo;
    
    @FXML
    private DatePicker startDatePicker, endDatePicker;
    
    @FXML
    private Label lblTotalTransaksi, lblDipinjam, lblDikembalikan, lblTerlambat, lblRecordCount;
    
    @FXML
    private Button btnExport;
    
    private PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
    private ObservableList<Peminjaman> riwayatList = FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
        setupTable();
        setupComboBox();
        loadData();
        updateStatistics();
        
        // Add search listener
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.trim().isEmpty() && 
                statusCombo.getSelectionModel().getSelectedIndex() == 0 &&
                startDatePicker.getValue() == null && 
                endDatePicker.getValue() == null) {
                loadData();
            }
        });
    }
    
    private void setupComboBox() {
        statusCombo.getItems().addAll("Semua Status", "dipinjam", "dikembalikan", "terlambat");
        statusCombo.getSelectionModel().select(0);
        
        // Make ComboBox full width
        statusCombo.setMaxWidth(Double.MAX_VALUE);
    }
    
    private void setupTable() {
        // Row number column
        colNo.setCellValueFactory(column -> {
            int index = riwayatTable.getItems().indexOf(column.getValue()) + 1;
            return new javafx.beans.property.SimpleObjectProperty<>(index);
        });
        
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tglPinjam"));
        colBuku.setCellValueFactory(new PropertyValueFactory<>("namaBuku"));
        colAnggota.setCellValueFactory(new PropertyValueFactory<>("namaAnggota"));
        colJenis.setCellValueFactory(new PropertyValueFactory<>("jenisAnggota"));
        colKembali.setCellValueFactory(new PropertyValueFactory<>("tglKembali"));
        
        // Status column with custom styling
        colStatus.setCellValueFactory(new PropertyValueFactory<>("statusPinjam"));
        colStatus.setCellFactory(column -> new TableCell<Peminjaman, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                    setStyle("");
                } else {
                    setText(item.toUpperCase());
                    setAlignment(Pos.CENTER);
                    
                    String style = "-fx-font-weight: bold; -fx-padding: 6 12; -fx-background-radius: 8;";
                    
                    if ("dikembalikan".equalsIgnoreCase(item)) {
                        setStyle(style + "-fx-background-color: #d4edda; -fx-text-fill: #155724;");
                    } else if ("dipinjam".equalsIgnoreCase(item)) {
                        setStyle(style + "-fx-background-color: #fff3cd; -fx-text-fill: #856404;");
                    } else {
                        setStyle(style + "-fx-background-color: #f8d7da; -fx-text-fill: #721c24;");
                    }
                }
            }
        });
        
        // Duration column
        colDurasi.setCellValueFactory(cellData -> {
            Peminjaman p = cellData.getValue();
            LocalDate start = p.getTglPinjam();
            LocalDate end = p.getTglKembali() != null ? p.getTglKembali() : LocalDate.now();
            
            long days = ChronoUnit.DAYS.between(start, end);
            return new SimpleStringProperty(days + " hari");
        });
        
        // Jenis column styling
        colJenis.setCellFactory(column -> new TableCell<Peminjaman, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setAlignment(Pos.CENTER);
                    
                    String style = "-fx-font-weight: 600; -fx-padding: 6 10; -fx-background-radius: 6;";
                    
                    if ("Siswa".equalsIgnoreCase(item)) {
                        setStyle(style + "-fx-background-color: #e3f2fd; -fx-text-fill: #1565c0;");
                    } else if ("Guru".equalsIgnoreCase(item)) {
                        setStyle(style + "-fx-background-color: #f3e5f5; -fx-text-fill: #6a1b9a;");
                    }
                }
            }
        });
        
        riwayatTable.setItems(riwayatList);
    }
    
    private void loadData() {
        riwayatList.clear();
        List<Peminjaman> data = peminjamanDAO.getAll();
        riwayatList.addAll(data);
        updateStatistics();
        updateRecordCount();
    }
    
    private void updateStatistics() {
        List<Peminjaman> allData = peminjamanDAO.getAll();
        
        int total = allData.size();
        int dipinjam = (int) allData.stream()
                .filter(p -> "dipinjam".equalsIgnoreCase(p.getStatusPinjam()))
                .count();
        int dikembalikan = (int) allData.stream()
                .filter(p -> "dikembalikan".equalsIgnoreCase(p.getStatusPinjam()))
                .count();
        
        // Count late returns (borrowed more than 7 days)
        int terlambat = (int) allData.stream()
                .filter(p -> {
                    if ("dipinjam".equalsIgnoreCase(p.getStatusPinjam())) {
                        long days = ChronoUnit.DAYS.between(p.getTglPinjam(), LocalDate.now());
                        return days > 7;
                    }
                    return false;
                })
                .count();
        
        lblTotalTransaksi.setText(String.valueOf(total));
        lblDipinjam.setText(String.valueOf(dipinjam));
        lblDikembalikan.setText(String.valueOf(dikembalikan));
        lblTerlambat.setText(String.valueOf(terlambat));
    }
    
    private void updateRecordCount() {
        int count = riwayatList.size();
        lblRecordCount.setText("Menampilkan " + count + " transaksi");
    }
    
    @FXML
    private void handleFilter() {
        String keyword = searchField.getText().trim();
        String status = statusCombo.getSelectionModel().getSelectedItem();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        
        riwayatList.clear();
        List<Peminjaman> data;
        
        // Priority: Date range > Keyword search > Status filter
        if (startDate != null && endDate != null) {
            data = peminjamanDAO.filterByDateRange(startDate, endDate);
        } else if (!keyword.isEmpty()) {
            data = peminjamanDAO.search(keyword);
        } else if (status != null && !status.equals("Semua Status")) {
            if ("terlambat".equalsIgnoreCase(status)) {
                // Filter late returns
                data = peminjamanDAO.getAll().stream()
                        .filter(p -> {
                            if ("dipinjam".equalsIgnoreCase(p.getStatusPinjam())) {
                                long days = ChronoUnit.DAYS.between(p.getTglPinjam(), LocalDate.now());
                                return days > 7;
                            }
                            return false;
                        })
                        .toList();
            } else {
                data = peminjamanDAO.filterByStatus(status);
            }
        } else {
            data = peminjamanDAO.getAll();
        }
        
        riwayatList.addAll(data);
        updateRecordCount();
        
        // Show notification
        showNotification("Filter berhasil diterapkan!");
    }
    
    @FXML
    private void handleRefresh() {
        searchField.clear();
        statusCombo.getSelectionModel().select(0);
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        loadData();
        
        showNotification("Data berhasil di-refresh!");
    }
    
    @FXML
    private void handleExport() {
        if (riwayatList.isEmpty()) {
            showError("Tidak ada data untuk diekspor!");
            return;
        }
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export Data Riwayat");
        fileChooser.setInitialFileName("riwayat_transaksi_" + LocalDate.now() + ".csv");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        
        File file = fileChooser.showSaveDialog(btnExport.getScene().getWindow());
        
        if (file != null) {
            try (FileWriter writer = new FileWriter(file)) {
                // Write header
                writer.write("No,Tanggal Pinjam,Judul Buku,Nama Anggota,Jenis,Tanggal Kembali,Status,Durasi\n");
                
                // Write data
                AtomicInteger index = new AtomicInteger(1);
                for (Peminjaman p : riwayatList) {
                    LocalDate start = p.getTglPinjam();
                    LocalDate end = p.getTglKembali() != null ? p.getTglKembali() : LocalDate.now();
                    long days = ChronoUnit.DAYS.between(start, end);
                    
                    writer.write(String.format("%d,%s,%s,%s,%s,%s,%s,%d hari\n",
                        index.getAndIncrement(),
                        p.getTglPinjam(),
                        p.getNamaBuku(),
                        p.getNamaAnggota(),
                        p.getJenisAnggota(),
                        p.getTglKembali() != null ? p.getTglKembali() : "-",
                        p.getStatusPinjam(),
                        days
                    ));
                }
                
                showSuccess("Data berhasil diekspor ke: " + file.getName());
            } catch (IOException e) {
                showError("Gagal mengekspor data: " + e.getMessage());
            }
        }
    }
    
    private void showNotification(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informasi");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void showSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sukses");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

