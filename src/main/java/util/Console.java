package util;

import exception.ServiceException;
import model.Cliente;
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

    public Pedido ingresarPedido() throws ServiceException {
        return new Pedido(
                ingresarNumero("Ingrese el id del cliente: "),
                ingresarPalabra("Ingrese el producto: "),
                ingresarNumero("Ingrese la cantidad de producto: "),
                ingresarDecimal("Ingrese el precio unitario del producto: "));
    }

    public Cliente ingresarCliente() throws ServiceException {
        return new Cliente(
                ingresarPalabra("Ingrese el nombre del cliente: "),
                ingresarEmail()
        );
    }

    public String ingresarEmail() throws ServiceException  {
        System.out.print("Ingrese el email del cliente: ");
        return Verificador.verificarEmail(scanner.nextLine().trim().toLowerCase());
    }


    private String ingresarPalabra(String mensaje) throws ServiceException {
        System.out.print(mensaje);
        return Verificador.verificarPalabra(scanner.nextLine().trim().toLowerCase());
    }

    public int ingresarNumero(String mensaje){
        System.out.print(mensaje);
        return Verificador.verificarNumeroIngresado(scanner.nextLine().trim());
    }

    private double ingresarDecimal(String mensaje) {
        System.out.print(mensaje);
        return Verificador.verificarDecimalIngresado(scanner.nextLine().trim());
    }

}
