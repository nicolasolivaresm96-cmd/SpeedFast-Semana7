package modelo;

import datos.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GestorPedidos {

    // Guardar un pedido en MySQL
    public static void agregarPedido(Pedido pedido) {

        String sql = "INSERT INTO pedido (id, direccion, tipo, estado) VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, pedido.getId());
            sentencia.setString(2, pedido.getDireccion());
            sentencia.setString(3, pedido.getTipo());
            sentencia.setString(4, pedido.getEstado());

            sentencia.executeUpdate();

            System.out.println("Pedido guardado correctamente en MySQL.");

        } catch (SQLException e) {
            System.out.println("Error al guardar pedido: " + e.getMessage());
        }
    }

    // Obtener todos los pedidos desde MySQL
    public static List<Pedido> obtenerPedidos() {

        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT id, direccion, tipo, estado FROM pedido";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                int id = resultado.getInt("id");
                String direccion = resultado.getString("direccion");
                String tipo = resultado.getString("tipo");
                String estado = resultado.getString("estado");

                Pedido pedido = new Pedido(id, direccion, tipo);
                pedido.setEstado(estado);

                pedidos.add(pedido);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener pedidos: " + e.getMessage());
        }

        return pedidos;
    }

    // Comprobar si ya existe un pedido con determinado ID
    public static boolean existeId(int id) {

        String sql = "SELECT id FROM pedido WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            try (ResultSet resultado = sentencia.executeQuery()) {
                return resultado.next();
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar pedido: " + e.getMessage());
            return false;
        }
    }

    // Actualizar el estado de un pedido en MySQL
    public static void actualizarEstado(int id, String estado) {

        String sql = "UPDATE pedido SET estado = ? WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, estado);
            sentencia.setInt(2, id);

            sentencia.executeUpdate();

            System.out.println("Estado del pedido actualizado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar estado: " + e.getMessage());
        }
    }
}