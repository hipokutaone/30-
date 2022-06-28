import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import papki.DatabaseHandler;
import papki.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {

    @FXML
    private PasswordField passwordpop;

    @FXML
    private TextField login;

    @FXML
    private Button ok;

    @FXML
    void initialize() {

        ok.setOnAction(event -> {
                String loginText = login.getText().trim();
                String loginPassword = passwordpop.getText().trim();

                if (!loginText.equals("") && !loginPassword.equals(""))
                    loginUser(loginText, loginPassword);
                else
                    System.out.println("Login and password is empty");

        });

        login.setOnAction(event -> {
            login.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vhod.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setUserName(loginText);
        user.setPassword(loginPassword);
        dbHandler.getUser(user);
        ResultSet result = dbHandler.getUser(user);

        int counter = 0;

        try {
            while (result.next()) {
                counter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

            if (counter >= 1) {
                System.out.println("Success!");
            }
        }
    }


