package model;

import exception.ServiceException;

import java.util.List;

public interface InterfazService<T> {

    void insertar(T entidad) throws ServiceException;
    void eliminar(int id) throws ServiceException;
    T buscarPorId(int id) throws ServiceException;
    List<T> listarTodos() throws ServiceException;
}