package managers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.SceneLocator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class SceneManager {
    private static SceneManager sceneManager;
    private Scene scene;
    private String currentPath;
    private Stack<String> history = new Stack<>();

    private SceneManager(){
        this.currentPath= SceneLocator.SING_IN;
        this.scene=this.initScene();
    }
    public static SceneManager getInstance(){
        if(sceneManager==null){
            sceneManager = new SceneManager();
        }
        return sceneManager;
    }
    public Scene initScene(){
        try{
            return new Scene(this.getParent(currentPath));
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public static void load(String path)throws Exception{
        if(sceneManager == null){
            throw new Exception(" Scene manager nuk është 'null' !");
        }
        sceneManager.loadParent(path);
    }
    private void loadParent(String path) throws Exception{
        if (currentPath != null && !currentPath.equals(path)) {
            history.push(currentPath);
        }
        Parent parent =getParent(path);
        this.currentPath =path;
        scene.setRoot(parent);
    }
    private Parent getParent(String path) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        return loader.load();
    }
    public Scene getScene(){
        return scene;
    }


}