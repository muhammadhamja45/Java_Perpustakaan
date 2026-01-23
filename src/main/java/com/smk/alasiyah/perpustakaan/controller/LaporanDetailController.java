package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.dao.*;
import com.smk.alasiyah.perpustakaan.model.*;
import com.smk.alasiyah.perpustakaan.util.PDFReportGenerator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.time.Year;

public class LaporanDetailController {
    
    @FXML
    private Label iconLabel, titleLabel, subtitleLabel, totalLabel;
    
    @FXML
    private ComboBox<String> formatCombo;
    
    @FXML
    private DatePicker dateFilter;
    
    @FXML
    private ComboBox<String> monthFilter;
    
    @FXML
    private ComboBox<String> yearFilter;
    
    @FXML
    private TableView<Object> dataTable;
    
    @FXML
    private Button btnGenerate, btnExport, btnApplyFilter, btnResetFilter;
    
    @FXML
    private VBox filterSection;
    
    private String jenisLaporan;
    private ObservableList<Object> dataList = FXCollections.observableArrayList();
    
    // DAOs
    private GuruDAO guruDAO = new GuruDAO();
    private SiswaDAO siswaDAO = new SiswaDAO();
    private BukuDAO bukuDAO = new BukuDAO();
    private PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
    
    @FXML
    private void initialize() {
        formatCombo.getItems().addAll("PDF", "Excel");
        formatCombo.getSelectionModel().select(0);
        
        // Initialize month filter
        monthFilter.getItems().addAll(
            "Januari", "Februari", "Maret", "April", "Mei", "Juni",
            "Juli", "Agustus", "September", "Oktober", "November", "Desember"
        );
        
        // Initialize year filter (current year and 5 years back)
        int currentYear = Year.now().getValue();
        for (int i = currentYear; i >= currentYear - 5; i--) {
            yearFilter.getItems().add(String.valueOf(i));
        }
    }
    
    public void setJenisLaporan(String jenis) {
        this.jenisLaporan = jenis;
        updateUIBasedOnJenis();
        setupTableColumns();
        updateFilterVisibility();
    }
    
    private void updateFilterVisibility() {
        // Hanya tampilkan filter untuk RIWAYAT
        boolean isRiwayat = "RIWAYAT".equals(jenisLaporan);
        if (filterSection != null) {
            filterSection.setVisible(isRiwayat);
            filterSection.setManaged(isRiwayat);
        }
        // Reset filter jika bukan riwayat
        if (!isRiwayat) {
            dateFilter.setValue(null);
            monthFilter.getSelectionModel().clearSelection();
            yearFilter.getSelectionModel().clearSelection();
        }
    }
    
    private void updateUIBasedOnJenis() {
        switch (jenisLaporan) {
            case "GURU":
                iconLabel.setText("👨‍🏫");
                titleLabel.setText("Laporan Data Guru");
                subtitleLabel.setText("Daftar semua data guru di perpustakaan");
                break;
            case "SISWA":
                iconLabel.setText("👨‍🎓");
                titleLabel.setText("Laporan Data Siswa");
                subtitleLabel.setText("Daftar semua data siswa di perpustakaan");
                break;
            case "BUKU":
                iconLabel.setText("📖");
                titleLabel.setText("Laporan Data Buku");
                subtitleLabel.setText("Daftar semua koleksi buku perpustakaan");
                break;
            case "PEMINJAMAN":
                iconLabel.setText("📤");
                titleLabel.setText("Laporan Data Peminjaman");
                subtitleLabel.setText("Daftar semua transaksi peminjaman buku");
                break;
            case "RIWAYAT":
                iconLabel.setText("📋");
                titleLabel.setText("Riwayat Peminjaman");
                subtitleLabel.setText("Riwayat lengkap semua transaksi peminjaman");
                break;
        }
    }
    
