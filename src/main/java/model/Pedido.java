package model;

import java.util.Objects;

public class Pedido {
    private int id;
    private int clienteID;
    private Cliente cliente;
    private int productoID;
    private Producto producto;
    private int cantidad;
    private double precio;
    private final double precioTotal;

    // CUANDO CREAMOS UNA INSTANCIA DESDE LA BBDD
    public Pedido(int id, int clienteID, int productoID, int cantidad, double precio) {
        this.id = id;
        this.clienteID = clienteID;
        this.productoID = productoID;
        this.cantidad = cantidad;
        this.precio = precio;
        this.precioTotal = cantidad * precio;
    }

    // CUANDO CREAMOS UN REGISTRO EN LA BBDD
    public Pedido(int clienteID, int productoID, int cantidad) {
        this.clienteID = clienteID;
        this.productoID = productoID;
        this.cantidad = cantidad;
        this.precioTotal = cantidad * precio;
    }

    // CUANDO TRANSFORMAMOS LA INSTANCIA DE LA BBDD EN OTRO CON LA INSTANCIA DE CLIENTE Y PRODUCTO
    public Pedido(int id, Cliente cliente, Producto producto, int cantidad, double precio) {
        this.id = id;
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.precioTotal = cantidad * precio;
    }

    public int getId() {
        return id;
    }

    public int getClienteID() { return clienteID; }

    public int getProductoID() {
        return productoID;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioTotal() { return precioTotal; }

    public void setPrecio(double precio) { this.precio = precio; }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pedido pedido)) return false;
        return id == pedido.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", cliente='" + cliente + '\'' +
                ", producto='" + producto + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", precioTotal=" + precioTotal;
    }
}