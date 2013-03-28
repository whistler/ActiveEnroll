
package Login;


import java.io.InputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Parent;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class MyLogin extends Application {
    
    private Stage stage;
    private static MyLogin instance;

    public MyLogin() {
        instance = this;
    }
    
    public static MyLogin getInstance() {
        return instance;
    }
    
    @Override public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            gotoLoginScreen();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(MyLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public static void main(String[] args) {
        launch(args);
    }
 
    public void gotoStudentDashBoard() {
        try {
            replaceSceneContent("profile.fxml");
        } catch (Exception ex) {
            Logger.getLogger(MyLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void gotoLoginScreen() {
        try {
            replaceSceneContent("LoginScreen.fxml");
        } catch (Exception ex) {
            Logger.getLogger(MyLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Parent replaceSceneContent(String fxml) throws Exception {
        Parent page = (Parent) FXMLLoader.load(MyLogin.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) {
            scene = new Scene(page, 700, 550);
            scene.getStylesheets().add(MyLogin.class.getResource("demo.css").toExternalForm());
            stage.setScene(scene);
        } else {
            stage.getScene().setRoot(page);
        }
        stage.sizeToScene();
        return page;
    }
}