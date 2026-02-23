/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.HashMap;
import java.util.Map;
import model.Pedido;

public class PedidoActivoManager {

    private Map<String, Pedido> pedidosPorMesa = new HashMap<>();

    public Pedido obtenerOcrear(String mesa) {
        return pedidosPorMesa.computeIfAbsent(mesa, m -> new Pedido());
    }

    public Pedido obtener(String mesa) {
        return pedidosPorMesa.get(mesa);
    }

    public void eliminar(String mesa) {
        pedidosPorMesa.remove(mesa);
    }
}   