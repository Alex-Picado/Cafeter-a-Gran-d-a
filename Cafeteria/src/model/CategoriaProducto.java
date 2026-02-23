/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 * Representa una categoría de productos dentro del sistema POS.
 * Permite clasificar productos y gestionar categorías dinámicamente.
 * Incluye estado activo para permitir eliminación lógica sin perder historial.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class CategoriaProducto implements Serializable {

    private String id;
    private String nombre;
    private boolean activa;

    // ================= CONSTRUCTORES =================

    public CategoriaProducto(String id, String nombre, boolean activa) {
        this.id = id;
        this.nombre = nombre;
        this.activa = activa;
    }

    public CategoriaProducto(String id, String nombre) {
        this(id, nombre, true);
    }

    public CategoriaProducto() {
    }

    // ================= GETTERS =================

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isActiva() {
        return activa;
    }

    // ================= SETTERS =================

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    // ================= UTILIDADES =================

    /**
     * Permite que JComboBox muestre el nombre de la categoría.
     */
    @Override
    public String toString() {
        return nombre;
    }

    /**
     * Comparación basada en ID para evitar duplicados.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CategoriaProducto)) return false;

        CategoriaProducto other = (CategoriaProducto) obj;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }
}
