import java.util.Date;


// Define the Gender enum
 enum Gender 
 {
    MALE,
    FEMALE,
}
// Customer class extending the User class
public class Customer extends User
{
    private double balance;
    private String address;
    private Gender gender; // Using the Gender enum
    private String[] interests;
    private ChatRoom chatRoom;

    // Constructor
    public Customer()
    {
    }
    // Constructor
    public Customer(String username, String password, Date dateOfBirth, double balance, String address, Gender gender, String[] interests) 
    {
        super(username, password, dateOfBirth); // Call the superclass constructor
        this.balance = balance;
        this.address = address;
        this.gender = gender;
        this.interests = interests;
         this.chatRoom = new ChatRoom();
    }

    public void setBalance(double balance)
    {
        if (balance < 0)
                {
            throw new IllegalArgumentException("Balance cannot be negative.");
        }
        this.balance = balance;
    }
    
    public double getBalance() {
        return balance;
    }

    public void setAddress(String address) 
    {
         if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        this.address = address;
    }

    
    public String getAddress()
    {
        return address;
    }
    
    public void setGender(Gender gender) 
    {
         if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null.");
        }
        this.gender = gender;
    }
    
    public Gender getGender()
    {
        return gender;
    }

    public void setInterests(String[] interests) 
    {
         if (interests == null || interests.length == 0)
         {
            throw new IllegalArgumentException("Interests cannot be null or empty.");
        }
        this.interests = interests;
    }
    
     public String[] getInterests() 
     {
        return interests;
    }
    
    @Override
    public String toString() {
        return "Customer: " + username + " (Balance: " + balance + ")";
    }
    
    // Method to view cart
    public void viewCart() {
        System.out.println("Viewing cart for customer: " + this.getUsername());
        // Logic to view cart
    }

    // Method to view orders
    public void viewOrders() {
        System.out.println("Viewing orders for customer: " + this.getUsername());
        // Logic to view orders
    }
   
    public void displayProfile()
    {
        System.out.println("Customer Profile");
        System.out.println("Username: " + username);
        System.out.println("Balance: " + balance);
        System.out.println("Address: " + address);
        System.out.println("Gender: " + gender);
        System.out.println("Interests: ");
        for (String interest : interests) {
            System.out.println(interest);
        }        
    }
     // Send a message to an admin
    public void sendMessageToAdmin(Admin admin, String content) {
        chatRoom.sendMessage(this.username, admin.getUsername(), content);
    }

    // View chat conversation with an admin
    public void viewConversation() {
        chatRoom.showConversation();
    }

}