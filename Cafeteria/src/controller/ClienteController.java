/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.util.Collection;
import model.Cliente;
import model.ClienteDAO;

/**
 * Controlador que administra las operaciones relacionadas con cliente.
 * Delega la persistencia al ClienteDAO.
 * 
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class ClienteController {

    private final ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    /**
     * Registra un nuevo cliente en el sistema.
     * @param cliente el cliente a registrar
     * @throws IllegalArgumentException si el cliente es nulo o ya existe
     */
    public void registrarCliente(Cliente cliente) {
        clienteDAO.guardar(cliente);
    }

    /**
     * Busca un cliente por su ID.
     * @param id la identificación del cliente
     * @return el cliente encontrado
     * @throws IllegalArgumentException si el cliente no existe
     */
    public Cliente buscarCliente(String id) {
        return clienteDAO.buscar(id);
    }

    /**
     * Modifica los datos de un cliente existente.
     * @param id la identificación del cliente
     * @param nombre el nuevo nombre
     * @param telefono el nuevo teléfono
     * @param correo el nuevo correo
     * @param direccion la nueva dirección
     * @throws IllegalArgumentException si el cliente no existe
     */
    public void modificarCliente(String id,
                                 String nombre,
                                 String telefono,
                                 String correo,
                                 String direccion) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("La identificación es obligatoria.");
        }

        Cliente cliente = clienteDAO.buscar(id);

        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        cliente.setCorreo(correo);
        cliente.setDireccion(direccion);

        clienteDAO.actualizar(cliente);
    }

    /**
     * Modifica los datos de un cliente existente incluyendo su frecuencia.
     * @param id la identificación del cliente
     * @param nombre el nuevo nombre
     * @param telefono el nuevo teléfono
     * @param correo el nuevo correo
     * @param direccion la nueva dirección
     * @param frecuencia la frecuencia del cliente (Frecuente, Poco Frecuente, Muy Poco Frecuente)
     * @throws IllegalArgumentException si el cliente no existe
     */
    public void modificarClienteConFrecuencia(String id,
                                              String nombre,
                                              String telefono,
                                              String correo,
                                              String direccion,
                                              String frecuencia) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("La identificación es obligatoria.");
        }

        Cliente cliente = clienteDAO.buscar(id);

        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        cliente.setCorreo(correo);
        cliente.setDireccion(direccion);
        cliente.setFrecuencia(frecuencia);

        clienteDAO.actualizar(cliente);
    }

    /**
     * Elimina un cliente del sistema.
     * @param id la identificación del cliente
     * @throws IllegalArgumentException si el cliente no existe
     */
    public void eliminarCliente(String id) {
        clienteDAO.eliminar(id);
    }

    /**
     * Obtiene todos los clientes registrados.
     * @return colección de todos los clientes
     */
    public Collection<Cliente> obtenerTodosLosClientes() {
        return clienteDAO.obtenerTodos();
    }

    /**
     * Verifica si existe un cliente con el ID especificado.
     * @param id la identificación a verificar
     * @return true si existe, false en caso contrario
     */
    public boolean clienteExiste(String id) {
        return clienteDAO.existe(id);
    }

    /**
     * Obtiene la cantidad total de clientes registrados.
     * @return número de clientes
     */
    public int contarClientes() {
        return clienteDAO.contar();
    }
}