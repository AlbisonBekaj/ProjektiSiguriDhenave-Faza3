package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import managers.SceneManager;
import models.User;
import service.UserService;
import utils.SceneLocator;
import managers.SessionManager;

public class ChangePasswordController {

    @FXML
    private PasswordField currentPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Label statusLabel;

    private final UserService userService = new UserService();

    @FXML
    public void initialize() {
        statusLabel.setText("");
        changePasswordButton.setDisable(true);

        currentPasswordField.textProperty().addListener((obs, oldVal, newVal) -> validateForm());
        newPasswordField.textProperty().addListener((obs, oldVal, newVal) -> validateForm());
        confirmPasswordField.textProperty().addListener((obs, oldVal, newVal) -> validateForm());
    }


    @FXML
    private void handleChangePasswordClick() {
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        User user = SessionManager.getCurrentUser();

        if (user == null) {
            statusLabel.setText("Përdoruesi nuk është i loguar.");
            return;
        }

        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            statusLabel.setText("Të gjitha fushat janë të detyrueshme.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            statusLabel.setText("Fjalëkalimet e reja nuk përputhen.");
            return;
        }

        String hashedInput = hashPassword(currentPassword, user.getSalt());
        if (!hashedInput.equals(user.getSaltedHash())) {
            statusLabel.setText("Fjalëkalimi aktual është i gabuar.");
            return;
        }

        boolean success = userService.changePassword(user.getId(), newPassword);
        if (success) {
            statusLabel.setText("Fjalëkalimi u ndryshua me sukses.");
        } else {
            statusLabel.setText("Gabim gjatë ndryshimit të fjalëkalimit.");
        }
    }

    private String hashPassword(String password, String salt) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            md.update(java.util.Base64.getDecoder().decode(salt));
            byte[] hashedPassword = md.digest(password.getBytes());
            return java.util.Base64.getEncoder().encodeToString(hashedPassword);
        } catch (Exception e) {
            throw new RuntimeException("Hashing error", e);
        }
    }

    @FXML
    private void handleBackClick() {
        try {
            SceneManager.load(SceneLocator.HELLO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateForm() {
        String currentPassword = currentPasswordField.getText();
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        boolean allFieldsFilled = !currentPassword.isEmpty() && !newPassword.isEmpty() && !confirmPassword.isEmpty();
        boolean passwordsMatch = newPassword.equals(confirmPassword);

        changePasswordButton.setDisable(!(allFieldsFilled && passwordsMatch));

        if (!confirmPassword.isEmpty() && !newPassword.equals(confirmPassword)) {
            statusLabel.setText("Fjalëkalimet nuk përputhen.");
        } else {
            statusLabel.setText("");
        }
    }

}
