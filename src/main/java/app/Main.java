package app;

import gestor.GestorClientes;
import gestor.GestorPedidos;
import gestor.GestorProductos;
import util.Console;

import java.util.Scanner;

// TODO DEBEREMOS MODIFICAR STOCK CUANDO HAGAMOS UN NUEVO PEDIDO

public class Main {
    public static void main(String[] args) {
        Console console = new Console(new Scanner(System.in));
        boolean seguir = true;

        while (seguir) {
            try {

                if (ejecutarOpcion(console) == 0 ) { return; }
                seguir = console.continuarGestorPedidos("¿Desea seguir en el gestor? S/N: ");

            } catch (NumberFormatException e) { System.err.println("Por favor, ingrese un número.");
            } finally { if (!seguir){ System.err.println("Saliendo..."); } }
        }
    }

    private static String mostrarMensajeMenu(){
        return """
            ================================
               SISTEMA DE GESTIÓN PEDIDOS
            ================================
            
                    1. Pedidos
                    2. Clientes
                    3. Productos
                    4. Salir
            
            Seleccione una opción del 1 al 4:""";
    }

    private static int ejecutarOpcion(Console console) {
        int opc = console.ingresarNumero(mostrarMensajeMenu());

        switch (opc){
            case 1 -> GestorPedidos.ejecutarGestorPedidos(console);
            case 2 -> GestorClientes.ejecutarGestorClientes(console);
            case 3 -> GestorProductos.ejecutarGestorProductos(console);


            case 4 -> System.err.println("Saliendo...");
            default -> System.err.println("Debe ingresar un número comprendido entre 0 y 3");
        }

        return opc;
    }
}