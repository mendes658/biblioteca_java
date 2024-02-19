package com.pbl.biblioteca.controller;

import com.pbl.biblioteca.dao.DAO;
import com.pbl.biblioteca.exceptionHandler.*;
import com.pbl.biblioteca.model.*;
import com.pbl.biblioteca.model.Book;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    public static class LoanTable{
        private  String isbn;
        private  String initialDate;
        private  String endDate;
        private  String librarianUsername;

        public LoanTable(String isbn, String initialDate, String endDate, String librarianUsername){
            this.initialDate = initialDate;
            this.endDate = endDate;
            this.isbn = isbn;
            this.librarianUsername = librarianUsername;
        }

        public String getEndDate() {
            return endDate;
        }

        public String getInitialDate() {
            return initialDate;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getLibrarianUsername() {
            return librarianUsername;
        }
    }

    private Admin nowAdmin;

    private PauseTransition pause;

    private Reader searchedReader;

    private Admin searchedAdmin;

    private Librarian searchedLibrarian;

    private User lastSearched;

    private Integer editName;

    private Integer editPhone;

    private Integer editAddress;

    private Integer editAuthor;
    private Integer editTitle;
    private Integer editPublisher;
    private Integer editCategory;
    private Integer editYear;

    private Book lastSearchedBook;



    @FXML
    private Button addCopiesBtn;

    @FXML
    private Button blockUserBtn;

    @FXML
    private TextField categoryTextField;

    @FXML
    private TextField copiesQttTextField;

    @FXML
    private TextField profileUsername;

    @FXML
    private TextField profileName;

    @FXML
    private TextField profilePhone;

    @FXML
    private TextField activeLoansTF;

    @FXML
    private TextField overdueLoanTF;

    @FXML
    private TextField activeReservesTF;

    @FXML
    private TextField totalLoansTF;

    @FXML
    private TextField totalReservesTF;

    @FXML
    private TextField searchHistoryLoanTF;

    @FXML
    private Button searchHistoryBtn;

    @FXML
    private TableView<Book> tablePopularBook;

    @FXML
    private TableColumn<Book, String> titlePopularCol;

    @FXML
    private TableColumn<Book, String> isbnPopularCol;

    @FXML
    private TableView<LoanTable> tableLoan;

    @FXML
    private TableColumn<LoanTable, String> isbnLoanCol;

    @FXML
    private TableColumn<LoanTable, String> initalDateLoanCol;

    @FXML
    private TableColumn<LoanTable, String> finalDateLoanCol;

    @FXML
    private TableColumn<LoanTable, String> librarianLoanCol;


    @FXML
    private TextField profileAddress;

    @FXML
    private TextField profileType;

    @FXML
    private TableView<Book> tableBook;

    @FXML
    private TableColumn<Book, String> publisherCol;

    @FXML
    private TableColumn<Book, String> isbnCol;

    @FXML
    private TableColumn<Book, String> authorCol;

    @FXML
    private TableColumn<Book, String> titleCol;

    @FXML
    private TableColumn<Book, Integer> yearCol;

    @FXML
    private TableColumn<Book, String> categoryCol;

    @FXML
    private TextField createAddressTF;

    @FXML
    private TextField blockDaysTF;

    @FXML
    private TextField createAuthorTF;

    @FXML
    private Button createBookBtn;

    @FXML
    private TextField createCategoryTF;

    @FXML
    private Text warningSearchUpdateBook;

    @FXML
    private TextField createIsbnTF;

    @FXML
    private TextField createNameTF;

    @FXML
    private PasswordField createPasswordPF;

    @FXML
    private TextField createPhoneTF;

    @FXML
    private TextField createPublisherTF;

    @FXML
    private TextField createTitleTF;

    @FXML
    private TextField createTotalCopiesTF;

    @FXML
    private Button createUserBtn;

    @FXML
    private Button editAuthorBtn;

    @FXML
    private ComboBox<String> createUserTypeCB;

    @FXML
    private TextField createUsernameTF;

    @FXML
    private TextField authorTextField;

    @FXML
    private TextField createYearTF;

    @FXML
    private Button deleteBookBtn;

    @FXML
    private TextField deleteIsbnTF;

    @FXML
    private Button deleteUserBtn;

    @FXML
    private TextField deleteUsernameTF;

    @FXML
    private Button editAddressBtn;

    @FXML
    private Button editCategoryBtn;

    @FXML
    private Button editNameBtn;

    @FXML
    private Button editPhoneBtn;

    @FXML
    private Button editPublisherBtn;

    @FXML
    private Button editTitleBtn;

    @FXML
    private Button editYearBtn;

    @FXML
    private TextField isbnTextField;

    @FXML
    private MenuItem menuCrudBook;

    @FXML
    private MenuItem menuCrudUser;

    @FXML
    private Menu menuMyProfile;

    @FXML
    private Menu menuReport;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem menuSearchBook;

    @FXML
    private MenuItem menuUpdateBook;

    @FXML
    private MenuItem menuUpdateUser;

    @FXML
    private AnchorPane paneCrudBook;

    @FXML
    private AnchorPane paneCrudUser;

    @FXML
    private AnchorPane paneUpdateBook;

    @FXML
    private AnchorPane paneUpdateUser;

    @FXML
    private AnchorPane paneMyProfile;

    @FXML
    private AnchorPane paneSearchBook;

    @FXML
    private AnchorPane paneReport;

    @FXML
    private TextField publisherTextField;

    @FXML
    private Button removeCopiesBtn;

    @FXML
    private Button saveChangesBtn;

    @FXML
    private Button saveUpdateBookBtn;

    @FXML
    private Text searchBookAlert;

    @FXML
    private Text statusShowText;

    @FXML
    private Text warningSearchUpdateUser;

    @FXML
    private Button searchBookButton;

    @FXML
    private ComboBox<String> searchBookComboBox;

    @FXML
    private TextField searchBookTextField;

    @FXML
    private Button searchUpdateBookButton;

    @FXML
    private TextField searchUpdateBookTextField;

    @FXML
    private Button searchUpdateUserBtn;

    @FXML
    private ComboBox<String> searchUpdateUserCB;

    @FXML
    private TextField searchUpdateUserTF;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField totalCopiesTextField;

    @FXML
    private TextField updateAddressTF;

    @FXML
    private TextField updateNameTF;

    @FXML
    private TextField updatePhoneTF;

    @FXML
    private TextField updateUsernameTF;

    @FXML
    private Text userStatusText;

    @FXML
    private Text warningCreateBook;

    @FXML
    private Text warningCreateUser;

    @FXML
    private Text warningDeleteBook;

    @FXML
    private Text warningDeleteUser;

    @FXML
    private Text warningUpdateBook;

    @FXML
    private Text warningUpdateUser;

    @FXML
    private ComboBox<String> deleteUserCB;

    @FXML
    private TextField yearTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createUserTypeCB.getItems().removeAll(createUserTypeCB.getItems());
        createUserTypeCB.getItems().addAll("Admin", "Bibliotecário", "Leitor");
        createUserTypeCB.getSelectionModel().select("Admin");

        deleteUserCB.getItems().removeAll(deleteUserCB.getItems());
        deleteUserCB.getItems().addAll("Admin", "Bibliotecário", "Leitor");
        deleteUserCB.getSelectionModel().select("Admin");

        searchUpdateUserCB.getItems().removeAll(searchUpdateUserCB.getItems());
        searchUpdateUserCB.getItems().addAll("Admin", "Bibliotecário", "Leitor");
        searchUpdateUserCB.getSelectionModel().select("Admin");

        searchBookComboBox.getItems().removeAll(searchBookComboBox.getItems());
        searchBookComboBox.getItems().addAll("Título", "ISBN", "Autor", "Categoria");
        searchBookComboBox.getSelectionModel().select("ISBN");

        warningCreateBook.setTextAlignment(TextAlignment.CENTER);
        warningCreateUser.setTextAlignment(TextAlignment.CENTER);
        warningDeleteBook.setTextAlignment(TextAlignment.CENTER);
        warningDeleteUser.setTextAlignment(TextAlignment.CENTER);
        warningUpdateBook.setTextAlignment(TextAlignment.CENTER);
        warningUpdateUser.setTextAlignment(TextAlignment.CENTER);

        statusShowText.setVisible(false);
        blockUserBtn.setVisible(false);
        blockDaysTF.setVisible(false);
        userStatusText.setVisible(false);

        disableTF(updateAddressTF);
        disableTF(updateNameTF);
        disableTF(updatePhoneTF);
        disableTF(updateUsernameTF);
        disableTF(isbnTextField);
        disableTF(titleTextField);
        disableTF(authorTextField);
        disableTF(publisherTextField);
        disableTF(categoryTextField);
        disableTF(yearTextField);
        disableTF(totalCopiesTextField);

        disableTF(isbnTextField);
        disableTF(authorTextField);
        disableTF(titleTextField);
        disableTF(publisherTextField);
        disableTF(categoryTextField);
        disableTF(yearTextField);
        disableTF(totalCopiesTextField);

        disableTF(profileUsername);
        disableTF(profileAddress);
        disableTF(profileName);
        disableTF(profilePhone);
        disableTF(profileType);

        disableTF(activeLoansTF);
        disableTF(activeReservesTF);
        disableTF(totalLoansTF);
        disableTF(totalReservesTF);
        disableTF(overdueLoanTF);

        editName = 1;
        editAddress = 1;
        editPhone = 1;
        editAuthor = 1;
        editCategory = 1;
        editPublisher = 1;
        editTitle = 1;
        editYear = 1;

        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        yearCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("year"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<Book, String>("category"));
        isbnCol.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));

        librarianLoanCol.setCellValueFactory(new PropertyValueFactory<LoanTable, String>("librarianUsername"));
        isbnLoanCol.setCellValueFactory(new PropertyValueFactory<LoanTable, String>("isbn"));
        initalDateLoanCol.setCellValueFactory(new PropertyValueFactory<LoanTable, String>("initialDate"));
        finalDateLoanCol.setCellValueFactory(new PropertyValueFactory<LoanTable, String>("endDate"));

        titlePopularCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        isbnPopularCol.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));

        goToCrudUser();
    }

    private void disableTF(TextField tf){
        tf.setFocusTraversable(false);
        tf.setMouseTransparent(true);
        tf.setEditable(false);
    }

    private void enableTF(TextField tf){
        tf.setFocusTraversable(true);
        tf.setMouseTransparent(false);
        tf.setEditable(true);
    }

    // Métodos do painel de CRUD do Usuário
    @FXML
    protected void createUser(){
        String chosenType = createUserTypeCB.getValue();

        if (chosenType != null && !createPasswordPF.getText().isEmpty()
        && !createUsernameTF.getText().isEmpty()){
            try {

                switch (chosenType) {
                    case "Admin" -> {
                        nowAdmin = (Admin) LocalSystem.getNowUser();
                        nowAdmin.createUser(createUsernameTF.getText(), createPasswordPF.getText(),
                                createAddressTF.getText(),createPhoneTF.getText(),
                                createNameTF.getText(), "admin");

                        warningCreateUser.setText("Admin criado com sucesso");
                    }
                    case "Bibliotecário" -> {
                        nowAdmin = (Admin) LocalSystem.getNowUser();
                        nowAdmin.createUser(createUsernameTF.getText(), createPasswordPF.getText(),
                                createAddressTF.getText(),createPhoneTF.getText(),
                                createNameTF.getText(), "librarian");

                        warningCreateUser.setText("Bibliotecário criado com sucesso");
                    }
                    case "Leitor" -> {
                        nowAdmin = (Admin) LocalSystem.getNowUser();
                        nowAdmin.createUser(createUsernameTF.getText(), createPasswordPF.getText(),
                                createAddressTF.getText(),createPhoneTF.getText(),
                                createNameTF.getText(), "reader");

                        warningCreateUser.setText("Leitor criado com sucesso");
                    }
                }

            } catch (usernameAlreadyInUseException e){
                warningCreateUser.setText("Username em uso");

            }
        } else if (createPasswordPF.getText().isEmpty()){
            warningCreateUser.setText("Digite uma senha");
        } else if (createUsernameTF.getText().isEmpty()){
            warningCreateUser.setText("Digite um username");
        } else {
            warningCreateUser.setText("Escolha um tipo de usuário");
        }

        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
                warningCreateUser.setText(""));
        pause.play();
    }

    @FXML
    protected void deleteUser(){
        String chosenType = deleteUserCB.getValue();
        nowAdmin = (Admin) LocalSystem.getNowUser();

        if (chosenType != null){
            try {
                switch (chosenType) {
                    case "Admin" -> {
                        nowAdmin.deleteUser(nowAdmin.getUser(deleteUsernameTF.getText(),
                                "admin"));

                        warningDeleteUser.setText("Admin deletado com sucesso");
                    }
                    case "Bibliotecário" -> {
                        nowAdmin.deleteUser(nowAdmin.getUser(deleteUsernameTF.getText(),
                                "librarian"));

                        warningDeleteUser.setText("Bibliotecário deletado com sucesso");
                    }
                    case "Leitor" -> {
                        nowAdmin.deleteUser(nowAdmin.getUser(deleteUsernameTF.getText(),
                                "reader"));

                        warningDeleteUser.setText("Leitor deletado com sucesso");
                    }
                }

            } catch (notFoundException e){
                warningDeleteUser.setText("Usuário inexistente");

            }
        } else {
            warningDeleteUser.setText("Escolha um tipo de usuário");
        }

        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
                warningDeleteUser.setText(""));
        pause.play();
    }

    private void setUpdateScreenTFs(User u){
        updateUsernameTF.setText(u.getUsername());
        updateAddressTF.setText(u.getAddress());
        updateNameTF.setText(u.getName());
        updatePhoneTF.setText(u.getTelephone());
    }

    @FXML
    protected void searchUserToUpdate(){
        String chosenType = searchUpdateUserCB.getValue();
        nowAdmin = (Admin) LocalSystem.getNowUser();

        if (chosenType != null){
            try {
                switch (chosenType) {
                    case "Admin" -> {
                        searchedAdmin = (Admin) nowAdmin.getUser(searchUpdateUserTF.getText(), "admin");
                        statusShowText.setVisible(false);
                        blockUserBtn.setVisible(false);
                        blockUserBtn.setVisible(false);
                        userStatusText.setVisible(false);
                        setUpdateScreenTFs(searchedAdmin);

                        lastSearched = searchedAdmin;
                    }
                    case "Bibliotecário" -> {
                        searchedLibrarian = (Librarian) nowAdmin.getUser(searchUpdateUserTF.getText(), "librarian");
                        statusShowText.setVisible(false);
                        blockUserBtn.setVisible(false);
                        blockUserBtn.setVisible(false);
                        userStatusText.setVisible(false);
                        setUpdateScreenTFs(searchedLibrarian);

                        lastSearched = searchedLibrarian;
                    }
                    case "Leitor" -> {
                        searchedReader = (Reader) nowAdmin.getUser(searchUpdateUserTF.getText(), "reader");
                        statusShowText.setVisible(true);
                        blockUserBtn.setVisible(true);
                        userStatusText.setVisible(true);

                        if (searchedReader.getBlocked()){
                            userStatusText.setText("Bloqueado");
                            blockUserBtn.setText("Desbloquear");
                            LocalDate endBlock = searchedReader.getDateEndBlock();

                            userStatusText.setText("Bloqueado até " + endBlock.getDayOfMonth()
                                    + "/" + endBlock.getMonthValue() + "/" + endBlock.getYear());
                        } else {
                            userStatusText.setText("Livre");
                            blockUserBtn.setText("Bloquear");
                            blockDaysTF.setVisible(true);
                        }

                        lastSearched = searchedReader;
                        setUpdateScreenTFs(searchedReader);
                    }
                }
            } catch (notFoundException e){
                warningSearchUpdateUser.setText("Usuário inexistente");
            }
        } else {
            warningSearchUpdateUser.setText("Escolha um tipo de usuário");
        }

        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
                warningSearchUpdateUser.setText(""));
        pause.play();

    }

    @FXML
    protected void blockUser(){
        nowAdmin = (Admin) LocalSystem.getNowUser();

        if (blockUserBtn.getText().equals("Desbloquear")){
            unblockUser();
        } else {

            try {

                Integer days = Integer.valueOf(blockDaysTF.getText());
                if (days < 1) {
                    throw new NumberFormatException();
                }

                if (searchedReader != null) {
                    nowAdmin.blockReader(searchedReader, days);

                    searchedReader = (Reader) nowAdmin.getUser(searchedReader.getUsername(), "reader");
                    LocalDate endBlock = searchedReader.getDateEndBlock();

                    userStatusText.setText("Bloqueado até " + endBlock.getDayOfMonth()
                    + "/" + endBlock.getMonthValue() + "/" + endBlock.getYear());

                    blockUserBtn.setText("Desbloquear");
                    blockDaysTF.setVisible(false);
                }

            } catch (NumberFormatException k) {
                warningUpdateUser.setText("Número de dias inválido");

                pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event ->
                        warningUpdateUser.setText(""));
                pause.play();
            } catch (notFoundException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    protected void unblockUser(){
        if (searchedReader != null){
            nowAdmin.unblockReader(searchedReader);
            userStatusText.setText("Livre");
            blockUserBtn.setText("Bloquear");
            blockDaysTF.setVisible(true);
        }
    }

    @FXML
    protected void allowEditName(){
        if (editName > 0){
            enableTF(updateNameTF);
        } else {
            disableTF(updateNameTF);
        }

        editName *= -1;
    }

    @FXML
    protected void allowEditAddress(){
        if (editAddress > 0){
            enableTF(updateAddressTF);
        } else {
            disableTF(updateAddressTF);
        }

        editAddress *= -1;
    }

    @FXML
    protected void allowEditPhone(){
        if (editPhone > 0){
            enableTF(updatePhoneTF);
        } else {
            disableTF(updatePhoneTF);
        }

        editPhone *= -1;
    }

    @FXML
    protected void updateUser(){
        nowAdmin = (Admin) LocalSystem.getNowUser();

        lastSearched.setName(updateNameTF.getText());
        lastSearched.setTelephone(updatePhoneTF.getText());
        lastSearched.setAddress(updateAddressTF.getText());

        nowAdmin.updateUser(lastSearched);

        warningUpdateUser.setText("Usuário atualizado com sucesso");

        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
                warningUpdateUser.setText(""));
        pause.play();
    }

    // Métodos de CRUD de livros

    @FXML
    protected void createBook(){
        Integer year = null;
        Integer copies = null;
        nowAdmin = (Admin) LocalSystem.getNowUser();


        try {
            year = Integer.valueOf(createYearTF.getText());
            copies = Integer.valueOf(createTotalCopiesTF.getText());

            if (copies <= 0){
                throw new NumberFormatException();
            }

            nowAdmin.createBook(createTitleTF.getText(), createAuthorTF.getText(),
                    createPublisherTF.getText(), year, createCategoryTF.getText(),
                    createIsbnTF.getText(), copies);

            warningCreateBook.setText("Livro criado com sucesso");

        } catch (isbnAlreadyInUseException e){
            warningCreateBook.setText("ISBN já utilizado");
        } catch (NumberFormatException k){
            if (copies == null){
                warningCreateBook.setText("Ano inválido");
            }
            warningCreateBook.setText("Número de cópias inválido");
        }

        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
                warningCreateBook.setText(""));
        pause.play();


    }

    @FXML
    protected void deleteBook(){
        nowAdmin = (Admin) LocalSystem.getNowUser();
        Book b = DAO.getBookDAO().getByPK(deleteIsbnTF.getText());

        if (b != null){
            nowAdmin.deleteBook(b);
            warningDeleteBook.setText("Livro deletado com sucesso");
        } else {
            warningDeleteBook.setText("Não existe livro com o ISBN informado");
        }

        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
                warningDeleteBook.setText(""));
        pause.play();
    }

    protected void setUpdateBookTFs(Book b){
        isbnTextField.setText(b.getIsbn());
        authorTextField.setText(b.getAuthor());
        titleTextField.setText(b.getTitle());
        publisherTextField.setText(b.getPublisher());
        yearTextField.setText(b.getYear().toString());
        categoryTextField.setText(b.getCategory());
        totalCopiesTextField.setText(b.getTotalCopies().toString());
    }

    @FXML
    protected void allowEditAuthor(){
        if (editAuthor > 0){
            enableTF(authorTextField);
        } else {
            disableTF(authorTextField);
        }

        editAuthor *= -1;
    }

    @FXML
    protected void allowEditTitle(){
        if (editTitle > 0){
            enableTF(titleTextField);
        } else {
            disableTF(titleTextField);
        }

        editTitle *= -1;
    }

    @FXML
    protected void allowEditPublisher(){
        if (editPublisher > 0){
            enableTF(publisherTextField);
        } else {
            disableTF(publisherTextField);
        }

        editPublisher *= -1;
    }

    @FXML
    protected void allowEditCategory(){
        if (editCategory > 0){
            enableTF(categoryTextField);
        } else {
            disableTF(categoryTextField);
        }

        editCategory *= -1;
    }

    @FXML
    protected void allowEditYear(){
        if (editYear > 0){
            enableTF(yearTextField);
        } else {
            disableTF(yearTextField);
        }

        editYear *= -1;
    }

    @FXML
    protected void searchBookToUpdate(){
        nowAdmin = (Admin) LocalSystem.getNowUser();
        Book b = DAO.getBookDAO().getByPK(searchUpdateBookTextField.getText());

        if (b != null){
            setUpdateBookTFs(b);
            lastSearchedBook = b;
        } else {
            warningSearchUpdateBook.setText("Não existe livro com o ISBN informado");
        }

        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
                warningSearchUpdateBook.setText(""));
        pause.play();
    }

    @FXML
    protected void searchBook(){

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

    @FXML
    protected void updateBook(){
        nowAdmin = (Admin) LocalSystem.getNowUser();

        try {
            Integer year = Integer.valueOf(yearTextField.getText());
            lastSearchedBook.setTitle(titleTextField.getText());
            lastSearchedBook.setCategory(categoryTextField.getText());
            lastSearchedBook.setAuthor(authorTextField.getText());
            lastSearchedBook.setPublisher(publisherTextField.getText());
            lastSearchedBook.setYear(year);

            nowAdmin.updateBook(lastSearchedBook);

            warningUpdateBook.setText("Livro atualizado com sucesso");

        } catch (NumberFormatException e){
            warningUpdateBook.setText("Ano inválido");
        }

        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
                warningUpdateBook.setText(""));
        pause.play();
    }

    @FXML
    protected void addCopy(){
        nowAdmin = (Admin) LocalSystem.getNowUser();

        try{
            Integer copies = Integer.valueOf(copiesQttTextField.getText());
            nowAdmin.addBookCopies(lastSearchedBook, copies);
            lastSearchedBook = DAO.getBookDAO().getByPK(lastSearchedBook.getIsbn());

            copies += Integer.parseInt(totalCopiesTextField.getText());
            totalCopiesTextField.setText(copies.toString());

        } catch(NumberFormatException e){
            warningUpdateBook.setText("Número de cópias inválido");
        }

        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
                warningUpdateBook.setText(""));
        pause.play();
    }

    @FXML
    protected void removeCopy(){
        nowAdmin = (Admin) LocalSystem.getNowUser();

        try{
            Integer copies = Integer.valueOf(copiesQttTextField.getText());
            nowAdmin.removeBookCopies(lastSearchedBook, copies);
            lastSearchedBook = DAO.getBookDAO().getByPK(lastSearchedBook.getIsbn());

            copies = Integer.parseInt(totalCopiesTextField.getText()) - copies;
            totalCopiesTextField.setText(copies.toString());

        } catch(NumberFormatException e){
            warningUpdateBook.setText("Número de cópias inválido");
        } catch (notFoundException e){
            warningUpdateBook.setText("Não é possível retirar essa quantidade atualmente");
        }

        pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->
                warningUpdateBook.setText(""));
        pause.play();
    }

    // Métodos do Meu Perfil
    @FXML private void setMyProfileInfo(){
        nowAdmin = (Admin) LocalSystem.getNowUser();

        profileUsername.setText(nowAdmin.getUsername());
        profileAddress.setText(nowAdmin.getAddress());
        profileName.setText(nowAdmin.getName());
        profilePhone.setText(nowAdmin.getTelephone());
    }

    // Métodos do relatório
    @FXML private void fillLoanTable(){
        nowAdmin = (Admin) LocalSystem.getNowUser();
        String toSearch = searchHistoryLoanTF.getText();
        ArrayList<Loan> searched;
        LoanTable tableRow;
        ObservableList<LoanTable> toShow = FXCollections.observableArrayList();
        String initialDate;
        String finalDate;

        if (toSearch != null){
            searched = DAO.getReportDAO().getReaderLoanHistory(toSearch);

            for (Loan l : searched){
                initialDate = l.getInitialDate().getDayOfMonth() + "/" +
                        l.getInitialDate().getMonthValue() + "/" + l.getInitialDate().getYear();

                finalDate = l.getFinalDate().getDayOfMonth() + "/" +
                        l.getFinalDate().getMonthValue() + "/" + l.getFinalDate().getYear();

                tableRow = new LoanTable(l.getBookIsbn(), initialDate, finalDate, l.getLibrarianUsername());
                toShow.add(tableRow);
            }

            tableLoan.setItems(toShow);
        }


    }

    @FXML
    private void fillGeneralReport(){
        activeLoansTF.setText(Report.getTotalActiveLoans().toString());
        overdueLoanTF.setText(Report.getTotalOverdueLoans(LocalDate.now()).toString());
        activeReservesTF.setText(Report.getTotalActiveReserves().toString());
        totalLoansTF.setText(Report.getTotalLoansAllTime().toString());
        totalReservesTF.setText(Report.getTotalReservesAllTime().toString());

        fillPopularBooks();
    }

    private void fillPopularBooks(){
        ObservableList<Book> toShow = FXCollections.observableArrayList();
        ArrayList<Pair<String, Integer>> toFill = DAO.getReportDAO().getPopularBooksAllTime();
        Book b;

        if (toFill != null){

            for (Pair<String, Integer> p : toFill){
                b = DAO.getBookDAO().getByPK(p.getKey());
                if (b != null ){
                    toShow.add(b);
                }
            }
        }

        tablePopularBook.setItems(toShow);
    }

    // Métodos de troca de telas do menu

    private void turnAllPanesInvisible(){
        paneCrudUser.setVisible(false);
        paneCrudBook.setVisible(false);
        paneUpdateBook.setVisible(false);
        paneUpdateUser.setVisible(false);
        paneSearchBook.setVisible(false);
        paneMyProfile.setVisible(false);
        paneReport.setVisible(false);
    }

    @FXML
    protected void goToReport(){
        turnAllPanesInvisible();
        fillGeneralReport();
        paneReport.setVisible(true);
    }

    @FXML
    protected void goToCrudUser(){
        turnAllPanesInvisible();
        paneCrudUser.setVisible(true);
    }

    @FXML
    protected void goToUpdateUser(){
        turnAllPanesInvisible();
        paneUpdateUser.setVisible(true);
    }

    @FXML
    protected void goToCrudBook(){
        turnAllPanesInvisible();
        paneCrudBook.setVisible(true);
    }

    @FXML
    protected void goToUpdateBook(){
        turnAllPanesInvisible();
        paneUpdateBook.setVisible(true);
    }

    @FXML
    protected void goToSearchBook(){
        turnAllPanesInvisible();
        paneSearchBook.setVisible(true);
    }

    @FXML
    protected void goToMyProfile(){
        setMyProfileInfo();
        turnAllPanesInvisible();
        paneMyProfile.setVisible(true);
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
