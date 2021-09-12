/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author JMARQUEZ
 */
public class conexionDB {

    private Connection conexion;

    public void conectar() {
        String db = "reto5db.db";
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + db);
            if (conexion != null) {
                System.out.println("Conexión exitosa");
            }
        } catch (SQLException excepcion) {
            System.out.println("Ocurrió un error conectando " + excepcion.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (conexion != null){
                conexion.close();
                System.out.println("Desconexión exitosa");
            }
        } catch (SQLException excepcion) {
            System.out.println("Ocurrió un error desconectando " + excepcion.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }
    
    
    
    

}
