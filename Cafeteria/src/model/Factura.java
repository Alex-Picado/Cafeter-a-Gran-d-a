/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa una factura generada a partir de un pedido, incluyendo la
 * información necesaria para registrar una venta.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class Factura {

    private String numeroFactura;
    private LocalDateTime fechaHora;
    private String mesa;
    private String cedulaCliente;
    private String metodoPago;

    private List<DetalleFactura> detalles = new ArrayList<>();

    private double subtotal;
    private double iva;
    private double descuento;
    private double total;
    private double montoRecibido;
    private double vuelto;
    private boolean pagada = false;

    public Factura(String numeroFactura, String mesa) {
        this.numeroFactura = numeroFactura;
        this.mesa = mesa;
        this.fechaHora = LocalDateTime.now();
        this.cedulaCliente = "NO IDENTIFICADO";
    }

    public void agregarDetalle(DetalleFactura detalle) {
        if (pagada) {
            throw new IllegalStateException("La factura ya está pagada y no puede modificarse");
        }
        detalles.add(detalle);
        recalcularTotales();
    }

    private void recalcularTotales() {

        subtotal = detalles.stream()
                .mapToDouble(DetalleFactura::getSubtotal)
                .sum();

        iva = subtotal * 0.13; // IVA CR 13%

        double totalAntesDescuento = subtotal + iva;
        double montoDescuento = totalAntesDescuento * (descuento / 100.0);

        total = totalAntesDescuento - montoDescuento;
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
        if (pagada) {
            throw new IllegalStateException("La factura ya está pagada");
        }

        this.cedulaCliente = cedulaCliente;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setDescuento(double descuento) {
        if (pagada) {
            throw new IllegalStateException("La factura ya está pagada");
        }

        this.descuento = descuento;
        recalcularTotales();
    }

    public void setMetodoPago(String metodoPago) {
        if (pagada) {
            throw new IllegalStateException("La factura ya está pagada");
        }
        this.metodoPago = metodoPago;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMontoRecibido(double montoRecibido) {
        this.montoRecibido = montoRecibido;
    }

    public double getMontoRecibido() {
        return montoRecibido;
    }

    public void setVuelto(double vuelto) {
        this.vuelto = vuelto;
    }

    public double getVuelto() {
        return vuelto;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void marcarComoPagada() {
        this.pagada = true;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void setTotalesDirectos(double subtotal, double iva, double descuento, double total) {
        this.subtotal = subtotal;
        this.iva = iva;
        this.descuento = descuento;
        this.total = total;
    }

}
