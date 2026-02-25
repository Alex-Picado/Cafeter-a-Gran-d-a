/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Modela el proceso de división de un pedido en múltiples cuentas, permitiendo
 * asignar productos a diferentes personas.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class DivisionCuenta implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private Map<Integer, Map<String, Integer>> asignaciones = new HashMap<>();
    private transient java.util.Set<Integer> personasPagadas = new java.util.HashSet<>();

    private int numeroPersonas;

    public DivisionCuenta(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;

        for (int i = 1; i <= numeroPersonas; i++) {
            asignaciones.put(i, new HashMap<>());
        }
    }

    public void asignar(int persona, String productoId, int delta) {
        Map<String, Integer> mapa = asignaciones.get(persona);
        mapa.put(productoId, mapa.getOrDefault(productoId, 0) + delta);
    }

    public int getCantidad(int persona, String productoId) {
        return asignaciones.get(persona).getOrDefault(productoId, 0);
    }

    public Map<Integer, Map<String, Integer>> getAsignaciones() {
        return asignaciones;
    }

    public int totalAsignadoProducto(String productoId) {

        int total = 0;

        for (Map<String, Integer> mapa : asignaciones.values()) {
            total += mapa.getOrDefault(productoId, 0);
        }

        return total;
    }

    public void quitar(int persona, String productoId, int delta) {

        Map<String, Integer> mapa = asignaciones.get(persona);

        if (mapa == null) {
            return;
        }

        int actual = mapa.getOrDefault(productoId, 0) - delta;

        if (actual <= 0) {
            mapa.remove(productoId);
        } else {
            mapa.put(productoId, actual);
        }
    }

    public Map<String, Integer> getProductosPersona(int persona) {
        return asignaciones.computeIfAbsent(persona, k -> new HashMap<>());
    }

    public void limpiar() {
        asignaciones.clear();
    }

    public void ajustarPersonas(int nuevasPersonas) {

        if (nuevasPersonas > numeroPersonas) {

            for (int i = numeroPersonas + 1; i <= nuevasPersonas; i++) {
                asignaciones.put(i, new HashMap<>());
            }

        } else if (nuevasPersonas < numeroPersonas) {

            for (int i = numeroPersonas; i > nuevasPersonas; i--) {
                asignaciones.remove(i);
            }
        }

        numeroPersonas = nuevasPersonas;
    }

    public boolean tieneAsignaciones() {

        for (var mapa : asignaciones.values()) {

            for (int cantidad : mapa.values()) {

                if (cantidad > 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public void marcarPersonaPagada(int persona) {
        asegurarSet();
        personasPagadas.add(persona);
    }

    public boolean estaPagada(int persona) {
        asegurarSet();
        return personasPagadas.contains(persona);
    }

    private void asegurarSet() {
        if (personasPagadas == null) {
            personasPagadas = new java.util.HashSet<>();
        }
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

}
