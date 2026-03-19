package model;

import exception.DAOException;

import java.util.List;

public interface InterfazDAO<T> {

    void insertar(T generico) throws DAOException;
    void eliminar (int id) throws DAOException;
    void modificar(int id, int cantidad) throws DAOException;
    List<T> listar() throws DAOException;
}