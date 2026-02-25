package controller;

import model.Producto;
import model.ProductoDAO;
import model.CategoriaProducto;
import view.PanelProductos;
import view.PanelProducto;

import javax.swing.*;
import java.util.List;
import view.MainFrame;

/**
 * Controlador encargado de coordinar las operaciones relacionadas con los
 * productos. Gestiona la comunicación entre la interfaz de usuario y el
 * acceso a datos, además de actualizar la vista según el estado del stock
 * y los pedidos activos.
 */
public class ProductoController {

    private ProductoDAO productoDAO;
    private PanelProductos panelProductos;

    public ProductoController(ProductoDAO productoDAO,
            PanelProductos panelProductos) {

        this.productoDAO = productoDAO;
        this.panelProductos = panelProductos;
    }

    /**
     * Carga los productos activos en la vista, calculando el stock disponible
     * considerando las cantidades reservadas en pedidos activos.
     */
    public void cargarProductosEnVista() {

        List<Producto> productos = productoDAO.listarActivos();

        JPanel listaPanel = panelProductos.getPanelLista();

        listaPanel.removeAll();

        MainFrame frame = (MainFrame) javax.swing.SwingUtilities.getWindowAncestor(panelProductos);
        var pedidoManager = frame.getPedidoActivoManager();

        for (Producto p : productos) {

            int reservado = pedidoManager.cantidadReservada(p.getId());
            int disponible = p.getStock() - reservado;

            PanelProducto tarjeta = new PanelProducto();
            tarjeta.setProducto(p);
            tarjeta.setStock(Math.max(disponible, 0));

            listaPanel.add(tarjeta);
        }

        listaPanel.revalidate();
        listaPanel.repaint();
    }

    /**
     * Busca productos por coincidencia parcial de identificador.
     *
     * @param id Texto de búsqueda.
     * @return Lista de productos encontrados.
     */
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

      /**
     * Obtiene todos los productos activos.
     *
     * @return Lista de productos.
     */
    public List<Producto> obtenerTodos() {
        return productoDAO.listarActivos();
    }

       /**
     * Actualiza la lista visual de productos en la interfaz.
     *
     * @param productos Productos a mostrar.
     */
    public void mostrarProductos(List<Producto> productos) {

        JPanel listaPanel = panelProductos.getPanelLista();

        listaPanel.removeAll();

        for (Producto p : productos) {

            PanelProducto tarjeta = new PanelProducto();
            tarjeta.setProducto(p);

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

        /**
     * Agrega un nuevo producto al sistema aplicando imagen por defecto si es necesario.
     *
     * @param producto Producto a registrar.
     */
    public void agregarProducto(Producto producto) {

        aplicarImagenDefaultSiNecesario(producto);

        productoDAO.agregarProducto(producto);
        cargarProductosEnVista();
    }

     /**
     * Actualiza los datos de un producto existente.
     *
     * @param producto Producto actualizado.
     */
    public void actualizarProducto(Producto producto) {

        aplicarImagenDefaultSiNecesario(producto);

        productoDAO.actualizarProducto(producto);
        cargarProductosEnVista();
    }

    /**
     * Persiste los cambios realizados en los productos.
     */
    public void guardarCambios() {
        productoDAO.guardarCambios();
    }

     /**
     * Busca un producto por su identificador.
     *
     * @param id Identificador del producto.
     * @return Producto encontrado o null.
     */
    public Producto buscarProducto(String id) {
        return productoDAO.buscarPorId(id);
    }

     /**
     * Elimina un producto del sistema.
     *
     * @param id Identificador del producto.
     */
    public void borrarProducto(String id) {
        productoDAO.borrarProducto(id);
        cargarProductosEnVista();
    }

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
