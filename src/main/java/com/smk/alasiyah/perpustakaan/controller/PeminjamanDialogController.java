package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.dao.BukuDAO;
import com.smk.alasiyah.perpustakaan.dao.GuruDAO;
import com.smk.alasiyah.perpustakaan.dao.PeminjamanDAO;
import com.smk.alasiyah.perpustakaan.dao.SiswaDAO;
import com.smk.alasiyah.perpustakaan.model.Buku;
import com.smk.alasiyah.perpustakaan.model.Guru;
import com.smk.alasiyah.perpustakaan.model.Peminjaman;
import com.smk.alasiyah.perpustakaan.model.Siswa;
import com.smk.alasiyah.perpustakaan.util.SessionManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDate;

public class PeminjamanDialogController {
    
    @FXML
    private ComboBox<String> jenisCombo;
    
    @FXML
    private ComboBox<String> anggotaCombo;
    
    @FXML
    private ComboBox<String> bukuCombo;
    
    @FXML
    private DatePicker tglPinjamPicker, tglKembaliPicker;
    
    @FXML
    private Label stokLabel;
    
    @FXML
    private Button btnSimpan, btnBatal;
    
    private PeminjamanController peminjamanController;
    private BukuDAO bukuDAO = new BukuDAO();
    private SiswaDAO siswaDAO = new SiswaDAO();
    private GuruDAO guruDAO = new GuruDAO();
    private PeminjamanDAO peminjamanDAO = new PeminjamanDAO();
    
    @FXML
    private void initialize() {
        jenisCombo.getItems().addAll("Siswa", "Guru");
        jenisCombo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            loadAnggota(newVal);
        });
        
        bukuCombo.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                Buku buku = bukuDAO.getByKode(newVal.split(" - ")[0]);
                if (buku != null) {
                    stokLabel.setText("Stok tersedia: " + buku.getStok());
                    if (buku.getStok() <= 0) {
                        stokLabel.setStyle("-fx-text-fill: red;");
                    } else {
                        stokLabel.setStyle("-fx-text-fill: green;");
                    }
                }
            }
        });
        
        tglPinjamPicker.setValue(LocalDate.now());
        tglKembaliPicker.setValue(LocalDate.now().plusDays(7));
        
        loadBuku();
    }
    
    public void setPeminjamanController(PeminjamanController controller) {
        this.peminjamanController = controller;
    }
    
    private void loadAnggota(String jenis) {
        anggotaCombo.getItems().clear();
        if ("Siswa".equals(jenis)) {
            siswaDAO.getAll().forEach(s -> {
                if ("aktif".equals(s.getStatus())) {
                    anggotaCombo.getItems().add(s.getIdSiswa() + " - " + s.getNama() + " (" + s.getKelas() + ")");
                }
            });
        } else if ("Guru".equals(jenis)) {
            guruDAO.getAll().forEach(g -> {
                if ("aktif".equals(g.getStatus())) {
                    anggotaCombo.getItems().add(g.getIdGuru() + " - " + g.getNama() + " (" + g.getJabatan() + ")");
                }
            });
        }
    }
    
    private void loadBuku() {
        bukuCombo.getItems().clear();
        bukuDAO.getAll().forEach(b -> {
            if (b.getStok() > 0) {
                bukuCombo.getItems().add(b.getKodeBuku() + " - " + b.getJudul());
            }
        });
    }
    
    @FXML
    private void handleSimpan() {
        if (!validateInput()) {
            return;
        }
        
        String jenis = jenisCombo.getSelectionModel().getSelectedItem();
        String anggota = anggotaCombo.getSelectionModel().getSelectedItem();
        String buku = bukuCombo.getSelectionModel().getSelectedItem();
        
        int idAnggota = Integer.parseInt(anggota.split(" - ")[0]);
        Buku selectedBuku = bukuDAO.getByKode(buku.split(" - ")[0]);
        
        if (selectedBuku.getStok() <= 0) {
            showAlert("Error", "Stok buku habis!", Alert.AlertType.ERROR);
            return;
        }
        
        Peminjaman peminjaman = new Peminjaman();
        peminjaman.setIdUser(SessionManager.getCurrentUser().getIdUser());
        peminjaman.setIdBuku(selectedBuku.getIdBuku());
        
        if ("Siswa".equals(jenis)) {
            peminjaman.setIdSiswa(idAnggota);
        } else {
            peminjaman.setIdGuru(idAnggota);
        }
        
        peminjaman.setTglPinjam(tglPinjamPicker.getValue());
        peminjaman.setTglKembali(tglKembaliPicker.getValue());
        peminjaman.setStatusPinjam("dipinjam"); // Pastikan status di-set
        
        if (peminjamanDAO.create(peminjaman)) {
            bukuDAO.updateStok(selectedBuku.getIdBuku(), -1);
            showAlert("Sukses", "Peminjaman berhasil ditambahkan!", Alert.AlertType.INFORMATION);
            peminjamanController.refreshTable();
            closeDialog();
        } else {
            showAlert("Error", "Gagal menambahkan peminjaman!", Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void handleBatal() {
        closeDialog();
    }
    
    private boolean validateInput() {
        if (jenisCombo.getSelectionModel().getSelectedItem() == null ||
            anggotaCombo.getSelectionModel().getSelectedItem() == null ||
            bukuCombo.getSelectionModel().getSelectedItem() == null ||
            tglPinjamPicker.getValue() == null ||
            tglKembaliPicker.getValue() == null) {
            showAlert("Error", "Semua field harus diisi!", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }
    
    private void closeDialog() {
        Stage stage = (Stage) btnBatal.getScene().getWindow();
        stage.close();
    }
    
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

