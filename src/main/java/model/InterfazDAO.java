package model;

import exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface InterfazDAO<T> {

    void insertar(T generico) throws DAOException;
    void eliminar (int id) throws DAOException;
    Optional<T> buscarPorID(int id) throws DAOException;
    List<T> listar() throws DAOException;
}