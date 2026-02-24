/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author cchin
 */
public class CategoriaDAO {
     private static final String ARCHIVO = "categorias.txt";

    private List<CategoriaProducto> categorias;

    public CategoriaDAO() {
        categorias = cargarDesdeArchivo();
    }

    // ================= CARGA =================

    private List<CategoriaProducto> cargarDesdeArchivo() {
        List<CategoriaProducto> lista = new ArrayList<>();
        File file = new File(ARCHIVO);

        if (!file.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");

                if (partes.length == 3) {
                    String id = partes[0];
                    String nombre = partes[1];
                    boolean activa = Boolean.parseBoolean(partes[2]);

                    lista.add(new CategoriaProducto(id, nombre, activa));
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo categorias: " + e.getMessage());
        }

        return lista;
    }

    // ================= GUARDAR =================

    private void guardarEnArchivo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (CategoriaProducto c : categorias) {
                bw.write(c.getId() + "|" + c.getNombre() + "|" + c.isActiva());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error guardando categorias: " + e.getMessage());
        }
    }

    // ================= CRUD =================

    public void agregarCategoria(String nombre) {
        String nuevoId = generarId();
        CategoriaProducto nueva = new CategoriaProducto(nuevoId, nombre, true);
        categorias.add(nueva);
        guardarEnArchivo();
    }

    public void desactivarCategoria(String nombre) {
        for (CategoriaProducto c : categorias) {
            if (c.getNombre().equals(nombre)) {
                c.setActiva(false);
                break;
            }
        }
        guardarEnArchivo();
    }

    public CategoriaProducto buscarPorId(String id) {
        for (CategoriaProducto c : categorias) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public List<CategoriaProducto> listarActivas() {
        List<CategoriaProducto> activas = new ArrayList<>();
        for (CategoriaProducto c : categorias) {
            if (c.isActiva()) activas.add(c);
        }
        return activas;
    }

    public List<CategoriaProducto> listarTodas() {
        return new ArrayList<>(categorias);
    }

    // ================= UTILIDAD =================

    private String generarId() {
        int max = 0;

        for (CategoriaProducto c : categorias) {
            try {
                int id = Integer.parseInt(c.getId());
                if (id > max) max = id;
            } catch (NumberFormatException ignored) {}
        }

        return String.valueOf(max + 1);
    }
}
