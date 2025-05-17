package controllers;

import managers.SceneManager;
import utils.SceneLocator;

public class HelloController {
    public void handleSignOutClick(){
        try {
            SceneManager.load(SceneLocator.SING_IN);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void handleChangePasswordClick() {
        try {
            SceneManager.load("views/ChangePassword.fxml"); // ose përdor SceneLocator nëse e ke të konfiguruar
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
