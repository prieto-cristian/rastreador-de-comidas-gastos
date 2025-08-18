package seguimiento_comida_gastos.conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConexion(){
        var baseDatos = "seguimiento_comida_gastos_db";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var contrasenia = "password";

        try{
            Connection conexion = DriverManager.getConnection(url, usuario, contrasenia);
            return conexion;
        }catch (Exception e){
            System.out.println("No se pudo conectar a la base de datos: " + e.getMessage());
        }
        return null;
    }
}
