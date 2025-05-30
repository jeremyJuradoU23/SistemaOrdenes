package main;

import dao.ClienteDAO;
import dao.ProductoDAO;
import dao.OrdenDAO;
import dao.TicketDetalleDAO;
import entidad.Cliente;
import entidad.Producto;
import entidad.Orden;
import entidad.TicketDetalle;

import java.util.Date;
import java.util.List;

public class TicketApp {

    public static void main(String[] args) {
        ClienteDAO clienteDAO = new ClienteDAO();
        ProductoDAO productoDAO = new ProductoDAO();
        OrdenDAO ticketDAO = new OrdenDAO();
        TicketDetalleDAO ticketDetalleDAO = new TicketDetalleDAO();

        // Insertar un cliente
        Cliente cliente = new Cliente(0, "Juan Perez", "juan@mail.com", "123456789");
        boolean clienteInsertado = clienteDAO.insertar(cliente);
        System.out.println("Cliente insertado: " + clienteInsertado);

        // Insertar un producto
        Producto producto = new Producto(0, "Laptop", 1500.00, 10);
        boolean productoInsertado = productoDAO.insertar(producto);
        System.out.println("Producto insertado: " + productoInsertado);

        // Obtener lista de productos
        List<Producto> productos = productoDAO.listar();
        System.out.println("Lista de productos:");
        for (Producto p : productos) {
            System.out.println(p.getId() + " - " + p.getNombre() + " - Precio: " + p.getPrecio() + " - Stock: " + p.getStock());
        }

        // Crear un orden (venta)
        Orden orden = new Orden(0, new Date(), cliente.getId());
        boolean ticketInsertado = ticketDAO.insertar(orden);
        System.out.println("Orden insertado: " + ticketInsertado);
        System.out.println("Orden ID generado: " + orden.getId());

        // Insertar detalle de orden
        if (orden.getId() != 0 && !productos.isEmpty()) {
            Producto prod = productos.get(0);
            TicketDetalle detalle = new TicketDetalle(0, orden.getId(), prod.getId(), 2, prod.getPrecio() * 2);
            boolean detalleInsertado = ticketDetalleDAO.insertar(detalle);
            System.out.println("Detalle insertado: " + detalleInsertado);
        }

        // Listar ordenes
        List<Orden> ordenes = ticketDAO.listar();
        System.out.println("Lista de ordenes:");
        for (Orden t : ordenes) {
            System.out.println("ID: " + t.getId() + " Fecha: " + t.getFecha() + " Cliente ID: " + t.getIdCliente());
        }
    }
}
