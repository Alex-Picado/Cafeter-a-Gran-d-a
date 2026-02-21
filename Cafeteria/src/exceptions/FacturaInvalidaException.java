/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 * Excepción lanzada cuando una factura presenta inconsistencias o no puede
 * generarse correctamente a partir de los datos disponibles.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class FacturaInvalidaException extends Exception {

    /**
     * Crea la excepción con un mensaje descriptivo.
     */
    public FacturaInvalidaException(String message) {
        super(message);
    }

    /**
     * Crea la excepción con mensaje y causa original.
     */
    public FacturaInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }

}
