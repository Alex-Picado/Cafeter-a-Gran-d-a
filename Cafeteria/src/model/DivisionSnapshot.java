/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author eidan
 */
public class DivisionSnapshot {

    private static final String FILE = "division.snapshot";

    public void guardar(Map<String, DivisionCuentaPersistida> data) {
        try (ObjectOutputStream out =
                 new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, DivisionCuentaPersistida> cargar() {
        try (ObjectInputStream in =
                 new ObjectInputStream(new FileInputStream(FILE))) {
            return (Map<String, DivisionCuentaPersistida>) in.readObject();
        } catch (Exception e) {
            return new HashMap<>();
        }
    }
}
