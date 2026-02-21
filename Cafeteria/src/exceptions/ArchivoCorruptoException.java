/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 * Excepción lanzada cuando se detecta que un archivo de datos se encuentra
 * dañado o en un formato inesperado.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class ArchivoCorruptoException extends Exception {

    /**
     * Crea la excepción con un mensaje descriptivo.
     */
    public ArchivoCorruptoException(String message) {
        super(message);
    }

    /**
     * Crea la excepción con mensaje y causa original.
     */
    public ArchivoCorruptoException(String message, Throwable cause) {
        super(message, cause);
    }

}
