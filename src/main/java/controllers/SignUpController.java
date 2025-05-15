package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import managers.SceneManager;
import models.User;
import service.UserService;
import utils.SceneLocator;

public class SignUpController {
    UserService userService= new UserService();
    @FXML
    private TextField usernameFieldSignUp;
    @FXML
    private PasswordField passwordFieldSignUp;

    public void handleGoBackClick(){
        try {
            SceneManager.load(SceneLocator.SING_IN);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void handleSignUpClick(){
        try {
            String userName = usernameFieldSignUp.getText();
            String password = passwordFieldSignUp.getText();
            User signUp = userService.signUp(userName, password);
        }catch(UserService.ExceptionAlreadyExists e){
            new Alert(Alert.AlertType.INFORMATION, "Carefull ,this username already exits!!").showAndWait();
            e.printStackTrace();
        }catch(Exception e){
            new Alert(Alert.AlertType.INFORMATION, "Carefull Wrong information").showAndWait();
            e.printStackTrace();
        }
    }
}
