package model;

import java.util.Objects;

public class Pedido {
    private int id;
    private final String cliente;
    private final String producto;
    private final int cantidad;
    private final double precio;
    private final double precioTotal;

    public Pedido(int id, String cliente, String producto, int cantidad, double precio) {
        this.id = id;
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.precioTotal = cantidad * precio;
    }

    public Pedido(String cliente, String producto, int cantidad, double precio) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precio = precio;
        this.precioTotal = cantidad * precio;
    }

    public int getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getProducto() {
        return producto;
    }

    public double getPrecio() {
        return precio;
    }

    public double getPrecioTotal() {
        return precioTotal;
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