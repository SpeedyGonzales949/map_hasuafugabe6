package com.example.map_hsg6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * this class starts the app
 */
public class CollegeApp extends Application {
    @Override
    public void start(@NotNull Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CollegeApp.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Best App Ever");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}