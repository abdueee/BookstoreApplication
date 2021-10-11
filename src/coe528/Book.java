/*
Group 36:
Rendel Abrasia -  500942743 - Section 11
Abdul Rehman Zahid Mohammed - 500931563 - Section 7
Reza Aablue - 500966944 - Section 11
Huzaifa Ali - 500901727 - Section 11
*/

package coe528;

import javafx.scene.control.CheckBox;



public class Book {
    
    private String bookName;
    
    private double bookPrice; 
    
    private CheckBox select;

    public Book(String bookName, double bookPrice){
        
        this.bookName = bookName;
        
        this.bookPrice = bookPrice;
        
        this.select=new CheckBox();
    }
    
    public String getName(){
        
        return this.bookName;
    }
    
    public double getPrice(){
        
        return this.bookPrice;
    }
    
    public CheckBox getSelect(){
        
        return this.select;
    }
    
    public void setName(String bookName){
        
        this.bookName=bookName;
    }
    
    public void setPrice(double bookPrice){
        
        this.bookPrice=bookPrice;
        
    }
    
    public void setSelect(CheckBox select){
        
        this.select=select;
    }
}
