package controllers;

import database.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import managers.SessionManager;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ChangePasswordController {

    @FXML
    private PasswordField oldPasswordField, newPasswordField, confirmPasswordField;

    @FXML
    private Label statusLabel;

    @FXML
    private void handleChangePassword() {
        String oldPass = oldPasswordField.getText();
        String newPass = newPasswordField.getText();
        String confirmPass = confirmPasswordField.getText();

        if (!newPass.equals(confirmPass)) {
            statusLabel.setText("Fjalëkalimet e reja nuk përputhen!");
            return;
        }

        User user = SessionManager.getCurrentUser();
        if (user == null) {
            statusLabel.setText("Nuk jeni i kyçur.");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT password FROM users WHERE id = ?");
            stmt.setInt(1, user.getId());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String currentHashedPassword = rs.getString("password");

                if (!oldPass.equals(currentHashedPassword)) {
                    statusLabel.setText("Fjalëkalimi aktual është i gabuar.");
                    return;
                }

                PreparedStatement updateStmt = conn.prepareStatement("UPDATE users SET password = ? WHERE id = ?");
                updateStmt.setString(1, newPass);
                updateStmt.setInt(2, user.getId());
                updateStmt.executeUpdate();

                statusLabel.setText("Fjalëkalimi u ndryshua me sukses.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusLabel.setText("Gabim gjatë ndryshimit të fjalëkalimit.");
        }
    }
}
