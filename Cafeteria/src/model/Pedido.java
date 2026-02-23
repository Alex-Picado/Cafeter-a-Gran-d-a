/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un pedido realizado en el sistema, incluyendo los productos
 * solicitados y su estado antes de la facturación.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class Pedido {

    private List<DetallePedido> items = new ArrayList<>();

    public List<DetallePedido> getItems() {
        return items;
    }

    public void agregarProducto(Producto producto) {

        for (DetallePedido item : items) {
            if (item.getProducto().getId().equals(producto.getId())) {
                item.incrementarCantidad();
                return;
            }
        }

        items.add(new DetallePedido(producto, 1));
    }
}
