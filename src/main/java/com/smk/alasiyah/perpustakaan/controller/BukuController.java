package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.Main;
import com.smk.alasiyah.perpustakaan.dao.BukuDAO;
import com.smk.alasiyah.perpustakaan.model.Buku;
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

public class BukuController {
    
    @FXML
    private TableView<Buku> bukuTable;
    
    @FXML
    private TableColumn<Buku, String> colKode, colJudul, colPengarang, colPenerbit, colKategori, colLokasi;
    
    @FXML
    private TableColumn<Buku, Integer> colTahun, colStok;
    
    @FXML
    private TableColumn<Buku, Void> colAksi;
    
    @FXML
    private TextField searchField;
    
    @FXML
    private ComboBox<String> kategoriCombo;
    
    @FXML
    private Button btnTambah, btnSearch, btnRefresh;
    
    private BukuDAO bukuDAO = new BukuDAO();
    private ObservableList<Buku> bukuList = FXCollections.observableArrayList();
    
    @FXML
    private void initialize() {
        setupTable();
        loadKategori();
        loadData();
    }
    
    private void setupTable() {
        colKode.setCellValueFactory(new PropertyValueFactory<>("kodeBuku"));
        colJudul.setCellValueFactory(new PropertyValueFactory<>("judul"));
        colPengarang.setCellValueFactory(new PropertyValueFactory<>("pengarang"));
        colPenerbit.setCellValueFactory(new PropertyValueFactory<>("penerbit"));
        colTahun.setCellValueFactory(new PropertyValueFactory<>("tahun"));
        colKategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        colStok.setCellValueFactory(new PropertyValueFactory<>("stok"));
        colLokasi.setCellValueFactory(new PropertyValueFactory<>("lokasiRak"));
        
        colAksi.setCellFactory(param -> new TableCell<Buku, Void>() {
            private final Button btnEdit = new Button("✏️ Edit");
            private final Button btnHapus = new Button("🗑️ Hapus");
            
            {
                javafx.scene.control.Tooltip tooltipEdit = new javafx.scene.control.Tooltip("Klik untuk mengedit data buku");
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
                
                javafx.scene.control.Tooltip tooltipHapus = new javafx.scene.control.Tooltip("Klik untuk menghapus data buku");
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
                    Buku buku = getTableView().getItems().get(getIndex());
                    if (buku != null) {
                        handleEdit(buku);
                    }
                });
                btnHapus.setOnAction(event -> {
                    Buku buku = getTableView().getItems().get(getIndex());
                    if (buku != null) {
                        handleHapus(buku);
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
        
        bukuTable.setItems(bukuList);
    }
    
    private void loadKategori() {
        List<String> kategori = bukuDAO.getAllKategori();
        kategoriCombo.getItems().addAll(kategori);
        kategoriCombo.getItems().add(0, "Semua Kategori");
        kategoriCombo.getSelectionModel().select(0);
    }
    
    private void loadData() {
        bukuList.clear();
        List<Buku> data = bukuDAO.getAll();
        bukuList.addAll(data);
    }
    
    @FXML
    private void handleTambah() {
        showBukuDialog(null);
    }
    
    @FXML
    private void handleEdit(Buku buku) {
        showBukuDialog(buku);
    }
    
    @FXML
    private void handleHapus(Buku buku) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Konfirmasi");
        alert.setHeaderText("Hapus Buku");
        alert.setContentText("Apakah Anda yakin ingin menghapus buku: " + buku.getJudul() + "?");
        
        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            if (bukuDAO.delete(buku.getIdBuku())) {
                loadData();
                showAlert("Sukses", "Buku berhasil dihapus!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error", "Gagal menghapus buku!", Alert.AlertType.ERROR);
            }
        }
    }
    
    @FXML
    private void handleSearch() {
        String keyword = searchField.getText().trim();
        String kategori = kategoriCombo.getSelectionModel().getSelectedItem();
        
        bukuList.clear();
        List<Buku> data;
        
        if (!keyword.isEmpty()) {
            data = bukuDAO.search(keyword);
        } else if (kategori != null && !kategori.equals("Semua Kategori")) {
            data = bukuDAO.filterByKategori(kategori);
        } else {
            data = bukuDAO.getAll();
        }
        
        bukuList.addAll(data);
    }
    
    @FXML
    private void handleRefresh() {
        searchField.clear();
        kategoriCombo.getSelectionModel().select(0);
        loadData();
    }
    
    private void showBukuDialog(Buku buku) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/buku_dialog.fxml"));
            Scene scene = new Scene(loader.load());
            BukuDialogController controller = loader.getController();
            controller.setBuku(buku);
            controller.setBukuController(this);
            
            Stage stage = new Stage();
            stage.setTitle(buku == null ? "Tambah Buku" : "Edit Buku");
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

