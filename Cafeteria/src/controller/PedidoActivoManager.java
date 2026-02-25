/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import model.Pedido;
import model.PedidoActivoSnapshot;

public class PedidoActivoManager {

    private Map<String, Pedido> pedidosPorMesa = new HashMap<>();
    private PedidoActivoSnapshot snapshot = new PedidoActivoSnapshot();

    public Pedido obtenerOcrear(String mesa) {
        return pedidosPorMesa.computeIfAbsent(mesa, m -> new Pedido());
    }

    public Pedido obtener(String mesa) {
        return pedidosPorMesa.get(mesa);
    }

    public void eliminar(String mesa) {
        pedidosPorMesa.remove(mesa);
    }

    public void guardarSnapshot() {
        snapshot.guardar(pedidosPorMesa);
    }

    public void setPedidos(Map<String, Pedido> pedidos) {
        this.pedidosPorMesa = pedidos;
    }

    public Map<String, Pedido> getPedidos() {
        return pedidosPorMesa;
    }

    public int generarNumeroDisponible() {

        boolean[] usados = new boolean[10];

        for (Pedido p : pedidosPorMesa.values()) {
            if (p.getNumeroPedido() > 0 && p.getNumeroPedido() < usados.length) {
                usados[p.getNumeroPedido()] = true;
            }
        }

        for (int i = 1; i < usados.length; i++) {
            if (!usados[i]) {
                return i;
            }
        }

        return usados.length;
    }

    public int cantidadReservada(String productoId) {
        int total = 0;

        for (Pedido pedido : pedidosPorMesa.values()) {

            if (pedido == null) {
                continue;
            }

            for (var item : pedido.getItems()) {

                if (item.getProducto().getId().equals(productoId)) {
                    total += item.getCantidad();
                }
            }
        }

        return total;
    }
}
