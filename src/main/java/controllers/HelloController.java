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
}
