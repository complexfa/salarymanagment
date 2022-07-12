package SalaryManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class Conn {
      public Connection Getdata() {
    	 Connection conn = null;
    	 String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
       String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=salary"; 
       String userName="sa";   
       String userPwd="123456"; 
       try
       {
        Class.forName(driverName);
        conn=DriverManager.getConnection(dbURL,userName,userPwd);
         System.out.println(conn);
       }
       catch(Exception e)
       {
        e.printStackTrace();
        System.out.print("¡¨Ω” ß∞‹");
       }
    	 return conn;
      }
     
      public static void main(String args[]) {
    	  Connection con = new Conn().Getdata();
    	  PreparedStatement preparedStatement = null;
      }
}
