/*
Group 36:
Rendel Abrasia -  500942743 - Section 11
Abdul Rehman Zahid Mohammed - 500931563 - Section 7
Reza Aablue - 500966944 - Section 11
Huzaifa Ali - 500901727 - Section 11
*/

package coe528;


public class Gold extends State{
    
   public Gold(){
       
        super("Gold");
   }
   
    @Override
    
    public void addPoints(Customer c){
        
        int cost= (int) c.getPurchaseCost();
        
        int points= cost*10;
        
        c.setPoints(c.getPoints()+ points);
    }
    
    @Override
    
    public void redeemPoints(Customer c){
        
        double cost= c.sumCost(c.getCart());
        
        int redeemCost= c.getPoints()/100;
        
        if(cost>redeemCost){
            
            c.setPoints(0);
            
            c.setState(new Silver());
            
            c.setPurchaseCost(cost-redeemCost);
            
        }
        if(cost<=redeemCost){
            
            c.setPoints(c.getPoints()-(int)cost*100);
            
            if(c.getPoints()<=1000){
                
                c.setState(new Silver());
            }
            c.setPurchaseCost(0.0);
        }
    }

   
   
    
    
}