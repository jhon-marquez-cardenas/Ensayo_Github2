/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Producto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import vista.vistaPrincipal;
import modelo.Modelo;

/**
 *
 * @author JMARQUEZ
 */
public class Controlador implements ActionListener {

    vistaPrincipal interfaz;
    Modelo modelo;
    ArrayList<Producto> lista;

    public Controlador(vistaPrincipal interfaz, Modelo modelo) { //El constructor debe incluir las instancias de vista y modelo
        this.interfaz = interfaz;
        this.modelo = modelo;

        iniciarVista();
    }

    public void iniciarVista() {
        interfaz.setVisible(true);
        interfaz.setGuardar.addActionListener(this);
        interfaz.setEliminar.addActionListener(this);
        interfaz.setActualizar.addActionListener(this);
        interfaz.setConsultar.addActionListener(this);
        interfaz.tablaConsultar.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int posicion = interfaz.tablaConsultar.getSelectedRow();
                Producto p = lista.get(posicion);
                interfaz.boxIdConsultar.setText(String.valueOf(p.getId()));
                interfaz.boxNombreConsultar.setText(p.getNombre());
                interfaz.boxCantConsultar.setText(String.valueOf(p.getCantidad()));
                interfaz.boxPrecioConsultar.setText(String.valueOf(p.getPrecio()));
                interfaz.comboCategoriaConsultar.setSelectedItem(p.getCategoria());

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        {
        };
        llenarTabla();

    }

    public void llenarTabla() {
        lista = modelo.select();
        String[] columnas = new String[]{"Id", "Nombre", "Categoría", "Cantidad", "Precio"};
        Object[][] filas = new Object[][]{};//para almacenamiento de contenido de las filas

        DefaultTableModel modeloTabla = new DefaultTableModel(filas, columnas) {
            @Override // condición puesta para evitar que las celdas se puedan editar
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        };

        for (int i = 0; i < lista.size(); i++) {
            Producto p = lista.get(i);
            Object[] fila1 = new Object[]{p.getId(), p.getNombre(), p.getCategoria(), p.getCantidad(), p.getPrecio()};
            modeloTabla.addRow(fila1);
        }

        interfaz.tablaConsultar.setModel(modeloTabla);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (e.getSource() == interfaz.setGuardar) {
            String nombre = interfaz.boxNombreRegistrar.getText();
            int cantidad = Integer.parseInt(interfaz.boxCantRegistrar.getText());
            double precio = Double.parseDouble(interfaz.boxPrecioRegistrar.getText());
            String categoria = (String) interfaz.comboCategoriaRegistrar.getSelectedItem();
            Producto p = new Producto(nombre, cantidad, categoria, precio);
            modelo.insert(p);
            JOptionPane.showMessageDialog(interfaz,"Producto agregado");
            llenarTabla();

            /*System.out.println("PRODUCTO");// Prueba inicial
            System.out.println(nombre);
            System.out.println(cantidad);
            System.out.println(precio);
            System.out.println(categoria);
            System.out.println("");*/

        }
        if (e.getSource() == interfaz.setEliminar) {
            int boton = JOptionPane.showConfirmDialog(interfaz, "Desea eliminar el producto?");
            if (boton == 0) {
                int indice = interfaz.tablaConsultar.getSelectedRow();
                Producto p = lista.get(indice);
                modelo.delete(p);
                llenarTabla();
            }

        }
        if(e.getSource() == interfaz.setActualizar){
            int indice = interfaz.tablaConsultar.getSelectedRow();
            Producto p = lista.get(indice);
            p.setNombre(interfaz.boxNombreConsultar.getText());
            p.setCantidad(Integer.parseInt(interfaz.boxCantConsultar.getText()));
            p.setCategoria((String)interfaz.comboCategoriaConsultar.getSelectedItem());
            p.setPrecio(Double.parseDouble(interfaz.boxPrecioConsultar.getText()));
            
            modelo.update(p);
            JOptionPane.showMessageDialog(interfaz, "Producto actualizado correctamente");
            llenarTabla();
        }
        
        if(e.getSource()== interfaz.setConsultar){
            llenarTabla();
        }
    }

}
