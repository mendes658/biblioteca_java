package com.pbl.biblioteca.controller;

import com.pbl.biblioteca.exceptionHandler.notFoundException;
import com.pbl.biblioteca.exceptionHandler.usernameAlreadyInUseException;
import com.pbl.biblioteca.exceptionHandler.wrongPasswordException;

import com.pbl.biblioteca.model.Admin;
import com.pbl.biblioteca.model.LocalSystem;
import com.pbl.biblioteca.model.User;
import com.pbl.biblioteca.view.View;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private static final int ADMIN = 0;
    private static final int GUEST = 1;
    private static final int LIBRARIAN = 2;
    private static final int READER = 3;


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

    protected void changeScene(ActionEvent ev, int type) throws IOException{

        Parent root;

        switch (type) {

            case ADMIN -> root = FXMLLoader.load(Objects.requireNonNull(View.class.getResource
                    ("/com/pbl/biblioteca/admin.fxml")));
            case READER -> root = FXMLLoader.load(Objects.requireNonNull(View.class.getResource
                    ("/com/pbl/biblioteca/reader.fxml")));
            case LIBRARIAN -> root = FXMLLoader.load(Objects.requireNonNull(View.class.getResource
                    ("/com/pbl/biblioteca/librarian.fxml")));
            case GUEST -> root = FXMLLoader.load(Objects.requireNonNull(View.class.getResource
                    ("/com/pbl/biblioteca/guest.fxml")));
            default -> {
                root = FXMLLoader.load(Objects.requireNonNull(View.class.getResource
                        ("/com/pbl/biblioteca/login.fxml")));
            }
        }


        Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    protected void login(ActionEvent ev) throws IOException{
        String chosenType = userType.getValue();

        if (chosenType != null){
            try {

                switch (chosenType) {
                    case "Admin" -> {
                        User a = LocalSystem.login(usernameInput.getText(), passwordInput.getText(), "admin");
                        LocalSystem.setNowUser(a);
                        changeScene(ev, ADMIN);
                    }
                    case "Bibliotecário" -> {
                        User a = LocalSystem.login(usernameInput.getText(), passwordInput.getText(), "librarian");
                        LocalSystem.setNowUser(a);
                        changeScene(ev, LIBRARIAN);
                    }
                    case "Leitor" -> {
                        User a = LocalSystem.login(usernameInput.getText(), passwordInput.getText(), "reader");
                        LocalSystem.setNowUser(a);
                        changeScene(ev, READER);
                    }
                }

            } catch (notFoundException e){
                System.out.println("Nao existe");
                warning.setText("");
                warning.setText("Usuário inexistente");

            } catch (wrongPasswordException e){
                warning.setText("");
                warning.setText("Senha inválida");

            }
        } else {
            warning.setText("");
            warning.setText("Escolha um tipo de usuário");
        }

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
                warning.setText(""));
        pause.play();

    }

    @FXML
    protected void guestLogin(ActionEvent ev) throws IOException{
        changeScene(ev, GUEST);
    }
}