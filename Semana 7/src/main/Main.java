package main;

import datos.ConexionBD;
import vista.VentanaPrincipal;

import javax.swing.*;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {

        // Probar conexión con MySQL
        Connection conexion = ConexionBD.conectar();

        if (conexion != null) {
            System.out.println("Base de datos conectada correctamente.");
        } else {
            System.out.println("No se pudo conectar a la base de datos.");
        }

        // Abrir la ventana principal de SpeedFast
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}