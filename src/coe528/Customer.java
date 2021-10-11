/*
Group 36:
Rendel Abrasia -  500942743 - Section 11
Abdul Rehman Zahid Mohammed - 500931563 - Section 7
Reza Aablue - 500966944 - Section 11
Huzaifa Ali - 500901727 - Section 11
*/

package coe528;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Customer{
    
    private String username;
    
    private String password;
    
    private int points;
    
    private State state;
    
    private ArrayList<Book> cart;
    
    private double purchaseCost;
    
    public Customer(String username, String password){
        
        this.username=username;
        
        this.password=password;
        
        this.points=0;
        
        this.state= new Silver();
        
    }
        
    public Customer(){
        
        this.username="";
        
        this.password="";
        
        this.cart=new ArrayList<>();
        
        this.points=0;
        
        this.state= new Silver();
    }
    
    public String getUsername() {
        
        return username;
    }
    
     public String getPassword() {
         
        return password;
    }
     
     public int getPoints() {
         
        return points;
    }
     
    public void addPoints(){
        
        state.addPoints(this);
    }
    
    public void redeemPoints(){
        
        state.redeemPoints(this);
       
    }
    
     public State getState() {
         
        return state;
    }
     
     public ArrayList<Book> getCart() {
         
        return cart;
    }
     
    public double getPurchaseCost() {
        
        return purchaseCost;
    }
    
    public void purchaseBooks(){
        
        for(Book temp: this.cart){
            
            cart.remove(temp);
        }
    }
    
     public double sumCost(ArrayList<Book> s){
         
        double cost=0;
        
        for(Book temp: s){
            
            cost= cost+temp.getPrice();
        }
        this.purchaseCost= cost;
        
        return cost;
    }

 
    public void setUsername(String username) {
        
        this.username = username;
    }

    public void setPassword(String password) {
        
        this.password = password;
    }

    public void setPoints(int points) {
        
        this.points = points;
    }

    public void setState(State state) {
        
        this.state = state;
    }

    public void setCart(ArrayList<Book> cart) {
        
        this.cart = cart;
    }
    
    public void setPurchaseCost(double purchaseCost) {
        
        this.purchaseCost = purchaseCost;
    }

    public void start(Stage primaryStage, Owner owner){
        
        Login login= new Login();
        
        Label label= new Label("Welcome " + this.getUsername()+ ". You have " + this.getPoints() +" points" + ". Your status is: " + this.state.getState());
        
        System.out.println(this.getPoints());
        
        HBox box1 = new HBox();
        
        box1.setAlignment(Pos.CENTER);
        
        box1.setSpacing(15);
        
        box1.setPadding(new Insets(30,30,30,30));
        
        box1.getChildren().addAll(label);
        
        
        
        Button button1= new Button("Buy");
        
        Button button2 = new Button("Redeem Points and Buy");
        
        Button button3= new Button("Logout");
        
        HBox box2 = new HBox();
        
        box2.setAlignment(Pos.CENTER);
        
        box2.setSpacing(15);
        
        box2.setPadding(new Insets(30,30,30,30));
        
        box2.getChildren().addAll(button1, button2, button3);                 
        
        TableView<Book> tbl = new TableView<>();
        
        TableColumn<Book, String> nCol = new TableColumn<>("Name");
        
        TableColumn<Book, CheckBox> sCol = new TableColumn<>("Select");
        
        sCol.setCellValueFactory(new PropertyValueFactory<>("select"));
        
        nCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        Book temp;
        
        for (int i = 0; i < owner.bookName.size(); i++) {
            
            temp = new Book(owner.bookName.get(i), owner.priceOfBook.get(i));
            
            tbl.getItems().add(temp);
        }
        
        
        
        TableColumn<Book, Double> pCol = new TableColumn<>("Price");
        
        pCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        tbl.getColumns().addAll(nCol,pCol, sCol);
  
        VBox vbox = new VBox();
        
        vbox.setAlignment(Pos.CENTER);
        
        vbox.getChildren().addAll(box1, tbl, box2);
        
        Scene scene = new Scene(vbox, 500, 300);
        
        primaryStage.setTitle("Bookstore 36");
        
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        button1.setOnAction(e-> buy(tbl,primaryStage, owner));
        
        button2.setOnAction(e-> redeem(tbl, primaryStage, owner));
        
        button3.setOnAction(e-> login.start(primaryStage));
    }
    
    
    public void buy(TableView<Book> table, Stage primaryStage, Owner owner){
        
        for(Book temp: table.getItems()){
            
            if (temp.getSelect().isSelected()){
                
                  this.getCart().add(temp);
            }
        }
        if(this.cart.size()>0){
            
            Login login = new Login();
            
            Label label1= new Label("Thank you for shopping at BookStore 36! Please come again.");
            
            Label label2= new Label("You received " + this.cart.size() + " book(s).");
            
            Label label3 = new Label("The price is " + this.sumCost(this.cart));
            
            this.addPoints();
            
            Label label4= new Label("Points total: " + this.points);
            
            Label label5 = new Label( "Status: " + (this.state.getState()));      
            
            System.out.println("Inital points: " + this.getPoints());
            
            for(int i=0; i<owner.getPoints().size(); i++){
                
                if(this.username.equals(owner.getUsers().get(i)) && this.password.equals(owner.getPswd().get(i)))
                    
                    owner.getPoints().set(i, this.points);
            }
            
            Button button4= new Button("Logout");

            VBox vbox = new VBox();
            
            vbox.setAlignment(Pos.CENTER);
            
            vbox.setSpacing(30);
            
            vbox.getChildren().addAll(label1, label2, label3, label4,label5, button4);
            
            Scene scene = new Scene(vbox, 500, 300);
            
            primaryStage.setTitle("Bookstore 36");
            
            primaryStage.setScene(scene);
            
            primaryStage.show();
            
            button4.setOnAction(e-> logout(login, primaryStage, owner));
        }
        
    }
    
    public void redeem(TableView<Book> tbl, Stage primaryStage, Owner owner) {
        
        for(Book temp: tbl.getItems()){
            
            if (temp.getSelect().isSelected()){
                
                this.getCart().add(temp);
            }
        }
        
        this.setPurchaseCost(sumCost(getCart())); 
        
        this.redeemPoints();
        
   
        if(this.cart.size()>0){
            
            Login login = new Login();
            
            Label label1= new Label("Thank you for shopping at BookStore 36! Please come again.");
            
            Label label2= new Label("You received " + this.cart.size() + " books.");
            
            Label label3 = new Label("The price is " + this.getPurchaseCost());
            
            Label label4= new Label("Points total: " + this.points);
            
            Label label5 = new Label( "Status: " + (this.state.getState()));
            
            Button button4= new Button("Logout");
            
            for(int i=0; i<owner.getPoints().size(); i++){
                
                if(this.username.equals(owner.getUsers().get(i)) && this.password.equals(owner.getPswd().get(i)))
                    
                    owner.getPoints().set(i, this.points);
            }
            
            VBox vbox = new VBox();
            
            vbox.setAlignment(Pos.CENTER);
            
            vbox.setSpacing(30);
            
            vbox.getChildren().addAll(label1, label2, label3, label4,label5, button4);
            
            Scene scene = new Scene(vbox, 500, 300);
            
            primaryStage.setTitle("Bookstore 36");
            
            primaryStage.setScene(scene);
            
            primaryStage.show();
            
            button4.setOnAction(e-> logout(login, primaryStage, owner));


        }
    }

    public void logout(Login login, Stage primaryStage, Owner owner) {
        
        try {
            
            FileWriter f1 = new FileWriter("Customer.txt");
            
            System.out.println(owner.getUsers().size());
            
            
            for (int i = 0; i <owner.getUsers().size(); i++) {
                
                f1.write("" + owner.getUsers().get(i) + " " + owner.getPswd().get(i) + " " + owner.getPoints().get(i) + "\n");
                
            }
            f1.close();
        }
        catch(IOException e){
            
            System.out.println("ERROR!");
            
            e.printStackTrace();
        }
        login.start(primaryStage);
    }
        
}


   
