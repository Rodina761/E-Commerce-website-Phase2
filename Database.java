import java.util.ArrayList;
import java.util.Date;


public class Database {
    private static ArrayList<Customer> customers = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Category> categories = new ArrayList<>();
    private static ArrayList<Order> orders = new ArrayList<>();
    private static ArrayList<DiscountCoupon> coupons = new ArrayList<>();

    // Constructor
    public Database() {
        // Default constructor
    }

    // Encapsulation: Provide public methods for accessing and modifying data
    public static void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static DiscountCoupon findCoupon(String code) {
        for (DiscountCoupon coupon : coupons) {
            if (coupon.getCouponCode().equalsIgnoreCase(code)) {
                return coupon;
            }
        } 
    return null;
   }

    public static void addAdmin(Admin admin) {
        admins.add(admin);
    }

    public static void addProduct(Product product) {
        products.add(product);
        (product.getCategory()).addProduct(product);
    }

    public static void removeProduct(Product product) {
        product.getCategory().removeProduct(product);
    }
    public static void addCategory(Category category) {
        categories.add(category);
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static ArrayList<Customer> getCustomers() {
        return customers;
    }

    public static ArrayList<Admin> getAdmins() {
        return admins;
    }

    public static ArrayList<Product> getProducts() {
        return products;
    }

    public static ArrayList<Category> getCategories() {
        return categories;
    }

    public static ArrayList<Order> getOrders() {
        return orders;
    }

    // Initialize dummy data
    static {
        initializeDummyData();
    }

    private static void initializeDummyData() {
        // Create Categories
        Category facial_care = new Category("Facial care", "facial care essentials for glowing skin");
        Category hair_care = new Category("Hair care", "Products to maintain, nourish, and protect hair");
        Category hand_care = new Category("Hand care", "Products to moisturize, protect, and soften hands");
        Category body_care = new Category("Body care", "Products to moisturize, protect, and soften your body");
        addCategory(facial_care);
        addCategory(hair_care);
        addCategory(hand_care);
        addCategory(body_care);

        // Create Products
        try{
            Product sunscreen = new Product(1, "Sunscreen", 500, 20, facial_care, 1);
            Product cleanser = new Product(2, "Cleanser", 300, 5, facial_care, 1);
            Product shampoo = new Product(3, "Shampoo", 200, 15, hair_care, 1);
            Product conditioner = new Product(4, "Conditioner", 250, 10, hair_care, 1);
            Product hair_mask = new Product(5, "Hair mask", 350, 2, hair_care, 1);
            Product hair_oil = new Product(6, "Hair oil", 450, 3, hair_care, 1);
            Product nail_oil = new Product(7, "Nail oil", 150, 7, hand_care, 1);
            Product hand_cream = new Product(8, "Hand cream", 250, 25, hand_care, 1);
            Product showergel = new Product(9, "Showergel", 275, 12, body_care, 1);
            Product moisturizer = new Product(10, "Moisturizer", 475, 5, body_care, 1);
            Product razor = new Product(11, "Razor", 50, 35, facial_care, 1);
            Database.addProduct(sunscreen);
            Database.addProduct(cleanser);
            Database.addProduct(shampoo);
            Database.addProduct(conditioner);
            Database.addProduct(hair_mask);
            Database.addProduct(hair_oil);
            Database.addProduct(nail_oil);
            Database.addProduct(hand_cream);
            Database.addProduct(showergel);
            Database.addProduct(moisturizer);
            Database.addProduct(razor);
            Cart cart1 = new Cart();
            cart1.addToCart(shampoo);
            cart1.addToCart(nail_oil);
            Order order1 = new Order(cart1, new CashPayment(cart1.calculateTotal()));
            Customer customer1 = new Customer("Menna", "password123", new Date(), 50000.0, "123 Main St", Gender.FEMALE, new String[]{"Facial care","Hair care"});
            Database.addCustomer(customer1);
            order1.setCustomer(customer1);
            Database.addOrder(order1);
        }
        catch(invalidOrder e)
        {
            System.out.println("There is not enough products in stock.");

        }
        
        Customer customer2 = new Customer("Basmala", "password123", new Date(), 15000.0, "123 Main Street", Gender.FEMALE, new String[]{"Hair care"});
        Customer customer3 = new Customer("Ahmed", "pass1234", new Date(), 150000.0, "123 Main Street", Gender.MALE, new String[]{"Facial care"});
        Database.addCustomer(customer2);
        Database.addCustomer(customer3);

        // Create Admins
        Admin admin1 = new Admin("Rodina", "pass123", new Date(), "Manager", 8);
        addAdmin(admin1);
        
        coupons.add(new DiscountCoupon("SAVE10", 10.0, 5));
        coupons.add(new DiscountCoupon("SAVE20", 20.0, 3));
        coupons.add(new DiscountCoupon("HALF50", 50.0, 2));
    }
}