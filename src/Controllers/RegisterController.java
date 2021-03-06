package Controllers;

import IWS.DBConnect;
import IWS.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import javax.management.Query;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private GridPane root;

    @FXML
    private TextField regusername;

    @FXML
    private PasswordField regpassword;

    @FXML
    private PasswordField regconpassword;

    @FXML
    private Button toregister;

    @FXML
    private Button backToLogin;

    @FXML
    private Label regstatus;

    @FXML
    void newRegister(ActionEvent event) {
        if(regusername.getText().isEmpty() || regpassword.getText().isEmpty() || regconpassword.getText().isEmpty()){
            regstatus.setText("Please fill all the details.");
        }
        else if(!regconpassword.getText().equals(regpassword.getText())){
            regstatus.setText("Password not matching.");
        }
        else if(doesUserExist(regusername.getText())){
            regstatus.setText("User already exists.");
        }
        else{
            String query = "INSERT INTO `users` (`username`, `password`) VALUES ('%s', '%s')";
            query = String.format(query, regusername.getText(), regpassword.getText());
            try{

                DBConnect.getStatement().executeUpdate(query);
                regstatus.setText("User Registered");
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    boolean doesUserExist(String username){
        boolean exist = false;
        String query = "SELECT * FROM `users` WHERE `username` = '%s'";
        try{
            ResultSet set = DBConnect.getStatement().executeQuery(String.format(query, username)); //press control + Q on "Execute Query"
            exist = set.next();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return exist;
    }

    @FXML
    void openLogin(ActionEvent event) throws IOException {
        Stage loginStage = Main.stage;
        loginStage.setTitle("Login");
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/Login.fxml"));
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }

}
