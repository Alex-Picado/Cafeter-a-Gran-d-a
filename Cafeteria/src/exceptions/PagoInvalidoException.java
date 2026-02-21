/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 * Excepción lanzada cuando un pago no cumple con las condiciones necesarias
 * para ser procesado correctamente.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class PagoInvalidoException extends Exception {

    /**
     * Crea la excepción con un mensaje descriptivo.
     */
    public PagoInvalidoException(String message) {
        super(message);
    }

    /**
     * Crea la excepción con mensaje y causa original.
     */
    public PagoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }

}
