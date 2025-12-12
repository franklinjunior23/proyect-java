package org.proyect.controllers;

import org.proyect.domain.models.Producto;
import org.proyect.service.ProductoService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private ProductoService productoService;

    public ProductController(ProductoService productoService) {
        this.productoService = productoService;
    }

    public List<Producto> findAllProducts() throws SQLException {
        try {
            return productoService.findAll();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return new ArrayList<>();
        }
    }
}
