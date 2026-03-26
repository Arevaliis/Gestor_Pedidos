package model;

import java.util.Objects;

public class Pedido {
    private int id;
    private int clienteID;
    private Cliente cliente;
    private final String producto;
    private final int cantidad;
    private final double precio;
    private final double precioTotal;

    public Pedido(int id, int clienteID, String producto, int cantidad, double precio) {
        this.id = id;
        this.clienteID = clienteID;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.precioTotal = cantidad * precio;
    }

    public Pedido(int clienteID, String producto, int cantidad, double precio) {
        this.clienteID = clienteID;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.precioTotal = cantidad * precio;
    }

    public Pedido(int id, Cliente cliente, String producto, int cantidad, double precio) {
        this.id = id;
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.precioTotal = cantidad * precio;
    }

    public Pedido(Cliente cliente, String producto, int cantidad, double precio) {
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

    public String getProducto() {
        return producto;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

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