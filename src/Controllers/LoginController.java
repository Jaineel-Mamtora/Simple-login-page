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

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private GridPane root;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Button register;

    @FXML
    private Label status;

    @FXML
    void onLogin(javafx.event.ActionEvent event) throws SQLException {
        String query = "SELECT * FROM `users` WHERE `username` = '%s' && `password` = '%s'";
        query = String.format(query, username.getText(), password.getText());
        if(username.getText().isEmpty() || password.getText().isEmpty()){
            status.setText("Username or password can't be empty.");
        }
        else{
            try{
                ResultSet set = DBConnect.getStatement().executeQuery(query); //press control + Q on "Execute Query"
                if(set.next()){
                    status.setText("Logged in Successfully.");
                }
                else{
                    status.setText("Incorrect username or password");
                }
                set.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    @FXML
    void onRegister(ActionEvent event) throws IOException {
        Stage registerStage = Main.stage;
        registerStage.setTitle("Register");
        root = FXMLLoader.load(getClass().getResource("/FXMLs/Register.fxml"));
        registerStage.setScene(new Scene(root));
        registerStage.show();
    }

}