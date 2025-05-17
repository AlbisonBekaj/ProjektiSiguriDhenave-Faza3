package controllers;

import managers.SceneManager;
import utils.SceneLocator;
import managers.SessionManager;

public class HelloController {
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
