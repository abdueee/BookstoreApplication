/*
Group 36:
Rendel Abrasia -  500942743 - Section 11
Abdul Rehman Zahid Mohammed - 500931563 - Section 7
Reza Aablue - 500966944 - Section 11
Huzaifa Ali - 500901727 - Section 11
*/

package coe528;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application{
    
    Owner owner = new Owner();
    
    Customer customer = new Customer();
    
    
    public static void main(String[] args) {
        
        launch(args);
    }
    
    
    @Override
    
    public void start(Stage primaryStage) {
        
        try {
                File f1 = new File("Book.txt");
            
                Scanner scanner = new Scanner(f1);
                
                String line;
            
                while (scanner.hasNext()) {
                
                line = scanner.next();
                
                owner.addBookName(line);
                
                line = scanner.next();
                
                owner.addPriceOfBook(Double.parseDouble(line));
          
            }
                f1 = new File("Customer.txt");
            
                scanner = new Scanner(f1);
                       
                while (scanner.hasNext()) {
                
                line = scanner.next();
                
                owner.addUsers(line);
                
                line = scanner.next();
                
                owner.addPswd(line);
                
                line= scanner.next();
                
                owner.addPoints(Integer.parseInt(line));
        
                                     
            }
        } catch (IOException e) {
            
            System.out.println("ERROR!.");
            
            e.printStackTrace();
        }
        
        
        Text header = new Text("Welcome to BookStore 36 !");
        
        Label username = new Label("Username: ");
        
        Label password = new Label("Password: ");
        
        TextField userField = new TextField();
        
        PasswordField passwordField = new PasswordField();
        
        Button button = new Button("Login");
        
        GridPane grid = new GridPane();
        
        grid.setPadding(new Insets(30, 30, 30, 30));
        
        grid.setVgap(25);
        
        
        grid.add(username, 1, 2);
        
        grid.add(userField, 2, 2);
        
        grid.add(password, 1, 3);
        
        grid.add(passwordField, 2, 3);
        
        grid.add(header, 1, 0);
                                
        grid.add(button, 1, 4);
        
        
        Scene scene = new Scene(grid, 500, 300);
        
        primaryStage.setTitle("Bookstore 36");
        
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
        
  
  
        button.setOnAction(e-> login(userField.getText(), passwordField.getText(), primaryStage));
    }
    
    public void start(Stage primaryStage, boolean throwaway) {
        
        Text header = new Text("Welcome to BookStore 36 !");
        
        Label username = new Label("Username: ");
        
        Label password = new Label("Password: ");
        
        TextField userField = new TextField();
        
        PasswordField passwordField = new PasswordField();
        
        Button button = new Button("Login");
        
        GridPane grid = new GridPane();
        
        grid.setPadding(new Insets(30, 30, 30, 30));
        
        grid.setVgap(15);
        
        
        grid.add(username, 0, 1);
        
        grid.add(userField, 1, 1);
        
        grid.add(password, 0, 2);
        
        grid.add(passwordField, 1, 2);
        
        grid.add(header, 0, 0);
                                
        grid.add(button, 1, 3);
        
        Scene scene = new Scene(grid, 500, 300);
        
        primaryStage.setTitle("Bookstore 36");
        
        primaryStage.setScene(scene);
        
        primaryStage.show();
        
  
  
        button.setOnAction(e-> login(userField.getText(), passwordField.getText(), primaryStage));
    }
    
    
    
   
    public void stop(Owner owner){
        
        System.out.println("Stage Closed");
        
        try {
            FileWriter f1 = new FileWriter("Customer.txt");
            
            System.out.println(owner.getUsers().size());
            
            
            for (int i = 0; i < owner.getUsers().size(); i++) {
                
                f1.write("" + owner.getUsers().get(i) + " " + owner.getPswd().get(i) + " " + owner.getPoints().get(i) + "\n");
            }
            f1.close();
            
            f1 = new FileWriter("Book.txt");
            
            for (int i = 0; i < owner.getBookName().size(); i++) {
                
                f1.write("" + owner.getBookName().get(i) + " " + owner.getPriceOfBook().get(i) + "\n");
                
            }
            f1.close();
            
        } catch (IOException e) {
            
            System.out.println("ERROR!");
            
            e.printStackTrace();
        }
        
    }
    
    public void login(String userField, String passwordField, Stage primaryStage){
        
           boolean login = false;
           
           boolean throwaway=false;
           
           for(int i=0;i<owner.getUsers().size();i++){
               
               if(userField.equals(owner.getUsers().get(i)) && passwordField.equals(owner.getPswd().get(i))){
                   
                    customer.setUsername((String)(owner.getUsers().get(i)));
                    
                    customer.setPassword((String)(owner.getPswd().get(i)));
                    
                    customer.setPoints((int)(owner.getPoints().get(i)));
                    
                    System.out.println("Intial points: " + customer.getPoints());
                    
                    if(customer.getPoints()>1000){
                        
                       customer.setState(new Gold());
                       
                    }
                    
                    else{
                        
                        customer.setState(new Silver());
                    }
                    login=true;
                    
                    customer.start(primaryStage, owner);
                   
               }
           }
            if((userField.equals("admin")) && (passwordField.equals("admin"))){
                
                owner.start(primaryStage);
            }
            
            else if(login == false){
            
                Label label = new Label("Invalid! Please try again");
                
                Button button = new Button("Go Back");
                
                VBox vbox= new VBox();
                
                vbox.setAlignment(Pos.CENTER);
                
                vbox.setSpacing(30);
                
                vbox.setPadding(new Insets(30,30,30,30));
                
                vbox.getChildren().addAll(label, button);
                                                                                  
                Scene scene = new Scene(vbox, 500, 300);
                
                primaryStage.setTitle("Bookstore 36");
                
                primaryStage.setScene(scene);
                
                primaryStage.show();
                
                button.setOnAction(e-> start(primaryStage, throwaway ));
            }
            
            
            
        }
    

   
    
}
