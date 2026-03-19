package dao;

import exception.DAOException;
import model.InterfazDAO;
import model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidosDAO implements InterfazDAO<Pedido> {
    private final Connection connection;

    public PedidosDAO(Connection connection){ this.connection = connection; }

    @Override
    public void insertar (Pedido pedido) throws DAOException {
        String sql = "INSERT INTO pedidos (cliente, producto, cantidad, precio) VALUES (?,?,?,?)";

        try(PreparedStatement insert = connection.prepareStatement(sql)){

            insert.setString(1, pedido.getCliente()); // TODO EN UN FUTURO TENER EN CUENTA QUE DEBE SER UN ID CLIENTE
            insert.setString(2, pedido.getProducto()); // TODO EN UN FUTURO TENER EN CUENTA QUE DEBE SER UN ID PRODUCTO
            insert.setInt(3, pedido.getCantidad());
            insert.setDouble(4, pedido.getPrecio());

            int filas = insert.executeUpdate();
            if (filas == 0) { throw new DAOException("DAO: No se ha completado la inserción"); }

        }catch (SQLException e){ throw new DAOException("DAO: Error en el insert, revise la consulta o los datos.", e); }
    }

    @Override
    public void eliminar(int id) throws DAOException{
        String sql = "DELETE FROM pedidos  WHERE id = ?";

        try(PreparedStatement delete = connection.prepareStatement(sql)){
            delete.setInt(1, id);

            int filas = delete.executeUpdate();
            if (filas == 0) { throw new DAOException("DAO: No se ha completado la eliminacion del pedido"); }

        }catch (SQLException e){ throw new DAOException("DAO: Error en el delete, revise la consulta o los datos.", e); }
    }

    @Override
    public void modificar(int id, int cantidad) throws DAOException {
        String sql = "UPDATE pedidos SET cantidad = ? WHERE id = ?";

        try(PreparedStatement update = connection.prepareStatement(sql)){
            update.setInt(1, cantidad);
            update.setInt(2, id);

            int filas = update.executeUpdate();
            if (filas == 0) { throw new DAOException("DAO: No se ha completado la modificación de la cantidad de pedido"); }

        }catch (SQLException e){ throw new DAOException("DAO: Error en el update, revise la consulta o los datos.", e); }
    }

    @Override
    public List<Pedido> listar() throws DAOException{
        String sql = "SELECT * FROM pedidos";
        List<Pedido> pedidos = new ArrayList<>();

        try(PreparedStatement selectAll = connection.prepareStatement(sql);
            ResultSet resultado = selectAll.executeQuery()){

            if (!resultado.next()){ return new ArrayList<>(); }

            do{
                pedidos.add( new Pedido(
                        resultado.getInt("id"),
                        resultado.getString("cliente_id"), // TODO EN UN FUTURO CAMBIAR POR INSTANCIA DE CLIENTE
                        resultado.getString("producto"), // TODO EN UN FUTURO CAMBIAR POR INSTANCIA DE PRODUCTO
                        resultado.getInt("cantidad"),
                        resultado.getDouble("precio")
                        )
                );
            } while (resultado.next());

        }catch (SQLException e){ throw new DAOException("DAO: Error al listar pedidos, revise la consulta o los datos.", e); }

        return pedidos;
    }

    public Optional<Pedido> listarPorId(int id) throws DAOException{
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        Pedido pedido;

        try(PreparedStatement selectAll = connection.prepareStatement(sql)){
            selectAll.setInt(1, id);

            try(ResultSet resultado = selectAll.executeQuery()) {

                if (!resultado.next()) { return Optional.empty(); }

                pedido = new Pedido(
                        resultado.getInt("id"),
                        resultado.getString("cliente_id"), // TODO EN UN FUTURO CAMBIAR POR INSTANCIA DE CLIENTE
                        resultado.getString("producto"), // TODO EN UN FUTURO CAMBIAR POR INSTANCIA DE CLIENTE
                        resultado.getInt("cantidad"),
                        resultado.getDouble("precio")
                );
            }
        }catch (SQLException e){ throw new DAOException("DAO: Error al buscar por ID, revise la consulta o los datos.", e); }

        return Optional.of(pedido);
    }
}