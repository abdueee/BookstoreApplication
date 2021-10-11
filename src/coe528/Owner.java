/*
Group 36:
Rendel Abrasia -  500942743 - Section 11
Abdul Rehman Zahid Mohammed - 500931563 - Section 7
Reza Aablue - 500966944 - Section 11
Huzaifa Ali - 500901727 - Section 11
*/

package coe528;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Owner extends Application {
    
    Scene scene2;
    
    ArrayList<String> users = new ArrayList<>();
    
    ArrayList<String> pswd = new ArrayList<>();
    
    ArrayList<Integer> points= new ArrayList<>();
    
    ArrayList<String> bookName = new ArrayList<>();
    
    ArrayList<Double> priceOfBook = new ArrayList<>();
    
    public static void main(String [] args){
        
        launch(args);
    }
    
    public ArrayList getUsers() {
        
        return users;
        
    }
    
    public ArrayList getPswd() {
        
        return pswd;
    }
    
    public ArrayList getPoints(){
        
        return points;
        
    }
    
    public ArrayList getBookName() {
        
        return bookName;
    }
    
    public ArrayList getPriceOfBook() {
        
        return priceOfBook;
    }
    
    public void addUsers(String username) {
        
        users.add(username);
    }
    
    public void addPswd(String password) {
        
        pswd.add(password);
    }
    
    public void addPoints(int point){
        
        points.add(point);
    }
    
    public void addBookName(String bookname) {
        
        bookName.add(bookname);
    }
    
    public void addPriceOfBook(Double price) {
        
        priceOfBook.add(price);
    }
    
    
    @Override
    
    public void start(Stage owner) {
        
        Login login = new Login();
        
        Button button1 = new Button("Books");
        
        Button button2= new Button("Customers");
        
        Button button3=new Button("Logout");
        
        button1.setOnAction(e-> bookpage(owner));
        
        button2.setOnAction(e-> customerpage(owner));
        
        button3.setOnAction(e-> login.start(owner));
     
        
        GridPane grid = new GridPane();
        
        grid.setAlignment(Pos.CENTER);
         
        grid.setVgap(10);
         
        grid.setPadding(new Insets(30, 30, 30, 30));  
        
        grid.addColumn(0,button1, button2, button3);     
        
        scene2= new Scene(grid, 500, 300);
        
        owner.setTitle("Bookstore 36");
        
        owner.setScene(scene2);
        
        owner.show();
    }
    
    public void bookpage(Stage owner){
        
        TableView<Book> tbl = new TableView<>();
        
        TableColumn<Book, String> nCol = new TableColumn<>("Name");
        
        nCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        
        TextField inputName= new TextField();
        
        inputName.setPromptText("Enter name");
        
        TextField inputPrice=new TextField();
        
        inputPrice.setPromptText("Enter price");
        
        Button add = new Button("Add");
        
        Button delete= new Button("Delete");
        
        Button back = new Button("Back");
        
        Book temp;
        
        for (int i = 0; i < bookName.size(); i++) {
            
            temp = new Book(bookName.get(i), priceOfBook.get(i));
            
            tbl.getItems().add(temp);
        }
        
        add.setOnAction(e -> addBook(inputName, inputPrice, tbl));
        
        delete.setOnAction(e-> deleteBook(tbl));
        
        back.setOnAction(e->start(owner));
        
        
        TableColumn<Book, Double> pCol = new TableColumn<>("Price");
        
        pCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        tbl.getColumns().addAll(nCol,pCol);
        
        HBox box1 = new HBox();
        
        box1.setSpacing(10);
        
        box1.setPadding(new Insets(15,15,15,15));       
       
        box1.getChildren().addAll(inputName, inputPrice, add, delete, back);
           
        VBox vbox = new VBox();
        
        vbox.getChildren().addAll(tbl, box1);
        
        Scene scene = new Scene(vbox, 500, 300);
        
        owner.setTitle("Bookstore 36");
        
        owner.setScene(scene);
        
        owner.show();
        
        
    }
    
    public void addBook(TextField inputName, TextField inputPrice, TableView tbl){
        
        Login login = new Login();
        
        Book book = new Book(inputName.getText(), Double.parseDouble(inputPrice.getText()));
        
        tbl.getItems().add(book);
        
        bookName.add(inputName.getText());
        
        priceOfBook.add(Double.parseDouble(inputPrice.getText()));
        
        inputName.clear();
        
        inputPrice.clear();
        
        login.stop(this);
     
      
    }
    
    public void deleteBook(TableView tbl){
        
        Login login = new Login();
        
        ObservableList<Book> bookSelected, allBooks;
        
        allBooks=tbl.getItems();
        
        bookSelected= tbl.getSelectionModel().getSelectedItems();
        
        ArrayList<Book> list = new ArrayList<>(bookSelected);
        
        bookName.remove(list.get(0).getName());
        
        priceOfBook.remove(list.get(0).getPrice());
        
        bookSelected.forEach(allBooks::remove);
        
        login.stop(this);
     
    }

    private void customerpage(Stage primaryStage) {
        
        TableView<Customer> customers= new TableView<>();
        
        TableColumn<Customer,String> nCol= new TableColumn<>("Username");
        
        TableColumn<Customer, String> pswdCol= new TableColumn<>("Password");
        
        TableColumn<Customer, Integer> ptCol= new TableColumn<>("Points");
        
        
        nCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        
        pswdCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        
        ptCol.setCellValueFactory(new PropertyValueFactory<>("points"));
        
        customers.getColumns().addAll(nCol,pswdCol, ptCol);
        
        TextField inputUsername= new TextField("username");
        
        inputUsername.setPromptText("Enter username:");
        
        TextField inputPswd = new TextField("password");
        
        
        inputPswd.setPromptText("Enter password: ");
        
        Button add= new Button("Add");
        
        Button delete= new Button("Delete");
        
        Button back = new Button("Back");
        
        Customer c;
        
        for (int i = 0; i < users.size(); i++) {
            
            c = new Customer(users.get(i), pswd.get(i));
            
            c.setPoints(points.get(i));
            
            customers.getItems().add(c);
        }
        
        add.setOnAction(e->addCustomer(inputUsername, inputPswd, customers));
        
        delete.setOnAction(e->removeCustomer(customers));
        
        back.setOnAction(e-> start(primaryStage));
        
        HBox box1 = new HBox();
        
        box1.setSpacing(10);
        
        box1.setPadding(new Insets(15,15,15,15));
        
        box1.getChildren().addAll(inputUsername, inputPswd, add, delete, back);
        
        VBox vbox = new VBox();
        
        vbox.getChildren().addAll(customers, box1);
        
        
        Scene scene = new Scene(vbox, 500, 300);
        
        
        primaryStage.setTitle("Bookstore 36");
        
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
    }

    public void addCustomer(TextField inputUsername, TextField inputPswd, TableView<Customer> customers) {
        
        Login login = new Login();
        
        String username= inputUsername.getText();
        
        String password= inputPswd.getText();
        
        Customer c= new Customer(username, password);
        
        c.setUsername(username);
        
        c.setPassword(password);
        
        customers.getItems().add(c);
        
        users.add(inputUsername.getText());
        
        pswd.add(inputPswd.getText());
        
        points.add(0);
        
        inputUsername.clear();
        
        inputPswd.clear();
        
        login.stop(this);
    }

    public void removeCustomer(TableView<Customer> customers) {
        
        Login login = new Login();
        
        ObservableList<Customer> selectedCustomer;
        
        ObservableList<Customer> totalCustomer;
        
        totalCustomer=customers.getItems();
        
        selectedCustomer = customers.getSelectionModel().getSelectedItems();
        
        ArrayList<Customer> list = new ArrayList<>(selectedCustomer);
        
        users.remove(list.get(0).getUsername());
        
        pswd.remove(list.get(0).getPassword());
        
        for(int i=0; i<points.size();i++){
            
            if(points.get(i)==list.get(0).getPoints()){
                
                points.remove(i);
            }
        }
        login.stop(this);
        
 
        selectedCustomer.forEach(totalCustomer::remove);
        
    }
}