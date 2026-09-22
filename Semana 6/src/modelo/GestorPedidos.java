package modelo;

import java.util.ArrayList;
import java.util.List;

public class GestorPedidos {

    private static final List<Pedido> pedidos = new ArrayList<>();

    public static void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public static List<Pedido> obtenerPedidos() {
        return pedidos;
    }

    public static boolean existeId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return true;
            }
        }
        return false;
    }
}