package com.example.map_hsg6;

import com.example.map_hsg6.Controller.Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    private Controller controller;

    public RegisterController() {
        this.controller = new Controller();
    }

    @FXML
    private ChoiceBox statusBox;
    @FXML
    private Button closeButton;
    @FXML
    private Button registerButton;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextArea idLabel;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusBox.getItems().removeAll(statusBox.getItems());
        statusBox.getItems().addAll("Student", "Teacher");
        statusBox.getSelectionModel().select("Student");
    }
    @FXML
    protected void closeButtonOnAction(){

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CollegeApp.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage=(Stage)closeButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void registerButtonOnAction(){
        try {

            idLabel.setText("Your id is:\n"+this.controller.addPerson(firstNameField.getText(),lastNameField.getText(),statusBox.getValue().equals("Student")?"1":"2").toString());

        } catch (SQLException exception) {
            this.showAlert(exception.toString());
        }
    }

    public void showAlert(@NotNull String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Oops something went wrong");
        alert.setHeaderText(error.split(":")[0]);
        alert.showAndWait();
    }

}
