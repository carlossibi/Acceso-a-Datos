# Gestión de inventario con CRUD, CSV y base de datos
Aplicación Java para la gestión de inventario con categorías, productos y movimientos de stock.  
Permite importar productos desde CSV, gestionar CRUD de categorías, productos y movimientos, y exportar consultas a JSON. 	

## Instalación
1. Importar el proyecto en Eclipse.
2. Configurar la conexión a la base de datos en `DBConnection.java`.
3. Ejecutar el proyecto.

## Uso
- El programa lee los productos desde el CSV al iniciar.
- Menú principal:
  - Gestión de categorías
  - Gestión de productos
  - Gestión de movimientos
  - Exportación a JSON de productos con stock bajo
- Logs de errores: `inventario_errores.log`

## Script SQL
-- Crear base de datos
CREATE DATABASE IF NOT EXISTS inventario CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE inventario;

-- Tabla de categorías
CREATE TABLE IF NOT EXISTS categoria (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(80) NOT NULL UNIQUE
);

-- Tabla de productos
CREATE TABLE IF NOT EXISTS producto (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_externo INT NOT NULL,
  nombre VARCHAR(120) NOT NULL,
  precio DECIMAL(10,2) NOT NULL CHECK (precio >= 0),
  stock INT NOT NULL CHECK (stock >= 0),
  id_categoria INT,
  CONSTRAINT fk_prod_cat FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);

-- Tabla de movimientos
CREATE TABLE IF NOT EXISTS movimiento (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_producto INT NOT NULL,
  tipo ENUM('ENTRADA','SALIDA') NOT NULL,
  cantidad INT NOT NULL CHECK (cantidad > 0),
  fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_mov_prod FOREIGN KEY (id_producto) REFERENCES producto(id)
);

# Instrucciones de uso

1. Ejecutar el script SQL anterior para crear la base de datos y tablas.
2. Asegurarse de tener el CSV de productos en `recursos/inventario.csv`.
3. Abrir el proyecto en Eclipse o tu IDE favorito.
4. Ejecutar la clase `Main.java`.
5. Navegar por el **menú principal**:

## Menú principal
- `1. Gestión de Categorías`: CRUD de categorías
- `2. Gestión de Productos`: CRUD de productos
- `3. Gestión de Movimientos`: Entradas y salidas de stock
- `4. Exportar productos con stock bajo a JSON`: genera `productos_stock_bajo.json`
- `0. Salir`

## Menú de Categorías
- `1. Listar categorías`
- `2. Crear categoría`
- `3. Actualizar categoría`
- `4. Eliminar categoría`
- `0. Volver`

## Menú de Productos
- `1. Listar productos`
- `2. Crear producto`
- `3. Actualizar producto`
- `4. Eliminar producto`
- `0. Volver`

## Menú de Movimientos
- `1. Listar movimientos`
- `2. Registrar entrada de stock`
- `3. Registrar salida de stock`
- `0. Volver`

## Ejemplos de uso

**Crear categoría:**  
- Nombre: `"Electrónica"`

**Crear producto:**  
- Nombre: `"Disco Duro 1TB"`  
- Categoría: `1`  
- Precio: `120.50`  
- Stock: `50`  
- ID externo: `1001`

**Registrar entrada:**  
- Producto ID `1001`, Cantidad `20`

**Registrar salida:**  
- Producto ID `1001`, Cantidad `5`

**Exportar stock bajo:**  
- Umbral `10` → genera archivo `productos_stock_bajo.json`

## Notas importantes
- Los IDs externos deben ser únicos.
- Precio y stock no pueden ser negativos.
- Los movimientos registran automáticamente la fecha y el tipo (ENTRADA o SALIDA).
- Todos los errores se registran en `inventario_errores.log`.
- La aplicación actualiza la base de datos en tiempo real para cada operación de creación, modificación o eliminación.

