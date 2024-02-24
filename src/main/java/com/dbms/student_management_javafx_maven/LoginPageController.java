
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


public class LoginPageController implements Initializable 
{
    @FXML
    private Label invalid;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField staffid;

    @FXML
    void login(ActionEvent event) 
    {
        String id = staffid.getText();
        String pass = password.getText();
        
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement","root","");  
            String sql = "SELECT staffid, name, password, COUNT(staffid) AS count FROM login WHERE staffid = ? AND password = ?";         
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,id);
            stmt.setString(2,pass);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                if(rs.getInt("count") == 1)
                {
                    try 
                    {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
                        Parent loginparent = (Parent) fxmlLoader.load();               
                        HomePageController c = (HomePageController) fxmlLoader.getController();
                        String name = rs.getString("name");
                        String sid = rs.getString("staffid");
                        c.setStaffName(name);
                        c.setStaffID(sid);
                        
                        //Parent loginparent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                        Scene homescene = new Scene(loginparent);
                        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                        window.setScene(homescene);
                        window.show();
                       
                    }
                    catch (Exception e) 
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    invalid.setText("Invalid username or password");
                }
            }


            rs.close();
            stmt.close();
            conn.close();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    @FXML
    void signup(ActionEvent event) throws IOException 
    {
        Parent loginparent = FXMLLoader.load(getClass().getResource("SignupPage.fxml"));
        Scene signupscene = new Scene(loginparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(signupscene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {

    }    
    
}
