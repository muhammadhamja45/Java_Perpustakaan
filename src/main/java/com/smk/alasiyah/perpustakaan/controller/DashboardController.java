package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.dao.PeminjamanDAO;
import com.smk.alasiyah.perpustakaan.dao.StatistikDAO;
import com.smk.alasiyah.perpustakaan.model.Peminjaman;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DashboardController {
    
    @FXML
    private Label totalBukuLabel, bukuTersediaLabel, bukuDipinjamLabel, totalAnggotaLabel;
    
    @FXML
    private LineChart<String, Number> chartHarian;
    
    @FXML
    private BarChart<String, Number> chartBulanan;
    
    @FXML
    private TableView<Peminjaman> activityTable;
    
    @FXML
    private TableColumn<Peminjaman, String> colAktivitas, colStatus;
    
    @FXML
    private TableColumn<Peminjaman, LocalDate> colTanggal;
    
    private StatistikDAO statistikDAO = new StatistikDAO();
    private PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
    
    @FXML
    private void initialize() {
        loadStatistics();
        loadCharts();
        loadActivityLog();
    }
    
    private void loadStatistics() {
        totalBukuLabel.setText(String.valueOf(statistikDAO.getTotalBuku()));
        bukuTersediaLabel.setText(String.valueOf(statistikDAO.getBukuTersedia()));
        bukuDipinjamLabel.setText(String.valueOf(statistikDAO.getBukuDipinjam()));
        totalAnggotaLabel.setText(String.valueOf(statistikDAO.getTotalSiswa() + statistikDAO.getTotalGuru()));
    }
    
    private void loadCharts() {
        try {
            LocalDate endDate = LocalDate.now();
            LocalDate startDate = endDate.minusMonths(3);
            
            // Chart Harian (7 hari terakhir)
            LocalDate harianStart = endDate.minusDays(7);
            Map<String, Integer> dataHarian = statistikDAO.getPeminjamanHarian(harianStart, endDate);
            
            if (chartHarian != null) {
                chartHarian.getData().clear();
                XYChart.Series<String, Number> seriesHarian = new XYChart.Series<>();
                seriesHarian.setName("Peminjaman Harian");
                if (dataHarian.isEmpty()) {
                    // Jika tidak ada data, tambahkan data dummy
                    seriesHarian.getData().add(new XYChart.Data<>("Tidak ada data", 0));
                } else {
                    for (Map.Entry<String, Integer> entry : dataHarian.entrySet()) {
                        seriesHarian.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
                    }
                }
                chartHarian.getData().add(seriesHarian);
            }
            
            // Chart Bulanan
            Map<String, Integer> dataBulanan = statistikDAO.getPeminjamanBulanan(startDate, endDate);
            
            if (chartBulanan != null) {
                chartBulanan.getData().clear();
                XYChart.Series<String, Number> seriesBulanan = new XYChart.Series<>();
                seriesBulanan.setName("Peminjaman Bulanan");
                if (dataBulanan.isEmpty()) {
                    // Jika tidak ada data, tambahkan data dummy
                    seriesBulanan.getData().add(new XYChart.Data<>("Tidak ada data", 0));
                } else {
                    for (Map.Entry<String, Integer> entry : dataBulanan.entrySet()) {
                        seriesBulanan.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
                    }
                }
                chartBulanan.getData().add(seriesBulanan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadActivityLog() {
        try {
            colTanggal.setCellValueFactory(new PropertyValueFactory<>("tglPinjam"));
            
            colAktivitas.setCellValueFactory(cellData -> {
                Peminjaman p = cellData.getValue();
                if (p != null) {
                    String buku = p.getNamaBuku() != null ? p.getNamaBuku() : "-";
                    String anggota = p.getNamaAnggota() != null ? p.getNamaAnggota() : "-";
                    String jenis = p.getJenisAnggota() != null ? p.getJenisAnggota() : "-";
                    String aktivitas = buku + " - " + anggota + " (" + jenis + ")";
                    return new SimpleStringProperty(aktivitas);
                }
                return new SimpleStringProperty("-");
            });
            
            colStatus.setCellValueFactory(new PropertyValueFactory<>("statusPinjam"));
            
            List<Peminjaman> recentPeminjaman = peminjamanDAO.getAll();
            if (recentPeminjaman != null && !recentPeminjaman.isEmpty()) {
                ObservableList<Peminjaman> data = FXCollections.observableArrayList(
                    recentPeminjaman.stream().limit(10).toList()
                );
                activityTable.setItems(data);
            } else {
                activityTable.setItems(FXCollections.observableArrayList());
            }
        } catch (Exception e) {
            e.printStackTrace();
            activityTable.setItems(FXCollections.observableArrayList());
        }
    }
}

