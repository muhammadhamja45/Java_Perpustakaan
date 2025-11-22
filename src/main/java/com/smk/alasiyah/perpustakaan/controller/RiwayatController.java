package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.dao.PeminjamanDAO;
import com.smk.alasiyah.perpustakaan.model.Peminjaman;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;

public class RiwayatController {
    
    @FXML
    private TableView<Peminjaman> riwayatTable;
    
    @FXML
    private TableColumn<Peminjaman, LocalDate> colTanggal, colKembali;
    
    @FXML
    private TableColumn<Peminjaman, String> colBuku, colAnggota, colJenis, colStatus;
    
    @FXML
    private TextField searchField;
    
    @FXML
    private ComboBox<String> statusCombo;
    
    @FXML
    private DatePicker startDatePicker, endDatePicker;
    
    private PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
    private ObservableList<Peminjaman> riwayatList = FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
        setupTable();
        statusCombo.getItems().addAll("Semua Status", "dipinjam", "dikembalikan");
        statusCombo.getSelectionModel().select(0);
        loadData();
    }
    
    private void setupTable() {
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tglPinjam"));
        colBuku.setCellValueFactory(new PropertyValueFactory<>("namaBuku"));
        colAnggota.setCellValueFactory(new PropertyValueFactory<>("namaAnggota"));
        colJenis.setCellValueFactory(new PropertyValueFactory<>("jenisAnggota"));
        colKembali.setCellValueFactory(new PropertyValueFactory<>("tglKembali"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("statusPinjam"));
        
        riwayatTable.setItems(riwayatList);
    }
    
    private void loadData() {
        riwayatList.clear();
        List<Peminjaman> data = peminjamanDAO.getAll();
        riwayatList.addAll(data);
    }
    
    @FXML
    private void handleFilter() {
        String keyword = searchField.getText().trim();
        String status = statusCombo.getSelectionModel().getSelectedItem();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();
        
        riwayatList.clear();
        List<Peminjaman> data;
        
        if (startDate != null && endDate != null) {
            data = peminjamanDAO.filterByDateRange(startDate, endDate);
        } else if (!keyword.isEmpty()) {
            data = peminjamanDAO.search(keyword);
        } else if (status != null && !status.equals("Semua Status")) {
            data = peminjamanDAO.filterByStatus(status);
        } else {
            data = peminjamanDAO.getAll();
        }
        
        riwayatList.addAll(data);
    }
    
    @FXML
    private void handleRefresh() {
        searchField.clear();
        statusCombo.getSelectionModel().select(0);
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        loadData();
    }
}

