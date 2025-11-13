package inventario;

import inventario.model.Producto;
import inventario.model.Categoria;
import inventario.model.dao.util.service.InventarioService;
import inventario.model.dao.InventarioFichero;
import inventario.model.dao.RegistroOperaciones;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventarioService service = new InventarioService();
        InventarioFichero inventarioFichero = new InventarioFichero();
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n==== MENÚ PRINCIPAL ====");
            System.out.println("1. Gestión de Inventario");
            System.out.println("2. Historial de operaciones");
            System.out.println("3. Hacer copia de seguridad");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> menuGestionInventario(service, sc);
                case 2 -> mostrarHistorialOperaciones();
                case 3 -> {
                    try {
                        String archivoBackup = inventarioFichero.hacerCopiaSeguridad();
                        System.out.println("Copia de seguridad creada correctamente: " + archivoBackup);
                    } catch (Exception e) {
                        System.out.println("Error al hacer la copia de seguridad: " + e.getMessage());
                    }
                }
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        sc.close();
    }

    private static void menuGestionInventario(InventarioService service, Scanner sc) {
        int o;
        do {
            System.out.println("\n--- Gestión de Inventario ---");
            System.out.println("1. Gestión de Categorías");
            System.out.println("2. Gestión de Productos");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            o = sc.nextInt(); sc.nextLine();
            switch (o) {
                case 1 -> menuCategorias(service, sc);
                case 2 -> menuProductos(service, sc);
                case 0 -> {}
                default -> System.out.println("Opción no válida.");
            }
        } while (o != 0);
    }

    private static void mostrarHistorialOperaciones() {
        List<String> historial = RegistroOperaciones.obtenerHistorialCompleto();
        if (historial.isEmpty()) {
            System.out.println("No hay operaciones registradas.");
        } else {
            System.out.println("--- Historial de Operaciones ---");
            for (String registro : historial) {
                System.out.println(registro);
            }
        }
    }

    // Gestión de Categorías
    private static void menuCategorias(InventarioService service, Scanner sc) {
        int o;
        do {
            System.out.println("\n--- Gestión de Categorías ---");
            System.out.println("1. Listar categorías");
            System.out.println("2. Crear categoría");
            System.out.println("3. Actualizar categoría");
            System.out.println("4. Eliminar categoría");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            o = sc.nextInt(); sc.nextLine();
            try {
                switch (o) {
                    case 1 -> {
                        List<Categoria> cats = service.listarCategorias();
                        if (cats.isEmpty()) System.out.println("Sin categorías.");
                        for (Categoria c : cats) System.out.println(c.getId() + " - " + c.getNombre());
                    }
                    case 2 -> {
                        System.out.print("Nombre nueva categoría: ");
                        String nombre = sc.nextLine();
                        service.crearCategoria(nombre);
                    }
                    case 3 -> {
                        System.out.print("ID a actualizar: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Nuevo nombre: ");
                        String nombre = sc.nextLine();
                        service.actualizarCategoria(id, nombre);
                    }
                    case 4 -> {
                        System.out.print("ID a eliminar: ");
                        int idEliminar = sc.nextInt(); sc.nextLine();
                        service.eliminarCategoria(idEliminar);
                    }
                    case 0 -> {}
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (o != 0);
    }

    // Gestión de Productos
    private static void menuProductos(InventarioService service, Scanner sc) {
        int o;
        do {
            System.out.println("\n--- Gestión de Productos ---");
            System.out.println("1. Listar productos");
            System.out.println("2. Crear producto");
            System.out.println("3. Actualizar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            o = sc.nextInt(); sc.nextLine();
            try {
                switch (o) {
                    case 1 -> {
                        List<Producto> prods = service.listarProductos();
                        if (prods.isEmpty()) System.out.println("Sin productos.");
                        for (Producto p : prods) System.out.println(p);
                    }
                    case 2 -> {
                        System.out.print("Nombre: ");
                        String nombre = sc.nextLine();
                        System.out.print("ID Categoría: ");
                        int idCat = sc.nextInt();
                        System.out.print("Precio: ");
                        double precio = sc.nextDouble();
                        System.out.print("Stock: ");
                        int stock = sc.nextInt();
                        System.out.print("ID Externo: ");
                        int idExterno = sc.nextInt(); sc.nextLine();
                        service.crearProducto(nombre, idCat, precio, stock, idExterno);
                    }
                    case 3 -> {
                        System.out.print("ID producto a modificar: ");
                        int id = sc.nextInt(); sc.nextLine();
                        System.out.print("Nombre nuevo: ");
                        String nombre = sc.nextLine();
                        System.out.print("Nuevo stock: ");
                        int stock = sc.nextInt();
                        System.out.print("Nuevo precio: ");
                        double precio = sc.nextDouble();
                        System.out.print("Nuevo id externo: ");
                        int idExt = sc.nextInt(); sc.nextLine();
                        service.actualizarProducto(id, nombre, stock, precio, idExt);
                    }
                    case 4 -> {
                        System.out.print("ID producto a eliminar: ");
                        int idEliminar = sc.nextInt(); sc.nextLine();
                        service.eliminarProducto(idEliminar);
                    }
                    case 0 -> {}
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (o != 0);
    }
}
