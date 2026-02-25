/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Map;

/**
 *
 * @author eidan
 */
public class DivisionManager {

    private Map<String, DivisionCuentaPersistida> divisiones;
    private DivisionSnapshot snapshot = new DivisionSnapshot();

    public DivisionManager() {
        divisiones = snapshot.cargar();
    }

    public DivisionCuentaPersistida obtenerOcrear(String mesa, int personas) {

        var persistida = divisiones.computeIfAbsent(mesa, m
                -> new DivisionCuentaPersistida(mesa, new DivisionCuenta(personas)));
        persistida.sincronizarDesdePersistencia();
        
        return persistida;
    }
    
    public DivisionCuentaPersistida obtener(String mesa) {
        var persistida = divisiones.get(mesa);

        if (persistida != null) {
            persistida.sincronizarDesdePersistencia();
        }
        
        return persistida;
    }

    public void marcarPersonaPagada(String mesa, int persona) {

        DivisionCuentaPersistida d = divisiones.get(mesa);

        if (d == null) {
            return;
        }

        d.marcarPagada(persona);

        guardar();
    }

    public void guardar() {
        snapshot.guardar(divisiones);
    }

    public void eliminar(String mesa) {
        divisiones.remove(mesa);
        guardar();
    }
    
    public boolean todasPagadas(String mesa) {

    DivisionCuentaPersistida p = divisiones.get(mesa);

    if (p == null) return false;

    DivisionCuenta d = p.getDivision();

    for (int i = 1; i <= d.getNumeroPersonas(); i++) {
        if (!d.estaPagada(i)) {
            return false;
        }
    }

    return true;
}
}
