package com.example.map_hsg6;

import com.example.map_hsg6.Controller.Controller;
import com.example.map_hsg6.Model.Student;
import com.example.map_hsg6.UI.Views;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;

public class StudentController implements Initializable {

    private Controller controller;

    @FXML
    Button registerButton;
    @FXML
    TextField idRegistrationField;
    @FXML
    TextField EnrollmentsCourseIdField;
    @FXML
    private TextArea LogsTextArea;
    @FXML
    private Button showCoursesButton;
    @FXML
    private Button closeButton;
    @FXML
    private Button EnrollementsCourseButton;
    @FXML
    private Button newPageButtonId;
    @FXML
    private Button showCreditsButton;

    @FXML
    protected void showCreditsButtonOnAction(){
        try{
            String uniqueId= ((Pair<String, String>) registerButton.getScene().getWindow().getUserData()).getValue();
            LogsTextArea.setText(String.valueOf(((Student)this.controller.getRegistrationSystem().findPerson(UUID.fromString(uniqueId))).getTotalCredits()));
        }catch (Exception exception){
            this.showAlert(exception.toString());
        }
    }

    @FXML
    protected void registerButtonOnAction(){
        if(idRegistrationField.getText().equals("")){
            try {
                LogsTextArea.setText(Views.registerForCourseView(this.controller.retrieveCoursesWithFreePlaces()));
            } catch (SQLException e) {
                this.showAlert(e.toString());
            }
        }else{
            try {
                String option=idRegistrationField.getText();

                String uniqueId= ((Pair<String, String>) registerButton.getScene().getWindow().getUserData()).getValue();
                String course_id=controller.register(this.controller.retrieveCoursesWithFreePlaces().get(Integer.parseInt(option)-1),uniqueId);
                LogsTextArea.setText("Course Id="+course_id);
            } catch (Exception exception) {
                this.showAlert(exception.toString());
            }

        }


    }
    @FXML
    protected void showCoursesButtonOnAction(){
        try {
            LogsTextArea.setText(Views.retrieveCoursesWithFreePlacesView(this.controller.retrieveCoursesWithFreePlaces()));
        } catch (Exception exception) {
            this.showAlert(exception.toString());
        }
    }

    @FXML
    protected  void EnrollementsCourseButtonOnAction(){

        String id=EnrollmentsCourseIdField.getText();
        if(id.equals("")){
            LogsTextArea.setText("No id provided...");
        }else{
            try {
                LogsTextArea.setText(Views.retrieveStudentsEnrolledForACourseView(this.controller.retrieveStudentsEnrolledForACourse(id)));
            } catch (Exception exception) {
                this.showAlert(exception.toString());
            }
        }

    }
    @FXML
    protected void closeButtonOnAction(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CollegeApp.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void newPageButtonOnAction(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CollegeApp.class.getResource("Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Best App Ever");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.controller=new Controller();
    }


    public void showAlert(@NotNull String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Oops something went wrong");
        alert.setHeaderText(error.split(":")[0]);
        alert.showAndWait();
    }

}
