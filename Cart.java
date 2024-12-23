import java.util.ArrayList;

class invalidOrder extends Exception{

}
public class Cart {
    private Customer customer;
    private ArrayList<Product> products ;

    public Cart(Customer customer, ArrayList<Product> products) {
        this.customer = customer;
        this.products = products;
    }

               
    public Cart() {
        this.products = new ArrayList<>();
    } 
    
    public void addToCart(Product product) throws invalidOrder{
        products.add(product);
        if(product.getQuantity()<=product.getStock()){
            product.setStock(product.getStock()-product.getQuantity());
        }
        else{
            throw new invalidOrder();
        }
    }

    public void removeFromCart(Product product) {
        products.remove(product);
    }

     public void viewCart() {
        System.out.println("Cart Contents:");
        for (Product product : products) {
            product.viewDetails();
        }
     }
        public ArrayList<Product> getProducts() {
        return products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" + "customer=" + customer + ", products=" + products + '}';
    }
    
    public double calculateTotal() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice(); // Accumulate the price of each product
        }
        return total;
    }
}