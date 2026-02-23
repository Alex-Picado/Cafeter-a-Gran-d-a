/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 * Excepción lanzada cuando ocurre un problema durante operaciones de
 * almacenamiento o lectura de datos en archivos o medios persistentes.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class ErrorPersistenciaException extends Exception {

    /**
     * Crea la excepción con un mensaje descriptivo.
     */
    public ErrorPersistenciaException(String message) {
        super(message);
    }

    /**
     * Crea la excepción con mensaje y causa original.
     */
    public ErrorPersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }

}
