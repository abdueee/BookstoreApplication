/*
Group 36:
Rendel Abrasia -  500942743 - Section 11
Abdul Rehman Zahid Mohammed - 500931563 - Section 7
Reza Aablue - 500966944 - Section 11
Huzaifa Ali - 500901727 - Section 11
*/

package coe528;

public abstract class State {
    
    private String state;
    
    public State(String state){ 
        
        this.state=state;
    }
    
    public abstract void addPoints(Customer c);
    
    public abstract void redeemPoints(Customer c);
    
    public String getState(){
        
        return state;
    }
    public void setState(String state){
        
        this.state=state;
    }
}
