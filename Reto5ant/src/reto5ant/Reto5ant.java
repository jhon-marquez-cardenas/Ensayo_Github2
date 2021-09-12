package Reto5ant;

import controlador.Controlador;
import vista.vistaPrincipal;
import modelo.conexionDB;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import modelo.Modelo;


/**
 *
 * @author JHON FREDY MÁRQUEZ CÁRDENAS
 * GRUPO J18
 * CREADO EN NetBeans IDE 12.4
 * 11/ago/21
 */
public class Reto5ant {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        vistaPrincipal interfaz = new vistaPrincipal();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(interfaz, modelo);
       
       

    }

}
