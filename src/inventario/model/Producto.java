package inventario.model;

public class Producto {
    private int id;
    private String nombre;
    private int id_categoria;
    private double precio;
    private int stock;
    private int id_externo;

    // Constructor completo con ID
    public Producto(int id, String nombre, int id_categoria, double precio, int stock, int id_externo) {
        this.id = id;
        this.nombre = nombre;
        this.id_categoria = id_categoria;
        this.precio = precio;
        this.stock = stock;
        this.id_externo = id_externo;
    }

    // Constructor sin ID para insertar
    public Producto(String nombre, int id_categoria, double precio, int stock, int id_externo) {
        this.nombre = nombre;
        this.id_categoria = id_categoria;
        this.precio = precio;
        this.stock = stock;
        this.id_externo = id_externo;
    }

    // Constructor vacío
    public Producto() {}

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getId_categoria() { return id_categoria; }
    public void setId_categoria(int id_categoria) { this.id_categoria = id_categoria; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public int getId_externo() { return id_externo; }
    public void setId_externo(int id_externo) { this.id_externo = id_externo; }

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", id_categoria=" + id_categoria + ", precio=" + precio
				+ ", stock=" + stock + ", id_externo=" + id_externo + "]";
	}
}
