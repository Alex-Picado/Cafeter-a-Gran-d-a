package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FacturaDAO {

    private static final String ARCHIVO = "facturas.txt";

    public void guardar(Factura factura) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {

            bw.write("FACTURA|" + factura.getNumeroFactura() + "|"
                    + factura.getMesa() + "|"
                    + factura.getFechaHora() + "|"
                    + factura.getSubtotal() + "|"
                    + factura.getIva() + "|"
                    + factura.getTotal());

            bw.newLine();

            for (DetalleFactura d : factura.getDetalles()) {

                bw.write("ITEM|" + d.getProductoId() + "|"
                        + d.getNombreProducto() + "|"
                        + d.getCantidad() + "|"
                        + d.getPrecioUnitario());

                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error guardando factura: " + e.getMessage());
        }
    }
}