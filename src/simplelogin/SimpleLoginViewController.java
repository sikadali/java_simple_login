package simplelogin;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * FXML Controller class
 *
 * @author Sika DALI
 */
public class SimpleLoginViewController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private Button btnLogin;
    
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    
    @FXML
    private void login(ActionEvent event) {
        String username = tfUsername.getText();
        String password = tfPassword.getText();
        
        if (username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(null, "Username and/or Password blank");
        }
        else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost/simple_login_db", "root", "");
                
                pst = conn.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
                pst.setString(1, username);
                pst.setString(2, password);
                
                rs = pst.executeQuery();
                
                if (rs.next()){
                    JOptionPane.showMessageDialog(null, "Login Success !!!");
                }else{
                    JOptionPane.showMessageDialog(null, "Login Failed !!!");
                    tfUsername.setText("");
                    tfPassword.setText("");
                    tfUsername.requestFocus();
                }
                        
                        
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(SimpleLoginViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
}
