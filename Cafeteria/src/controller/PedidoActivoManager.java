/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import model.Pedido;
import model.PedidoActivoSnapshot;

/**
 * Gestiona los pedidos activos asociados a cada mesa. Permite crear, recuperar
 * y eliminar pedidos, así como persistir su estado mediante snapshots para
 * garantizar la recuperación del sistema tras reinicios.
 */
public class PedidoActivoManager {

    private Map<String, Pedido> pedidosPorMesa = new HashMap<>();
    private PedidoActivoSnapshot snapshot = new PedidoActivoSnapshot();

    /**
     * Obtiene el pedido asociado a una mesa o lo crea si no existe.
     *
     * @param mesa Identificador de la mesa.
     * @return Pedido activo de la mesa.
     */
    public Pedido obtenerOcrear(String mesa) {
        return pedidosPorMesa.computeIfAbsent(mesa, m -> new Pedido());
    }

    /**
     * Obtiene el pedido existente de una mesa.
     *
     * @param mesa Identificador de la mesa.
     * @return Pedido o null si no existe.
     */
    public Pedido obtener(String mesa) {
        return pedidosPorMesa.get(mesa);
    }

    /**
     * Elimina el pedido activo de una mesa.
     *
     * @param mesa Identificador de la mesa.
     */
    public void eliminar(String mesa) {
        pedidosPorMesa.remove(mesa);
    }

    /**
     * Persiste el estado actual de los pedidos activos.
     */
    public void guardarSnapshot() {
        snapshot.guardar(pedidosPorMesa);
    }

    public void setPedidos(Map<String, Pedido> pedidos) {
        this.pedidosPorMesa = pedidos;
    }

    public Map<String, Pedido> getPedidos() {
        return pedidosPorMesa;
    }

    /**
     * Genera un número de pedido disponible que no esté en uso.
     *
     * @return Número de pedido libre.
     */
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

    /**
     * Calcula la cantidad total de un producto reservada en todos los pedidos.
     *
     * @param productoId Identificador del producto.
     * @return Cantidad reservada.
     */
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
