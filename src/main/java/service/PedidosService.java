package service;

import dao.PedidosDAO;
import exception.DAOException;
import exception.ServiceException;
import model.InterfazService;
import model.Pedido;
import util.Console;

import java.util.List;
import java.util.Optional;

public class PedidosService implements InterfazService<Pedido> {

    public final PedidosDAO pedidosDAO;
    public final Console console;

    public PedidosService(PedidosDAO pedidosDAO, Console console) {
        this.pedidosDAO = pedidosDAO;
        this.console = console;
    }

    public void insertar(Pedido pedido) throws ServiceException{

        try{

            pedidosDAO.insertar(pedido); // TODO DEBEREMOS MODIFICAR LA PARTE DE EL CLIENTE Y PRODUCTO

        } catch (DAOException e){ throw new ServiceException("SERVICE: No se pudo insertar el pedido debido a un error.", e); }
    }

    @Override
    public void modificar(int id, int cantidad) throws ServiceException{
        try {

            if (cantidad < 0){ throw new ServiceException("La cantidad ingresada no puede ser menor de 1."); }
            pedidosDAO.modificar(id, cantidad);

        } catch (DAOException e){ throw new ServiceException("SERVICE: No se pudo modificar el pedido debido a un error.", e); }
    }

    @Override
    public void eliminar(int id) throws ServiceException {
        try {

            pedidosDAO.eliminar(console.ingresarNumero("Ingrese el ID del pedido: "));

        } catch (DAOException e){ throw new ServiceException("SERVICE: No se pudo eliminar el pedido debido a un error.", e); }
    }

    @Override
    public List<Pedido> listarTodos() throws ServiceException{

        try {

            List<Pedido> pedidos = pedidosDAO.listar();

            if (pedidos.isEmpty()) { throw new ServiceException("No hay pedidos registrados en la base de datos"); }

            return pedidos;

        } catch (DAOException e){ throw new ServiceException("SERVICE: No se pudo mostrar los pedidos debido a un error.", e); }
    }

    @Override
    public Optional<Pedido> buscarPorId(int id) throws ServiceException {
        try {

            Optional<Pedido> pedido = pedidosDAO.listarPorId(id);
            if (pedido.isEmpty()) { throw new ServiceException("No existe el pedido con id: "+ id); }

            return pedido;

        } catch (DAOException e){ throw new ServiceException("SERVICE: No se pudo mostrar el pedido debido a un error.", e); }
    }
}