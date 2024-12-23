public interface PaymentMethod {
    boolean processPayment();
    boolean validateDetails();
    String getPaymentSummary();
 }