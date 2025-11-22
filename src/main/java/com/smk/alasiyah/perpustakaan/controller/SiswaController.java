package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.Main;
import com.smk.alasiyah.perpustakaan.dao.SiswaDAO;
import com.smk.alasiyah.perpustakaan.model.Siswa;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class SiswaController {
    
    @FXML
    private TableView<Siswa> siswaTable;
    
    @FXML
    private TableColumn<Siswa, String> colNis, colNama, colKelas, colAlamat, colTelp, colStatus;
    
    @FXML
    private TableColumn<Siswa, Void> colAksi;
    
    @FXML
    private TextField searchField;
    
    @FXML
    private ComboBox<String> statusCombo;
    
    private SiswaDAO siswaDAO = new SiswaDAO();
    private ObservableList<Siswa> siswaList = FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
        setupTable();
        statusCombo.getItems().addAll("Semua Status", "aktif", "nonaktif");
        statusCombo.getSelectionModel().select(0);
        loadData();
    }
    
    private void setupTable() {
        colNis.setCellValueFactory(new PropertyValueFactory<>("nis"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colKelas.setCellValueFactory(new PropertyValueFactory<>("kelas"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colTelp.setCellValueFactory(new PropertyValueFactory<>("noTelp"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        colAksi.setCellFactory(param -> new TableCell<Siswa, Void>() {
            private final Button btnEdit = new Button("✏️ Edit");
            private final Button btnHapus = new Button("🗑️ Hapus");
            
            {
                javafx.scene.control.Tooltip tooltipEdit = new javafx.scene.control.Tooltip("Klik untuk mengedit data siswa");
                tooltipEdit.setStyle("-fx-font-size: 12px; -fx-background-color: #2c3e50; -fx-text-fill: white; -fx-padding: 8 12 8 12;");
                tooltipEdit.setShowDelay(javafx.util.Duration.millis(300));
                btnEdit.setTooltip(tooltipEdit);
                btnEdit.setStyle("-fx-background-color: linear-gradient(to right, #3498db 0%, #2980b9 100%); " +
                                "-fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                "-fx-padding: 8 14 8 14; -fx-font-size: 12px; -fx-cursor: hand; " +
                                "-fx-effect: dropshadow(gaussian, rgba(52, 152, 219, 0.3), 8, 0, 0, 2);");
                btnEdit.setOnMouseEntered(e -> btnEdit.setStyle("-fx-background-color: linear-gradient(to right, #2980b9 0%, #3498db 100%); " +
                                "-fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                "-fx-padding: 8 14 8 14; -fx-font-size: 12px; -fx-cursor: hand; " +
                                "-fx-effect: dropshadow(gaussian, rgba(52, 152, 219, 0.5), 12, 0, 0, 4); " +
                                "-fx-scale-x: 1.05; -fx-scale-y: 1.05;"));
                btnEdit.setOnMouseExited(e -> btnEdit.setStyle("-fx-background-color: linear-gradient(to right, #3498db 0%, #2980b9 100%); " +
                                "-fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                "-fx-padding: 8 14 8 14; -fx-font-size: 12px; -fx-cursor: hand; " +
                                "-fx-effect: dropshadow(gaussian, rgba(52, 152, 219, 0.3), 8, 0, 0, 2); " +
                                "-fx-scale-x: 1.0; -fx-scale-y: 1.0;"));
                
                javafx.scene.control.Tooltip tooltipHapus = new javafx.scene.control.Tooltip("Klik untuk menghapus data siswa");
                tooltipHapus.setStyle("-fx-font-size: 12px; -fx-background-color: #2c3e50; -fx-text-fill: white; -fx-padding: 8 12 8 12;");
                tooltipHapus.setShowDelay(javafx.util.Duration.millis(300));
                btnHapus.setTooltip(tooltipHapus);
                btnHapus.setStyle("-fx-background-color: linear-gradient(to right, #e74c3c 0%, #c0392b 100%); " +
                                "-fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                "-fx-padding: 8 14 8 14; -fx-font-size: 12px; -fx-cursor: hand; " +
                                "-fx-effect: dropshadow(gaussian, rgba(231, 76, 60, 0.3), 8, 0, 0, 2);");
                btnHapus.setOnMouseEntered(e -> btnHapus.setStyle("-fx-background-color: linear-gradient(to right, #c0392b 0%, #e74c3c 100%); " +
                                "-fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                "-fx-padding: 8 14 8 14; -fx-font-size: 12px; -fx-cursor: hand; " +
                                "-fx-effect: dropshadow(gaussian, rgba(231, 76, 60, 0.5), 12, 0, 0, 4); " +
                                "-fx-scale-x: 1.05; -fx-scale-y: 1.05;"));
                btnHapus.setOnMouseExited(e -> btnHapus.setStyle("-fx-background-color: linear-gradient(to right, #e74c3c 0%, #c0392b 100%); " +
                                "-fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; " +
                                "-fx-padding: 8 14 8 14; -fx-font-size: 12px; -fx-cursor: hand; " +
                                "-fx-effect: dropshadow(gaussian, rgba(231, 76, 60, 0.3), 8, 0, 0, 2); " +
                                "-fx-scale-x: 1.0; -fx-scale-y: 1.0;"));
                
                btnEdit.setOnAction(event -> {
                    Siswa siswa = getTableView().getItems().get(getIndex());
                    if (siswa != null) {
                        handleEdit(siswa);
                    }
                });
                btnHapus.setOnAction(event -> {
                    Siswa siswa = getTableView().getItems().get(getIndex());
                    if (siswa != null) {
                        handleHapus(siswa);
                    }
                });
            }
            
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hbox = new HBox(10, btnEdit, btnHapus);
                    hbox.setAlignment(javafx.geometry.Pos.CENTER);
                    setGraphic(hbox);
                }
            }
        });
        
        siswaTable.setItems(siswaList);
    }
    
    private void loadData() {
        siswaList.clear();
        List<Siswa> data = siswaDAO.getAll();
        siswaList.addAll(data);
    }
    
    @FXML
    private void handleTambah() {
        showSiswaDialog(null);
    }
    
    @FXML
    private void handleEdit(Siswa siswa) {
        showSiswaDialog(siswa);
    }
    
    @FXML
    private void handleHapus(Siswa siswa) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi");
        alert.setHeaderText("Hapus Siswa");
        alert.setContentText("Apakah Anda yakin ingin menghapus siswa: " + siswa.getNama() + "?");
        
        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            if (siswaDAO.delete(siswa.getIdSiswa())) {
                loadData();
                showAlert("Sukses", "Siswa berhasil dihapus!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Gagal menghapus siswa!", Alert.AlertType.ERROR);
            }
        }
    }
    
    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().trim();
        String status = statusCombo.getSelectionModel().getSelectedItem();
        
        siswaList.clear();
        List<Siswa> data;
        
        if (!keyword.isEmpty()) {
            data = siswaDAO.search(keyword);
        } else if (status != null && !status.equals("Semua Status")) {
            data = siswaDAO.filterByStatus(status);
        } else {
            data = siswaDAO.getAll();
        }
        
        siswaList.addAll(data);
    }
    
    @FXML
    private void handleRefresh() {
        searchField.clear();
        statusCombo.getSelectionModel().select(0);
        loadData();
    }
    
    private void showSiswaDialog(Siswa siswa) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/siswa_dialog.fxml"));
            Scene scene = new Scene(loader.load());
            SiswaDialogController controller = loader.getController();
            controller.setSiswa(siswa);
            controller.setSiswaController(this);
            
            Stage stage = new Stage();
            stage.setTitle(siswa == null ? "Tambah Siswa" : "Edit Siswa");
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void refreshTable() {
        loadData();
    }
    
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

