package gestor;

import dao.ClientesDAO;
import exception.ServiceException;
import service.ClientesService;
import util.Console;
import util.DataBaseConexion;

import java.sql.Connection;
import java.sql.SQLException;

public class GestorClientes {
    public static void ejecutarGestorClientes(Console console){

        boolean seguir = true;

        try (Connection connection = DataBaseConexion.getConnection()) {
            ClientesService clientesService = new ClientesService(new ClientesDAO(connection));

            while (seguir) {
                try {
                    int opc = console.ingresarNumero(mostrarMensajeMenu());

                    if (opc == 0) { return; }
                    ejecutarOpcion(clientesService, opc, console);

                    seguir = console.continuarGestorPedidos("¿Desea seguir en la sección de clientes? S/N: ");

                } catch (NumberFormatException e) { System.err.println("Por favor, ingrese un número.");
                } catch (ServiceException e) { System.err.println(e.getMessage()); }
            }

        } catch (SQLException e) { System.err.println("Error en la conexión a la base de datos: " + e.getMessage()); }
    }

    private static String mostrarMensajeMenu(){
        return """
            ==================================
                SISTEMA DE GESTIÓN CLIENTES
            ==================================
            
                1. Crear Cliente
                2. Ver cliente por ID
                3. Listar todos los clientes
                4. Modificar cliente
                5. Eliminar cliente
                6. Salir
            
            Seleccione una opción del 1 al 6:""";
    }

    private static void ejecutarOpcion(ClientesService clientesService, int opc, Console console) throws ServiceException {
        switch (opc){
            case 1 -> {
                clientesService.insertar(console.ingresarCliente());
                System.out.println("Cliente ingresado");
            }

            case 2 -> System.out.println(clientesService.buscarPorId(console.ingresarNumero("Ingrese el ID del cliente: ")));
            case 3 -> clientesService.listarTodos().forEach(System.out::println);

            case 4 -> {
                clientesService.modificar(console.ingresarNumero("Ingrese el ID del cliente: "), console.ingresarEmail());
                System.out.println("Cliente modificado");
            }
            case 5 -> {
                clientesService.eliminar(console.ingresarNumero("Ingrese el ID del cliente: "));
                System.out.println("Cliente eliminado");
            }

            case 6 -> System.err.println("Volviendo...");
            default -> System.err.println("Debe ingresar un número comprendido entre 0 y 5");
        }
    }
}
