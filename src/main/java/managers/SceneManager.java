package managers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SceneManager {
    private static Stage primaryStage;
    private static final Map<String, String> scenePaths = new HashMap<>();
    private static final Map<String, Scene> sceneCache = new HashMap<>();

    static {
        scenePaths.put("signin", "/views/Sign_in.fxml");
        scenePaths.put("signup", "/views/Create_user.fxml");
        scenePaths.put("dashboard", "/views/Hello.fxml");
    }
}