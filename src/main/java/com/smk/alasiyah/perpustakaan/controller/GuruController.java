package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.Main;
import com.smk.alasiyah.perpustakaan.dao.GuruDAO;
import com.smk.alasiyah.perpustakaan.model.Guru;
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

public class GuruController {
    
    @FXML
    private TableView<Guru> guruTable;
    
    @FXML
    private TableColumn<Guru, String> colNip, colNama, colJabatan, colAlamat, colTelp, colStatus;
    
    @FXML
    private TableColumn<Guru, Void> colAksi;
    
    @FXML
    private TextField searchField;
    
    @FXML
    private ComboBox<String> statusCombo;
    
    private GuruDAO guruDAO = new GuruDAO();
    private ObservableList<Guru> guruList = FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
        setupTable();
        statusCombo.getItems().addAll("Semua Status", "aktif", "nonaktif");
        statusCombo.getSelectionModel().select(0);
        loadData();
    }
    
    private void setupTable() {
        colNip.setCellValueFactory(new PropertyValueFactory<>("nip"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colJabatan.setCellValueFactory(new PropertyValueFactory<>("jabatan"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        colTelp.setCellValueFactory(new PropertyValueFactory<>("noTelp"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        colAksi.setCellFactory(param -> new TableCell<Guru, Void>() {
            private final Button btnEdit = new Button("✏️ Edit");
            private final Button btnHapus = new Button("🗑️ Hapus");
            
            {
                javafx.scene.control.Tooltip tooltipEdit = new javafx.scene.control.Tooltip("Klik untuk mengedit data guru");
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
                
                javafx.scene.control.Tooltip tooltipHapus = new javafx.scene.control.Tooltip("Klik untuk menghapus data guru");
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
                    Guru guru = getTableView().getItems().get(getIndex());
                    if (guru != null) {
                        handleEdit(guru);
                    }
                });
                btnHapus.setOnAction(event -> {
                    Guru guru = getTableView().getItems().get(getIndex());
                    if (guru != null) {
                        handleHapus(guru);
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
        
        guruTable.setItems(guruList);
    }
    
    private void loadData() {
        guruList.clear();
        List<Guru> data = guruDAO.getAll();
        guruList.addAll(data);
    }
    
    @FXML
    private void handleTambah() {
        showGuruDialog(null);
    }
    
    @FXML
    private void handleEdit(Guru guru) {
        showGuruDialog(guru);
    }
    
    @FXML
    private void handleHapus(Guru guru) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi");
        alert.setHeaderText("Hapus Guru");
        alert.setContentText("Apakah Anda yakin ingin menghapus guru: " + guru.getNama() + "?");
        
        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            if (guruDAO.delete(guru.getIdGuru())) {
                loadData();
                showAlert("Sukses", "Guru berhasil dihapus!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Gagal menghapus guru!", Alert.AlertType.ERROR);
            }
        }
    }
    
    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().trim();
        String status = statusCombo.getSelectionModel().getSelectedItem();
        
        guruList.clear();
        List<Guru> data;
        
        if (!keyword.isEmpty()) {
            data = guruDAO.search(keyword);
        } else if (status != null && !status.equals("Semua Status")) {
            data = guruDAO.filterByStatus(status);
        } else {
            data = guruDAO.getAll();
        }
        
        guruList.addAll(data);
    }
    
    @FXML
    private void handleRefresh() {
        searchField.clear();
        statusCombo.getSelectionModel().select(0);
        loadData();
    }
    
    private void showGuruDialog(Guru guru) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/guru_dialog.fxml"));
            Scene scene = new Scene(loader.load());
            GuruDialogController controller = loader.getController();
            controller.setGuru(guru);
            controller.setGuruController(this);
            
            Stage stage = new Stage();
            stage.setTitle(guru == null ? "Tambah Guru" : "Edit Guru");
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

