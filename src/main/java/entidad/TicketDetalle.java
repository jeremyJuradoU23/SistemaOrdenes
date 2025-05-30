package entidad;

public class TicketDetalle {
    private int id;
    private int idTicket;
    private int idProducto;
    private int cantidad;
    private double subtotal;

    public TicketDetalle() {}

    public TicketDetalle(int id, int idTicket, int idProducto, int cantidad, double subtotal) {
        this.id = id;
        this.idTicket = idTicket;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdTicket() { return idTicket; }
    public void setIdTicket(int idTicket) { this.idTicket = idTicket; }
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
}
