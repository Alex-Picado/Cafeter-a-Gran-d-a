/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 * Representa un descuento aplicable a una factura o cuenta individual,
 * utilizado para ajustar el monto total según reglas de negocio.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class Descuento {
        private String nombre;
        private double porcentaje;

        public Descuento(String nombre, double porcentaje) {
            this.nombre = nombre;
            this.porcentaje = porcentaje;
        }

        public double getPorcentaje() {
            return porcentaje;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

