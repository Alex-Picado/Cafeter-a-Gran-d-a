/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

/**
 * Excepción lanzada cuando se intenta acceder a un pedido que no existe o no
 * puede ser localizado en el sistema.
 *
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class PedidoNoEncontradoException extends Exception {

    /**
     * Crea la excepción con un mensaje descriptivo.
     */
    public PedidoNoEncontradoException(String message) {
        super(message);
    }

    /**
     * Crea la excepción con mensaje y causa original.
     */
    public PedidoNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

}
