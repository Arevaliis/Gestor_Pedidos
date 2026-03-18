package gestor;

import ejercicios.Gestor_Pedidos.dao.PedidosDAO;
import ejercicios.Gestor_Pedidos.exception.ServiceException;
import ejercicios.Gestor_Pedidos.service.PedidosService;
import ejercicios.Gestor_Pedidos.util.Console;
import ejercicios.Gestor_Pedidos.util.DataBaseConexion;

import java.sql.Connection;
import java.sql.SQLException;

public class GestorPedidos {
    public static void ejecutarGestorPedidos(Console console){

        boolean seguir = true;

        try (Connection connection = DataBaseConexion.getConnection()) {
            PedidosService pedidosService = new PedidosService(new PedidosDAO(connection), console);

            while (seguir) {
                try {
                    int opc = console.ingresarNumero(mostrarMensajeMenu());

                    if (opc == 0) { return; }
                    ejecutarOpcion(pedidosService, opc, console);

                    seguir = console.continuarGestorPedidos("¿Desea seguir en la sección de pedidos? S/N: ");

                } catch (NumberFormatException e) { System.err.println("Por favor, ingrese un número.");
                } catch (ServiceException e) { System.out.println(e.getMessage()); }
            }

        } catch (SQLException e) { System.err.println("Error en la conexión a la base de datos: " + e.getMessage()); }
    }

    private static String mostrarMensajeMenu(){
        return """
            ================================
               SISTEMA DE GESTIÓN PEDIDOS
            ================================
            
                1. Crear pedido
                2. Ver pedido por ID
                3. Listar todos los pedidos
                4. Modificar pedido
                5. Cancelar pedido
                0. Salir
            
            Seleccione una opción del 0 al 3:  """;
    }

    private static void ejecutarOpcion(PedidosService pedidosService, int opc, Console console) throws ServiceException {
        switch (opc){
            case 1 -> {
                pedidosService.insertar(console.ingresarPedido());
                System.out.println("Pedido ingresado");
            }

            case 2 -> System.out.println(pedidosService.buscarPorId(console.ingresarNumero("Ingrese el ID del pedido: ")));
            case 3 -> pedidosService.listarTodos().forEach(System.out::println);

            case 4 -> {
                pedidosService.modificar(console.ingresarNumero("Ingrese el ID del pedido: "), console.ingresarNumero("Ingrese la cantidad del pedido: "));
                System.out.println("Pedido modificado");
            }
            case 5 -> {
                pedidosService.eliminar(console.ingresarNumero("Ingrese el ID del pedido: "));
                System.out.println("Pedido eliminado");
            }

            case 0 -> System.err.println("Volviendo...");
            default -> System.err.println("Debe ingresar un número comprendido entre 0 y 5");
        }
    }
}