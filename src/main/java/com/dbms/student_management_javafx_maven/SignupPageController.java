
package com.dbms.student_management_javafx_maven;

import com.jfoenix.controls.JFXPasswordField;
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


public class SignupPageController implements Initializable 
{
    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField staffid;

    @FXML
    private Label success;

    @FXML
    void create(ActionEvent event) 
    {
        String id = staffid.getText();
        String pa = password.getText();
        String na = name.getText();
        String em = email.getText();
        
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement","root","");  
      
            String sql1 = "SELECT COUNT(staffid) AS count FROM login WHERE staffid = ?";
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setString(1,id);
            ResultSet rs = stmt1.executeQuery();
            while(rs.next())
            {
                if(rs.getInt("count") == 1)
                {
                    success.setText("Account already exists");
                }
                else if(id.isEmpty() || pa.isEmpty() || na.isEmpty() || em.isEmpty())
                {
                    success.setText("All fields are required");                   
                }
                else
                {
                    String sql2 = "INSERT INTO login(staffid,name,email,password) VALUES(?, ?, ?, ?)";         
                    PreparedStatement stmt2 = conn.prepareStatement(sql2);
                    stmt2.setString(1,id);
                    stmt2.setString(2,na);
                    stmt2.setString(3,em);
                    stmt2.setString(4, pa);
                    stmt2.executeUpdate(); 
                    stmt2.close();
                    success.setText("Account created successfully!");
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
    void login(ActionEvent event) throws IOException 
    {
        Parent signupparent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene loginscene = new Scene(signupparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginscene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

    }    
    
}
