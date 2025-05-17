package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.User;
import service.UserService;
import session.SessionManager;

public class ChangePasswordController {

    @FXML private PasswordField currentPasswordField;
    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label statusLabel;

    private final UserService userService = new UserService();

    @FXML
    public void handleChangePassword() {
        User currentUser = SessionManager.getCurrentUser();
        if (currentUser == null) {
            statusLabel.setText("Nuk ka përdorues të kyçur.");
            return;
        }

        String current = currentPasswordField.getText();
        String newPass = newPasswordField.getText();
        String confirm = confirmPasswordField.getText();

        if (!newPass.equals(confirm)) {
            statusLabel.setText("Fjalëkalimet nuk përputhen.");
            return;
        }

        boolean result = userService.updatePassword(currentUser.getUsername(), current, newPass);

        if (result) {
            statusLabel.setText("Fjalëkalimi u ndryshua me sukses.");
        } else {
            statusLabel.setText("Fjalëkalimi aktual është i pasaktë.");
        }
    }
}
