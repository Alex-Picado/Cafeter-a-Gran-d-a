/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author eidan
 */
public class DivisionCuentaPersistida implements Serializable {
 
    private String mesa;
    private DivisionCuenta division;
    private Set<Integer> personasPagadas = new HashSet<>();

    public DivisionCuentaPersistida(String mesa, DivisionCuenta division) {
        this.mesa = mesa;
        this.division = division;
    }

    public String getMesa() {
        return mesa;
    }

    public DivisionCuenta getDivision() {
        return division;
    }

    public Set<Integer> getPersonasPagadas() {
        return personasPagadas;
    }

    public void marcarPagada(int persona) {
        personasPagadas.add(persona);
    }

    public boolean estaPagada(int persona) {
        return personasPagadas.contains(persona);
    }
    
    public boolean todasPagadas() {
        DivisionCuenta d = getDivision();
        for (int i = 1; i <= d.getNumeroPersonas(); i++) {
            if (!estaPagada(i)) {
                return false;
            }
        }
        return true;
    }
    
    public void sincronizarDesdePersistencia() {
        for (int persona : personasPagadas) {
            division.marcarPersonaPagada(persona);
        }
    }
    
}
