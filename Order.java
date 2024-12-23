import java.util.ArrayList;

enum Status {
   PENDING,
    COMPLETED,
    FAILED,
    CANCELLED
}
public class Order {
    private  static int orderId;
    private Cart cart;
    private Status status;
    private double totalCost;
    private PaymentMethod paymentMethod;
    private Customer customer; 


    public Order(){
        orderId++;
    }
    
    public Order(Cart cart, PaymentMethod paymentMethod) {
         if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method is required.");}
        
        
       
        this.cart = cart;
        this.paymentMethod = paymentMethod;
        this.status = Status.PENDING;
        this.totalCost = calculateTotal(cart);
        orderId++;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Total: " + totalCost + ", Payment: " + paymentMethod;
    }

    public void placeOrder() {
        if (paymentMethod.processPayment()) {
            this.status =Status.COMPLETED;
        } else {
            this.status = Status.FAILED;
        }
    }

    public void cancelOrder() {
        this.status = Status.CANCELLED;
    }
    private double calculateTotal(Cart cart) {
    double total = 0.0;
    for (Product product : cart.getProducts()) {
        total += product.getPrice(); // Accumulate the price of each product
    }
    return total;
}
  

    public  static int getOrderId() {
        return orderId;
    }

    public Cart getCart() {
        return cart;
    }

    public Status getStatus() {
        return status;
    }

    public double getTotalCost() {
        return calculateTotal(cart);
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

   
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method is required.");
        }
        this.paymentMethod = paymentMethod;
    }
    
      public void printOrderDetails() {
          if (paymentMethod == null) {
        throw new IllegalStateException("Payment method is not set. Cannot print order details.");
    }
    System.out.println("Order ID: " + orderId);
    System.out.println("Total Cost: " + cart.calculateTotal());
    System.out.println("Payment Method: " + paymentMethod);
    System.out.println("Items in Order:");

    for (Product product : cart.getProducts()) {
        System.out.println("- " + product.getName() + " (Price: " + product.getPrice() + ")");
    }
    }
      // Method to check if the order is completed
    public boolean isCompleted() {
        return this.status == Status.COMPLETED;
    }
}