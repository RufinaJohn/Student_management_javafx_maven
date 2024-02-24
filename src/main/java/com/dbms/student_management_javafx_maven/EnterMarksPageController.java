
package com.dbms.student_management_javafx_maven;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class EnterMarksPageController implements Initializable 
{
    
    @FXML
    private JFXTextField mark;

    @FXML
    private JFXComboBox rollnocombo;

    @FXML
    private JFXComboBox subjectcombo;

    @FXML
    private Label success;
    
    String rollno;
    String subject;


    @FXML
    void selectrollno(ActionEvent event) 
    {
        rollno = rollnocombo.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    void selectsubject(ActionEvent event) 
    {
        subject = subjectcombo.getSelectionModel().getSelectedItem().toString();
    }
    @FXML
    void insert(ActionEvent event) 
    {
        String ma = mark.getText();
        
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement","root","");  

            String sql = "UPDATE details SET " + subject + " = ? WHERE rollno = ?";         
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,ma);
            stmt.setString(2,rollno);
            stmt.executeUpdate(); 
            stmt.close();
            success.setText("Mark inserted");

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
        Parent entermarksparent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene homescene = new Scene(entermarksparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ObservableList<String> sublist = FXCollections.observableArrayList("DBMS","OS","TOC","AIML","ALGO");
        subjectcombo.setItems(sublist);
        
        ObservableList<String> rollnolist = FXCollections.observableArrayList();
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement","root",""); 
            String sql1 = "SELECT rollno FROM details";
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            ResultSet rs = stmt1.executeQuery();

            while (rs.next()) 
            {
                String rollno = rs.getString("rollno");
                rollnolist.add(rollno);
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        
        rollnocombo.setItems(rollnolist);

    }    
    
}
