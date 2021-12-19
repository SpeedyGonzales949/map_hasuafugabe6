package com.example.map_hsg6;

import com.example.map_hsg6.Controller.Controller;
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
import java.util.ResourceBundle;

public class TeacherController implements Initializable {

    private Controller controller;

    @FXML
    private TextField EnrollmentsCourseIdField;
    @FXML
    private TextArea LogsTextArea;
    @FXML
    private Button closeButton;
    @FXML
    private Button changeCreditsButton;
    @FXML
    private TextField changeCourseIdField;
    @FXML
    private TextField newCreditsField;
    @FXML
    private Button deleteCourseButton;
    @FXML
    private TextField deleteCourseIdField;
    @FXML
    private Button addCourseButton;
    @FXML
    private TextField NameField;
    @FXML
    private TextField MaxEnrollmentField;
    @FXML
    private TextField CreditsField;

    @FXML
    protected void addCourseButtonOnAction(){
        try{
            if(NameField.getText().equals("")||MaxEnrollmentField.getText().equals("")||CreditsField.getText().equals(""))
                LogsTextArea.setText("You need to provide all the data...");
            else{
                String uniqueId= ((Pair<String, String>) addCourseButton.getScene().getWindow().getUserData()).getValue();
                String course_id=this.controller.addCourse(uniqueId,NameField.getText(),MaxEnrollmentField.getText(),CreditsField.getText());
                LogsTextArea.setText("Course created.\n"+course_id);
            }
        }catch(Exception exception){
            this.showAlert(exception.toString());
        }
    }

    @FXML
    protected void deleteCourseButtonOnAction(){
        try {

            String uniqueId= ((Pair<String, String>) deleteCourseButton.getScene().getWindow().getUserData()).getValue();
            if(deleteCourseIdField.getText().equals(""))
                LogsTextArea.setText("No id provided...");
            else{
                this.controller.deleteCourse(deleteCourseIdField.getText(),uniqueId);
                LogsTextArea.setText("Course deleted");
            }

        } catch (Exception exception) {
            this.showAlert(exception.toString());
        }
    }
    @FXML
    protected void changeCreditsButtonOnAction(){
        try {
            String uniqueId= ((Pair<String, String>) changeCreditsButton.getScene().getWindow().getUserData()).getValue();
            if(changeCourseIdField.getText().equals(""))
                LogsTextArea.setText("No id provided...");
            else{
                this.controller.changeCredits(uniqueId,changeCourseIdField.getText(),newCreditsField.getText());
                LogsTextArea.setText("Updated Course Credits successfully...");
            }

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
    protected void showCoursesButtonOnAction(){
        try {
            LogsTextArea.setText(Views.retrieveCoursesWithFreePlacesView(this.controller.retrieveCoursesWithFreePlaces()));
        } catch (Exception exception) {
            this.showAlert(exception.toString());
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
    public void showAlert(@NotNull String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Oops something went wrong");
        alert.setHeaderText(error.split(":")[0]);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.controller=new Controller();
    }
}
