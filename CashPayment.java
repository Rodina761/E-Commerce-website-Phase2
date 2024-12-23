/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dell
 */

 public class CashPayment implements PaymentMethod {
     private double amount; 
 
     public CashPayment() {
     }
 
     public CashPayment(double amount) {
        
         if (amount <= 0) {
             throw new IllegalArgumentException("Amount must be greater than zero.");
         }
         this.amount = amount;
     }
     
     @Override
    public boolean validateDetails(){
  if (amount <= 0) {
             throw new IllegalArgumentException("Amount must be greater than zero.");
            
 }
           return true;
          
 
 }
 
     public void setAmount(double amount) {
         
         if (amount <= 0) {
             throw new IllegalArgumentException("Amount must be greater than zero.");
         }
         this.amount = amount;
     }
 
   
     public double getAmount() {
         return amount;
     }
 
     @Override
 public boolean processPayment() {
     try {
         validateDetails(); // Attempt validation
         System.out.println("Payment will be collected on delivery for $" + amount);
         return true;
     } catch (Exception e) {
         System.out.println("Payment failed: " + e.getMessage());
         return false; // Payment failed, but still executed
     }
 }
     
 
     @Override
     public String getPaymentSummary() {
        
             return "Payment Summary: Paid $" + amount;
         
     }
 
     @Override
     public String toString() {
        
         return "Cash payment";
     }
 }
 