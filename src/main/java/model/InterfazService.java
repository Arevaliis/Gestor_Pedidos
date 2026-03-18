package model;

import ejercicios.Gestor_Pedidos.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface InterfazService<T> {

    void insertar(T entidad) throws ServiceException;
    void modificar(int id, int cantidad) throws ServiceException;
    void eliminar(int id) throws ServiceException;
    Optional<T> buscarPorId(int id) throws ServiceException;
    List<T> listarTodos() throws ServiceException;
}