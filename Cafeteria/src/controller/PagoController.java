/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import exceptions.PagoInvalidoException;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.MetodoPago;
import model.Pago;

/**
 * Controlador encargado de gestionar el proceso de pago, coordinando la
 * validación y registro de transacciones asociadas a facturas.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class PagoController {

    private static final String ARCHIVO_PAGOS = "pagos_registro.txt";
    private final List<Pago> pagosRegistrados;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public PagoController() {
        this.pagosRegistrados = new ArrayList<>();
        cargarPagosDesdeArchivo();
    }

    /**
     * Procesa un pago validando todos los parámetros.
     * @param metodo el método de pago
     * @param monto el monto a pagar
     * @param montoRecibido el monto recibido
     * @return el objeto Pago creado
     * @throws PagoInvalidoException si algún parámetro es inválido
     */
    public Pago procesarPago(MetodoPago metodo, double monto, double montoRecibido)
            throws PagoInvalidoException {

        Pago pago = new Pago(metodo, monto, montoRecibido);
        registrarPago(pago);
        return pago;
    }

    /**
     * Procesa un pago cuando se utiliza tarjeta (sin vuelto obligatorios).
     * @param monto el monto a pagar
     * @return el objeto Pago creado
     * @throws PagoInvalidoException si el monto es inválido
     */
    public Pago procesarPagoTarjeta(double monto) throws PagoInvalidoException {
        return procesarPago(MetodoPago.TARJETA, monto, monto);
    }

    /**
     * Procesa un pago por SINPE.
     * @param monto el monto a pagar
     * @return el objeto Pago creado
     * @throws PagoInvalidoException si el monto es inválido
     */
    public Pago procesarPagoSinpe(double monto) throws PagoInvalidoException {
        return procesarPago(MetodoPago.SINPE, monto, monto);
    }

    /**
     * Procesa un pago por transferencia.
     * @param monto el monto a pagar
     * @return el objeto Pago creado
     * @throws PagoInvalidoException si el monto es inválido
     */
    public Pago procesarPagoTransferencia(double monto) throws PagoInvalidoException {
        return procesarPago(MetodoPago.TRANSFERENCIA, monto, monto);
    }

    /**
     * Procesa un pago en efectivo con vuelto.
     * @param monto el monto a pagar
     * @param montoRecibido el monto recibido en efectivo
     * @return el objeto Pago creado
     * @throws PagoInvalidoException si algún parámetro es inválido
     */
    public Pago procesarPagoEfectivo(double monto, double montoRecibido) throws PagoInvalidoException {
        return procesarPago(MetodoPago.EFECTIVO, monto, montoRecibido);
    }

    /**
     * Obtiene el vuelto de un pago en efectivo.
     * @param pago el pago realizado
     * @return el monto del vuelto
     */
    public double calcularVuelto(Pago pago) {
        return pago.getVuelto();
    }

    /**
     * Registra un pago en el archivo de historial.
     * @param pago el pago a registrar
     */
    private void registrarPago(Pago pago) {
        pagosRegistrados.add(pago);
        guardarPagosEnArchivo();
    }

    /**
     * Obtiene todos los pagos registrados en memoria.
     * @return lista de pagos
     */
    public List<Pago> obtenerTodosLosPagos() {
        return new ArrayList<>(pagosRegistrados);
    }

    /**
     * Obtiene el total de pagos registrados.
     * @return número de pagos
     */
    public int contarPagos() {
        return pagosRegistrados.size();
    }

    /**
     * Guarda los pagos registrados en archivo.
     */
    private void guardarPagosEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_PAGOS, true))) {

            for (Pago pago : pagosRegistrados) {
                // Formato: METODO|MONTO|MONTO_RECIBIDO|VUELTO|FECHA_HORA
                String linea = pago.getMetodo() + "|"
                        + pago.getMonto() + "|"
                        + pago.getMontoRecibido() + "|"
                        + pago.getVuelto() + "|"
                        + LocalDateTime.now().format(formatter);

                writer.write(linea);
                writer.newLine();
            }

            pagosRegistrados.clear(); // Limpia la lista de memoria después de guardar

        } catch (IOException e) {
            throw new RuntimeException("Error al guardar los pagos: " + e.getMessage(), e);
        }
    }

    /**
     * Carga los pagos desde el archivo al iniciar.
     */
    private void cargarPagosDesdeArchivo() {
        File file = new File(ARCHIVO_PAGOS);

        if (!file.exists()) {
            return; // Si no existe el archivo, no hay nada que cargar
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String linea;

            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] datos = linea.split("\\|");

                if (datos.length >= 4) {
                    try {
                        MetodoPago metodo = MetodoPago.valueOf(datos[0].trim());
                        double monto = Double.parseDouble(datos[1].trim());
                        double montoRecibido = Double.parseDouble(datos[2].trim());

                        Pago pago = new Pago(metodo, monto, montoRecibido);
                        pagosRegistrados.add(pago);

                    } catch (IllegalArgumentException | PagoInvalidoException e) {
                        System.err.println("Línea de pago ignorada (formato inválido): " + linea);
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al cargar los pagos: " + e.getMessage(), e);
        }
    }

    /**
     * Obtiene un resumen de pagos por método.
     * @return cadena con estadísticas de pagos
     */
    public String obtenerResumenPagos() {
        return "Total de pagos: " + contarPagos();
    }
}
