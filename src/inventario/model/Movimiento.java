package inventario.model;

import java.util.Date;

public class Movimiento {
    private int id;
    private int productoId;
    private String tipo;
    private int cantidad;
    private Date fecha;

    // Constructor completo con ID
    public Movimiento(int id, int productoId, String tipo, int cantidad, Date fecha) {
        this.id = id;
        this.productoId = productoId;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    // Constructor sin ID para insertar
    public Movimiento(int productoId, String tipo, int cantidad, Date fecha) {
        this.productoId = productoId;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    // Constructor vacío
    public Movimiento() {}

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getProductoId() { return productoId; }
    public void setProductoId(int productoId) { this.productoId = productoId; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
}
