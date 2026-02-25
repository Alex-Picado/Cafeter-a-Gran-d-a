/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.Factura;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class VentaRepository {

    private static final String ARCHIVO = "ventas.txt";

    public void guardar(Factura factura) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO, true))) {

            bw.write("Factura: " + factura.getNumeroFactura());
            bw.newLine();

            bw.write("Fecha: " + factura.getFechaHora());
            bw.newLine();

            bw.write("Total: " + factura.getTotal());
            bw.newLine();

            bw.write("----------------------");
            bw.newLine();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
