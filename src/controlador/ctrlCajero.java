package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import modelo.Cajero;
import vista.FrmCajero;

public class ctrlCajero implements MouseListener, KeyListener {

    private Cajero modeloCajero;
    private FrmCajero vistaCajero;

    // Constructor
    public ctrlCajero(Cajero modeloCajero, FrmCajero frmCajero) {
        this.modeloCajero = modeloCajero;
        this.vistaCajero = frmCajero;

        // Asignar eventos a los botones y la tabla
        vistaCajero.btnGuardar.addMouseListener(this);
        vistaCajero.btnEliminar.addMouseListener(this);
        vistaCajero.btnActualizar.addMouseListener(this);
        vistaCajero.jtbMenu.addMouseListener(this);

        // Mostrar los datos en la tabla
        modeloCajero.Mostrar(vistaCajero.jtbMenu);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistaCajero.btnGuardar) {
            // Configurar los datos del cajero
            modeloCajero.setNombre(vistaCajero.txtNombre.getText());
            modeloCajero.setEdad(Integer.parseInt(vistaCajero.txtEdad.getText()));
            modeloCajero.setPeso(Double.parseDouble(vistaCajero.txtPeso.getText()));
            modeloCajero.setCorreo(vistaCajero.txtCorreo.getText());

            // Guardar los datos en la base de datos
            modeloCajero.Guardar();

            // Actualizar la tabla en tiempo real
            modeloCajero.Mostrar(vistaCajero.jtbMenu);
        }

        if (e.getSource() == vistaCajero.btnEliminar) {
            modeloCajero.Eliminar(vistaCajero.jtbMenu);

            // Actualizar la tabla en tiempo real
            modeloCajero.Mostrar(vistaCajero.jtbMenu);
        }

        if (e.getSource() == vistaCajero.jtbMenu) {
            // Cargar datos de la fila seleccionada
            modeloCajero.cargarDatosTabla(vistaCajero);
        }

        if (e.getSource() == vistaCajero.btnActualizar) {
            // Actualizar los datos del cajero
            modeloCajero.setNombre(vistaCajero.txtNombre.getText());
            modeloCajero.setEdad(Integer.parseInt(vistaCajero.txtEdad.getText()));
            modeloCajero.setPeso(Double.parseDouble(vistaCajero.txtPeso.getText()));
            modeloCajero.setCorreo(vistaCajero.txtCorreo.getText());

            // Actualizar en la base de datos
            modeloCajero.Actualizar(vistaCajero.jtbMenu);

            // Actualizar la tabla en tiempo real
            modeloCajero.Mostrar(vistaCajero.jtbMenu);
        }
        
        if (e.getSource() == vistaCajero.btnLimpiar) {
        // Limpiar los campos de texto
        vistaCajero.txtNombre.setText("");
        vistaCajero.txtEdad.setText("");
        vistaCajero.txtPeso.setText("");
        vistaCajero.txtCorreo.setText("");
        vistaCajero.jtbMenu.clearSelection(); 
    }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
