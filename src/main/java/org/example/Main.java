package org.example;

import java.sql.*;
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
                        eliminarProducto(conexion);
                        break;
                    case 3:
                        modificarProducto(conexion);
                        break;
                    case 4:
                        listarProductos(conexion);
                        break;
                    case 5:
                        introducirCliente(conexion);
                        break;
                    case 6:
                        eliminarCliente(conexion);
                        break;
                    case 7:
                        listarClientes(conexion);
                        break;
                    case 8:
                        realizarVenta(conexion);
                        break;
                    case 9:
                        ventaCliente(conexion);
                        break;
                    default:
                        System.out.println("Opcion no valida");
                        break;
                }
            }while(opcion!=0);

        }   catch(SQLException e){
            System.out.println("Error al hacer la tarea");
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
        String introduccion="INSERT INTO productos (nombre,precio_compra,precio_venta,stock) VALUES (?,?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(introduccion);

        System.out.println("Ingrese el nombre del producto");
        ps.setString(1, sc.nextLine());
        System.out.println("Ingrese el precio de compra del producto");
        ps.setDouble(2, Double.parseDouble(sc.nextLine()));
        System.out.println("Ingrese el precio de venta del producto");
        ps.setDouble(3, Double.parseDouble(sc.nextLine()));
        System.out.println("Ingrese el stock del producto");
        ps.setInt(4, (int) Double.parseDouble(sc.nextLine()));

        if(ps.executeUpdate()>0){
            System.out.println("Se ha añadido el producto correctamente");
        }

    }
    public static void listarProductos(Connection conexion) throws SQLException {
        ResultSet rs=conexion.createStatement().executeQuery("select * from productos");

        while (rs.next()) {
            System.out.println("Nombre: "+rs.getString("nombre"));
            System.out.println("Precio Compra: "+rs.getFloat("precio_compra"));
            System.out.println("Precio Venta: "+rs.getFloat("precio_venta"));
            System.out.println("Stock: "+rs.getInt("stock"));
            System.out.println(" ");
        }

    }
    public static void listarClientes(Connection conexion) throws SQLException {

        ResultSet rs=conexion.createStatement().executeQuery("select * from clientes");

        while (rs.next()) {
            System.out.println("Nombre: "+rs.getString("nombre"));
            System.out.println("Email: "+rs.getString("email"));
            System.out.println("Telefono: "+rs.getString("telefono"));
            System.out.println("Direccion: "+rs.getString("direccion"));
            System.out.println(" ");
        }
    }
    public static void eliminarProducto(Connection conexion) throws SQLException {

        String elimnar="DELETE FROM productos where nombre=(?)";
        PreparedStatement ps = conexion.prepareStatement(elimnar);

        System.out.println("Ingrese el nombre del producto que quiere eliminar");
        ps.setString(1, sc.nextLine());


        if(ps.executeUpdate()>0){
            System.out.println("Se ha eliminado el producto correctamente");
        }
    }
    public static void modificarProducto(Connection conexion) throws SQLException {
        listarProductos(conexion);

        String modificar="UPDATE productos set precio_compra=(?),precio_venta=(?),stock=(?) where nombre=(?)";
        PreparedStatement ps = conexion.prepareStatement(modificar);

        System.out.println("Ingrese el nombre del producto que quiere modificar");
        ps.setString(4, sc.nextLine());
        System.out.println("Ingrese el precio de compra del producto nuevo");
        ps.setDouble(1, Double.parseDouble(sc.nextLine()));
        System.out.println("Ingrese el precio de venta del producto nuevo");
        ps.setDouble(2, Double.parseDouble(sc.nextLine()));
        System.out.println("Ingrese el stock del producto nuevo");
        ps.setInt(3, (int) Double.parseDouble(sc.nextLine()));

        if(ps.executeUpdate()>0){
            System.out.println("Se ha actualizado el producto correctamente");
        }

    }
    public static void introducirCliente(Connection conexion) throws SQLException {
        String introdicrCli="INSERT INTO clientes (nombre,email,telefono,direccion) VALUES (?,?,?,?)";
        PreparedStatement ps = conexion.prepareStatement(introdicrCli);

        System.out.println("Ingrese el nombre del cliente");
        ps.setString(1, sc.nextLine());
        System.out.println("Ingrese el email del cliente");
        ps.setString(2, sc.nextLine());
        System.out.println("Ingrese el telefono del cliente");
        ps.setString(3, sc.nextLine());
        System.out.println("Ingrese la direccion del cliente");
        ps.setString(4, sc.nextLine());

        if(ps.executeUpdate()>0){
            System.out.println("Se ha añadido el cliente correctamente");
        }
    }

    public static void eliminarCliente(Connection conexion) throws SQLException {
        String elimnar="DELETE FROM clientes where nombre=(?)";
        PreparedStatement ps = conexion.prepareStatement(elimnar);

        System.out.println("Ingrese el nombre del cliente que quiere eliminar");
        ps.setString(1, sc.nextLine());

        if(ps.executeUpdate()>0){
            System.out.println("Se ha eliminado el cliente correctamente");
        }
    }

    public static void realizarVenta(Connection conexion) throws SQLException {
        String realizarVen="DELETE FROM productos where nombre=(?)";
        PreparedStatement ps = conexion.prepareStatement(realizarVen);
            System.out.println("Se ha eliminado el cliente correctamente");

    }

    public static void ventaCliente(Connection conexion) throws SQLException {
        String ventaCli="DELETE FROM clientes where nombre=(?)";
        PreparedStatement ps = conexion.prepareStatement(ventaCli);
        System.out.println("Se ha eliminado el cliente correctamente");
    }


}