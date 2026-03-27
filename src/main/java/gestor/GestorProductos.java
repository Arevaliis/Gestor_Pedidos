package gestor;

import dao.ProductosDAO;
import exception.ServiceException;
import service.ProductosService;
import util.Console;
import util.DataBaseConexion;

import java.sql.Connection;
import java.sql.SQLException;

public class GestorProductos {
    public static void ejecutarGestorProductos(Console console){

        boolean seguir = true;

        try (Connection connection = DataBaseConexion.getConnection()) {
            ProductosService productosService = new ProductosService(new ProductosDAO(connection));

            while (seguir) {
                try {
                    int opc = console.ingresarNumero(mostrarMensajeMenu());

                    if (opc == 0) { return; }
                    ejecutarOpcion(productosService, opc, console);

                    seguir = console.continuarGestorPedidos("¿Desea seguir en la sección de productos? S/N: ");

                } catch (NumberFormatException e) { System.err.println("Por favor, ingrese un número.");
                } catch (ServiceException e) { System.err.println(e.getMessage()); }
            }

        } catch (SQLException e) { System.err.println("Error en la conexión a la base de datos: " + e.getMessage()); }
    }

    private static String mostrarMensajeMenu(){
        return """
            ===================================
                SISTEMA DE GESTIÓN PRODUCTOS
            ===================================
            
                1. Crear producto
                2. Ver producto por ID
                3. Listar todos los productos
                4. Modificar producto
                5. Eliminar producto
                6. Salir
            
            Seleccione una opción del 1 al 6:""";
    }

    private static void ejecutarOpcion(ProductosService productosService, int opc, Console console) throws ServiceException {
        switch (opc){
            case 1 -> {
                productosService.insertar(console.ingresarProducto());
                System.out.println("Producto ingresado");
            }

            case 2 -> System.out.println(productosService.buscarPorId(console.ingresarNumero("Ingrese el ID del producto: ")));
            case 3 -> productosService.listarTodos().forEach(System.out::println);

            case 4 -> {
                productosService.modificar(console.ingresarNumero("Ingrese el ID del producto: "), console.ingresarDecimal("Ingrese el precio del producto: "));
                System.out.println("Producto modificado");
            }
            case 5 -> {
                productosService.eliminar(console.ingresarNumero("Ingrese el ID del producto: "));
                System.out.println("Producto eliminado");
            }

            case 6 -> System.err.println("Volviendo...");
            default -> System.err.println("Debe ingresar un número comprendido entre 0 y 5");
        }
    }
}