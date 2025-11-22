package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.dao.SiswaDAO;
import com.smk.alasiyah.perpustakaan.model.Siswa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SiswaDialogController {
    
    @FXML
    private Label titleLabel;
    
    @FXML
    private TextField nisField, namaField, kelasField, telpField;
    
    @FXML
    private TextArea alamatField;
    
    @FXML
    private ComboBox<String> statusCombo;
    
    @FXML
    private Button btnSimpan, btnBatal;
    
    private Siswa siswa;
    private SiswaController siswaController;
    private SiswaDAO siswaDAO = new SiswaDAO();
    
    @FXML
    private void initialize() {
        statusCombo.getItems().addAll("aktif", "nonaktif");
        statusCombo.getSelectionModel().select(0);
    }
    
    public void setSiswa(Siswa siswa) {
        this.siswa = siswa;
        if (siswa != null) {
            titleLabel.setText("Edit Siswa");
            nisField.setText(siswa.getNis());
            nisField.setEditable(false);
            namaField.setText(siswa.getNama());
            kelasField.setText(siswa.getKelas());
            alamatField.setText(siswa.getAlamat());
            telpField.setText(siswa.getNoTelp());
            statusCombo.getSelectionModel().select(siswa.getStatus());
        }
    }
    
    public void setSiswaController(SiswaController controller) {
        this.siswaController = controller;
    }
    
    @FXML
    private void handleSimpan() {
        if (!validateInput()) {
            return;
        }
        
        if (siswa == null) {
            Siswa newSiswa = new Siswa();
            newSiswa.setNis(nisField.getText().trim());
            newSiswa.setNama(namaField.getText().trim());
            newSiswa.setKelas(kelasField.getText().trim());
            newSiswa.setAlamat(alamatField.getText().trim());
            newSiswa.setNoTelp(telpField.getText().trim());
            newSiswa.setStatus(statusCombo.getSelectionModel().getSelectedItem());
            
            if (siswaDAO.create(newSiswa)) {
                showAlert("Sukses", "Siswa berhasil ditambahkan!", Alert.AlertType.INFORMATION);
                siswaController.refreshTable();
                closeDialog();
            } else {
                showAlert("Error", "Gagal menambahkan siswa! NIS mungkin sudah ada.", Alert.AlertType.ERROR);
            }
        } else {
            siswa.setNama(namaField.getText().trim());
            siswa.setKelas(kelasField.getText().trim());
            siswa.setAlamat(alamatField.getText().trim());
            siswa.setNoTelp(telpField.getText().trim());
            siswa.setStatus(statusCombo.getSelectionModel().getSelectedItem());
            
            if (siswaDAO.update(siswa)) {
                showAlert("Sukses", "Siswa berhasil diupdate!", Alert.AlertType.INFORMATION);
                siswaController.refreshTable();
                closeDialog();
            } else {
                showAlert("Error", "Gagal mengupdate siswa!", Alert.AlertType.ERROR);
            }
        }
    }
    
    @FXML
    private void handleBatal() {
        closeDialog();
    }
    
    private boolean validateInput() {
        if (nisField.getText().trim().isEmpty() ||
            namaField.getText().trim().isEmpty() ||
            kelasField.getText().trim().isEmpty()) {
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