    private void setupTableColumns() {
        dataTable.getColumns().clear();
        
        // Column No
        TableColumn<Object, Integer> colNo = new TableColumn<>("No");
        colNo.setMinWidth(50);
        colNo.setCellFactory(column -> new TableCell<Object, Integer>() {
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
        dataTable.getColumns().add(colNo);
        
        switch (jenisLaporan) {
            case "GURU":
                setupGuruColumns();
                break;
            case "SISWA":
                setupSiswaColumns();
                break;
            case "BUKU":
                setupBukuColumns();
                break;
            case "PEMINJAMAN":
            case "RIWAYAT":
                setupPeminjamanColumns();
                break;
        }
    }
    
    private void setupGuruColumns() {
        TableColumn<Object, String> colNIP = new TableColumn<>("NIP");
        colNIP.setCellValueFactory(new PropertyValueFactory<>("nip"));
        colNIP.setMinWidth(120);
        
        TableColumn<Object, String> colNama = new TableColumn<>("Nama");
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colNama.setMinWidth(200);
        
        TableColumn<Object, String> colJabatan = new TableColumn<>("Jabatan");
        colJabatan.setCellValueFactory(new PropertyValueFactory<>("jabatan"));
        colJabatan.setMinWidth(150);
        
        TableColumn<Object, String> colTelp = new TableColumn<>("No. Telepon");
        colTelp.setCellValueFactory(new PropertyValueFactory<>("noTelp"));
        colTelp.setMinWidth(130);
        
        TableColumn<Object, String> colAlamat = new TableColumn<>("Alamat");
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colAlamat.setMinWidth(250);
        
        dataTable.getColumns().addAll(colNIP, colNama, colJabatan, colTelp, colAlamat);
    }
    
    private void setupSiswaColumns() {
        TableColumn<Object, String> colNIS = new TableColumn<>("NIS");
        colNIS.setCellValueFactory(new PropertyValueFactory<>("nis"));
        colNIS.setMinWidth(120);
        
        TableColumn<Object, String> colNama = new TableColumn<>("Nama");
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colNama.setMinWidth(200);
        
        TableColumn<Object, String> colKelas = new TableColumn<>("Kelas");
        colKelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));
        colKelas.setMinWidth(80);
        
        TableColumn<Object, String> colTelp = new TableColumn<>("No. Telepon");
        colTelp.setCellValueFactory(new PropertyValueFactory<>("noTelp"));
        colTelp.setMinWidth(130);
        
        TableColumn<Object, String> colAlamat = new TableColumn<>("Alamat");
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colAlamat.setMinWidth(250);
        
        dataTable.getColumns().addAll(colNIS, colNama, colKelas, colTelp, colAlamat);
    }
    
    private void setupBukuColumns() {
        TableColumn<Object, String> colKode = new TableColumn<>("Kode Buku");
        colKode.setCellValueFactory(new PropertyValueFactory<>("kodeBuku"));
        colKode.setMinWidth(120);
        
        TableColumn<Object, String> colJudul = new TableColumn<>("Judul Buku");
        colJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        colJudul.setMinWidth(250);
        
        TableColumn<Object, String> colPengarang = new TableColumn<>("Pengarang");
        colPengarang.setCellValueFactory(new PropertyValueFactory<>("pengarang"));
        colPengarang.setMinWidth(150);
        
        TableColumn<Object, String> colPenerbit = new TableColumn<>("Penerbit");
        colPenerbit.setCellValueFactory(new PropertyValueFactory<>("penerbit"));
        colPenerbit.setMinWidth(150);
        
        TableColumn<Object, Integer> colTahun = new TableColumn<>("Tahun");
        colTahun.setCellValueFactory(new PropertyValueFactory<>("tahun"));
        colTahun.setMinWidth(80);
        
        TableColumn<Object, Integer> colStok = new TableColumn<>("Stok");
        colStok.setCellValueFactory(new PropertyValueFactory<>("stok"));
        colStok.setMinWidth(70);
        
        dataTable.getColumns().addAll(colKode, colJudul, colPengarang, colPenerbit, colTahun, colStok);
    }
    
    private void setupPeminjamanColumns() {
        TableColumn<Object, LocalDate> colTanggal = new TableColumn<>("Tanggal Pinjam");
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tglPinjam"));
        colTanggal.setMinWidth(130);
        
        TableColumn<Object, String> colBuku = new TableColumn<>("Buku");
        colBuku.setCellValueFactory(new PropertyValueFactory<>("namaBuku"));
        colBuku.setMinWidth(250);
        
        TableColumn<Object, String> colAnggota = new TableColumn<>("Anggota");
        colAnggota.setCellValueFactory(new PropertyValueFactory<>("namaAnggota"));
        colAnggota.setMinWidth(200);
        
        TableColumn<Object, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("statusPinjam"));
        colStatus.setMinWidth(120);
        
