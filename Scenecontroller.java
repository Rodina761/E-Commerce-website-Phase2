import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Scenecontroller {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Model instances
    private Customer customer = new Customer();
    private Cart cart = new Cart();
    private Order order = new Order();
    private String loginResult;

    // FXML fields
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private TextField regname;
    @FXML private TextField regpass;
    @FXML private DatePicker dateOfBirth;
    @FXML private CheckBox fcheck;
    @FXML private CheckBox mcheck;
    @FXML private TextArea interests;
    @FXML private TextField newbalance;
    @FXML private TextField newcustname;
    @FXML private TextField newpassword;
    @FXML private Label orderId;
    @FXML private Label totalCost;

    public Scenecontroller() {
        order.setCart(cart); // Link the cart to the order during initialization
    }

    // Centralized method to load a new scene
    private void loadScene(String fxmlFile, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        root = loader.load();
        Scenecontroller controller = loader.getController();
        controller.setCart(cart); // Pass the shared Cart instance
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    // Utility method for showing alerts
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String message) {
        showAlert(Alert.AlertType.ERROR, title, message);
    }

    // Scene switching methods
    public void switchToRegister(ActionEvent event) throws IOException {
        loadScene("register.fxml", event);
    }

    @FXML
    public void switchToAdminHomepage(ActionEvent event) throws IOException {
        loadScene("adminhomepage.fxml", event);
    }

    public void switchToShowall(ActionEvent event) throws IOException {
        loadScene("showall.fxml", event);
    }

    public void switchToCustomers(ActionEvent event) throws IOException {
        loadScene("Customers.fxml", event);
    }

    public void switchToCategories(ActionEvent event) throws IOException {
        loadScene("admincategories.fxml", event);
    }

    public void switchToHomepage(ActionEvent event) throws IOException {
        loginResult = User.loginUser(username.getText(), password.getText());
        if ("admin".equals(loginResult) || "customer".equals(loginResult)) {
            if ("customer".equals(loginResult)) {
                customer = (Customer) User.loginUserGUI(username.getText(), password.getText());
                cart.setCustomer(customer);
                order.setCustomer(customer);
                order.setPaymentMethod(new CashPayment());
                loadScene("homepage.fxml", event);
            } else {
                loadScene("adminhomepage.fxml", event);
            }
        } else {
            password.clear();
            username.clear();
            showErrorAlert("Login Failed", "Invalid username or password.");
        }
    }

    public void switchToHomepageAfterRegistration(ActionEvent event) throws IOException {
        if (User.registerUser(regname.getText(), regpass.getText())) {
            loadScene("homepage.fxml", event);
        } else {
            showErrorAlert("Registration Failed", "Could not register user.");
        }
    }

    public void switchToOrder(ActionEvent event) throws IOException {
        if (cart == null || order == null) {
            showErrorAlert("Error", "Cart or Order is not initialized.");
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("cartOrder.fxml"));
        Parent root = loader.load();
        Scenecontroller controller = loader.getController();
        controller.setOrderDetails(Order.getOrderId(), order.getTotalCost());
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setOrderDetails(int orderIdValue, double totalCostValue) {
        if (orderId != null && totalCost != null) {
            orderId.setText("Order ID: " + orderIdValue);
            totalCost.setText("Total: $" + totalCostValue);
        }
    }

    // Product addition methods
    private void addProductToCart(Product product) {
        try {
            cart.addToCart(product);
        } catch (invalidOrder e) {
            showErrorAlert("Error Adding Product", "Insufficient stock for: " + product.getName());
        }
    }

    public void addSunscreen(ActionEvent event) throws invalidOrder{
        addProductToCart(new Product(1, "Sunscreen", 500, 20, new Category("Facial care", "Facial care essentials"), 1));
    }

    public void addCleanser(ActionEvent event) throws invalidOrder{
        addProductToCart(new Product(2, "Cleanser", 300, 5, new Category("Facial care", "Facial care essentials"), 1));
    }

    public void addRazor(ActionEvent event) throws invalidOrder{
        addProductToCart(new Product(11, "Razor", 50, 35, new Category("Facial care", "Facial care essentials"), 1));
    }

    public void addShampoo(ActionEvent event) throws invalidOrder{
        addProductToCart(new Product(3, "Shampoo", 200, 15, new Category("Hair care", "Hair care essentials"), 1));
    }

    public void addHairOil(ActionEvent event) throws invalidOrder{
        addProductToCart(new Product(6, "Hair oil", 450, 3, new Category("Hair care", "Hair care essentials"), 1));
    }

    public void addHairMask(ActionEvent event) throws invalidOrder{
        addProductToCart(new Product(5, "Hair mask", 350, 2, new Category("Hair care", "Hair care essentials"), 1));
    }

    public void addConditioner(ActionEvent event) throws invalidOrder{
        addProductToCart(new Product(4, "Conditioner", 250, 10, new Category("Hair care", "Hair care essentials"), 1));
    }

    public void addNailOil(ActionEvent event) throws invalidOrder{
        addProductToCart(new Product(7, "Nail oil", 150, 7, new Category("Hand care", "Hand care essentials"), 1));
    }

    public void addHandCream(ActionEvent event) throws invalidOrder{
        addProductToCart(new Product(8, "Hand cream", 250, 25, new Category("Hand care", "Hand care essentials"), 1));
    }

    public void addShowerGel(ActionEvent event) throws invalidOrder{
        addProductToCart(new Product(9, "Showergel", 275, 12, new Category("Body care", "Body care essentials"), 1));
    }

    public void addMoisturizer(ActionEvent event) throws invalidOrder{
        addProductToCart(new Product(10, "Moisturizer", 475, 5, new Category("Body care", "Body care essentials"), 1));
    }

    // Scene switches for specific care categories
    public void switchToFacialCare(ActionEvent event) throws IOException {
        loadScene("facialcare.fxml", event);
    }

    public void switchToBodyCare(ActionEvent event) throws IOException {
        loadScene("bodycare.fxml", event);
    }

    public void switchToHairCare(ActionEvent event) throws IOException {
        loadScene("haircare.fxml", event);
    }

    public void switchToHandCare(ActionEvent event) throws IOException {
        loadScene("handcare.fxml", event);
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        loadScene("log in page.fxml", event);
    }

    public void switchToPayment(ActionEvent event) throws IOException {
        loadScene("payment.fxml", event);
    }
}
