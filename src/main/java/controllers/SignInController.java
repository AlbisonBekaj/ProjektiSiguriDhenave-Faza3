package controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import managers.SceneManager;
import utils.SceneLocator;

public class SignInController {
    @FXML
    private AnchorPane AnchorPaneSignIn;

    public void handleCreateUserClick(){
        try {
            SceneManager.load(SceneLocator.SING_UP);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
