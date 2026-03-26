package service;

import dao.ClientesDAO;
import dao.PedidosDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Cliente;
import model.InterfazService;
import model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidosService implements InterfazService<Pedido> {
    private final PedidosDAO pedidosDAO;
    private final ClientesDAO clienteDAO;

    public PedidosService(PedidosDAO pedidosDAO, ClientesDAO clientesDAO) {
        this.pedidosDAO = pedidosDAO;
        this.clienteDAO = clientesDAO;
    }

    public void insertar(Pedido pedido) throws ServiceException{

        try{

            pedidosDAO.insertar(pedido); // TODO DEBEREMOS MODIFICAR LA PARTE DE PRODUCTO

        } catch (DAOException e){ throw new ServiceException("SERVICE: No se pudo insertar el pedido debido a un error.", e); }
    }

    public void modificar(int id, int cantidad) throws ServiceException{
        try {

            if (cantidad <= 0){ throw new ServiceException("La cantidad ingresada no puede ser menor de 1."); }
            pedidosDAO.modificar(id, cantidad);

        } catch (DAOException e){ throw new ServiceException("SERVICE: No se pudo modificar el pedido debido a un error.", e); }
    }

    @Override
    public void eliminar(int id) throws ServiceException {
        try {

            pedidosDAO.eliminar(id);

        } catch (DAOException e){ throw new ServiceException("SERVICE: No se pudo eliminar el pedido debido a un error.", e); }
    }

    @Override
    public List<Pedido> listarTodos() throws ServiceException{

        try {
            List<Pedido> pedidos_ID = pedidosDAO.listar();
            if (pedidos_ID.isEmpty()) { throw new ServiceException("No hay pedidos registrados en la base de datos"); }

            List<Pedido> pedidos = new ArrayList<>();

            for (Pedido pedido: pedidos_ID){
                Cliente cliente = clienteDAO.buscarPorID(pedido.getClienteID())
                        .orElseThrow(() -> new ServiceException("Cliente no encontrado"));

                    pedidos.add(new Pedido( pedido.getId(),
                                            cliente,
                                            pedido.getProducto(),
                                            pedido.getCantidad(),
                                            pedido.getPrecio())
                    );
            }

            return pedidos;

        } catch (DAOException e){ throw new ServiceException("SERVICE: No se pudo mostrar los pedidos debido a un error.", e); }
    }

    @Override
    public Pedido buscarPorId(int id) throws ServiceException {
        try {

            Pedido pedido_id = pedidosDAO.buscarPorID(id)
                    .orElseThrow(() -> new ServiceException("Pedido no existe"));

            Cliente cliente = clienteDAO.buscarPorID(pedido_id.getClienteID())
                    .orElseThrow(() -> new ServiceException("Cliente no encontrado"));

            return new Pedido(pedido_id.getId(), cliente, pedido_id.getProducto(), pedido_id.getCantidad(), pedido_id.getPrecio());

        } catch (DAOException e){ throw new ServiceException("SERVICE: No se pudo mostrar el pedido debido a un error.", e); }
    }
}