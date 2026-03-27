package dao;

import exception.DAOException;
import model.InterfazDAO;
import model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductosDAO implements InterfazDAO<Producto> {
    private final Connection connection;

    public ProductosDAO(Connection connection) { this.connection = connection; }

    @Override
    public void insertar(Producto producto) throws DAOException {
        String sql = "INSERT INTO productos (nombre, precio, stock) VALUES (?,?,?)";

        try (PreparedStatement insert = connection.prepareStatement(sql)){
            insert.setString(1, producto.getNombre());
            insert.setDouble(2, producto.getPrecio());
            insert.setInt(3, producto.getStock());

            int filas = insert.executeUpdate();
            if (filas == 0) { throw new DAOException("DAO: No se ha completado la inserción del producto"); }

        } catch (SQLException e){ throw new DAOException("DAO: Error en el insert de productos, revise la consulta o los datos.", e); }

}

    @Override
    public void eliminar(int id) throws DAOException {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (PreparedStatement delete = connection.prepareStatement(sql)){
            delete.setInt(1, id);

            int filas = delete.executeUpdate();
            if (filas == 0) { throw new DAOException("DAO: No se ha completado la eliminación del producto"); }

        } catch (SQLException e){ throw new DAOException("DAO: Error en el insert de productos, revise la consulta o los datos.", e); }

    }

    public void modificar(int id, Double precio) throws DAOException {
        String sql = "UPDATE productos SET precio = ? WHERE id = ?";

        try(PreparedStatement update = connection.prepareStatement(sql)) {
            update.setDouble(1, precio);
            update.setInt(2, id);

            int filas = update.executeUpdate();
            if (filas == 0){throw new DAOException("DAO: No se ha completado la actualización de stock del producto"); }

        } catch (SQLException e){ throw new DAOException("DAO: Error en el update de productos, revise la consulta o los datos.", e); }
    }

    @Override
    public Optional<Producto> buscarPorID(int id) throws DAOException {
        String sql = "SELECT * FROM productos WHERE id = ?";
        Producto producto;

        try (PreparedStatement selectPorId = connection.prepareStatement(sql)) {
            selectPorId.setInt(1, id);

            try(ResultSet rs = selectPorId.executeQuery()){

                if (!rs.next()){ return Optional.empty(); }

                producto = new Producto(rs.getInt("id"),
                                        rs.getString("nombre"),
                                        rs.getDouble("precio"),
                                        rs.getInt("stock")
                        );

                return Optional.of(producto);
            }

        } catch (SQLException e){ throw new DAOException("DAO: Error en al buscar producto por id, revise la consulta o los datos.", e); }

    }

    @Override
    public List<Producto> listar() throws DAOException {
        String sql = "SELECT * FROM productos";

        try (PreparedStatement selectAll = connection.prepareStatement(sql);
            ResultSet rs = selectAll.executeQuery()) {

            if (!rs.next()){ return new ArrayList<>(); }

            List<Producto> productos = new ArrayList<>();

            do {
                productos.add( new Producto(rs.getInt("id"),
                                            rs.getString("nombre"),
                                            rs.getDouble("precio"),
                                            rs.getInt("stock")
                    )
                );

            } while (rs.next());

            return productos;

            } catch (SQLException e){ throw new DAOException("DAO: Error en el insert de productos, revise la consulta o los datos.", e); }
    }
}
