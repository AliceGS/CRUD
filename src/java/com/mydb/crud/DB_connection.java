package com.mydb.crud;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_connection {
    
    public static void main(String[] args) throws Exception{
        DB_connection obj_DB_connection=new DB_connection();
        System.out.println(obj_DB_connection.get_connection());
    }
    public Connection get_connection() throws Exception{
        Connection connection=null;
        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC","root","root12345678");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
}
