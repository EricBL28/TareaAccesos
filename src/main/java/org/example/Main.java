package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String url="jdbc:mysql://localhost:3306/papeleria";
        String usuario="alumno";
        String password="alumno";

        try(Connection conexion = DriverManager.getConnection(url,usuario,password)){
            System.out.println("Exitoso");
        }   catch(SQLException e){
            System.out.println("sda");
        }


    }
}