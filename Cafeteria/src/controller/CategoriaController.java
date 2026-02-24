/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.CategoriaDAO;
import model.CategoriaProducto;

import java.util.List;

public class CategoriaController {

    private CategoriaDAO categoriaDAO;

    public CategoriaController(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    public List<CategoriaProducto> obtenerCategoriasActivas() {
        return categoriaDAO.listarActivas();
    }

    public void agregarCategoria(String nombre) {
        categoriaDAO.agregarCategoria(nombre);
    }

    public void desactivarCategoria(String nombre) {
        categoriaDAO.desactivarCategoria(nombre);
    }
}

