package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Fotomanager extends Application {
    private static Fotomanager fotomanager = null;
    private static String Title = "FotoGenerator";
    private Scene scene;
    private Stage stage;

    static Fotomanager getInstance(){
        return fotomanager;
    }

    @Override
    public void start(Stage primaryStage){
        Fotomanager.fotomanager = this;
        this.stage = primaryStage;

        final URL fxmlUrl = getClass().getResource("/View/Main.fxml");

        final FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);

        try {
            this.scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(this.scene);
        stage.show();
        stage.setOnCloseRequest(event -> {

        });

        stage.setTitle(Title);
        stage.setScene(scene);
        stage.show();

        stage.setResizable(false);
    }

    Scene getScene(){
        return this.scene;
    }
    Stage getStage() {return this.stage; }
}
