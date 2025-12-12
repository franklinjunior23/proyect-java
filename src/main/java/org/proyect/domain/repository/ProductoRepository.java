package org.proyect.domain.repository;

import org.proyect.config.Database;
import org.proyect.domain.models.Categoria;
import org.proyect.domain.models.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepository {

    private Categoria mapToCategoria(ResultSet rs) throws SQLException {
        Categoria c = new Categoria();
        c.setId(rs.getInt("categoria_id"));
        c.setNombre(rs.getString("categoria_nombre"));
        c.setDescripcion(rs.getString("categoria_descripcion"));
        return c;
    }

    public Producto mapToProducto(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setSku(rs.getString("sku"));
        p.setPrecioVenta(rs.getDouble("precio_venta"));
        p.setStockActual(rs.getInt("stock_actual"));
        // categoría
        if (rs.getInt("categoria_id") != 0) {
            Categoria c = mapToCategoria(rs);
            p.setCategoria(c);
        }
// fechas base
        p.setFechaCreacion(rs.getTimestamp("created_at").toLocalDateTime());
        p.setFechaActualizacion(rs.getTimestamp("updated_at").toLocalDateTime());

        return p;
    }

    // =========================================================
    // CREATE
    // =========================================================
    public Producto create(Producto producto) throws SQLException {

        String sql = """
                INSERT INTO producto (nombre, sku, precio_venta, stock_actual, categoria_id, created_at, updated_at)
                VALUES (?, ?, ?, ?, ?)
                RETURNING *;
                """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getSku());
            stmt.setDouble(3, producto.getPrecioVenta());
            stmt.setInt(4, producto.getStockActual());

            if (producto.getCategoria() != null) {
                stmt.setInt(5, producto.getCategoria().getId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapToProducto(rs);
            }
        }
        return null;
    }

    // =========================================================
    // FIND ALL
    // =========================================================
    public List<Producto> findAll() throws SQLException {

        List<Producto> productos = new ArrayList<>();

        String sql = """
                SELECT p.*, c.nombre AS categoria_nombre
                FROM producto p
                LEFT JOIN categoria c ON c.id = p.categoria_id
                ORDER BY p.id DESC
                """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                productos.add(mapToProducto(rs));
            }
        }
        return productos;
    }

    // =========================================================
    // FIND BY ID
    // =========================================================
    public Producto findById(int id) throws SQLException {

        String sql = """
                SELECT p.*, c.nombre AS categoria_nombre
                FROM producto p
                LEFT JOIN categoria c ON c.id = p.categoria_id
                WHERE p.id = ?
                """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapToProducto(rs);
            }
        }
        return null;
    }

    // =========================================================
    // FIND BY SKU
    // =========================================================
    public Producto findBySku(String sku) throws SQLException {

        String sql = """
                SELECT p.*, c.nombre AS categoria_nombre
                FROM producto p
                LEFT JOIN categoria c ON c.id = p.categoria_id
                WHERE sku = ?
                """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sku);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapToProducto(rs);
            }
        }
        return null;
    }

    // =========================================================
    // UPDATE
    // =========================================================
    public boolean update(Producto producto) throws SQLException {

        String sql = """
                UPDATE producto
                SET nombre = ?, sku = ?, precio_venta = ?, stock_actual = ?, categoria_id = ?
                WHERE id = ?
                """;

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getSku());
            stmt.setDouble(3, producto.getPrecioVenta());
            stmt.setInt(4, producto.getStockActual());

            if (producto.getCategoria() != null) {
                stmt.setInt(5, producto.getCategoria().getId());
            } else {
                stmt.setNull(5, Types.INTEGER);
            }

            stmt.setInt(6, producto.getId());

            return stmt.executeUpdate() > 0;
        }
    }
}
