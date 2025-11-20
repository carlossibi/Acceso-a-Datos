# Gestión de Inventario con Ficheros - Proyecto 2º DAM

## Descripción

Sistema de gestión de inventario desarrollado en Java que utiliza ficheros de texto para almacenar productos, categorías y movimientos, sin depender de base de datos. El programa permite manejar el inventario, registrar todas las operaciones realizadas con fecha y hora, y realizar copias de seguridad del archivo principal de datos.

---

## Contenido del Proyecto

- `Main.java`: menú para manejar inventario, historial y backups.
- `InventarioService.java`: lógica para manejar datos.
- `InventarioFichero.java`: lectura y escritura del fichero principal.
- `RegistroOperaciones.java`: archivo con historial de acciones.
- Clases de datos: `Producto`, `Categoria` y `Movimiento`.
- Carpeta `recursos/` con archivos `inventario.txt` y `registro.txt`.

---

## Funcionalidades Principales

- Gestión completa de **categorías** y **productos**: listar, crear, actualizar y eliminar.
- Registro automático de cada operación en historial con fecha y hora.
- Consulta del historial de operaciones para auditoría.
- Creación de copias de seguridad del inventario con fecha en el nombre del archivo.
- Manejo de excepciones y logging de errores para garantizar robustez.

---

## Uso

1. Ejecuta la clase `Main.java`.
2. Navega por el menú:
   - Selecciona gestión de inventario para operar sobre categorías o productos.
   - Consulta el historial de operaciones.
   - Realiza copias de seguridad del inventario.
3. Ingresa los datos solicitados en consola para crear, modificar o eliminar registros.

---

## Ejemplo: Crear nueva categoría
1. Gestión de Inventario

2. Gestión de Categorías

3. Crear categoría
	Nombre nueva categoría: Electrónica 
	Categoría creada correctamente.
	
## Estructura básica
	
	src/
├─ inventario/
├─ model/
├─ model/dao/
├─ model/dao/util/service/
recursos/
├─ inventario.txt
├─ registro.txt
	

