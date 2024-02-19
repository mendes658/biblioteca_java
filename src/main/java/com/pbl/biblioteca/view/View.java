package com.pbl.biblioteca.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

import static com.pbl.biblioteca.dao.ConnectionFile.setDefaultFileUrls;

public class View extends Application {

    @Override
    public void init(){
        setDefaultFileUrls();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(View.class.getResource
                ("/com/pbl/biblioteca/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setWidth(800);
        stage.setHeight(600);
        stage.setResizable(false);
        stage.setTitle("Biblioteca");




        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}