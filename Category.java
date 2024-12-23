import java.util.ArrayList;

public class Category {
    private String name;
    private String description;
    private ArrayList<Product> products;

    public Category(){
        
    }
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
    }

    public void removeProduct(int productId) {
        products.removeIf(p -> p.getId() == productId);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
    public void viewProducts() {
        System.out.println(products);
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    // Override toString() method
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Category: ").append(name).append("\n")
          .append("Description: ").append(description).append("\n")
          .append("Products: ");
        
        if (products.isEmpty()) {
            sb.append("No products available");
        } else {
            for (Product product : products) {
                sb.append("\n- ").append(product.toString());
            }
        }
        
        return sb.toString();
    }
}