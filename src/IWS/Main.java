package IWS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        primaryStage.setTitle("Login");
        Parent root = FXMLLoader.load(getClass().getResource("/FXMLs/Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    public static void main(String[] args){
        try {
            DBConnect.connect();
            launch();
            DBConnect.disconnect();
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
}
