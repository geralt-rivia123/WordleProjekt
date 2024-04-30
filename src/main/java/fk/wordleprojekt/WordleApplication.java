package fk.wordleprojekt;

import fk.wordleprojekt.controllers.WordleController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class WordleApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WordleApplication.class.getResource("wordle-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Wordle");
        stage.setScene(scene);
        stage.show();

        GameManager gameManager = GameManager.getInstance();
        gameManager.setCurrentRound(0);
        WordleController controller = new WordleController();



    }

    public static void main(String[] args) {
        launch();
    }
}