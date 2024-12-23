/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dell
 */

public class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private String expirationDate;
    private String cvv;
    private double amount;

    // Default constructor
    public CreditCardPayment() {
    }

    // Constructor to initialize credit card details
    public CreditCardPayment(String cardNumber, String cardHolderName, String expirationDate, String cvv,double amount) {
        
        
          if (cardNumber == null || cardHolderName == null || expirationDate == null || cvv == null) {
            throw new IllegalArgumentException("Card details cannot be null.");
        }
        if (cardNumber.length() != 16 || cvv.length() != 3 || cardHolderName.isEmpty()) {
            throw new IllegalArgumentException("Invalid card details provided.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.amount=amount;
    }

   public void setCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() != 16) {
            throw new IllegalArgumentException("Card number must be 16 digits.");
        }
        this.cardNumber = cardNumber;
    }

    public void setCardHolderName(String cardHolderName) {
        if (cardHolderName == null || cardHolderName.isEmpty()) {
            throw new IllegalArgumentException("Card holder name cannot be empty.");
        }
        this.cardHolderName = cardHolderName;
    }

    public void setExpirationDate(String expirationDate) {
        if (expirationDate == null || expirationDate.isEmpty()) {
            throw new IllegalArgumentException("Expiration date cannot be empty.");
        }
        this.expirationDate = expirationDate;
    }

    public void setCvv(String cvv) {
        if (cvv == null || cvv.length() != 3) {
            throw new IllegalArgumentException("CVV must be 3 digits.");
        }
        this.cvv = cvv;
    }

    public void setAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
        this.amount = amount;
    }

    // Getters
    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    @Override
      public boolean validateDetails() {
        if (cardNumber == null || cardHolderName == null || cvv == null || expirationDate == null) {
            throw new IllegalArgumentException("Card details cannot be null.");
        }
        if (cardNumber.length() != 16 || cvv.length() != 3 || cardHolderName.isEmpty()) {
            throw new IllegalArgumentException("Invalid card details provided.");
        }
        return true;
    }
    
    
    
    @Override
public boolean processPayment() {
    try {
        validateDetails(); // Attempt validation
        System.out.println("Credit Card Payment processed successfully.");
        return true;
    } catch (Exception e) {
        System.out.println("Credit Card Payment failed: " + e.getMessage());
        return false; // Payment failed, but still executed
    }
}

    @Override
    public String toString() {
        return "Credit Card Payment" + " Card number:" + cardNumber + ", Card Holder Name:" + cardHolderName + ", Expiration Date=" + expirationDate + ", cvv=" + cvv +'}';
    }

     @Override
    public String getPaymentSummary() {
        
            return "Payment Summary: Paid $" + amount + " with Credit Card ending in " +
                   cardNumber.substring(cardNumber.length() - 4) + ".";
        }

   
}
        
    
    

