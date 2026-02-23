package model;

import java.io.Serializable;
import exceptions.PagoInvalidoException;

/**
 * Representa un pago realizado sobre una factura.
 */
public class Pago implements Serializable {

    private final MetodoPago metodo;
    private final double monto;
    private final double montoRecibido;
    private final double vuelto;

    public Pago(MetodoPago metodo, double monto, double montoRecibido)
            throws PagoInvalidoException {

        if (metodo == null)
            throw new PagoInvalidoException("Método de pago inválido");

        if (monto <= 0)
            throw new PagoInvalidoException("Monto inválido");

        if (montoRecibido < monto)
            throw new PagoInvalidoException("Monto recibido insuficiente");

        this.metodo = metodo;
        this.monto = monto;
        this.montoRecibido = montoRecibido;
        this.vuelto = montoRecibido - monto;
    }

    public MetodoPago getMetodo() {
        return metodo;
    }

    public double getMonto() {
        return monto;
    }

    public double getMontoRecibido() {
        return montoRecibido;
    }

    public double getVuelto() {
        return vuelto;
    }
}