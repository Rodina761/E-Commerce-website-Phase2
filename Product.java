
public class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private Category category;
    private int quantity;

    public Product(){
        
    }
    public Product(int id, String name, double price, int stock, Category category, int quantity) throws invalidOrder{
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
        if(quantity<=stock){
            this.quantity=quantity;
        }
        else{
            throw new invalidOrder();
        }
    }

    // Getter methods for each field
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public Category getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    // Override the toString() method to format the output
    @Override
    public String toString() {
        return "Product: " + name + " (ID: " + id + ", Price: " + price + ", Stock: " + stock + ")";
    }
    public void setPrice(double price) {
        this.price=price ;
    }
   public void setName(String name) {
        this.name=name ;
    }
    
    public void setStock(int stock) {
        this.stock=stock ;
    }
    public void setQuantity(int quantity) throws invalidOrder{
        if(quantity<=stock){
            this.quantity=quantity;
        }
        else{
            throw new invalidOrder();
        }
    }
 
    public void updateStock(int quantity) {
         if (this.stock + quantity < 0) {
        throw new IllegalArgumentException("Stock cannot be negative.");
         }
        this.stock += quantity;
    }

    public void viewDetails() {
        System.out.println("Product: " + name + ", Price: " + price);
    }
}