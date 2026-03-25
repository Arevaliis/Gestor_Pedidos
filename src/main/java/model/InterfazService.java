package model;

import exception.ServiceException;

import java.util.List;
import java.util.Optional;

public interface InterfazService<T> {

    void insertar(T entidad) throws ServiceException;
    void eliminar(int id) throws ServiceException;
    Optional<T> buscarPorId(int id) throws ServiceException;
    List<T> listarTodos() throws ServiceException;
}