/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.*;
import java.util.*;

/**
 * Controlador responsable de la recuperación del estado del sistema tras un
 * reinicio o fallo, restaurando pedidos activos y datos necesarios.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class RecuperacionController {

    private PedidoActivoSnapshot snapshot;
    private ProductoDAO productoDAO;

    public RecuperacionController(PedidoActivoSnapshot snapshot,
            ProductoDAO productoDAO) {
        this.snapshot = snapshot;
        this.productoDAO = productoDAO;
    }

    /**
     * Recupera los pedidos activos almacenados en el snapshot utilizando la
     * información de productos disponible.
     *
     * @return Mapa de pedidos por mesa.
     */
    public Map<String, Pedido> recuperar() {
        return snapshot.cargar(productoDAO);
    }
}
