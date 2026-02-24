/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;

public class PedidoNumeroGenerator {

    private static final String ARCHIVO = "pedido_numero.txt";

    public static synchronized int siguienteNumero() {

        int ultimo = leerUltimo();
        int nuevo = ultimo + 1;

        guardarNuevo(nuevo);

        return nuevo;
    }

    private static int leerUltimo() {

        File file = new File(ARCHIVO);

        if (!file.exists()) return 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String linea = reader.readLine();

            if (linea == null || linea.isBlank()) return 0;

            return Integer.parseInt(linea.trim());

        } catch (Exception e) {
            throw new RuntimeException("Error leyendo número de pedido", e);
        }
    }

    private static void guardarNuevo(int numero) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {

            writer.write(String.valueOf(numero));

        } catch (IOException e) {
            throw new RuntimeException("Error guardando número de pedido", e);
        }
    }
}