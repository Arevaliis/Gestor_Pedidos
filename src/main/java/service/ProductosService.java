package service;

import dao.ProductosDAO;
import exception.DAOException;
import exception.ServiceException;
import model.InterfazService;
import model.Producto;

import java.util.List;

public class ProductosService implements InterfazService<Producto> {
    private final ProductosDAO productosDAO;

    public ProductosService(ProductosDAO productosDAO) { this.productosDAO = productosDAO; }

    @Override
    public void insertar(Producto producto) throws ServiceException {
        try{
            List<String> nombreProductos = listarTodos().stream().map(Producto::getNombre).toList();
            if (nombreProductos.contains(producto.getNombre())){ throw new ServiceException("El nombre ingresado ya existe"); }

            productosDAO.insertar(producto);
        } catch (DAOException e) { throw new ServiceException("SERVICE: No se pudo insertar el producto debido a un error.", e); }
    }

    @Override
    public void eliminar(int id) throws ServiceException {
        try {
            productosDAO.eliminar(id);

        }catch (DAOException e) { throw new ServiceException("SERVICE: No se pudo eliminar el producto debido a un error.", e); }
    }

    public void modificar(int id, double cantidad) throws ServiceException{
        try {

            productosDAO.modificar(id, cantidad);

        }catch (DAOException e) { throw new ServiceException("SERVICE: No se pudo modificar el producto debido a un error.", e); }
    }

    @Override
    public Producto buscarPorId(int id) throws ServiceException {
        try {
            return productosDAO.buscarPorID(id)
                    .orElseThrow(() -> new ServiceException("No existe el producto"));

        }catch (DAOException e) { throw new ServiceException("SERVICE: No se pudo mostrar el producto debido a un error.", e); }
    }

    @Override
    public List<Producto> listarTodos() throws ServiceException {
        try {
            List<Producto> productos = productosDAO.listar();

            if (productos.isEmpty()) { throw new ServiceException("No hay productos registrados en la base de datos"); }

            return productos;

        }catch (DAOException e) { throw new ServiceException("SERVICE: No se pudo mostrar los productos debido a un error.", e); }
    }
}