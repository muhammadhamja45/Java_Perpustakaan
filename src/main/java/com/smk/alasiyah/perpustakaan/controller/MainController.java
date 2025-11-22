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
    }
    
    @FXML
    private void showBuku() {
        loadView("view/buku.fxml");
        setActiveButton(btnBuku);
    }
    
    @FXML
    private void showSiswa() {
        loadView("view/siswa.fxml");
        setActiveButton(btnSiswa);
    }
    
    @FXML
    private void showGuru() {
        loadView("view/guru.fxml");
        setActiveButton(btnGuru);
    }
    
    @FXML
    private void showPeminjaman() {
        loadView("view/peminjaman.fxml");
        setActiveButton(btnPeminjaman);
    }
    
    @FXML
    private void showPengembalian() {
        loadView("view/pengembalian.fxml");
        setActiveButton(btnPengembalian);
    }
    
    @FXML
    private void showRiwayat() {
        loadView("view/riwayat.fxml");
        setActiveButton(btnRiwayat);
    }
    
    @FXML
    private void showLaporan() {
        loadView("view/laporan.fxml");
        setActiveButton(btnLaporan);
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
    }
}

