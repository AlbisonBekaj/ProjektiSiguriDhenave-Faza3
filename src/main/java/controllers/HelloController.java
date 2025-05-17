package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import managers.SceneManager;
import models.User;
import utils.SceneLocator;
import managers.SessionManager;

public class HelloController {
    @FXML
    public Label salted;
    @FXML
    public Label saltedHash;

    public void initialize(){
        User user = SessionManager.getCurrentUser();
        salted.setText(user.getSalt());
        saltedHash.setText(user.getSaltedHash());
    }

    public void handleSignOutClick(){
        try {
            SessionManager.clear();
            SceneManager.load(SceneLocator.SING_IN);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void handleChangePasswordClick() {
        try {
            SceneManager.load("/views/Change_password.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
