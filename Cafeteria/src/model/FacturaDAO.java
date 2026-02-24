package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class FacturaDAO {

    private static final String ARCHIVO = "facturas.txt";

    public void guardar(Factura factura) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {

            writer.write("FACTURA|"
                    + factura.getNumeroFactura() + "|"
                    + factura.getFechaHora() + "|"
                    + factura.getMesa() + "|"
                    + (factura.getCedulaCliente() == null ? "NO_IDENTIFICADO" : factura.getCedulaCliente()) + "|"
                    + (factura.getMetodoPago() == null ? "DESCONOCIDO" : factura.getMetodoPago()) + "|"
                    + factura.getSubtotal() + "|"
                    + factura.getIva() + "|"
                    + factura.getDescuento() + "|"
                    + factura.getTotal() + "|"
                    + factura.getMontoRecibido() + "|"
                    + factura.getVuelto());
            writer.newLine();

            for (DetalleFactura d : factura.getDetalles()) {

                writer.write("DETALLE|"
                        + d.getProductoId() + "|"
                        + d.getNombreProducto() + "|"
                        + d.getPrecioUnitario() + "|"
                        + d.getCantidad() + "|"
                        + d.getSubtotal());
                writer.newLine();
            }

            writer.write("END");
            writer.newLine();

        } catch (IOException e) {
            throw new RuntimeException("Error guardando factura", e);
        }
    }

    public List<Factura> obtenerTodas() {

        List<Factura> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {

            String linea;
            Factura facturaActual = null;

            while ((linea = br.readLine()) != null) {

                if (linea.isBlank()) {
                    continue;
                }

                String[] p = linea.split("\\|");

                switch (p[0]) {

                    case "FACTURA":

                        facturaActual = new Factura(p[1], p[3]);

                        facturaActual.setFechaHora(LocalDateTime.parse(p[2]));
                        facturaActual.setCedulaCliente(p[4]);
                        facturaActual.setMetodoPago(p[5]);

                        facturaActual.setTotalesDirectos(
                                Double.parseDouble(p[6]),
                                Double.parseDouble(p[7]),
                                Double.parseDouble(p[8]),
                                Double.parseDouble(p[9])
                        );
                        facturaActual.setMontoRecibido(Double.parseDouble(p[10]));
                        facturaActual.setVuelto(Double.parseDouble(p[11]));

                        break;

                    case "DETALLE":

                        if (facturaActual != null) {

                            DetalleFactura d = new DetalleFactura(
                                    p[1],
                                    p[2],
                                    Double.parseDouble(p[3]),
                                    Integer.parseInt(p[4])
                            );

                            facturaActual.getDetalles().add(d);
                        }

                        break;

                    case "END":

                        if (facturaActual != null) {
                            lista.add(facturaActual);
                            facturaActual = null;
                        }

                        break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

}
