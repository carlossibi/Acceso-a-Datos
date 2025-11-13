package inventario.model.dao;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegistroOperaciones {
    private static final String RUTA_REGISTRO = "recursos/registro.txt";
    private static final DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void registrar(String operacion) {
        String fechaHora = LocalDateTime.now().format(formatoFechaHora);
        String linea = fechaHora + " - " + operacion;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_REGISTRO, true))) {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al registrar operación: " + e.getMessage());
        }
    }

    public static List<String> obtenerHistorialCompleto() {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_REGISTRO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (IOException e) {
            System.err.println("Error al leer historial de operaciones: " + e.getMessage());
        }
        return lineas;
    }
}
