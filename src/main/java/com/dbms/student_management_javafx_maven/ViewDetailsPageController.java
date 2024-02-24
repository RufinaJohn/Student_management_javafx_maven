
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewDetailsPageController implements Initializable 
{
    @FXML
    private Label aiml;

    @FXML
    private Label algo;

    @FXML
    private Label dbms;

    @FXML
    private Label department;

    @FXML
    private Label name;

    @FXML
    private Label os;

    @FXML
    private Label rollno;

    @FXML
    private JFXTextField rollnoinput;

    @FXML
    private Label semester;

    @FXML
    private Label toc;

    @FXML
    private Label year;
    
    @FXML
    private ScrollPane scrollpaneabsent;
    
    @FXML
    private VBox vboxabsent;

    @FXML
    void home(ActionEvent event) throws IOException 
    {
        Parent viewdetailsparent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene homescene = new Scene(viewdetailsparent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }

    @FXML
    void viewdetails(ActionEvent event) 
    {
        try 
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/studentmanagement","root","");  
      
            String sql1 = "SELECT * FROM details WHERE rollno = ?";
            PreparedStatement stmt1 = conn.prepareStatement(sql1);
            stmt1.setString(1,rollnoinput.getText());
            ResultSet rs1 = stmt1.executeQuery();
            while (rs1.next()) 
            {
                rollno.setText(Integer.toString(rs1.getInt("rollno")));
                name.setText(rs1.getString("name"));
                department.setText(rs1.getString("department"));
                year.setText(Integer.toString(rs1.getInt("year")));
                semester.setText(Integer.toString(rs1.getInt("semester")));
                dbms.setText(Integer.toString(rs1.getInt("dbms")));
                os.setText(Integer.toString(rs1.getInt("os")));
                toc.setText(Integer.toString(rs1.getInt("toc")));
                aiml.setText(Integer.toString(rs1.getInt("aiml")));
                algo.setText(Integer.toString(rs1.getInt("algo")));

            }
  
            
            
            String sql2 = "SELECT DISTINCT(date) FROM attendance WHERE rollno = ?";
            PreparedStatement stmt2 = conn.prepareStatement(sql2);
            stmt2.setString(1,rollnoinput.getText());
            ResultSet rs2 = stmt2.executeQuery();
            while(rs2.next())
            {
                Label l = new Label(rs2.getString("date"));
                vboxabsent.getChildren().add(l);
            }
            scrollpaneabsent.setContent(vboxabsent);
            
            
            stmt1.close();
            stmt2.close();
            conn.close();
            
            
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
  

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
      
    }    
    
}
