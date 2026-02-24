/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * Representa una factura generada a partir de un pedido,
 * incluyendo la información necesaria para registrar una venta.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class Factura {
    private String numeroFactura;
    private LocalDateTime fechaHora;
    private String mesa;
    private String cedulaCliente;

    private List<DetalleFactura> detalles = new ArrayList<>();

    private double subtotal;
    private double iva;
    private double descuento;
    private double total;
    
    public Factura(String numeroFactura, String mesa) {
        this.numeroFactura = numeroFactura;
        this.mesa = mesa;
        this.fechaHora = LocalDateTime.now();
    }
    public void agregarDetalle(DetalleFactura detalle) {
        detalles.add(detalle);
        recalcularTotales();
    }

    private void recalcularTotales() {

        subtotal = detalles.stream()
                .mapToDouble(DetalleFactura::getSubtotal)
                .sum();

        iva = subtotal * 0.13; // IVA CR 13%

        total = subtotal + iva - descuento;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getMesa() {
        return mesa;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIva() {
        return iva;
    }

    public double getDescuento() {
        return descuento;
    }

    public double getTotal() {
        return total;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
        recalcularTotales();
    }
    
}
