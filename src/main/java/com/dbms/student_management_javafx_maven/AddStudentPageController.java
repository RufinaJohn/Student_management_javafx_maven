
package com.dbms.student_management_javafx_maven;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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


public class AddStudentPageController implements Initializable 
{
    @FXML
    private JFXTextField department;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField rollno;

    @FXML
    private JFXTextField semester;

    @FXML
    private JFXTextField year;
    
    @FXML
    private Label success;

    @FXML
    void addstudent(ActionEvent event) 
    {
        String id = rollno.getText();
        String de = department.getText();
        String na = name.getText();
        String ye = year.getText();
        String se = semester.getText();
        
       
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement","root","");  
      
            String sql1 = "SELECT COUNT(rollno) AS count FROM details WHERE rollno = ?";
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setString(1,id);
            ResultSet rs = stmt1.executeQuery();
            while(rs.next())
            {
                if(rs.getInt("count") == 1)
                {
                    success.setText("Student already present    ");
                }
                else if(id.isEmpty() || de.isEmpty() || na.isEmpty() || ye.isEmpty() || se.isEmpty())
                {
                    success.setText("All fields are required");                   
                }
                else
                {
                    String sql2 = "INSERT INTO details(rollno,name,department,year,semester) VALUES(?, ?, ?, ?, ?)";         
                    PreparedStatement stmt2 = conn.prepareStatement(sql2);
                    stmt2.setString(1,id);
                    stmt2.setString(2,na);
                    stmt2.setString(3,de);
                    stmt2.setString(4, ye);
                    stmt2.setString(5,se);
                    stmt2.executeUpdate(); 
                    stmt2.close();
                    success.setText("Student added successfully!");
                    conn.close();
                }
            }
  
            stmt1.close();

            conn.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    @FXML
    void home(ActionEvent event) throws IOException 
    {
        Parent addstudentparent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene homescene = new Scene(addstudentparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

    }    
    
}
