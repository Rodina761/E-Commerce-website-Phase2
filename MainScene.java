import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainScene extends Application {
 
 @Override
 public void start(Stage stage) {
  try {
   
   Parent root = FXMLLoader.load(getClass().getResource("log in page.fxml"));
   Scene scene = new Scene(root);
   stage.setScene(scene);
   stage.setTitle("Soft Beauty");
   stage.show();
   
  } catch(Exception e) {
   e.printStackTrace();
  }
 } 

 public static void main(String[] args) {
    Database database = new Database();
    launch(args);
 }
}