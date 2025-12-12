package org.proyect.service;

import org.proyect.domain.models.Producto;
import org.proyect.domain.repository.ProductoRepository;

import java.sql.SQLException;
import java.util.List;

public class ProductoService {
    private ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    public List<Producto> findAll() throws SQLException {
        return productoRepository.findAll();
    }
}
