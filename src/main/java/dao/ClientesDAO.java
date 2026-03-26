package dao;

import exception.DAOException;
import model.Cliente;
import model.InterfazDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientesDAO implements InterfazDAO<Cliente> {
    private final Connection connection;

    public ClientesDAO(Connection connection) { this.connection = connection; }

    @Override
    public void insertar(Cliente cliente) throws DAOException {
        String sql = "INSERT INTO clientes (nombre, email) VALUES (?, ?)";

        try(PreparedStatement insert = connection.prepareStatement(sql)) {
            insert.setString(1, cliente.getNombre());
            insert.setString(2, cliente.getEmail());
            int filas = insert.executeUpdate();

            if (filas == 0) { throw new DAOException("DAO: No se ha completado la inserción del cliente"); }

        } catch (SQLException e){ throw new DAOException("DAO: Error en el insert de clientes, revise la consulta o los datos.", e); }

    }

    @Override
    public void eliminar(int id) throws DAOException {
        String sql = "DELETE FROM clientes WHERE id = ?";

        try(PreparedStatement delete = connection.prepareStatement(sql)) {
            delete.setInt(1, id);

            int filas = delete.executeUpdate();
            if (filas == 0){throw new DAOException("DAO: No se ha completado la eliminación del cliente"); }

        } catch (SQLException e){ throw new DAOException("DAO: Error en el delete de clientes, revise la consulta o los datos.", e); }
    }

    public void modificar(int id, String email) throws DAOException {
        String sql = "UPDATE clientes SET email = ? WHERE id = ?";

        try(PreparedStatement update = connection.prepareStatement(sql)) {
            update.setString(1, email);
            update.setInt(2, id);

            int filas = update.executeUpdate();
            if (filas == 0){throw new DAOException("DAO: No se ha completado la actualización del cliente"); }

        } catch (SQLException e){ throw new DAOException("DAO: Error en el update de clientes, revise la consulta o los datos.", e); }
    }

    @Override
    public List<Cliente> listar() throws DAOException {
        String sql = "SELECT * FROM clientes";
        List<Cliente> clientes = new ArrayList<>();

        try(PreparedStatement selectAll = connection.prepareStatement(sql);
            ResultSet resultado = selectAll.executeQuery()) {

            if (! resultado.next()){ return new ArrayList<>(); }

            do {
                clientes.add(
                        new Cliente(resultado.getInt("id"),
                                    resultado.getString("nombre"),
                                    resultado.getString("email"))
                );
            } while (resultado.next());

            return clientes;

        } catch (SQLException e){ throw new DAOException("DAO: Error en al listar los clientes, revise la consulta o los datos.", e); }
    }

    @Override
    public Optional<Cliente> buscarPorID(int id) throws DAOException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        Cliente cliente;

        try(PreparedStatement select = connection.prepareStatement(sql)){
            select.setInt(1, id);

            try(ResultSet resultado = select.executeQuery()) {

                if (! resultado.next()){ return Optional.empty(); }
                cliente = new Cliente( resultado.getInt("id"),
                        resultado.getString("nombre"),
                        resultado.getString("email"));

                return Optional.of(cliente);
        }


        } catch (SQLException e){ throw new DAOException("DAO: Error en el listar cliente, revise la consulta o los datos.", e); }
    }
}