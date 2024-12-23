import java.util.Scanner;
import java.util.Date;

public class Main2 {
    public static void main(String[] args) {
        // Create an Admin and Customer instance
        Admin admin = new Admin("admin1", "adminpass", new Date(), "Manager", 40);
        Customer customer = new Customer("customer1", "customerpass", new Date(), 500.0, "123 Main St", Gender.MALE, new String[]{"Electronics", "Books"});

        // Scanner for reading user input
        Scanner scanner = new Scanner(System.in);
        String input;
        
        // Loop for interactive chat
        while (true) {
            System.out.println("\n--- Chat Menu ---");
            System.out.println("1. Admin to Customer");
            System.out.println("2. Customer to Admin");
            System.out.println("3. View Conversation");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            input = scanner.nextLine();
            
            switch (input) {
                case "1":
                    // Admin to Customer
                    System.out.print("Enter message to send to Customer: ");
                    String adminMessage = scanner.nextLine();
                    admin.sendMessageToCustomer(customer, adminMessage);
                    break;
                    
                case "2":
                    // Customer to Admin
                    System.out.print("Enter message to send to Admin: ");
                    String customerMessage = scanner.nextLine();
                    customer.sendMessageToAdmin(admin, customerMessage);
                    break;
                    
                case "3":
                    // View Conversation
                    System.out.println("\n--- Viewing Conversation ---");
                    admin.viewConversation();
                    break;
                    
                case "4":
                    // Exit the chat
                    System.out.println("Exiting chat...");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice, please select a valid option.");
            }
        }
    }
}