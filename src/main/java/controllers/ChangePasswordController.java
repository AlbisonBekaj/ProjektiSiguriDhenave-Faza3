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
            statusLabel.setText("User is not logged!");
            return;
        }

        if (currentPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            statusLabel.setText("All fields are required!");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            statusLabel.setText("New passwords do not match!");
            return;
        }

        String hashedInput = hashPassword(currentPassword, user.getSalt());
        if (!hashedInput.equals(user.getSaltedHash())) {
            statusLabel.setText("Current password is incorrect!");
            return;
        }

        boolean success = userService.changePassword(user.getId(), newPassword);
        if (success) {
            statusLabel.setText("Password was changed successfully.");
        } else {
            statusLabel.setText("Error while changing password!");
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
            statusLabel.setText("Passwords do not match!");
        } else {
            statusLabel.setText("");
        }
    }

}
