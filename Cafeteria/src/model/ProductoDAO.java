/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import model.CategoriaProducto;
import model.Producto;

/**
 *
 * @author cchin
 */
public class ProductoDAO {

    private static final String ARCHIVO = "productos.txt";

    private List<Producto> productos;
    private CategoriaDAO categoriaDAO;

    public ProductoDAO() {
        categoriaDAO = new CategoriaDAO();
        productos = cargarDesdeArchivo();
    }

    private List<Producto> cargarDesdeArchivo() {
        List<Producto> lista = new ArrayList<>();
        File file = new File(ARCHIVO);

        if (!file.exists()) {
            return lista;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split("\\|");

                if (partes.length == 7) {

                    String id = partes[0];
                    String nombre = partes[1];
                    String categoriaId = partes[2];
                    double precio = Double.parseDouble(partes[3]);
                    int stock = Integer.parseInt(partes[4]);
                    String rutaImagen = partes[5];
                    boolean activo = Boolean.parseBoolean(partes[6]);

                    CategoriaProducto categoria
                            = categoriaDAO.buscarPorId(categoriaId);

                    Producto p = new Producto(id, nombre, categoria,
                            precio, stock, rutaImagen, activo);

                    lista.add(p);
                }
            }

        } catch (IOException e) {
            System.out.println("Error leyendo productos: " + e.getMessage());
        }

        return lista;
    }

    private void guardarEnArchivo() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {

            for (Producto p : productos) {

                String categoriaId = p.getCategoria() != null
                        ? p.getCategoria().getId()
                        : "";

                bw.write(p.getId() + "|"
                        + p.getNombre() + "|"
                        + categoriaId + "|"
                        + p.getPrecio() + "|"
                        + p.getStock() + "|"
                        + p.getRutaImagen() + "|"
                        + p.isActivo());

                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error guardando productos: " + e.getMessage());
        }
    }

    public void guardarCambios() {
        guardarEnArchivo();
    }

    public void agregarProducto(Producto producto) {

        if (buscarPorIdIncluyendoInactivos(producto.getId()) != null) {
            throw new RuntimeException("Ya existe un producto con ese ID");
        }

        productos.add(producto);
        guardarEnArchivo();
    }

    public void actualizarProducto(Producto productoActualizado) {

        for (int i = 0; i < productos.size(); i++) {

            if (productos.get(i).getId()
                    .equals(productoActualizado.getId())) {

                productos.set(i, productoActualizado);
                break;
            }
        }

        guardarEnArchivo();
    }

    public Producto buscarPorId(String id) {

        for (Producto p : productos) {
            if (p.getId().equals(id) && p.isActivo()) {
                return p;
            }
        }

        return null;
    }

    public List<Producto> listarActivos() {

        List<Producto> lista = new ArrayList<>();

        for (Producto p : productos) {
            if (p.isActivo()) {
                lista.add(p);
            }
        }

        return lista;
    }

    public Producto buscarPorIdIncluyendoInactivos(String id) {

        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }

        return null;
    }

    public void borrarProducto(String id) {
        for (Producto p : productos) {

            if (p.getId().equals(id)) {
                productos.remove(p);
                break;
            }
        }

        guardarEnArchivo();
        productos = cargarDesdeArchivo();
    }

}
