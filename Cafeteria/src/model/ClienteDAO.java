package model;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Capa de acceso a datos para Cliente.
 * Maneja la persistencia de clientes en archivo.
 * 
 * @author Eidan Alexandre Picado Leiva - C4I410
 */
public class ClienteDAO {

    private final Map<String, Cliente> clientes;
    private static final String ARCHIVO = "clientes.txt";

    public ClienteDAO() {
        this.clientes = new HashMap<>();
        cargarDesdeArchivo();
    }

    /**
     * Obtiene todos los clientes cargados en memoria.
     * @return colección de clientes
     */
    public Collection<Cliente> obtenerTodos() {
        return clientes.values();
    }

    /**
     * Busca un cliente por su ID.
     * @param id identificación del cliente
     * @return cliente encontrado
     * @throws IllegalArgumentException si el cliente no existe
     */
    public Cliente buscar(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("La identificación es obligatoria.");
        }

        Cliente cliente = clientes.get(id.trim());

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no encontrado con ID: " + id);
        }

        return cliente;
    }

    /**
     * Verifica si un cliente existe.
     * @param id identificación del cliente
     * @return true si existe, false en caso contrario
     */
    public boolean existe(String id) {
        return id != null && !id.isBlank() && clientes.containsKey(id.trim());
    }

    /**
     * Guarda un nuevo cliente.
     * @param cliente el cliente a guardar
     * @throws IllegalArgumentException si ya existe un cliente con ese ID
     */
    public void guardar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        if (clientes.containsKey(cliente.getId())) {
            throw new IllegalArgumentException("Ya existe un cliente con esa identificación: " + cliente.getId());
        }

        clientes.put(cliente.getId(), cliente);
        guardarEnArchivo();
    }

    /**
     * Actualiza un cliente existente.
     * @param cliente el cliente actualizado
     * @throws IllegalArgumentException si el cliente no existe
     */
    public void actualizar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo.");
        }

        if (!clientes.containsKey(cliente.getId())) {
            throw new IllegalArgumentException("Cliente no encontrado: " + cliente.getId());
        }

        clientes.put(cliente.getId(), cliente);
        guardarEnArchivo();
    }

    /**
     * Elimina un cliente.
     * @param id identificación del cliente
     * @throws IllegalArgumentException si el cliente no existe
     */
    public void eliminar(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("La identificación es obligatoria.");
        }

        if (!clientes.containsKey(id.trim())) {
            throw new IllegalArgumentException("Cliente no encontrado: " + id);
        }

        clientes.remove(id.trim());
        guardarEnArchivo();
    }

    /**
     * Obtiene la cantidad total de clientes.
     * @return número de clientes
     */
    public int contar() {
        return clientes.size();
    }

    /**
     * Guarda todos los clientes en archivo.
     */
    private void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Cliente cliente : clientes.values()) {
                writer.write(cliente.toDataString());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar los clientes: " + e.getMessage(), e);
        }
    }

    /**
     * Carga todos los clientes desde archivo.
     */
    private void cargarDesdeArchivo() {
        File file = new File(ARCHIVO);

        if (!file.exists()) {
            return; // Si no existe el archivo, simplemente no carga nada
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) continue; // Ignora líneas vacías

                String[] datos = linea.split(";", -1); // -1 para mantener campos vacíos

                if (datos.length >= 5) {
                    try {
                        String frecuencia = datos.length >= 6 ? datos[5].trim() : "Frecuente";
                        
                        Cliente cliente = new Cliente(
                                datos[0].trim(),
                                datos[1].trim(),
                                datos[2].trim(),
                                datos[3].trim(),
                                datos[4].trim(),
                                frecuencia
                        );
                        clientes.put(cliente.getId(), cliente);
                    } catch (IllegalArgumentException e) {
                        // Ignora líneas con datos inválidos
                        System.err.println("Línea ignorada (datos inválidos): " + linea);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al cargar los clientes: " + e.getMessage(), e);
        }
    }
}
