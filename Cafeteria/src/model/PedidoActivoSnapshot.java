/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import java.util.*;

/**
 * Representa una captura del estado de los pedidos activos, utilizada para
 * recuperación ante fallos o reinicio del sistema.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class PedidoActivoSnapshot {
private static final String ARCHIVO = "pedidos_activos.txt";

    public void guardar(Map<String, Pedido> pedidos) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {

            for (var entry : pedidos.entrySet()) {

                String mesa = entry.getKey();
                Pedido pedido = entry.getValue();

                for (DetallePedido item : pedido.getItems()) {

                    bw.write(mesa + "|"
                            + item.getProducto().getId() + "|"
                            + item.getCantidad());

                    bw.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error guardando snapshot: " + e.getMessage());
        }
    }

    public Map<String, List<String[]>> cargarRaw() {

        Map<String, List<String[]>> data = new HashMap<>();

        File file = new File(ARCHIVO);

        if (!file.exists()) return data;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split("\\|");

                if (partes.length != 3) continue;

                data.computeIfAbsent(partes[0], k -> new ArrayList<>())
                        .add(partes);
            }

        } catch (IOException e) {
            System.out.println("Error leyendo snapshot");
        }

        return data;
    }
}
