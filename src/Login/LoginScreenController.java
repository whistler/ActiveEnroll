
package Login;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class LoginScreenController implements Initializable {
    
    @FXML
    TextField userId;
    @FXML
    PasswordField userPassword;
    @FXML
    Button login;
    @FXML
    Label errorLabel;

    private MyLogin application;
    private UserLoginModel userLogin = new UserLoginModel();
    
    public void setApp(MyLogin application){
        this.application = application;
        errorLabel.setText("");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorLabel.setText("");
    }    
    
    public void processLogin(ActionEvent event) {
            authenticateLogin();
    }
    
    private void authenticateLogin(){
        if(userId.getText().isEmpty() || userPassword.getText().isEmpty()){
            errorLabel.setText("Username and password is mandatory!!!");
        }else{
            if(userPassword.getText().equals(userLogin.getPassword(userId.getText()))){
                MyLogin.getInstance().gotoStudentDashBoard();
            }else{
                errorLabel.setText("Useranme/Password invalid!!!!");
            } 
        }
    }
    
}
