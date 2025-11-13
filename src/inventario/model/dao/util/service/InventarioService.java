package inventario.model.dao.util.service;

import inventario.model.Producto;
import inventario.model.Categoria;
import inventario.model.Movimiento;
import inventario.model.dao.InventarioFichero;
import inventario.model.dao.RegistroOperaciones;

import java.io.IOException;
import java.util.List;

public class InventarioService {

    private final InventarioFichero inventarioFichero = new InventarioFichero();

    public List<Producto> listarProductos() throws IOException {
        RegistroOperaciones.registrar("Listado de productos");
        return inventarioFichero.listarProductos();
    }

    public void crearProducto(String nombre, int idCategoria, double precio, int stock, int idExterno) throws IOException {
        int nuevoId = generarNuevoIdProducto();
        Producto nuevo = new Producto(nuevoId, nombre, idCategoria, precio, stock, idExterno);
        inventarioFichero.agregarProducto(nuevo);
        RegistroOperaciones.registrar("Creado producto ID: " + nuevoId + ", Nombre: " + nombre);
    }

    public void actualizarProducto(int id, String nuevoNombre, int nuevoStock, double nuevoPrecio, int nuevoIdExterno) throws IOException {
        Producto p = inventarioFichero.obtenerProductoPorId(id);
        if (p != null) {
            p.setNombre(nuevoNombre);
            p.setStock(nuevoStock);
            p.setPrecio(nuevoPrecio);
            p.setId_externo(nuevoIdExterno);
            inventarioFichero.actualizarProducto(p);
            RegistroOperaciones.registrar("Actualizado producto ID: " + id);
        } else {
            RegistroOperaciones.registrar("Intento de actualizar producto no encontrado ID: " + id);
        }
    }

    public void eliminarProducto(int id) throws IOException {
        inventarioFichero.eliminarProducto(id);
        RegistroOperaciones.registrar("Eliminado producto ID: " + id);
    }

    public Producto buscarProductoPorId(int id) throws IOException {
        RegistroOperaciones.registrar("Búsqueda de producto ID: " + id);
        return inventarioFichero.obtenerProductoPorId(id);
    }

    public List<Categoria> listarCategorias() throws IOException {
        RegistroOperaciones.registrar("Listado de categorías");
        return inventarioFichero.listarCategorias();
    }

    public void crearCategoria(String nombre) throws IOException {
        int nuevoId = generarNuevoIdCategoria();
        Categoria c = new Categoria(nuevoId, nombre);
        inventarioFichero.agregarCategoria(c);
        RegistroOperaciones.registrar("Creada categoría ID: " + nuevoId + ", Nombre: " + nombre);
    }

    public void actualizarCategoria(int id, String nuevoNombre) throws IOException {
        List<Categoria> categorias = inventarioFichero.listarCategorias();
        boolean encontrado = false;
        for (Categoria c : categorias) {
            if (c.getId() == id) {
                c.setNombre(nuevoNombre);
                inventarioFichero.actualizarCategoria(c);
                RegistroOperaciones.registrar("Actualizada categoría ID: " + id);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            RegistroOperaciones.registrar("Intento de actualizar categoría no encontrada ID: " + id);
        }
    }

    public void eliminarCategoria(int id) throws IOException {
        inventarioFichero.eliminarCategoria(id);
        RegistroOperaciones.registrar("Eliminada categoría ID: " + id);
    }

    public List<Movimiento> listarMovimientos() throws IOException {
        RegistroOperaciones.registrar("Listado de movimientos");
        return inventarioFichero.listarMovimientos();
    }

    public void agregarMovimiento(Movimiento m) throws IOException {
        inventarioFichero.agregarMovimiento(m);
        RegistroOperaciones.registrar("Añadido movimiento ID: " + m.getId());
    }

    // Métodos auxiliares para generar IDs nuevos (puedes adaptar si tienes otro sistema)
    private int generarNuevoIdProducto() throws IOException {
        List<Producto> productos = inventarioFichero.listarProductos();
        return productos.stream().mapToInt(Producto::getId).max().orElse(0) + 1;
    }

    private int generarNuevoIdCategoria() throws IOException {
        List<Categoria> categorias = inventarioFichero.listarCategorias();
        return categorias.stream().mapToInt(Categoria::getId).max().orElse(0) + 1;
    }
}
