package modelo;

import java.sql.*;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.FrmCajero;

public class Cajero {
    String UUID_Cajero;
    String nombre;
    int edad;
    double peso;
    String correo;

    // Getters y Setters
    public String getUUID_Cajero() {
        return UUID_Cajero;
    }

    public void setUUID_Cajero(String UUID_Cajero) {
        this.UUID_Cajero = UUID_Cajero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Métodos CRUD
    public void Guardar() {
        Connection conexion = ClaseConexion.getConexion();
        try {
            PreparedStatement addCajero = conexion.prepareStatement("INSERT INTO tbCajero(UUID_Cajero, Nombre_Cajero, Edad_Cajero, Peso_Cajero, Correo_Cajero) VALUES (?, ?, ?, ?, ?)");
            addCajero.setString(1, UUID.randomUUID().toString());
            addCajero.setString(2, getNombre());
            addCajero.setInt(3, getEdad());
            addCajero.setDouble(4, getPeso());
            addCajero.setString(5, getCorreo());
            addCajero.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error en el método Guardar: " + ex);
        }
    }

    public void Mostrar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modeloCajero = new DefaultTableModel();
        modeloCajero.setColumnIdentifiers(new Object[]{"UUID_Cajero", "Nombre", "Edad", "Peso", "Correo"});
        try {
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tbCajero");
            while (rs.next()) {
                modeloCajero.addRow(new Object[]{
                    rs.getString("UUID_Cajero"),
                    rs.getString("Nombre_Cajero"),
                    rs.getInt("Edad_Cajero"),
                    rs.getDouble("Peso_Cajero"),
                    rs.getString("Correo_Cajero")
                });
            }
            tabla.setModel(modeloCajero);
        } catch (SQLException e) {
            System.out.println("Error en el método Mostrar: " + e);
        }
    }

    public void Eliminar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();
        String miUUID = tabla.getValueAt(filaSeleccionada, 0).toString();
        try {
            PreparedStatement deleteCajero = conexion.prepareStatement("DELETE FROM tbCajero WHERE UUID_Cajero = ?");
            deleteCajero.setString(1, miUUID);
            deleteCajero.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en el método Eliminar: " + e);
        }
    }

    public void cargarDatosTabla(FrmCajero vista) {
        int filaSeleccionada = vista.jtbMenu.getSelectedRow();
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vista.jtbMenu.getValueAt(filaSeleccionada, 0).toString();
            String NombreDeTB = vista.jtbMenu.getValueAt(filaSeleccionada, 1).toString();
            String EdadTB = vista.jtbMenu.getValueAt(filaSeleccionada, 2).toString();
            String PesoTB = vista.jtbMenu.getValueAt(filaSeleccionada, 3).toString();
            String CorreoTB = vista.jtbMenu.getValueAt(filaSeleccionada, 4).toString();

            vista.txtNombre.setText(NombreDeTB);
            vista.txtEdad.setText(EdadTB);
            vista.txtPeso.setText(PesoTB);
            vista.txtCorreo.setText(CorreoTB);
        }
    }

    public void Actualizar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            String miUUID = tabla.getValueAt(filaSeleccionada, 0).toString();
            try {
                PreparedStatement updateCajero = conexion.prepareStatement("UPDATE tbCajero SET Nombre_Cajero = ?, Edad_Cajero = ?, Peso_Cajero = ?, Correo_Cajero = ? WHERE UUID_Cajero = ?");
                updateCajero.setString(1, getNombre());
                updateCajero.setInt(2, getEdad());
                updateCajero.setDouble(3, getPeso());
                updateCajero.setString(4, getCorreo());
                updateCajero.setString(5, miUUID);
                updateCajero.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error en el método Actualizar: " + e);
            }
        } else {
            System.out.println("No se seleccionó ninguna fila para actualizar.");
        }
    }
}
