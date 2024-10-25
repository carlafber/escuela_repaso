package org.example.escuela_repaso;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EscuelaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EscuelaApplication.class.getResource("inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ESCUELA");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}