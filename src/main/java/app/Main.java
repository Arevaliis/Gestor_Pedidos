package app;

import ejercicios.Gestor_Pedidos.gestor.GestorPedidos;
import ejercicios.Gestor_Pedidos.util.Console;

import java.util.Scanner;

// TODO CUANDO TENGA LAS TABLAS DE PRODUCTOS Y CLIENTES MODIFICAR EL DAO DE PEDIDOS

public class Main {
    public static void main(String[] args) {
        Console console = new Console(new Scanner(System.in));
        boolean seguir = true;

        while (seguir) {
            try {

                if (ejecutarOpcion(console) == 0) { return; }
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
                    0. Salir
            
            Seleccione una opción del 0 al 3:  """;
    }

    private static int ejecutarOpcion(Console console) {
        int opc = console.ingresarNumero(mostrarMensajeMenu());

        switch (opc){
            case 1 -> GestorPedidos.ejecutarGestorPedidos(console);
            case 2 -> System.out.println("e");
            case 3 -> System.out.println("d");


            case 0 -> System.err.println("Saliendo...");
            default -> System.err.println("Debe ingresar un número comprendido entre 0 y 3");
        }

        return opc;
    }
}
