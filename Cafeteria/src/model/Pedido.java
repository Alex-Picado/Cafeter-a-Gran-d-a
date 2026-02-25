/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import controller.PedidoActivoManager;
import java.time.LocalDateTime;
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
    private int numeroPedido;
    private LocalDateTime fechaCreacion;

    public Pedido() {
    }

    public List<DetallePedido> getItems() {
        return items;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
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

    public void inicializarSiEsNuevo(PedidoActivoManager manager) {
        if (numeroPedido == 0) {
            numeroPedido = manager.generarNumeroDisponible();
            fechaCreacion = java.time.LocalDateTime.now();
        }
    }

    public void resetearSiVacio() {
        if (items.isEmpty()) {
            numeroPedido = 0;
            fechaCreacion = null;
        }
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
