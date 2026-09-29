package vista;

import modelo.GestorPedidos;
import modelo.Pedido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaListaPedidos extends JFrame {

    private DefaultTableModel modeloTabla;
    private JTable tablaPedidos;

    public VentanaListaPedidos() {

        setTitle("SpeedFast - Lista de Pedidos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Dirección", "Tipo", "Estado"},
                0
        );

        tablaPedidos = new JTable(modeloTabla);

        JScrollPane scrollPane = new JScrollPane(tablaPedidos);

        JButton botonRefrescar = new JButton("Refrescar");
        botonRefrescar.addActionListener(e -> cargarPedidos());

        add(scrollPane, BorderLayout.CENTER);
        add(botonRefrescar, BorderLayout.SOUTH);

        cargarPedidos();
    }

    private void cargarPedidos() {

        modeloTabla.setRowCount(0);

        for (Pedido pedido : GestorPedidos.obtenerPedidos()) {

            modeloTabla.addRow(new Object[]{
                    pedido.getId(),
                    pedido.getDireccion(),
                    pedido.getTipo(),
                    pedido.getEstado()
            });
        }
    }
}