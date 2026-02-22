package model;

import java.io.Serializable;

/**
 * Representa un producto del sistema POS.
 * Contiene información necesaria para venta, inventario y visualización.
 *
 * Incluye categoría, precio, stock e imagen.
 * Permite edición y persistencia.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class Producto implements Serializable {

    private String id;
    private String nombre;
    private CategoriaProducto categoria;
    private double precio;
    private int stock;
    private String rutaImagen; // solo nombre archivo
    private boolean activo;

    // ================= CONSTRUCTORES =================

    public Producto(String id, String nombre, CategoriaProducto categoria,
                    double precio, int stock, String rutaImagen, boolean activo) {

        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
        this.rutaImagen = rutaImagen;
        this.activo = activo;
    }

    public Producto(String id, String nombre, CategoriaProducto categoria,
                    double precio, int stock, String rutaImagen) {

        this(id, nombre, categoria, precio, stock, rutaImagen, true);
    }

    public Producto() {
        this.activo = true;
    }

    // ================= GETTERS =================

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public boolean isActivo() {
        return activo;
    }

    // ================= SETTERS =================

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    // ================= UTILIDADES =================

    public void desactivar() {
        this.activo = false;
    }

    public void activar() {
        this.activo = true;
    }

    // ================= VALIDACION BASICA =================

    public boolean tieneImagen() {
        return rutaImagen != null && !rutaImagen.isBlank();
    }

    // ================= OVERRIDES =================

    @Override
    public String toString() {
        return id + " - " + nombre;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Producto)) return false;

        Producto other = (Producto) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}