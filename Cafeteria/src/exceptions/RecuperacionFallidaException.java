/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 * Excepción lanzada cuando falla el proceso de recuperación del estado del
 * sistema tras un reinicio o interrupción inesperada.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class RecuperacionFallidaException extends Exception{

    /**
     * Crea la excepción con un mensaje descriptivo.
     */
    public RecuperacionFallidaException(String message) {
        super(message);
    }
    
    /**
     * Crea la excepción con mensaje y causa original.
     */
    public RecuperacionFallidaException(String message, Throwable cause) {
        super(message, cause);
    }
}
