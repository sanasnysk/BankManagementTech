package BMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {

    final static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    final static String DB_URL = "jdbc:mysql://localhost:3306/exambank";
    final static String USER = "root";
    final static String PASS = "123456";
    Connection connection;
    Statement stmt;
    public Conn(){
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = connection.createStatement();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
