/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author JMARQUEZ
 */
public class Modelo {

    public ArrayList<Producto> select() {
        ArrayList<Producto> lista = new ArrayList<>();

        conexionDB con = new conexionDB();
        con.conectar();
        String sql = "SELECT * FROM productos";

        try {
            Statement stm = con.getConexion().createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Producto p = new Producto();
                int id = rs.getInt("id");
                p.setId(id);
                p.setNombre(rs.getString("nombre"));
                p.setCantidad(rs.getInt("Cantidad"));
                p.setCategoria(rs.getString("Categoria"));
                p.setPrecio(rs.getDouble("precio"));
                lista.add(p);

            }
        } catch (SQLException excepcion) {
            System.out.println("Ocurrió un error consultando " + excepcion.getMessage());
        }

        return lista;
    }

    public void delete(Producto p) {
        String sql = "DELETE FROM productos WHERE id = ?";
        conexionDB conn = new conexionDB();
        conn.conectar();
        try {
            PreparedStatement pstm = conn.getConexion().prepareStatement(sql);
            pstm.setInt(1, p.getId());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ocurrió un error eliminando " + excepcion.getMessage());
        }
        conn.desconectar();

    }

    public void update(Producto p) {
        conexionDB conn = new conexionDB();
        conn.conectar();

        String sql = "UPDATE productos SET nombre = ?, categoria = ?, cantidad = ?, precio = ? WHERE id = ?";
        try {
            PreparedStatement pstm = conn.getConexion().prepareStatement(sql);
            pstm.setString(1, p.getNombre());
            pstm.setString(2, p.getCategoria());
            pstm.setInt(3, p.getCantidad());
            pstm.setDouble(4, p.getPrecio());
            pstm.setInt(5, p.getId());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ocurrió un error actualizando " + excepcion.getMessage());
        }
        conn.desconectar();

    }

    public void insert(Producto p) {
        conexionDB conn = new conexionDB();
        conn.conectar();

        String sql = "INSERT INTO productos(nombre, categoria, cantidad, precio)VALUES(?, ?, ?, ?)";
        try {
            PreparedStatement pstm = conn.getConexion().prepareStatement(sql);
            pstm.setString(1, p.getNombre());
            pstm.setString(2, p.getCategoria());
            pstm.setInt(3, p.getCantidad());
            pstm.setDouble(4, p.getPrecio());
            pstm.executeUpdate();
        } catch (SQLException excepcion) {
            System.out.println("Ocurrió un error insertando " + excepcion.getMessage());
        }
        conn.desconectar();
    }
}
