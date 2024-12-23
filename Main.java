import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initialize Database to ensure dummy data is loaded
        Database database = new Database();
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\nWelcome to the E-commerce platform!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            User currentUser = null;

            if (choice == 1) {
                // Registration
                currentUser = User.registerUser(scanner);
            } else if (choice == 2) {
                // Login
                currentUser = User.loginUser(scanner);
            } else if (choice == 3) {
                // Exit
                System.out.println("Exiting the application. Goodbye!");
                isRunning = false;
                continue;
            } else {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            // If the user successfully logs in or registers
            if (currentUser != null) {
                if (currentUser instanceof Admin) {
                    // Admin functionality
                    Admin admin = (Admin) currentUser;
                    int adminChoice;

                    do {
                        System.out.println("\nAdmin Functions:");
                        System.out.println("1. Show all data");
                        System.out.println("2. Add a product");
                        System.out.println("3. Remove a product");
                        System.out.println("4. Update a product");
                        System.out.println("5. Show Analysis");
                        System.out.println("6. Logout");
                        System.out.println("7. Exit");
                        System.out.print("Choose an option: ");
                        adminChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (adminChoice) {
                            case 1:
                                // Show all data
                                admin.showAll(Database.getCustomers(),Database.getCategories(), Database.getOrders());
                                break;

                            case 2:
                                // Add a product
                                System.out.println("Enter product details (id, name, price, stock, category,quantity):");
                                int id = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                String name = scanner.nextLine();
                                double price = scanner.nextDouble();
                                int stock = scanner.nextInt();
                                int quantity = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                String categoryName = scanner.nextLine();
                                Category category = Database.getCategories().stream()
                                        .filter(c -> c.getName().equalsIgnoreCase(categoryName))
                                        .findFirst()
                                        .orElse(null);
                                if (category != null) {
                                    
                                    try{
                                        Product newProduct = new Product(id,name, price, stock, category, 1);
                                        Product check = Database.getProducts().stream()
                                        .filter(p -> p.getId() == id)
                                        .findFirst()
                                        .orElse(null);
                                        if (check != null) {
                                            admin.addProduct(newProduct);
                                            System.out.println("Product added successfully.");
                                        } else {
                                            System.out.println("There is already a product with this Id.");
                                        }
                                    
                                    } 
                                    catch(invalidOrder e)
                                    {
                                        while(quantity<stock)
                                        {
                                            System.out.println("There is not enough products in stock.");
                                            System.out.println("Please chose a new quantity :");
                                            quantity = scanner.nextInt();
                                        }
                                    }
                                }
                                else
                                {
                                    System.out.println("Category was not found.");
                                }
                                break;

                            case 3:
                                // Remove a product
                                System.out.print("Enter product ID to remove: ");
                                int productIdToRemove = scanner.nextInt();
                                Product productToRemove = Database.getProducts().stream()
                                        .filter(p -> p.getId() == productIdToRemove)
                                        .findFirst()
                                        .orElse(null);
                                if (productToRemove != null) {
                                    admin.removeProduct(productToRemove);
                                    productToRemove.getCategory().removeProduct(productIdToRemove);
                                    System.out.println("Product removed successfully.");
                                } else {
                                    System.out.println("Product not found.");
                                }
                                break;

                            case 4:
                                // Update a product
                                System.out.println("Enter updated product details (id, name, price, stock,quantity):");
                                int updateId = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                String updateName = scanner.nextLine();
                                double updatePrice = scanner.nextDouble();
                                int updateStock = scanner.nextInt();
                                int quantity2 = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                Category productCategory = null; // If updating category is required, add logic here
                                try{
                                    Product updatedProduct = new Product(updateId,updateName, updatePrice, updateStock, productCategory, 1);
                                    admin.updateProduct(updatedProduct);
                                }
                                catch(invalidOrder e)
                                {
                                    while(quantity2<updateStock)
                                    {
                                        System.out.println("There is not enough products in stock.");
                                        System.out.println("Please chose a new quantity :");
                                        quantity = scanner.nextInt();
                                    }
                                }
                                
                                break;

                                case 5:
                                // Show Analysis
                                int analysisChoice;
                                do {
                                    System.out.println("\nAnalysis Options:");
                                    System.out.println("1. Product Analysis");
                                    System.out.println("2. Order Analysis");
                                    System.out.println("3. Customer Analysis");
                                    System.out.println("4. Back to Main Menu");
                                    System.out.print("Choose an option: ");
                                    analysisChoice = scanner.nextInt();
                                    scanner.nextLine(); // Consume newline
                            
                                    switch (analysisChoice) {
                                        case 1:
                                            // Show Product Analysis
                                            admin.showProductAnalysis();
                                            break;
                            
                                        case 2:
                                            // Show Order Analysis
                                            admin.showOrderAnalysis(Database.getOrders());
                                            break;
                            
                                        case 3:
                                            // Show Customer Analysis
                                            admin.showCustomerAnalysis(Database.getCustomers());
                                            break;
                            
                                        case 4:
                                            // Back to main menu
                                            System.out.println("Returning to main menu...");
                                            break;
                            
                                        default:
                                            System.out.println("Invalid option. Please try again.");
                                    }
                                } while (analysisChoice != 4);
                                break;                            
                            case 6:
                                // Logout
                                System.out.println("Logging out...");
                                currentUser = null;
                                break;
            
                            case 7:
                                // Exit
                                System.out.println("Exiting the application. Goodbye!");
                                isRunning = false;
                                break;
            
                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                    } while (adminChoice != 6 && isRunning);

                } else {
                    // Regular user functionality
                    int userChoice;
                    Cart cart = new Cart();

                    do {
                        System.out.println("\nWelcome, " + currentUser.getUsername() + "!");
                        System.out.println("1. Update Profile");
                        System.out.println("2. View Products");
                        System.out.println("3. Add to Cart");
                        System.out.println("4. View Cart");
                        System.out.println("5. Place Order");
                        System.out.println("6. Logout");
                        System.out.println("7. Exit");
                        System.out.print("Choose an option: ");
                        userChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        switch (userChoice) {
                            case 1:
                                currentUser.displayProfile();
                                boolean exit = false;
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Date format
                                while (!exit) {
                                    System.out.println("\n--- Update Profile ---");
                                    System.out.println("1. Change Username");
                                    System.out.println("2. Change Password");
                                    System.out.println("3. Change Date of Birth (yyyy-MM-dd)");
                                    System.out.println("4. Change Gender (MALE/FEMALE)");
                                    System.out.println("5. Change Balance");
                                    System.out.println("6. Change Interest");
                                    System.out.println("7. Exit Profile Update");
                                    System.out.print("Choose an option: ");

                                    int choice3 = scanner.nextInt();
                                    Scanner scanner2 = new Scanner(System.in);
                                    switch (choice3) {
                                        case 1:
                                            System.out.print("Enter new username: ");
                                            String newUsername = scanner2.nextLine();
                                            currentUser.setUsername(newUsername);
                                            System.out.println("Username updated successfully.");
                                            break;

                                        case 2:
                                            System.out.print("Enter new password: ");
                                            String newPassword = scanner2.nextLine();
                                            currentUser.setPassword(newPassword);
                                            System.out.println("Password updated successfully.");
                                            break;

                                        case 3:
                                            System.out.print("Enter new Date of Birth (yyyy-MM-dd): ");
                                            String dobInput = scanner2.nextLine();
                                            try {
                                                Date newDOB = sdf.parse(dobInput);
                                                currentUser.setDateOfBirth(newDOB);
                                                System.out.println("Date of Birth updated successfully.");
                                                } catch (ParseException e) 
                                                {
                                                    System.out.println("Invalid date format. Please try again.");
                                                }
                                            break;

                                        case 4:
                                            System.out.print("Enter gender (MALE, FEMALE, OTHER): ");
                                            String genderInput = scanner2.nextLine().toUpperCase();
                                            try {
                                                Gender newGender = Gender.valueOf(genderInput);
                                                ((Customer) currentUser).setGender(newGender);
                                                System.out.println("Gender updated successfully.");
                                            } 
                                            catch (IllegalArgumentException e) {
                                                System.out.println("Invalid gender. Please enter MALE or FEMALE.");
                                            }
                                            break;

                                        case 5:
                                            System.out.print("Enter new balance: ");
                                            double newBalance = scanner2.nextDouble();
                                            scanner.nextLine(); // Consume newline
                                            ((Customer) currentUser).setBalance(newBalance);
                                            System.out.println("Balance updated successfully.");
                                            break;

                                        case 6:
                                            System.out.print("Enter new interest: ");
                                            String input = scanner2.nextLine(); // Read the entire line
                                            String[] newInterests = input.split(",\\s*"); // Split by comma and optional spaces
                                            ((Customer) currentUser).setInterests(newInterests); // Set interests
                                            System.out.println("Interest updated successfully.");
                                            break;

                                        case 7:
                                            System.out.println("Exiting profile update...");
                                            exit = true;
                                            break;

                                        default:
                                            System.out.println("Invalid choice. Please choose again.");
                                            break;
                                        }
                                    }
                            case 2:
                                // Display all categories and products
                                System.out.println("\nCategories and Products:");
                                for (Category category : Database.getCategories()) {
                                    System.out.println("Category: " + category.getName());
                                    category.viewProducts();
                                }
                                break;

                            case 3:
                                // Add product to cart
                                System.out.print("Enter product ID to add to cart: ");
                                int productId = scanner.nextInt();
                                scanner.nextLine(); // Consume newline
                                Product productToAdd = Database.getProducts().stream()
                                        .filter(p -> p.getId() == productId)
                                        .findFirst()
                                        .orElse(null);
                                if (productToAdd != null) {
                                    try {
                                        cart.addToCart(productToAdd);
                                        System.out.println("Product added to cart.");
                                    } catch (invalidOrder e) {
                                        System.out.println("There is not enough product in stock.");
                                    }
                                } else {
                                    System.out.println("Product not found.");
                                }
                                break;

                            case 4:
                                // View cart
                                System.out.println("\nYour Cart:");
                                cart.viewCart();
                                break;

                                case 5:
                                
                                // Checkout
                             if (cart.getProducts().isEmpty()) {
                                 System.out.println("Your cart is empty. Add products to your cart before checking out.");
                                 break;
                             }
         
                             double totalCost = cart.calculateTotal();
                             System.out.println("Cart Total: $" + totalCost);
         
                             // Apply coupon code
                             System.out.print("Do you have a coupon code? (yes/no): ");
                             String hasCoupon = scanner.nextLine();
                             if (hasCoupon.equalsIgnoreCase("yes")) {
                                 System.out.print("Enter coupon code: ");
                                 String couponCode = scanner.nextLine();
                                 DiscountCoupon coupon = Database.findCoupon(couponCode);
                                 if (coupon != null && coupon.canUse()) {
                                     double discountAmount = coupon.getDiscountAmount();
                                     totalCost -= discountAmount;
                                     coupon.useCoupon();
                                     System.out.println("Coupon applied! Discount: $" + discountAmount);
                                     System.out.println("New Total: $" + totalCost);
                                 } else {
                                     System.out.println("Invalid or expired coupon.");
                                 }
                             } else {
                                 System.out.println("No coupon applied.");
                             }
         
                             // Choose payment method
                             System.out.println("\nChoose Payment Method:");
                             System.out.println("1. Cash");
                             System.out.println("2. Credit Card");
                             int paymentChoice = scanner.nextInt();
                             scanner.nextLine(); // Consume newline
         
                             PaymentMethod paymentMethod = null;
         
                             if (paymentChoice == 1) {
                                 paymentMethod = new CashPayment(totalCost);
                             } else if (paymentChoice == 2) {
                                 try {
                                     System.out.print("Enter Credit Card Number (16 digits): ");
                                     String cardNumber = scanner.nextLine();
                                     System.out.print("Enter Card Holder Name: ");
                                     String cardHolderName = scanner.nextLine();
                                     System.out.print("Enter Expiration Date (MM/YY): ");
                                     String expirationDate = scanner.nextLine();
                                     System.out.print("Enter CVV (3 digits): ");
                                     String cvv = scanner.nextLine();
         
                                     paymentMethod = new CreditCardPayment(cardNumber, cardHolderName, expirationDate, cvv, totalCost);
                                 } catch (IllegalArgumentException e) {
                                     System.out.println("Error: " + e.getMessage());
                                     continue;
                                 }
                             } else {
                                 System.out.println("Invalid payment method choice.");
                                 break;
                             }
         
                             // Show cart and ask for confirmation
                             System.out.println("\nReview Your Cart:");
                             cart.viewCart();
                             System.out.println("Total Cost: $" + totalCost);
                             System.out.print("Do you want to place the order? (yes/no): ");
                             String confirmOrder = scanner.nextLine();
         
                             if (confirmOrder.equalsIgnoreCase("yes")) {
                                 // Place order
                                 Order order = new Order (cart, paymentMethod);
                                 order.placeOrder();
                                 if (order.getStatus() == Status.COMPLETED) {
                                     Database.addOrder(order);
                                     order.printOrderDetails();
                                     System.out.println("Order placed successfully!");
                                     cart = new Cart(); // Reset the cart
                                 } else {
                                     System.out.println("Order failed. Please try again.");
                                 }
                             } else {
                                 System.out.println("Order not placed. Returning to main menu...");
                             }
                             break;

                            case 6:
                                // Logout
                                System.out.println("Logging out...");
                                currentUser = null;
                                break;

                            case 7:
                                // Exit
                                System.out.println("Exiting the application. Goodbye!");
                                isRunning = false;
                                break;

                            default:
                                System.out.println("Invalid option. Please try again.");
                        }
                    
                        if (currentUser == null) {
                            break; // Ensures we exit the loop and return to the main menu
                        }
                    } while (userChoice != 7 && isRunning);
                } 
            } else {
                System.out.println("\nReturning to the main menu...");
            }
        }

        scanner.close();
    }
}

