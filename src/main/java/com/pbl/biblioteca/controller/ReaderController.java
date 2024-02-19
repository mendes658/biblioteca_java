package com.pbl.biblioteca.controller;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.*;
import com.pbl.biblioteca.model.*;
import com.pbl.biblioteca.view.View;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class ReaderController implements Initializable {

    private Reader nowReader;

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TableColumn<Book, String> categoryCol;

    @FXML
    private TextField isbnToSend;

    @FXML
    private MenuBar menuBar;

    @FXML
    private TableColumn<AdminController.LoanTable, String> finalDateLoanCol;

    @FXML
    private TableColumn<AdminController.LoanTable, String> initalDateLoanCol;

    @FXML
    private TableColumn<Book, String> isbnCol;

    @FXML
    private TableColumn<AdminController.LoanTable, String> isbnLoanCol;

    @FXML
    private TableColumn<AdminController.LoanTable, String> librarianLoanCol;

    @FXML
    private Menu menuMyProfile;

    @FXML
    private MenuItem menuSearchBook;

    @FXML
    private AnchorPane paneMyProfile;

    @FXML
    private AnchorPane paneSearchBook;

    @FXML
    private TextField profileAddress;

    @FXML
    private TextField profileName;

    @FXML
    private TextField profilePhone;

    @FXML
    private TextField profileStatus;

    @FXML
    private TextField profileType;

    @FXML
    private TextField profileUsername;

    @FXML
    private TextField profileUsername1;

    @FXML
    private TextField profileUsername11;

    @FXML
    private TableColumn<Book, String> publisherCol;

    @FXML
    private Text searchBookAlert;

    @FXML
    private Button searchBookButton;

    @FXML
    private ComboBox<String> searchBookComboBox;

    @FXML
    private TextField searchBookTextField;

    @FXML
    private TableView<Book> tableBook;

    @FXML
    private TableView<AdminController.LoanTable> tableLoan;


    @FXML
    private TableColumn<Book, String> titleCol;

    @FXML
    private Text warningReserveBook;

    @FXML
    private TableColumn<Book, Integer> yearCol;

    private PauseTransition pause;

    @FXML
    void removeReserve(ActionEvent event) {
        nowReader = (Reader) LocalSystem.getNowUser();
        String toSend = isbnToSend.getText();

        if (toSend != null){
            Book b = DAO.getBookDAO().getByPK(isbnToSend.getText());

            try {
                if (b != null){
                    nowReader.removeReserve(b);

                    warningReserveBook.setText("Reserva criada com sucesso");
                } else {
                    warningReserveBook.setText("ISBN inválido");
                }
            } catch (notFoundException e){
                warningReserveBook.setText("Não existe reserva para o ISBN informado");
            }
        }



        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e1 ->
                warningReserveBook.setText(""));
        pause.play();
    }

    @FXML
    void reserveBook(ActionEvent event) {
        nowReader = (Reader) LocalSystem.getNowUser();
        String toSend = isbnToSend.getText();
        if (toSend != null){
            Book b = DAO.getBookDAO().getByPK(isbnToSend.getText());

            try{
                nowReader.createBookReserve(b);
                warningReserveBook.setText("Reserva criada com sucesso");
            } catch (readerIsBlockedException e){
                warningReserveBook.setText("Não é possível reservar no momento");
            } catch (fullException j){
                warningReserveBook.setText("Muitas reservas para o livro selecionado");
            } catch (copyAvailableException k){
                warningReserveBook.setText("Ainda existe uma cópia livre, não é necessário reservar");
            } catch (NullPointerException m){
                warningReserveBook.setText("Livro inexistente");
            }
        }


        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e1 ->
                warningReserveBook.setText(""));
        pause.play();
    }

    @FXML
    void renewLoan(ActionEvent event) {
        nowReader = (Reader) LocalSystem.getNowUser();
        ArrayList<Loan> allLoans = DAO.getLoanDAO().getAllFromUser(nowReader.getUsername());
        Loan toRenew = null;

        if (allLoans != null){
            for (Loan l : allLoans){
                if (l.getBookIsbn().equals(isbnToSend.getText())){
                    toRenew = l;
                }
            }
        }

        if (toRenew != null){
            try{
                nowReader.renewLoan(toRenew);
                warningReserveBook.setText("Renovação feita com sucesso");
                fillLoanTable();
            }catch (alreadyRenewedException e){
                warningReserveBook.setText("Este empréstimo já foi renovado uma vez");
            }catch (tooManyReservesException j){
                warningReserveBook.setText("Existem muitas reservas para o livro escolhido");
            }
        } else{
            warningReserveBook.setText("Não existe empréstimo para o ISBN informado");
        }

        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e1 ->
                warningReserveBook.setText(""));
        pause.play();
    }

    @FXML
    void searchBook(ActionEvent event) {

        Reader guestReader = new Reader("a", "a", "a", "a", "a");
        ObservableList<Book> list = FXCollections.observableArrayList();

        ArrayList<Book> searchedBooks = new ArrayList<>();

        String valueToSearch = searchBookTextField.getText();

        switch (searchBookComboBox.getValue()){
            case ("ISBN") -> {
                searchedBooks = guestReader.searchBookByIsbn(valueToSearch);
            }
            case ("Categoria") -> {
                searchedBooks = guestReader.searchBookByCategory(valueToSearch);
            }
            case ("Autor") -> {
                searchedBooks = guestReader.searchBookByAuthor(valueToSearch);
            }
            case ("Título") -> {
                searchedBooks = guestReader.searchBookByTitle(valueToSearch);
            }
        }

        if (searchedBooks != null){
            list.addAll(searchedBooks);
            tableBook.setItems(list);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        disableTF(profileUsername);
        disableTF(profileAddress);
        disableTF(profileName);
        disableTF(profilePhone);
        disableTF(profileType);
        disableTF(profileStatus);

        librarianLoanCol.setCellValueFactory(new PropertyValueFactory<AdminController.LoanTable, String>("librarianUsername"));
        isbnLoanCol.setCellValueFactory(new PropertyValueFactory<AdminController.LoanTable, String>("isbn"));
        initalDateLoanCol.setCellValueFactory(new PropertyValueFactory<AdminController.LoanTable, String>("initialDate"));
        finalDateLoanCol.setCellValueFactory(new PropertyValueFactory<AdminController.LoanTable, String>("endDate"));

        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        yearCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("year"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));

        searchBookComboBox.getItems().removeAll(searchBookComboBox.getItems());
        searchBookComboBox.getItems().addAll("Título", "ISBN", "Autor", "Categoria");
        searchBookComboBox.getSelectionModel().select("ISBN");

        goToMyProfile();
    }

    @FXML private void setMyProfileInfo(){
        nowReader = (Reader) LocalSystem.getNowUser();

        profileUsername.setText(nowReader.getUsername());
        profileAddress.setText(nowReader.getAddress());
        profileName.setText(nowReader.getName());
        profilePhone.setText(nowReader.getTelephone());

        if (nowReader.getBlocked()){
            LocalDate endBlock = nowReader.getDateEndBlock();

            profileStatus.setText("Bloqueado até " + endBlock.getDayOfMonth()
                    + "/" + endBlock.getMonthValue() + "/" + endBlock.getYear());
        } else {
            profileStatus.setText("Livre");
        }
    }

    @FXML
    protected void goToSearchBook(){
        turnAllPanesInvisible();
        paneSearchBook.setVisible(true);
    }

    @FXML
    protected void goToMyProfile(){
        setMyProfileInfo();
        fillLoanTable();
        turnAllPanesInvisible();
        paneMyProfile.setVisible(true);
    }

    private void turnAllPanesInvisible(){
        paneSearchBook.setVisible(false);
        paneMyProfile.setVisible(false);
    }

    private void disableTF(TextField tf){
        tf.setFocusTraversable(false);
        tf.setMouseTransparent(true);
        tf.setEditable(false);
    }

    @FXML
    protected void searchBook(){
        nowReader = (Reader) LocalSystem.getNowUser();

        ObservableList<Book> list = FXCollections.observableArrayList();

        ArrayList<Book> searchedBooks = new ArrayList<>();

        String valueToSearch = searchBookTextField.getText();

        switch (searchBookComboBox.getValue()){
            case ("ISBN") -> {
                searchedBooks = nowReader.searchBookByIsbn(valueToSearch);
            }
            case ("Categoria") -> {
                searchedBooks = nowReader.searchBookByCategory(valueToSearch);
            }
            case ("Autor") -> {
                searchedBooks = nowReader.searchBookByAuthor(valueToSearch);
            }
            case ("Título") -> {
                searchedBooks = nowReader.searchBookByTitle(valueToSearch);
            }
        }

        if (searchedBooks != null){
            list.addAll(searchedBooks);
            tableBook.setItems(list);
        }
    }


    @FXML
    private void fillLoanTable(){
        nowReader = (Reader) LocalSystem.getNowUser();

        ArrayList<Loan> searched;
        AdminController.LoanTable tableRow;
        ObservableList<AdminController.LoanTable> toShow = FXCollections.observableArrayList();
        String initialDate;
        String finalDate;

        if (nowReader.getUsername() != null){
            searched = DAO.getLoanDAO().getAllFromUser(nowReader.getUsername());

            for (Loan l : searched){
                initialDate = l.getInitialDate().getDayOfMonth() + "/" +
                        l.getInitialDate().getMonthValue() + "/" + l.getInitialDate().getYear();

                finalDate = l.getFinalDate().getDayOfMonth() + "/" +
                        l.getFinalDate().getMonthValue() + "/" + l.getFinalDate().getYear();

                tableRow = new AdminController.LoanTable(l.getBookIsbn(), initialDate, finalDate, l.getLibrarianUsername());
                toShow.add(tableRow);
            }

            tableLoan.setItems(toShow);
        }
    }

    @FXML
    protected void logout(ActionEvent ev) throws IOException {

        Parent root;
        root = FXMLLoader.load(Objects.requireNonNull(View.class.getResource
                ("/com/pbl/biblioteca/login.fxml")));
        Stage stage = (Stage) menuBar.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
