package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {

        setTitle("SpeedFast - Gestión de Entregas");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel titulo = new JLabel(
                "Sistema de Gestión SpeedFast",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JButton botonRegistrar = new JButton("Registrar Pedido");
        JButton botonListar = new JButton("Listar Pedidos");
        JButton botonEntrega = new JButton("Asignar Repartidor / Iniciar Entrega");

        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(
                BorderFactory.createEmptyBorder(20, 40, 30, 40)
        );

        panelBotones.add(botonRegistrar);
        panelBotones.add(botonListar);
        panelBotones.add(botonEntrega);

        add(titulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        botonRegistrar.addActionListener(e -> {
            new VentanaRegistroPedido().setVisible(true);
        });

        botonListar.addActionListener(e -> {
            new VentanaListaPedidos().setVisible(true);
        });

        botonEntrega.addActionListener(e -> {
            new VentanaEntrega().setVisible(true);
        });
    }
}