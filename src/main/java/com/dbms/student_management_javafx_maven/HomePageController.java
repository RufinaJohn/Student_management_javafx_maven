
package com.dbms.student_management_javafx_maven;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomePageController implements Initializable 
{
    
    @FXML
    private Label name;

    @FXML
    private Label staffid;
    
    public void setStaffName(String staffname) 
    {
        name.setText(staffname);
    }
    
    public void setStaffID(String sid) 
    {
        staffid.setText(sid);
    }
    
    @FXML
    void addstudent(ActionEvent event) throws IOException 
    {
        Parent homeparent = FXMLLoader.load(getClass().getResource("AddStudentPage.fxml"));
        Scene addstudentscene = new Scene(homeparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(addstudentscene);
        window.show();
    }

    @FXML
    void enterattendance(ActionEvent event) throws IOException 
    {
        Parent homeparent = FXMLLoader.load(getClass().getResource("EnterAttendancePage.fxml"));
        Scene enterattendancescene = new Scene(homeparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(enterattendancescene);
        window.show();
    }

    @FXML
    void entermarks(ActionEvent event) throws IOException 
    {
        Parent homeparent = FXMLLoader.load(getClass().getResource("EnterMarksPage.fxml"));
        Scene entermarksscene = new Scene(homeparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(entermarksscene);
        window.show();
    }

    @FXML
    void logout(ActionEvent event) throws IOException 
    {
        Parent homeparent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene logoutscene = new Scene(homeparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(logoutscene);
        window.show();
    }

    @FXML
    void viewdetails(ActionEvent event) throws IOException 
    {
        Parent homeparent = FXMLLoader.load(getClass().getResource("ViewDetailsPage.fxml"));
        Scene viewdetailsscene = new Scene(homeparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(viewdetailsscene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    
    
}
