package service;

import dao.ClientesDAO;
import exception.DAOException;
import exception.ServiceException;
import model.Cliente;
import model.InterfazService;

import java.util.List;

public class ClientesService implements InterfazService<Cliente> {
    private final ClientesDAO clientesDAO;

    public ClientesService(ClientesDAO clientesDAO ) { this.clientesDAO = clientesDAO; }

    @Override
    public void insertar(Cliente cliente) throws ServiceException {
        try{
            List<String> emailClientes = listarTodos().stream().map(Cliente::getEmail).toList();
            if (emailClientes.contains(cliente.getEmail())){ throw new ServiceException("El email ingresado ya existe"); }

            clientesDAO.insertar(cliente);
        } catch (DAOException e) { throw new ServiceException("SERVICE: No se pudo insertar el cliente debido a un error.", e); }
    }

    @Override
    public void eliminar(int id) throws ServiceException {
        try {
            clientesDAO.eliminar(id);
        }catch (DAOException e) { throw new ServiceException("SERVICE: No se pudo eliminar al cliente debido a un error.", e); }

    }

    public void modificar(int id, String email) throws ServiceException {
        try {
            List<String> emailClientes = listarTodos().stream().map(Cliente::getEmail).toList();
            if (emailClientes.contains(email)){ throw new ServiceException("El email ingresado ya existe"); }

            clientesDAO.modificar(id, email);

        }catch (DAOException e) { throw new ServiceException("SERVICE: No se pudo modificar el cliente debido a un error.", e); }
    }

    @Override
    public Cliente buscarPorId(int id) throws ServiceException {
        try {
            return clientesDAO.buscarPorID(id)
                    .orElseThrow(() -> new ServiceException("No existe el cliente"));

        }catch (DAOException e) { throw new ServiceException("SERVICE: No se pudo mostrar el cliente debido a un error.", e); }
    }

    @Override
    public List<Cliente> listarTodos() throws ServiceException {
        try {
            List<Cliente> clientes = clientesDAO.listar();

            if (clientes.isEmpty()) { throw new ServiceException("No hay clientes registrados en la base de datos"); }

            return clientes;

        }catch (DAOException e) { throw new ServiceException("SERVICE: No se pudo mostrar los clientes debido a un error.", e); }
    }
}