/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Representa la relación entre una factura y los productos incluidos en ella,
 * almacenando la información detallada de cada ítem facturado.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class DetalleFactura {

    private String productoId;
    private String nombreProducto;
    private double precioUnitario;
    private int cantidad;

    public DetalleFactura(String productoId,
            String nombreProducto,
            double precioUnitario,
            int cantidad) {

        this.productoId = productoId;
        this.nombreProducto = nombreProducto;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    public String getProductoId() {
        return productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return precioUnitario * cantidad;
    }

}
