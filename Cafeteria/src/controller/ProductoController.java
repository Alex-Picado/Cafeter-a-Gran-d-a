package controller;

import model.Producto;
import model.ProductoDAO;
import model.CategoriaProducto;
import view.PanelProductos;
import view.PanelProducto;

import javax.swing.*;
import java.util.List;

/**
 * Controlador encargado de coordinar operaciones sobre productos. Maneja la
 * comunicación entre la vista y el DAO.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class ProductoController {

    private ProductoDAO productoDAO;
    private PanelProductos panelProductos;

    public ProductoController(ProductoDAO productoDAO,
            PanelProductos panelProductos) {

        this.productoDAO = productoDAO;
        this.panelProductos = panelProductos;
    }

    // ================= CARGAR LISTA =================
    public void cargarProductosEnVista() {

        List<Producto> productos = productoDAO.listarActivos();

        JPanel listaPanel = panelProductos.getPanelLista();

        listaPanel.removeAll();

        for (Producto p : productos) {

            PanelProducto tarjeta = new PanelProducto();

            tarjeta.setNombre(p.getNombre());
            tarjeta.setPrecio(p.getPrecio());
            tarjeta.setStock(p.getStock());
            tarjeta.setId(p.getId());
            tarjeta.setImagen(p.getRutaImagen());

            listaPanel.add(tarjeta);
        }

        listaPanel.revalidate();
        listaPanel.repaint();
    }

    public List<Producto> buscarPorIdParcial(String id) {

        if (id == null || id.isBlank()) {
            return productoDAO.listarActivos();
        }

        Producto p = productoDAO.buscarPorId(id);

        if (p != null) {
            return List.of(p);
        }

        return List.of();
    }

    public List<Producto> obtenerTodos() {
        return productoDAO.listarActivos();
    }

    public void mostrarProductos(List<Producto> productos) {

        JPanel listaPanel = panelProductos.getPanelLista();

        listaPanel.removeAll();

        for (Producto p : productos) {

            PanelProducto tarjeta = new PanelProducto();

            tarjeta.setNombre(p.getNombre());
            tarjeta.setPrecio(p.getPrecio());
            tarjeta.setStock(p.getStock());
            tarjeta.setId(p.getId());
            tarjeta.setImagen(p.getRutaImagen());

            listaPanel.add(tarjeta);
        }

        listaPanel.revalidate();
        listaPanel.repaint();
    }

    public List<Producto> buscarPorIdExacto(String id) {

        if (id == null || id.isBlank()) {
            return productoDAO.listarActivos();
        }

        Producto p = productoDAO.buscarPorId(id);

        if (p != null) {
            return List.of(p);
        }

        return List.of();
    }

    public List<Producto> buscarPorCategoria(CategoriaProducto categoria) {

        return productoDAO.listarActivos()
                .stream()
                .filter(p -> p.getCategoria().equals(categoria))
                .toList();
    }

    // ================= AGREGAR =================
    public void agregarProducto(Producto producto) {

        aplicarImagenDefaultSiNecesario(producto);

        productoDAO.agregarProducto(producto);
        cargarProductosEnVista();
    }

    // ================= ACTUALIZAR =================
    public void actualizarProducto(Producto producto) {

        aplicarImagenDefaultSiNecesario(producto);

        productoDAO.actualizarProducto(producto);
        cargarProductosEnVista();
    }

    // ================= BUSCAR =================
    public Producto buscarProducto(String id) {
        return productoDAO.buscarPorId(id);
    }

    public void desactivarProducto(String id) {
        productoDAO.desactivarProducto(id);
        cargarProductosEnVista();
    }

    // ================= LOGICA IMAGEN =================
    private void aplicarImagenDefaultSiNecesario(Producto producto) {

        if (producto.getRutaImagen() == null || producto.getRutaImagen().isBlank()) {

            String defaultImg = obtenerImagenDefault(producto.getCategoria());
            producto.setRutaImagen(defaultImg);
        }
    }

    private String obtenerImagenDefault(CategoriaProducto categoria) {

        if (categoria == null || categoria.getNombre() == null) {
            return "imgProductoDefault.png";
        }

        String nombre = categoria.getNombre().toLowerCase();

        if (nombre.contains("cafe")) {
            return "imgProductoCafeDefault.png";
        }

        if (nombre.contains("reposteria")) {
            return "imgProductoReposteriaDefault.png";
        }

        if (nombre.contains("bebida")) {
            return "imgProductoBebidaDefault.png";
        }

        return "imgProductoDefault.png";
    }
}
