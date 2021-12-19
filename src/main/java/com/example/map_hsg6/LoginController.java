package com.example.map_hsg6;

import com.example.map_hsg6.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * this class handlesthe user interface loging into an account
 */
public class LoginController {
    private Controller controller;
    public LoginController() {
        this.controller=new Controller();
    }

    @FXML
    private Button singUpButton;

    @FXML Button loginButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;


    /**
     * this method implements the logic when clicking on {@link LoginController#loginButton}
     * it is used when user wants to log into his account
     */
    @FXML
    protected void loginButtonOnAction(){
        String name = null;
        try {
            name=controller.findPerson(passwordField.getText());
        }catch (Exception exception){
            this.showAlert(exception.toString());
        }
        if(name!=null){
            loginButton.getScene().getWindow().setUserData(new Pair<>(name, passwordField.getText()));
            if (name.charAt(0) == 'S'){
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(CollegeApp.class.getResource("StudentView.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.setTitle(name);
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(CollegeApp.class.getResource("TeacherView.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.setTitle(name);
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }else{
            errorLabel.setText("Invalid Id. Please try again.");
        }
    }

    /**
     * this method implements the logic when clicking on {@link LoginController#singUpButton}
     * this is used when user wants to make a new account
     */
    @FXML
    protected void signUpButtonOnAction() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CollegeApp.class.getResource("Register.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) singUpButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    /**
     * this method displays the error in a nice way
     * @param error the error that ocurred
     */
    public void showAlert(@NotNull String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Oops something went wrong");
        alert.setHeaderText(error.split(":")[0]);
        alert.showAndWait();
    }


}