package com.pbl.biblioteca.controller;

import com.pbl.biblioteca.exceptionHandler.notFoundException;
import com.pbl.biblioteca.exceptionHandler.wrongPasswordException;

import com.pbl.biblioteca.model.LocalSystem;
import com.pbl.biblioteca.view.View;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Label welcomeText;

    @FXML
    private TextField usernameInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private ComboBox<String> userType;

    @FXML
    private Text warning;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        userType.getItems().removeAll(userType.getItems());
        userType.getItems().addAll("Admin", "Bibliotecário", "Leitor");
        warning.setTextAlignment(TextAlignment.CENTER);
    }

    protected void changeToAdminScene(ActionEvent ev) throws IOException{
        root = FXMLLoader.load(View.class.getResource
                ("/com/pbl/biblioteca/admin.fxml"));

        stage = (Stage)((Node)ev.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    protected void login(ActionEvent ev) throws IOException {
        String chosenType = userType.getValue();

        if (chosenType != null){
            try {

                if ( chosenType.equals("Admin")) {
                    LocalSystem.login(usernameInput.getText(), passwordInput.getText(), "admin");
                    changeToAdminScene(ev);

                } else if ( chosenType.equals("Bibliotecário")) {
                    LocalSystem.login(usernameInput.getText(), passwordInput.getText(), "librarian");


                } else if ( chosenType.equals("Leitor")) {
                    LocalSystem.login(usernameInput.getText(), passwordInput.getText(), "reader");


                }

            } catch (notFoundException e){
                System.out.println("Nao existe");
                warning.setText("                              ");
                warning.setText("Usuário inexistente");

            } catch (wrongPasswordException e){
                warning.setText("                       ");
                warning.setText("Senha inválida");

            }
        } else {
            warning.setText("                              ");
            warning.setText("Escolha um tipo de usuário");
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
                warning.setText("                         "));
        pause.play();

    }

    @FXML
    protected void guestLogin(){

    }
}