/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import java.time.LocalDateTime;
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

                if (pedido.getItems().isEmpty()) {
                    continue;
                }

                bw.write("PEDIDO|" + mesa + "|"
                        + pedido.getNumeroPedido() + "|"
                        + pedido.getFechaCreacion());

                bw.newLine();

                for (DetallePedido item : pedido.getItems()) {

                    bw.write("ITEM|"
                            + item.getProducto().getId() + "|"
                            + item.getCantidad());

                    bw.newLine();
                }

                bw.write("END");
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error guardando snapshot: " + e.getMessage());
        }
    }



    public Map<String, Pedido> cargar(ProductoDAO productoDAO) {

        Map<String, Pedido> pedidos = new HashMap<>();

        File file = new File(ARCHIVO);
        if (!file.exists()) {
            return pedidos;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String linea;
            Pedido pedidoActual = null;
            String mesaActual = null;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split("\\|");

                if (partes[0].equals("PEDIDO")) {

                    mesaActual = partes[1];

                    pedidoActual = new Pedido();

                    pedidoActual.setNumeroPedido(Integer.parseInt(partes[2]));
                    pedidoActual.setFechaCreacion(LocalDateTime.parse(partes[3]));

                    pedidos.put(mesaActual, pedidoActual);
                } else if (partes[0].equals("ITEM")) {

                    String productoId = partes[1];
                    int cantidad = Integer.parseInt(partes[2]);

                    Producto producto = productoDAO.buscarPorId(productoId);

                    for (int i = 0; i < cantidad; i++) {
                        pedidoActual.agregarProducto(producto);
                    }
                } else if (partes[0].equals("END")) {

                    pedidoActual = null;
                    mesaActual = null;
                }
            }

        } catch (IOException e) {
            System.out.println("Error cargando snapshot");
        }

        return pedidos;
    }
}
