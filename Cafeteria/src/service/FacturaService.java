/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import model.*;
/**
 *
 * @author eidan
 */
public class FacturaService {
    public Factura crearDesdePedido(Pedido pedido, String mesa) {

        String numeroFactura = generarNumeroFactura();

        Factura factura = new Factura(numeroFactura, mesa);

        for (DetallePedido item : pedido.getItems()) {

            Producto p = item.getProducto();

            DetalleFactura df = new DetalleFactura(
                    p.getId(),
                    p.getNombre(),
                    p.getPrecio(),
                    item.getCantidad()
            );

            factura.agregarDetalle(df);
        }

        return factura;
    }

    private String generarNumeroFactura() {
        return "F-" + System.currentTimeMillis();
    }
}
