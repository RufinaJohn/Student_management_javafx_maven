module com.dbms.student_management_javafx_maven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.jfoenix;

    opens com.dbms.student_management_javafx_maven to javafx.fxml;
    exports com.dbms.student_management_javafx_maven;
}