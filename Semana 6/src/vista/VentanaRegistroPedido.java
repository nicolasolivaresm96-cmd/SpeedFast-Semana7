package vista;

import modelo.GestorPedidos;
import modelo.Pedido;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistroPedido extends JFrame {

    private JTextField campoId;
    private JTextField campoDireccion;
    private JComboBox<String> comboTipo;

    public VentanaRegistroPedido() {

        setTitle("SpeedFast - Registrar Pedido");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("ID:"));
        campoId = new JTextField();
        panel.add(campoId);

        panel.add(new JLabel("Dirección:"));
        campoDireccion = new JTextField();
        panel.add(campoDireccion);

        panel.add(new JLabel("Tipo:"));
        comboTipo = new JComboBox<>(
                new String[]{"Comida", "Encomienda", "Express"}
        );
        panel.add(comboTipo);

        JButton botonGuardar = new JButton("Guardar");
        panel.add(new JLabel(""));
        panel.add(botonGuardar);

        add(panel);

        botonGuardar.addActionListener(e -> guardarPedido());
    }

    private void guardarPedido() {

        String textoId = campoId.getText().trim();
        String direccion = campoDireccion.getText().trim();
        String tipo = (String) comboTipo.getSelectedItem();

        if (textoId.isEmpty() || direccion.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe completar todos los campos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            int id = Integer.parseInt(textoId);

            if (GestorPedidos.existeId(id)) {
                JOptionPane.showMessageDialog(
                        this,
                        "Ya existe un pedido con ese ID.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            Pedido pedido = new Pedido(id, direccion, tipo);
            GestorPedidos.agregarPedido(pedido);

            JOptionPane.showMessageDialog(
                    this,
                    "Pedido registrado correctamente."
            );

            campoId.setText("");
            campoDireccion.setText("");
            comboTipo.setSelectedIndex(0);

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "El ID debe ser un número.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}