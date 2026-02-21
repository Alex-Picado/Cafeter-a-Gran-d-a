/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 * Excepción lanzada cuando ocurre un error durante la división de cuentas, como
 * asignaciones incompletas o inconsistentes.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class DivisionInvalidaException extends Exception {

    /**
     * Crea la excepción con un mensaje descriptivo.
     */
    public DivisionInvalidaException(String message) {
        super(message);
    }

    /**
     * Crea la excepción con mensaje y causa original.
     */
    public DivisionInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }

}
