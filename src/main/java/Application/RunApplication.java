package Application;

import javafx.application.Application;
import javafx.stage.Stage;
import managers.SceneManager;

import java.io.IOException;

public class RunApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SceneManager sceneManager = SceneManager.getInstance();
        stage.setScene(sceneManager.getScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}