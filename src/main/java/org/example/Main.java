package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)  {
        String url="jdbc:mysql://localhost:3306/papeleria";
        String usuario="root";
        String password="";

        try(Connection conexion = DriverManager.getConnection(url,usuario,password)){

            System.out.println("Conexion exitosa");
            int opcion;
            do{
                mostrarMenu();
                opcion= Integer.parseInt(sc.nextLine());
                switch(opcion){
                    case 0:
                        System.out.println("Saliendo del menú");
                        break;
                    case 1:
                        anadirProducto(conexion);
                        break;
                    case 2:
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            }while(opcion!=0);




        }   catch(SQLException e){
            System.out.println("sda");
        }


    }

    public static void mostrarMenu(){
        System.out.println(" --------------------------------");
        System.out.println("| 0. Salir                       |");
        System.out.println("| 1. Introducir producto         |");
        System.out.println("| 2. Eliminar producto           |");
        System.out.println("| 3. Modificar producto          |");
        System.out.println("| 4. Listar productos            |");
        System.out.println("| 5. Introducir cliente          |");
        System.out.println("| 6. Eliminar cliente            |");
        System.out.println("| 7. Listar clientes             |");
        System.out.println("| 8. Realizar venta              |");
        System.out.println("| 9. Realizar venta con cliente  |");
        System.out.println(" --------------------------------");
    }

    public static void anadirProducto(Connection conexion) throws SQLException {
        String titulo="Select * from productos";
        conexion.prepareStatement(titulo);

        System.out.println("añadiendo producto");
    }
}