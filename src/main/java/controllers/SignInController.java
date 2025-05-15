package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import managers.SceneManager;
import models.User;
import repository.UserRepository;
import service.UserService;
import utils.SceneLocator;

public class SignInController {
    UserService userService=new UserService();
    @FXML
    private AnchorPane AnchorPaneSignIn;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void handleCreateUserClick(){
        try {
            SceneManager.load(SceneLocator.SING_UP);
        }catch(Exception e ){
            e.printStackTrace();
        }
    }
    public void handleSignInClick(){
        try {
            String userName = usernameField.getText();
            String password = passwordField.getText();
            int signin = userService.signIn(userName, password);
            if (signin > 0) {
                SceneManager.load(SceneLocator.HELLO);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Carefull Wrong information").showAndWait();
            }
        }catch(Exception e){
            new Alert(Alert.AlertType.INFORMATION, "Carefull Wrong username or password").showAndWait();
            e.printStackTrace();
        }
    }
}
