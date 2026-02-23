/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import model.*;
import java.util.*;
/**
 * Controlador responsable de la recuperación del estado del sistema tras un
 * reinicio o fallo, restaurando pedidos activos y datos necesarios.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class RecuperacionController {
    private PedidoActivoSnapshot snapshot;
    private ProductoDAO productoDAO;

    public RecuperacionController(PedidoActivoSnapshot snapshot,
                                  ProductoDAO productoDAO) {
        this.snapshot = snapshot;
        this.productoDAO = productoDAO;
    }

    public Map<String, Pedido> recuperar() {

        Map<String, Pedido> resultado = new HashMap<>();

        var raw = snapshot.cargarRaw();

        for (var entry : raw.entrySet()) {

            String mesa = entry.getKey();
            Pedido pedido = new Pedido();

            for (String[] linea : entry.getValue()) {

                String productoId = linea[1];
                int cantidad = Integer.parseInt(linea[2]);

                Producto p = productoDAO.buscarPorId(productoId);

                if (p == null) continue;

                for (int i = 0; i < cantidad; i++) {
                    pedido.agregarProducto(p);
                }
            }

            resultado.put(mesa, pedido);
        }

        return resultado;
    }
}
