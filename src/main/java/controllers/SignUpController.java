package controllers;

import managers.SceneManager;
import utils.SceneLocator;

public class SignUpController {
    public void handleGoBackClick(){
        try {
            SceneManager.load(SceneLocator.SING_IN);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void handleSignUpClick(){

    }
}
