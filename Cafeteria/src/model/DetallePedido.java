/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Representa un ítem dentro de un pedido, incluyendo producto y cantidad,
 * utilizado para construir el consumo antes de la facturación.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class DetallePedido {

    private Producto producto;
    private int cantidad;

    public DetallePedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void incrementarCantidad() {
        cantidad++;
    }

    public void decrementarCantidad() {
        if (cantidad > 0) {
            cantidad--;
        }
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
