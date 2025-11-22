package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.dao.BukuDAO;
import com.smk.alasiyah.perpustakaan.model.Buku;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class BukuDialogController {
    
    @FXML
    private Label titleLabel;
    
    @FXML
    private TextField kodeField, judulField, pengarangField, penerbitField, tahunField, kategoriField, stokField, lokasiField;
    
    @FXML
    private Button btnSimpan, btnBatal;
    
    private Buku buku;
    private BukuController bukuController;
    private BukuDAO bukuDAO = new BukuDAO();
    
    public void setBuku(Buku buku) {
        this.buku = buku;
        if (buku != null) {
            titleLabel.setText("Edit Buku");
            kodeField.setText(buku.getKodeBuku());
            kodeField.setEditable(false);
            judulField.setText(buku.getJudul());
            pengarangField.setText(buku.getPengarang());
            penerbitField.setText(buku.getPenerbit());
            if (buku.getTahun() != null) {
                tahunField.setText(String.valueOf(buku.getTahun()));
            }
            kategoriField.setText(buku.getKategori());
            stokField.setText(String.valueOf(buku.getStok()));
            lokasiField.setText(buku.getLokasiRak());
        }
    }
    
    public void setBukuController(BukuController controller) {
        this.bukuController = controller;
    }
    
    @FXML
    private void handleSimpan() {
        if (!validateInput()) {
            return;
        }
        
        if (buku == null) {
            // Create new
            Buku newBuku = new Buku();
            newBuku.setKodeBuku(kodeField.getText().trim());
            newBuku.setJudul(judulField.getText().trim());
            newBuku.setPengarang(pengarangField.getText().trim());
            newBuku.setPenerbit(penerbitField.getText().trim());
            if (!tahunField.getText().trim().isEmpty()) {
                try {
                    newBuku.setTahun(Integer.parseInt(tahunField.getText().trim()));
                } catch (NumberFormatException e) {
                    showAlert("Error", "Tahun harus berupa angka!", Alert.AlertType.ERROR);
                    return;
                }
            }
            newBuku.setKategori(kategoriField.getText().trim());
            try {
                newBuku.setStok(Integer.parseInt(stokField.getText().trim()));
            } catch (NumberFormatException e) {
                showAlert("Error", "Stok harus berupa angka!", Alert.AlertType.ERROR);
                return;
            }
            newBuku.setLokasiRak(lokasiField.getText().trim());
            
            if (bukuDAO.create(newBuku)) {
                showAlert("Sukses", "Buku berhasil ditambahkan!", Alert.AlertType.INFORMATION);
                bukuController.refreshTable();
                closeDialog();
            } else {
                showAlert("Error", "Gagal menambahkan buku! Kode buku mungkin sudah ada.", Alert.AlertType.ERROR);
            }
        } else {
            // Update existing
            buku.setJudul(judulField.getText().trim());
            buku.setPengarang(pengarangField.getText().trim());
            buku.setPenerbit(penerbitField.getText().trim());
            if (!tahunField.getText().trim().isEmpty()) {
                try {
                    buku.setTahun(Integer.parseInt(tahunField.getText().trim()));
                } catch (NumberFormatException e) {
                    showAlert("Error", "Tahun harus berupa angka!", Alert.AlertType.ERROR);
                    return;
                }
            } else {
                buku.setTahun(null);
            }
            buku.setKategori(kategoriField.getText().trim());
            try {
                buku.setStok(Integer.parseInt(stokField.getText().trim()));
            } catch (NumberFormatException e) {
                showAlert("Error", "Stok harus berupa angka!", Alert.AlertType.ERROR);
                return;
            }
            buku.setLokasiRak(lokasiField.getText().trim());
            
            if (bukuDAO.update(buku)) {
                showAlert("Sukses", "Buku berhasil diupdate!", Alert.AlertType.INFORMATION);
                bukuController.refreshTable();
                closeDialog();
            } else {
                showAlert("Error", "Gagal mengupdate buku!", Alert.AlertType.ERROR);
            }
        }
    }
    
    @FXML
    private void handleBatal() {
        closeDialog();
    }
    
    private boolean validateInput() {
        if (kodeField.getText().trim().isEmpty() ||
            judulField.getText().trim().isEmpty() ||
            pengarangField.getText().trim().isEmpty() ||
            penerbitField.getText().trim().isEmpty() ||
            kategoriField.getText().trim().isEmpty() ||
            stokField.getText().trim().isEmpty() ||
            lokasiField.getText().trim().isEmpty()) {
            showAlert("Error", "Semua field bertanda * harus diisi!", Alert.AlertType.ERROR);
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

