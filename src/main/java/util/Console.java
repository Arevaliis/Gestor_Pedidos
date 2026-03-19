package util;

import model.Pedido;

import java.util.Scanner;

public class Console {
    public final Scanner scanner;

    public Console(Scanner scanner) { this.scanner = scanner; }

    public boolean continuarGestorPedidos(String mensaje){
        String respuesta;

        do {
            System.err.print(mensaje);
            respuesta = scanner.nextLine().trim().toUpperCase();

        } while (!respuesta.equals("S") && !respuesta.equals("N"));

        return respuesta.equals("S");
    }

    public Pedido ingresarPedido(){
        return new Pedido(
                ingresarPalabra("Ingrese el nombre del cliente: "),
                ingresarPalabra("Ingrese el producto: "),
                ingresarNumero("Ingrese la cantidad de producto: "),
                ingresarDecimal("Ingrese el precio unitario del producto: "));
    }

    private String ingresarPalabra(String mensaje){
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public int ingresarNumero(String mensaje){
        System.out.print(mensaje);
        return Integer.parseInt(scanner.nextLine());
    }

    private double ingresarDecimal(String mensaje) {
        System.out.print(mensaje);
        return Double.parseDouble(scanner.nextLine());
    }
}
