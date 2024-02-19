package com.pbl.biblioteca.controller;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.fullException;
import com.pbl.biblioteca.exceptionHandler.notFoundException;
import com.pbl.biblioteca.exceptionHandler.readerIsBlockedException;
import com.pbl.biblioteca.exceptionHandler.tooManyReservesException;
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

public class LibrarianController implements Initializable {

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TableColumn<Book, String> categoryCol;

    @FXML
    private TextField createLoanIsbn;

    @FXML
    private TextField createLoanUsername;

    @FXML
    private TableColumn<Book, String> isbnCol;

    @FXML
    private Menu menuMyProfile;

    @FXML
    private Menu menuMyProfile1;

    @FXML
    private MenuItem menuSearchBook;

    @FXML
    private MenuBar menuBar;

    @FXML
    private AnchorPane paneLoan;

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
    private TextField profileType;

    @FXML
    private TextField profileUsername;

    @FXML
    private TableColumn<Book, String> publisherCol;

    @FXML
    private TextField retrieveLoanIsbn;

    @FXML
    private TextField retrieveLoanUsername;

    @FXML
    private Text searchBookAlert;

    @FXML
    private Button searchBookButton;

    @FXML
    private ComboBox<String> searchBookComboBox;

    @FXML
    private TextField searchBookTextField;

    @FXML
    private TextField createLoanDays;

    @FXML
    private TableView<Book> tableBook;

    @FXML
    private TableColumn<Book, String> titleCol;

    @FXML
    private Text warningCreateLoan;

    @FXML
    private Text warningRetrieveBook;

    @FXML
    private TableColumn<Book, Integer> yearCol;

    private Librarian nowLibrarian;

    private PauseTransition pause;

    @FXML
    void borrowBook(ActionEvent event) {
        nowLibrarian = (Librarian) LocalSystem.getNowUser();
        Integer days;
        Reader r = null;
        Book b;
        LocalDate endBlock;

        try{
            days = Integer.valueOf(createLoanDays.getText());
            r = DAO.getReaderDAO().getByPK(createLoanUsername.getText());
            b = DAO.getBookDAO().getByPK(createLoanIsbn.getText());

            nowLibrarian.createBookLoan(b, r, days);
            warningCreateLoan.setText("Empréstimo criado com sucesso");
        } catch (NumberFormatException e){
            warningCreateLoan.setText("Total de dias inválido");
        } catch (readerIsBlockedException f){
            endBlock = r.getDateEndBlock();
            warningCreateLoan.setText("Usuário bloqueado até " + endBlock.getDayOfMonth()
                    + "/" + endBlock.getMonthValue() + "/" + endBlock.getYear());

        } catch (notFoundException g){
            warningCreateLoan.setText("Não existem cópias disponíveis");
        } catch (fullException f){
            warningCreateLoan.setText("O usuário/livro já atingiu o limite de empréstimos");
        } catch (tooManyReservesException e){
            warningCreateLoan.setText("Já existem muitas reservas para o livro atual");
        }

        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e1 ->
                warningCreateLoan.setText(""));
        pause.play();
    }

    @FXML
    void goToMyProfile(ActionEvent event) {
        turnAllPanesInvisible();
        paneMyProfile.setVisible(true);
    }

    @FXML
    void goToSearchBook(ActionEvent event) {
        turnAllPanesInvisible();
        paneSearchBook.setVisible(true);
    }

    @FXML
    void goToLoan(ActionEvent event) {
        turnAllPanesInvisible();
        paneLoan.setVisible(true);
    }

    @FXML
    void retrieveLoan(ActionEvent event) {
        nowLibrarian = (Librarian) LocalSystem.getNowUser();
        ArrayList<Loan> allUser = null;
        String isbn;
        Loan toDelete = null;

        isbn = retrieveLoanIsbn.getText();
        allUser = DAO.getLoanDAO().getAllFromUser(retrieveLoanUsername.getText());

        if (allUser != null) {
            System.out.println(allUser.get(0).getBookIsbn());
            for (Loan l : allUser) {
                if (l.getBookIsbn().equals(isbn)) {
                    toDelete = l;
                }
            }
        }

        if (toDelete != null) {
            nowLibrarian.deleteBookLoan(toDelete.getId());
            warningRetrieveBook.setText("Livro devolvido com sucesso");

        } else {
            warningRetrieveBook.setText("Empréstimo não encontrado");
        }

        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(e1 ->
                warningRetrieveBook.setText(""));
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
        searchBookComboBox.getItems().removeAll(searchBookComboBox.getItems());
        searchBookComboBox.getItems().addAll("Título", "ISBN", "Autor", "Categoria");
        searchBookComboBox.getSelectionModel().select("ISBN");

        disableTF(profileUsername);
        disableTF(profileAddress);
        disableTF(profileName);
        disableTF(profilePhone);
        disableTF(profileType);

        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        yearCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("year"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
    }

    private void turnAllPanesInvisible(){
        paneSearchBook.setVisible(false);
        paneMyProfile.setVisible(false);
        paneLoan.setVisible(false);
    }

    private void disableTF(TextField tf){
        tf.setFocusTraversable(false);
        tf.setMouseTransparent(true);
        tf.setEditable(false);
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
