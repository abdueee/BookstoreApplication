/*
Group 36:
Rendel Abrasia -  500942743 - Section 11
Abdul Rehman Zahid Mohammed - 500931563 - Section 7
Reza Aablue - 500966944 - Section 11
Huzaifa Ali - 500901727 - Section 11
*/

package coe528;

public class Silver extends State{
    
    
    public Silver(){
        
        super("Silver");
    }
    
    
    @Override 
    public void addPoints(Customer c){
        
        int cost= (int)c.sumCost(c.getCart());
        
        c.setPoints(c.getPoints() + cost*10);
        
        if(c.getPoints()>1000){
            
            c.setState(new Gold());
        }
    }
    @Override 
    
    public void redeemPoints(Customer c){
        
        double cost= c.sumCost(c.getCart());
        
        int redeemCost= c.getPoints()/100;
        
        if(cost>redeemCost){
            
            c.setPoints(0);
            
            c.setPurchaseCost(cost-redeemCost);
            
            System.out.println(c.getPoints());
        }
        if(cost<=redeemCost){
            
            c.setPoints((int)cost*100);
            
            c.setPurchaseCost(0.0);
        }
    }
}