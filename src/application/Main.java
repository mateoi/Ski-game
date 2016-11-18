package application;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage window) {
        try {
            Group root = new Group();
            Scene scene = new Scene(root);
            window.setScene(scene);

            Canvas canvas = new Canvas(300, 550);
            root.getChildren().add(canvas);
            Game game = new Game(10, 2.0, 0.03, 1, 0.05, canvas.getWidth(), canvas.getHeight());
            GameController controller = new GameController(game, canvas, scene);
            window.show();
            controller.playGame();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