        dataTable.getColumns().addAll(colTanggal, colBuku, colAnggota, colStatus);
    }
    
    @FXML
    private void handleGenerate() {
        loadDataWithFilter();
    }
    
    @FXML
    private void handleApplyFilter() {
        loadDataWithFilter();
    }
    
    @FXML
    private void handleResetFilter() {
        dateFilter.setValue(null);
        monthFilter.getSelectionModel().clearSelection();
        yearFilter.getSelectionModel().clearSelection();
        loadDataWithFilter();
    }
    
    private void loadDataWithFilter() {
        try {
            dataList.clear();
            
            // Hanya gunakan filter untuk RIWAYAT
            boolean isRiwayat = "RIWAYAT".equals(jenisLaporan);
            LocalDate selectedDate = isRiwayat ? dateFilter.getValue() : null;
            String selectedMonth = isRiwayat ? monthFilter.getSelectionModel().getSelectedItem() : null;
            String selectedYear = isRiwayat ? yearFilter.getSelectionModel().getSelectedItem() : null;
            
            boolean hasDateFilter = selectedDate != null;
            boolean hasMonthYearFilter = selectedMonth != null && selectedYear != null;
            
            switch (jenisLaporan) {
                case "GURU":
                    List<Guru> guruList = guruDAO.getAll();
                    dataList.addAll(guruList);
                    break;
                case "SISWA":
                    List<Siswa> siswaList = siswaDAO.getAll();
                    dataList.addAll(siswaList);
                    break;
                case "BUKU":
                    List<Buku> bukuList = bukuDAO.getAll();
                    dataList.addAll(bukuList);
                    break;
                case "PEMINJAMAN":
                    List<Peminjaman> peminjamanList = peminjamanDAO.getAll();
                    dataList.addAll(peminjamanList);
                    break;
                case "RIWAYAT":
                    List<Peminjaman> riwayatList;
                    if (hasDateFilter) {
                        riwayatList = peminjamanDAO.filterByDate(selectedDate);
                    } else if (hasMonthYearFilter) {
                        int month = monthFilter.getSelectionModel().getSelectedIndex() + 1;
                        int year = Integer.parseInt(selectedYear);
                        riwayatList = peminjamanDAO.filterByMonthYear(month, year);
                    } else {
                        riwayatList = peminjamanDAO.getAll();
                    }
                    dataList.addAll(riwayatList);
                    break;
            }
            
            dataTable.setItems(dataList);
            String filterInfo = "";
            if (hasDateFilter) {
                filterInfo = " (Filter: " + selectedDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ")";
            } else if (hasMonthYearFilter) {
                filterInfo = " (Filter: " + selectedMonth + " " + selectedYear + ")";
            }
            totalLabel.setText("Total: " + dataList.size() + " data" + filterInfo);
            
            if (dataList.isEmpty()) {
                showAlert("Info", "Tidak ada data yang tersedia" + (hasDateFilter || hasMonthYearFilter ? " untuk filter yang dipilih." : "."), Alert.AlertType.INFORMATION);
            } else {
                String message = "Berhasil memuat " + dataList.size() + " data";
                if (hasDateFilter || hasMonthYearFilter) {
                    message += " dengan filter yang diterapkan";
                }
                showAlert("Sukses", message + ".", Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            showAlert("Error", "Gagal memuat data: " + e.getMessage(), Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleExport() {
        if (dataList.isEmpty()) {
            showAlert("Error", "Generate laporan terlebih dahulu!", Alert.AlertType.ERROR);
            return;
        }
        
        String format = formatCombo.getSelectionModel().getSelectedItem();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Simpan Laporan");
        
        String fileName = "laporan_" + jenisLaporan.toLowerCase() + "_" + 
                         LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        
        if ("PDF".equals(format)) {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            fileChooser.setInitialFileName(fileName + ".pdf");
        } else {
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
            fileChooser.setInitialFileName(fileName + ".xlsx");
        }
        
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            try {
                if ("PDF".equals(format)) {
                    generatePDFReport(file);
                } else {
                    generateExcelReport(file);
                }
                showAlert("Sukses", "Laporan berhasil disimpan!\n\nFile: " + file.getAbsolutePath(), Alert.AlertType.INFORMATION);
            } catch (Exception e) {
                showAlert("Error", "Gagal membuat laporan: " + e.getMessage(), Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    private void generatePDFReport(File file) {
        switch (jenisLaporan) {
            case "GURU":
                com.smk.alasiyah.perpustakaan.util.PDFReportGeneratorExtended.generateGuruPDF(
                    (List<Guru>) (List<?>) dataList, file);
                break;
            case "SISWA":
                com.smk.alasiyah.perpustakaan.util.PDFReportGeneratorExtended.generateSiswaPDF(
                    (List<Siswa>) (List<?>) dataList, file);
                break;
            case "BUKU":
                com.smk.alasiyah.perpustakaan.util.PDFReportGeneratorExtended.generateBukuPDF(
                    (List<Buku>) (List<?>) dataList, file);
                break;
            case "PEMINJAMAN":
            case "RIWAYAT":
                PDFReportGenerator.generateLaporanPDF(
                    (List<Peminjaman>) (List<?>) dataList, file, jenisLaporan, LocalDate.now());
                break;
        }
    }
    
    @SuppressWarnings("unchecked")
    private void generateExcelReport(File file) {
        switch (jenisLaporan) {
            case "GURU":
                com.smk.alasiyah.perpustakaan.util.PDFReportGeneratorExtended.generateGuruExcel(
                    (List<Guru>) (List<?>) dataList, file);
                break;
            case "SISWA":
                com.smk.alasiyah.perpustakaan.util.PDFReportGeneratorExtended.generateSiswaExcel(
                    (List<Siswa>) (List<?>) dataList, file);
                break;
            case "BUKU":
                com.smk.alasiyah.perpustakaan.util.PDFReportGeneratorExtended.generateBukuExcel(
                    (List<Buku>) (List<?>) dataList, file);
                break;
            case "PEMINJAMAN":
            case "RIWAYAT":
                PDFReportGenerator.generateExcelReport(
                    (List<Peminjaman>) (List<?>) dataList, file, jenisLaporan, LocalDate.now());
                break;
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

