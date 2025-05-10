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

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void loadScene(String sceneName) {
        try {
            if (sceneCache.containsKey(sceneName)) {
                primaryStage.setScene(sceneCache.get(sceneName));
                return;
            }

            String fxmlPath = scenePaths.get(sceneName);
            if (fxmlPath == null) {
                throw new IllegalArgumentException("Scene name not registered: " + sceneName);
            }

            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            sceneCache.put(sceneName, scene);

            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("Failed to load scene: " + sceneName);
            e.printStackTrace();
        }
    }
}