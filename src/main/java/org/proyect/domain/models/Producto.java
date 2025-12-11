package org.proyect.domain.models;


import java.math.BigDecimal;

public class Producto extends ModelBase {

    private String nombre;
    private String sku;
    private double precioVenta;
    private int stockActual;
    private Categoria categoria;

    public Producto() {}

    public Producto(Integer id, String nombre, String sku, double precioVenta, int stockActual, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.sku = sku;
        this.precioVenta = precioVenta;
        this.stockActual = stockActual;
        this.categoria = categoria;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public int getStockActual() { return stockActual; }
    public void setStockActual(int stockActual) { this.stockActual = stockActual; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public void aumentarStock(int cantidad) {
        this.stockActual += cantidad;
    }

    public void disminuirStock(int cantidad) {
        if (cantidad > stockActual) {
            throw new IllegalArgumentException("Stock insuficiente para salida.");
        }
        this.stockActual -= cantidad;
    }

    @Override
    public String toString() {
        return nombre + " (" + sku + ")";
    }
}