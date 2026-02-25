/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.CategoriaDAO;
import model.CategoriaProducto;

import java.util.List;

/**
 * Controlador encargado de gestionar las operaciones relacionadas con las
 * categorías de productos. Actúa como intermediario entre la capa de
 * persistencia (CategoriaDAO) y las vistas, permitiendo consultar, registrar y
 * desactivar categorías dentro del sistema.
 */
public class CategoriaController {

    private CategoriaDAO categoriaDAO;

    /**
     * Crea un controlador de categorías utilizando el DAO proporcionado.
     *
     * @param categoriaDAO Objeto encargado del acceso a datos de categorías.
     */
    public CategoriaController(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    /**
     * Obtiene la lista de categorías activas disponibles en el sistema.
     *
     * @return Lista de categorías activas.
     */
    public List<CategoriaProducto> obtenerCategoriasActivas() {
        return categoriaDAO.listarActivas();
    }

    /**
     * Registra una nueva categoría en el sistema.
     *
     * @param nombre Nombre de la categoría a agregar.
     */
    public void agregarCategoria(String nombre) {
        categoriaDAO.agregarCategoria(nombre);
    }

    /**
     * Desactiva una categoría existente para que no pueda ser utilizada.
     *
     * @param nombre Nombre de la categoría a desactivar.
     */
    public void desactivarCategoria(String nombre) {
        categoriaDAO.desactivarCategoria(nombre);
    }
}
