import java.util.Scanner;

import javafx.scene.control.Alert;

import java.util.Date;

public abstract class User implements Comparable<User> {
    protected String username;
    protected String password;
    protected Date dateOfBirth;

    public User()
    {
    }

    // Constructor
    protected User(String username, String password, Date dateOfBirth) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username) 
    {
          if (username == null || username.isEmpty())
          {
            throw new IllegalArgumentException("Username cannot be null or empty");
          }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth()
    {
        return dateOfBirth;
    }

   public void setDateOfBirth(Date dateOfBirth)
   {
        if (dateOfBirth.after(new Date())) // If the date is in the future
        { 
            throw new IllegalArgumentException("Date of birth cannot be in the future.");
        }
        this.dateOfBirth = dateOfBirth;
    }

   

    // Abstract method for profile display
    public abstract void displayProfile();

    // Register new user
    public static User registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

 // Check for existing user in database
      for (int i = 0; i < Database.getCustomers().size(); i++)
      {
          if (Database.getCustomers().get(i).getUsername().equals(username)) 
            {
               System.out.println("Error: User with the same username already exists.");
               return null ;
            }
      }

  // Create and register new customer
        Customer newCustomer = new Customer(username, password, new Date(), 0.0, "", Gender.MALE, new String[]{});
        Database.addCustomer(newCustomer);
        System.out.println("Registration successful.");
        return newCustomer;
    }
    public static boolean registerUser(String username,String password) {

 // Check for existing user in database
      for (int i = 0; i < Database.getCustomers().size(); i++)
      {
          if (Database.getCustomers().get(i).getUsername().equals(username)) 
            {
               Alert alert = new Alert (Alert.AlertType.ERROR); 
                alert.setTitle("Error registering");
                alert.setContentText("Register unsuccessful,the user already exist"); 
                alert.showAndWait();
               return false ;
            }
      }
  // Create and register new customer
        Customer newCustomer = new Customer(username, password, new Date(), 0.0, "", Gender.MALE, new String[]{});
        Database.addCustomer(newCustomer);
        return true;
    }

    // Login user
    public static User loginUser(Scanner scanner) 
    {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

       // Search for matching user
       User user = null;

    // Check customers
    for (Customer c : Database.getCustomers())
    {
        if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
        user = c;
        break;
        }
    }

    // Check admins if user is not found
    if (user == null)
    {
        for (Admin a : Database.getAdmins()) {
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
            user = a;
            break;
            }
        }
    }

    // Return the user (Customer or Admin)
    if (user != null) 
    {
        if (user.getClass().equals(Admin.class)) 
        {
            System.out.println("Admin login successful.");
        } else {
            System.out.println("Customer login successful.");
        }
    } 
    else
    {
        System.out.println("Invalid username or password.");
    }

    return user;

    }

    public static User loginUserGUI(String username,String password) 
    {
       // Search for matching user
       User user = null;

    // Check customers
    for (Customer c : Database.getCustomers())
    {
        if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
        user = c;
        break;
        }
    }
    // Return the user (Customer or Admin)
    return user;

    }

    public static String loginUser(String username,String password) 
    {
       // Search for matching user
       User user = null;

    // Check customers
    for (Customer c : Database.getCustomers())
    {
        if (c.getUsername().equals(username) && c.getPassword().equals(password)) {
        user = c;
        break;
        }
    }

    // Check admins if user is not found
    if (user == null)
    {
        for (Admin a : Database.getAdmins()) {
            if (a.getUsername().equals(username) && a.getPassword().equals(password)) {
            user = a;
            break;
            }
        }
    }

    // Return the user (Customer or Admin)
    if (user != null) 
    {
        if(user instanceof Admin)
            {
                return "admin";
            }
        else
            {
                return "customer";
            }
    } 
    else
    {
        return "invalid";
    }

    }
    // Logout
    public void logout()
    {
          if (username == null)
          {
            throw new NullPointerException("Username is null. Cannot log out.");
          }
        System.out.println(username + " has logged out.");
    }

    // Compare based on username only
    @Override
    public int compareTo(User u)
    {
        if(username.equals(u.username) && password.equals(u.password))
            {
                return 0;
            }
        else
            {
                return -1;
            }
    }
    @Override
    public String toString()
    {
        return "User{" + "username=" + username + ", password=" + password + ", dateOfBirth=" + dateOfBirth + '}';
    }
}