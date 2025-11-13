package inventario.model.dao.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerUtil {

    private static final String RUTA_LOG = "inventario_errores.log";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Método para registrar errores
    public static void logError(String mensaje, Exception e) {
        try (FileWriter fw = new FileWriter(RUTA_LOG, true);
             PrintWriter pw = new PrintWriter(fw)) {

            String fecha = LocalDateTime.now().format(FORMATO_FECHA);
            pw.println("[" + fecha + "] ERROR: " + mensaje);
            if (e != null) {
                e.printStackTrace(pw);
            }

        } catch (IOException io) {
            System.err.println("No se pudo escribir en el log: " + io.getMessage());
        }
    }
}
