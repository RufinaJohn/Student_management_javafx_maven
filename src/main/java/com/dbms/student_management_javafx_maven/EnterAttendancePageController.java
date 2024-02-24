
package com.dbms.student_management_javafx_maven;

import com.jfoenix.controls.JFXTextArea;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EnterAttendancePageController implements Initializable 
{
    @FXML
    private DatePicker date;

    @FXML
    private Label registered;

    @FXML
    private JFXTextArea textfieldabsent;
    
    
    @FXML
    void enterattendance(ActionEvent event) 
    {
        String rollNumbers = textfieldabsent.getText(); 
        String[] rollNumberArray = rollNumbers.split("\n"); 

        String dateValue = date.getValue().toString();
     

        for (String rollno : rollNumberArray) 
        {
            try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement","root","");  
         
                String sql = "INSERT INTO attendance (rollno, date, attendance) VALUES (?, ?, ?)";         
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1,rollno);
                stmt.setString(2,dateValue);
                stmt.setString(3,"ABSENT");
 
                stmt.executeUpdate(); 
                stmt.close();
                conn.close();
              
            }
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        } 
        

        registered.setText("Attendance for " + dateValue + " entered successfully");
        textfieldabsent.clear();
      
       
    }
    
    @FXML
    void home(ActionEvent event) throws IOException 
    {
        Parent enterattendanceparent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene homescene = new Scene(enterattendanceparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }
}    
