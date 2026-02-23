package model;

import java.io.Serializable;

/**
 * Representa un cliente del sistema.
 */
public class Cliente implements Serializable, Persistible {

    private static final long serialVersionUID = 1L;

    private final String id;

    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;
    private String frecuencia;

    public Cliente(String id,
            String nombre,
            String telefono,
            String correo,
            String direccion) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID inválido");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre inválido");
        }

        this.id = id.trim();
        this.nombre = nombre.trim();
        this.telefono = telefono == null ? "" : telefono.trim();
        this.correo = correo == null ? "" : correo.trim();
        this.direccion = direccion == null ? "" : direccion.trim();
        this.frecuencia = "Frecuente"; // Valor por defecto
    }

    public Cliente(String id,
            String nombre,
            String telefono,
            String correo,
            String direccion,
            String frecuencia) {

        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID inválido");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre inválido");
        }

        this.id = id.trim();
        this.nombre = nombre.trim();
        this.telefono = telefono == null ? "" : telefono.trim();
        this.correo = correo == null ? "" : correo.trim();
        this.direccion = direccion == null ? "" : direccion.trim();
        this.frecuencia = frecuencia == null ? "Frecuente" : frecuencia.trim();
    }

    // =========================
    // GETTERS
    // =========================
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    // =========================
    // SETTERS CONTROLADOS
    // =========================
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre inválido");
        }
        this.nombre = nombre.trim();
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono == null ? "" : telefono.trim();
    }

    public void setCorreo(String correo) {
        this.correo = correo == null ? "" : correo.trim();
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion == null ? "" : direccion.trim();
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia == null ? "Frecuente" : frecuencia.trim();
    }

    // =========================
    // PERSISTENCIA
    // =========================
    @Override
    public String toDataString() {
        return id + ";"
                + nombre + ";"
                + telefono + ";"
                + correo + ";"
                + direccion + ";"
                + frecuencia;
    }

    // =========================
    // OVERRIDES IMPORTANTES
    // =========================
    @Override
    public String toString() {
        return "Cliente{"
                + "id='" + id + '\''
                + ", nombre='" + nombre + '\''
                + ", telefono='" + telefono + '\''
                + ", correo='" + correo + '\''
                + ", direccion='" + direccion + '\''
                + ", frecuencia='" + frecuencia + '\''
                + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) obj;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
