import java.util.Date;
import java.util.ArrayList;

public class Admin extends User {
   private String role;
   private int workingHours;
   private ChatRoom chatRoom;

   public Admin() 
   {
   }
  

   public Admin(String username, String password, Date dateOfBirth, String role, int workingHours) {
        super(username, password, dateOfBirth);
        this.role = role;
        this.workingHours = workingHours;
        this.chatRoom = new ChatRoom(); // Initialize the chat room
    }

   public String getRole() {
      return role;
   }

   public void setRole(String role) {
      if (workingHours < 0) {
         throw new IllegalArgumentException("Working hours cannot be negative.");
      }
      this.role = role;
   }

   public int getWorkingHours() {
      return workingHours;
   }

   public void setWorkingHours(int workingHours) {
      this.workingHours = workingHours;
   }

   // Show all customers, categories, and orders
   public void showAll(ArrayList<Customer> customers, ArrayList<Category> categories, ArrayList<Order> orders) {
      System.out.println("All Customers:");
      for (Customer c : customers) {
         c.displayProfile();
         System.out.println();
      }
      System.out.println();
      System.out.println();
      System.out.println("\nAll Categories:");
      for (Category ca : categories) {
         System.out.println(ca);
         System.out.println();
      }
      System.out.println();
      System.out.println();
      System.out.println("\nAll Orders:");
      for (Order o : orders) {
         System.out.println(o);
      }
   }

   // Add a product to the database
   public void addProduct(Product product)
   {
      if (product == null) 
      {
         throw new IllegalArgumentException("Cannot add a null product.");
      }
      Database.getProducts().add(product);
   }

   // Remove a product from the database
   public void removeProduct(Product product) {
      Database.getProducts().remove(product);
   }

   // Update product information in the database
   public void updateProduct(Product updatedProduct) {
      boolean productFound = false;
      ArrayList<Product> products = Database.getProducts();

      for (int i = 0; i < products.size(); i++) {
         Product product = products.get(i);
         if (product.getId() == updatedProduct.getId()) {
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setStock(updatedProduct.getStock());
            System.out.println("Product updated successfully:");
            System.out.println(product);
            productFound = true;
            break;
         }
      }

      if (!productFound) {
         System.out.println("Product not found with ID: " + updatedProduct.getId());
      }
   }

   // Display admin profile
   public void displayProfile() 
   {
      System.out.println("Admin Profile:");
      System.out.println("Username: " + this.username);
      System.out.println("Date of Birth: " + String.valueOf(this.dateOfBirth));
      System.out.println("Role: " + this.role);
      System.out.println("Working Hours: " + this.workingHours);
   }

   // Show product statistics, such as low stock, high sales, etc.
   public void showProductAnalysis() {
      ArrayList<Product> products = Database.getProducts();
      System.out.println("\nProduct Analysis:");
      
      // List products with low stock
      System.out.println("Products with low stock (less than 10 items):");
      for (Product p : products)
      {
         if (p.getStock() < 10)
         {
            System.out.println(p.getName() + " - Stock: " + p.getStock());
         }
      }

      // List products with high price
      System.out.println("\nProducts with high price (greater than $100):");
      for (Product p : products) {
         if (p.getPrice() > 350) {
            System.out.println(p.getName() + " - Price: " + p.getPrice());
         }
      }
   }

   // Show order analysis, including total sales and pending orders
   public void showOrderAnalysis(ArrayList<Order> orders) 
   {
      int totalOrders = 0;
      double totalRevenue = 0.0;
      int pendingOrders = 0;

      for (Order o : orders) {
         totalOrders++;
         
         if (!o.isCompleted())
         {
            pendingOrders++;
         }
         else
         {
             totalRevenue += o.getTotalCost();
         }
      }

      System.out.println("\nOrder Analysis:");
      System.out.println("Total Orders: " + totalOrders);
      System.out.println("Total Revenue: $" + totalRevenue);
      System.out.println("Pending Orders: " + pendingOrders);
   }


public void showCustomerAnalysis(ArrayList<Customer> customers) {
    System.out.println("\nCustomer Analysis:");

    // Find the most frequent customer
    Customer mostFrequentCustomer = null;
    int maxOrders = 0;

    // Count orders for each customer manually
    for (Customer c : customers) {
        int orderCount = 0;
        for (Order o : Database.getOrders())
        {
            if (o.getCustomer().equals(c))
            {
                orderCount++;
            }
        }
        if (orderCount > maxOrders) {
            mostFrequentCustomer = c;
            maxOrders = orderCount;
        }
    }

    // Print most frequent customer info
    
    if (mostFrequentCustomer != null)
    {
    System.out.println("Most Frequent Customer: " + mostFrequentCustomer.getUsername() + " with " + maxOrders + " orders.");
    }

}
   @Override
   public String toString() 
   {
      return "Admin{" + "role:" + role + ", workingHours:" + workingHours + '}';
   }
   
   // Send a message to a customer
    public void sendMessageToCustomer(Customer customer, String content) {
        chatRoom.sendMessage(this.username, customer.getUsername(), content);
    }

    // View chat conversation with a customer
    public void viewConversation() 
    {
        chatRoom.showConversation();
    }
}