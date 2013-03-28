
package Login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author Admin
 */
public class StudentDashboardController implements Initializable {
    
    @FXML private TextField user;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private TextArea address;
    @FXML private CheckBox subscribed;
    @FXML private Label loggedUser;
    @FXML private Label success;

    private MyLogin application;
    
    
    public void setApp(MyLogin application){
        this.application = application;
        
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        success.setOpacity(0);
        loggedUser.setText("Welcome Karthik Krish!!!");
    }    
    
    @FXML public void processLogout(ActionEvent event) {
            MyLogin.getInstance().gotoLoginScreen();
    }
    
    @FXML protected void processUpdate() {
        animateMessage();
    }
    
    private void animateMessage() {
        FadeTransition ft = new FadeTransition(new Duration(3000), success);
        ft.setFromValue(0.0);
        ft.setToValue(1);
        ft.play();
    }  
}
