package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

public class EntregaDAO {

    public static void guardar(int idPedido, int idRepartidor) {

        String sql = "INSERT INTO entrega " +
                "(id_pedido, id_repartidor, fecha, hora) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, idPedido);
            sentencia.setInt(2, idRepartidor);
            sentencia.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
            sentencia.setTime(4, java.sql.Time.valueOf(LocalTime.now()));

            sentencia.executeUpdate();

            System.out.println("Entrega guardada correctamente en MySQL.");

        } catch (SQLException e) {
            System.out.println(
                    "Error al guardar entrega: " + e.getMessage()
            );
        }
    }

    public static int obtenerUltimoIdRepartidor() {

        String sql = "SELECT id FROM repartidor ORDER BY id DESC LIMIT 1";

        try (Connection conexion = ConexionBD.conectar();
             Statement sentencia = conexion.createStatement();
             ResultSet resultado = sentencia.executeQuery(sql)) {

            if (resultado.next()) {
                return resultado.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println(
                    "Error al obtener repartidor: " + e.getMessage()
            );
        }

        return -1;
    }
}