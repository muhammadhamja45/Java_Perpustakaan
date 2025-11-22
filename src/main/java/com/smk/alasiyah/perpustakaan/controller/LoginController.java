package com.smk.alasiyah.perpustakaan.controller;

import com.smk.alasiyah.perpustakaan.Main;
import com.smk.alasiyah.perpustakaan.dao.UserDAO;
import com.smk.alasiyah.perpustakaan.model.User;
import com.smk.alasiyah.perpustakaan.util.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        
        if (username.isEmpty() || password.isEmpty()) {
            showError("Username dan password harus diisi!");
            return;
        }
        
        UserDAO userDAO = new UserDAO();
        User user = userDAO.login(username, password);
        
        if (user != null) {
            SessionManager.setCurrentUser(user);
            try {
                loadMainView();
            } catch (IOException e) {
                e.printStackTrace();
                showError("Gagal memuat aplikasi!");
            }
        } else {
            showError("Username atau password salah!");
        }
    }
    
    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);
    }
    
    private void loadMainView() throws IOException {
        Stage stage = (Stage) usernameField.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("view/main.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        // Load CSS untuk main view
        scene.getStylesheets().add(Main.class.getResource("view/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Perpustakaan SMK AL-ASIYAH");
        stage.setMaximized(true);
        stage.show();
    }
}

