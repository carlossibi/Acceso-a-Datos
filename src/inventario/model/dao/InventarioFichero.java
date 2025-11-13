package inventario.model.dao;

import inventario.model.Producto;
import inventario.model.Categoria;
import inventario.model.Movimiento;
import inventario.model.dao.util.LoggerUtil;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class InventarioFichero {

    private final String rutaArchivo = "recursos/inventario.txt";
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public InventarioFichero() {
        try {
            File file = new File(rutaArchivo);
            if (!file.exists()) file.createNewFile();
        } catch (IOException e) {
            LoggerUtil.logError("Error creando archivo inventario.txt", e);
        }
    }

    // Lee todos los datos del inventario: productos, categorías y movimientos
    public List<Object> cargarInventarioCompleto() throws IOException {
        List<Object> elementos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] campos = linea.split(";");
                String tipo = campos[0];
                switch (tipo) {
                    case "P":
                        Producto p = new Producto(
                            Integer.parseInt(campos[1]), campos[2], Integer.parseInt(campos[3]),
                            Double.parseDouble(campos[4]), Integer.parseInt(campos[5]), Integer.parseInt(campos[6])
                        );
                        elementos.add(p);
                        break;
                    case "C":
                        Categoria c = new Categoria(
                            Integer.parseInt(campos[1]), campos[2]
                        );
                        elementos.add(c);
                        break;
                    case "M":
                        Date fecha;
                        try {
                            fecha = sdf.parse(campos[6]);
                        } catch (Exception e) {
                            fecha = new Date();
                            LoggerUtil.logError("Error parsing fecha movimiento", e);
                        }
                        Movimiento m = new Movimiento(
                            Integer.parseInt(campos[1]), Integer.parseInt(campos[2]), campos[3],
                            Integer.parseInt(campos[4]), fecha
                        );
                        elementos.add(m);
                        break;
                    default:
                        LoggerUtil.logError("Tipo desconocido en inventario.txt: " + tipo, null);
                }
            }
        }
        return elementos;
    }

    public void guardarInventarioCompleto(List<Object> elementos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Object obj : elementos) {
                String linea = "";
                if (obj instanceof Producto p) {
                    linea = "P;" + p.getId() + ";" + p.getNombre() + ";" + p.getId_categoria() + ";" +
                            p.getPrecio() + ";" + p.getStock() + ";" + p.getId_externo();
                } else if (obj instanceof Categoria c) {
                    linea = "C;" + c.getId() + ";" + c.getNombre();
                } else if (obj instanceof Movimiento m) {
                    String fechaStr = sdf.format(m.getFecha());
                    linea = "M;" + m.getId() + ";" + m.getProductoId() + ";" + m.getTipo() + ";" +
                            m.getCantidad() + ";" + fechaStr;
                }
                bw.write(linea);
                bw.newLine();
            }
        }
    }

    // Métodos auxiliares para productos
    public List<Producto> listarProductos() throws IOException {
        List<Object> lista = cargarInventarioCompleto();
        List<Producto> productos = new ArrayList<>();
        for (Object o : lista) if (o instanceof Producto p) productos.add(p);
        return productos;
    }

    public void agregarProducto(Producto p) throws IOException {
        List<Object> lista = cargarInventarioCompleto();
        lista.add(p);
        guardarInventarioCompleto(lista);
    }

    public void actualizarProducto(Producto p) throws IOException {
        List<Object> lista = cargarInventarioCompleto();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) instanceof Producto prod && prod.getId() == p.getId()) {
                lista.set(i, p);
                break;
            }
        }
        guardarInventarioCompleto(lista);
    }

    public void eliminarProducto(int id) throws IOException {
        List<Object> lista = cargarInventarioCompleto();
        lista.removeIf(o -> (o instanceof Producto p) && p.getId() == id);
        guardarInventarioCompleto(lista);
    }

    public Producto obtenerProductoPorId(int id) throws IOException {
        return listarProductos().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    // Métodos auxiliares para categorías
    public List<Categoria> listarCategorias() throws IOException {
        List<Object> lista = cargarInventarioCompleto();
        List<Categoria> categorias = new ArrayList<>();
        for (Object o : lista) if (o instanceof Categoria c) categorias.add(c);
        return categorias;
    }

    public void agregarCategoria(Categoria c) throws IOException {
        List<Object> lista = cargarInventarioCompleto();
        lista.add(c);
        guardarInventarioCompleto(lista);
    }

    public void actualizarCategoria(Categoria c) throws IOException {
        List<Object> lista = cargarInventarioCompleto();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i) instanceof Categoria cat && cat.getId() == c.getId()) {
                lista.set(i, c);
                break;
            }
        }
        guardarInventarioCompleto(lista);
    }

    public void eliminarCategoria(int id) throws IOException {
        List<Object> lista = cargarInventarioCompleto();
        lista.removeIf(o -> (o instanceof Categoria c) && c.getId() == id);
        guardarInventarioCompleto(lista);
    }

    // Métodos auxiliares para movimientos
    public List<Movimiento> listarMovimientos() throws IOException {
        List<Object> lista = cargarInventarioCompleto();
        List<Movimiento> movimientos = new ArrayList<>();
        for (Object o : lista) if (o instanceof Movimiento m) movimientos.add(m);
        return movimientos;
    }

    public void agregarMovimiento(Movimiento m) throws IOException {
        List<Object> lista = cargarInventarioCompleto();
        lista.add(m);
        guardarInventarioCompleto(lista);
    }

    // Método para copia de seguridad con fecha en el nombre
    public String hacerCopiaSeguridad() throws IOException {
        String fechaActual = LocalDate.now().toString(); // formato YYYY-MM-DD
        String nombreBackup = "recursos/inventario_backup_" + fechaActual + ".txt";

        Files.copy(Paths.get(rutaArchivo), Paths.get(nombreBackup), StandardCopyOption.REPLACE_EXISTING);
        
        return nombreBackup;
    }
}
