package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.Main;
import com.smk.alasiyah.perpustakaan.util.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {
    
    @FXML
    private StackPane contentArea;
    
    @FXML
    private VBox sidebar;
    
    @FXML
    private Label userLabel;
    
    @FXML
    private Button btnDashboard, btnBuku, btnSiswa, btnGuru, btnPeminjaman, btnPengembalian, btnRiwayat, btnLaporan;
    
    @FXML
    private Button btnLaporanGuru, btnLaporanSiswa, btnLaporanBuku, btnLaporanPeminjaman, btnLaporanRiwayat;
    
    @FXML
    private VBox submenuLaporan;
    
    @FXML
    private void initialize() {
        if (SessionManager.getCurrentUser() != null) {
            userLabel.setText("User: " + SessionManager.getCurrentUser().getNamaLengkap() + 
                            "\nRole: " + SessionManager.getCurrentUser().getRole());
        }
        showDashboard();
    }
    
    @FXML
    private void showDashboard() {
        loadView("view/dashboard.fxml");
        setActiveButton(btnDashboard);
        hideSubmenuLaporan();
    }
    
    @FXML
    private void showBuku() {
        loadView("view/buku.fxml");
        setActiveButton(btnBuku);
        hideSubmenuLaporan();
    }
    
    @FXML
    private void showSiswa() {
        loadView("view/siswa.fxml");
        setActiveButton(btnSiswa);
        hideSubmenuLaporan();
    }
    
    @FXML
    private void showGuru() {
        loadView("view/guru.fxml");
        setActiveButton(btnGuru);
        hideSubmenuLaporan();
    }
    
    @FXML
    private void showPeminjaman() {
        loadView("view/peminjaman.fxml");
        setActiveButton(btnPeminjaman);
        hideSubmenuLaporan();
    }
    
    @FXML
    private void showPengembalian() {
        loadView("view/pengembalian.fxml");
        setActiveButton(btnPengembalian);
        hideSubmenuLaporan();
    }
    
    @FXML
    private void showRiwayat() {
        loadView("view/riwayat.fxml");
        setActiveButton(btnRiwayat);
        hideSubmenuLaporan();
    }
    
    @FXML
    private void toggleLaporanMenu() {
        if (submenuLaporan.isVisible()) {
            hideSubmenuLaporan();
        } else {
            showSubmenuLaporan();
        }
    }
    
    private void showSubmenuLaporan() {
        submenuLaporan.setVisible(true);
        submenuLaporan.setManaged(true);
        btnLaporan.setText("📊 Laporan ▲");
    }
    
    private void hideSubmenuLaporan() {
        submenuLaporan.setVisible(false);
        submenuLaporan.setManaged(false);
        btnLaporan.setText("📊 Laporan ▼");
    }
    
    @FXML
    private void showLaporanGuru() {
        loadLaporanView("GURU");
        setActiveSubmenuButton(btnLaporanGuru);
    }
    
    @FXML
    private void showLaporanSiswa() {
        loadLaporanView("SISWA");
        setActiveSubmenuButton(btnLaporanSiswa);
    }
    
    @FXML
    private void showLaporanBuku() {
        loadLaporanView("BUKU");
        setActiveSubmenuButton(btnLaporanBuku);
    }
    
    @FXML
    private void showLaporanPeminjaman() {
        loadLaporanView("PEMINJAMAN");
        setActiveSubmenuButton(btnLaporanPeminjaman);
    }
    
    @FXML
    private void showLaporanRiwayat() {
        loadLaporanView("RIWAYAT");
        setActiveSubmenuButton(btnLaporanRiwayat);
    }
    
    private void loadLaporanView(String jenisLaporan) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/laporan_detail.fxml"));
            Parent view = loader.load();
            
            // Pass jenis laporan to controller
            com.smk.alasiyah.perpustakaan.controller.LaporanDetailController controller = loader.getController();
            controller.setJenisLaporan(jenisLaporan);
            
            contentArea.getChildren().setAll(view);
            showSubmenuLaporan();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void showLaporan() {
        loadView("view/laporan.fxml");
        setActiveButton(btnLaporan);
        hideSubmenuLaporan();
    }
    
    @FXML
    private void handleLogout() {
        SessionManager.logout();
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/login.fxml"));
            Parent root = loader.load();
            contentArea.getScene().setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void loadView(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
            Parent view = loader.load();
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void setActiveButton(Button activeBtn) {
        Button[] buttons = {btnDashboard, btnBuku, btnSiswa, btnGuru, btnPeminjaman, btnPengembalian, btnRiwayat, btnLaporan};
        for (Button btn : buttons) {
            if (btn == activeBtn) {
                btn.getStyleClass().add("active");
                btn.getStyleClass().removeAll("menu-button");
                btn.getStyleClass().add("menu-button");
            } else {
                btn.getStyleClass().removeAll("active");
            }
        }
        // Clear submenu active states
        clearSubmenuActiveStates();
    }
    
    private void setActiveSubmenuButton(Button activeBtn) {
        // Clear main menu active states except laporan
        Button[] buttons = {btnDashboard, btnBuku, btnSiswa, btnGuru, btnPeminjaman, btnPengembalian, btnRiwayat};
        for (Button btn : buttons) {
            btn.getStyleClass().removeAll("active");
        }
        
        // Set laporan as partially active
        btnLaporan.getStyleClass().removeAll("active");
        
        // Set active submenu
        Button[] submenuButtons = {btnLaporanGuru, btnLaporanSiswa, btnLaporanBuku, btnLaporanPeminjaman, btnLaporanRiwayat};
        for (Button btn : submenuButtons) {
            if (btn == activeBtn) {
                btn.getStyleClass().add("active");
                btn.getStyleClass().removeAll("submenu-button");
                btn.getStyleClass().add("submenu-button");
            } else {
                btn.getStyleClass().removeAll("active");
            }
        }
    }
    
    private void clearSubmenuActiveStates() {
        Button[] submenuButtons = {btnLaporanGuru, btnLaporanSiswa, btnLaporanBuku, btnLaporanPeminjaman, btnLaporanRiwayat};
        for (Button btn : submenuButtons) {
            btn.getStyleClass().removeAll("active");
        }
    }
}

