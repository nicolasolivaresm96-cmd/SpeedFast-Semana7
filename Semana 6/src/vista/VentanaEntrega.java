package vista;

import modelo.GestorPedidos;
import modelo.Pedido;
import modelo.Repartidor;

import javax.swing.*;
import java.awt.*;

public class VentanaEntrega extends JFrame {

    private JComboBox<String> comboPedidos;
    private JTextField campoRepartidor;

    public VentanaEntrega() {

        setTitle("SpeedFast - Iniciar Entrega");
        setSize(450, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("Pedido:"));

        comboPedidos = new JComboBox<>();

        for (Pedido pedido : GestorPedidos.obtenerPedidos()) {
            if (pedido.getEstado().equals("Pendiente")) {
                comboPedidos.addItem(
                        pedido.getId() + " - " + pedido.getDireccion()
                );
            }
        }

        panel.add(comboPedidos);

        panel.add(new JLabel("Repartidor:"));
        campoRepartidor = new JTextField();
        panel.add(campoRepartidor);

        JButton botonIniciar = new JButton("Iniciar Entrega");

        panel.add(new JLabel(""));
        panel.add(botonIniciar);

        add(panel);

        botonIniciar.addActionListener(e -> iniciarEntrega());
    }

    private void iniciarEntrega() {

        if (comboPedidos.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No hay pedidos pendientes.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String nombreRepartidor = campoRepartidor.getText().trim();

        if (nombreRepartidor.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar el nombre del repartidor.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        String seleccion = (String) comboPedidos.getSelectedItem();
        int id = Integer.parseInt(seleccion.split(" - ")[0]);

        for (Pedido pedido : GestorPedidos.obtenerPedidos()) {

            if (pedido.getId() == id) {

                Repartidor repartidor = new Repartidor(nombreRepartidor);

                pedido.setRepartidor(repartidor);
                pedido.setEstado("En entrega - " + repartidor.getNombre());

                JOptionPane.showMessageDialog(
                        this,
                        "Entrega iniciada correctamente.\nRepartidor: "
                                + repartidor.getNombre()
                );

                dispose();
                return;
            }
        }
    }
}