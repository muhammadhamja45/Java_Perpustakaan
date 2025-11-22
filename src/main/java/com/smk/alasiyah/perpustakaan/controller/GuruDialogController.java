package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.dao.GuruDAO;
import com.smk.alasiyah.perpustakaan.model.Guru;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GuruDialogController {
    
    @FXML
    private Label titleLabel;
    
    @FXML
    private TextField nipField, namaField, jabatanField, telpField;
    
    @FXML
    private TextArea alamatField;
    
    @FXML
    private ComboBox<String> statusCombo;
    
    @FXML
    private Button btnSimpan, btnBatal;
    
    private Guru guru;
    private GuruController guruController;
    private GuruDAO guruDAO = new GuruDAO();
    
    @FXML
    private void initialize() {
        statusCombo.getItems().addAll("aktif", "nonaktif");
        statusCombo.getSelectionModel().select(0);
    }
    
    public void setGuru(Guru guru) {
        this.guru = guru;
        if (guru != null) {
            titleLabel.setText("Edit Guru");
            nipField.setText(guru.getNip());
            nipField.setEditable(false);
            namaField.setText(guru.getNama());
            jabatanField.setText(guru.getJabatan());
            alamatField.setText(guru.getAlamat());
            telpField.setText(guru.getNoTelp());
            statusCombo.getSelectionModel().select(guru.getStatus());
        }
    }
    
    public void setGuruController(GuruController controller) {
        this.guruController = controller;
    }
    
    @FXML
    private void handleSimpan() {
        if (!validateInput()) {
            return;
        }
        
        if (guru == null) {
            Guru newGuru = new Guru();
            newGuru.setNip(nipField.getText().trim());
            newGuru.setNama(namaField.getText().trim());
            newGuru.setJabatan(jabatanField.getText().trim());
            newGuru.setAlamat(alamatField.getText().trim());
            newGuru.setNoTelp(telpField.getText().trim());
            newGuru.setStatus(statusCombo.getSelectionModel().getSelectedItem());
            
            if (guruDAO.create(newGuru)) {
                showAlert("Sukses", "Guru berhasil ditambahkan!", Alert.AlertType.INFORMATION);
                guruController.refreshTable();
                closeDialog();
            } else {
                showAlert("Error", "Gagal menambahkan guru! NIP mungkin sudah ada.", Alert.AlertType.ERROR);
            }
        } else {
            guru.setNama(namaField.getText().trim());
            guru.setJabatan(jabatanField.getText().trim());
            guru.setAlamat(alamatField.getText().trim());
            guru.setNoTelp(telpField.getText().trim());
            guru.setStatus(statusCombo.getSelectionModel().getSelectedItem());
            
            if (guruDAO.update(guru)) {
                showAlert("Sukses", "Guru berhasil diupdate!", Alert.AlertType.INFORMATION);
                guruController.refreshTable();
                closeDialog();
            } else {
                showAlert("Error", "Gagal mengupdate guru!", Alert.AlertType.ERROR);
            }
        }
    }
    
    @FXML
    private void handleBatal() {
        closeDialog();
    }
    
    private boolean validateInput() {
        if (nipField.getText().trim().isEmpty() ||
            namaField.getText().trim().isEmpty()) {
            showAlert("Error", "Field bertanda * harus diisi!", Alert.AlertType.ERROR);
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

