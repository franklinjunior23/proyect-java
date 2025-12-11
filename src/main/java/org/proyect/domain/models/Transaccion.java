package org.proyect.domain.models;

import org.proyect.domain.enums.TipoMovimiento;



public class Transaccion extends ModelBase {

    public enum TipoMovimiento { ENTRADA, SALIDA, AJUSTE }

    private Producto producto;
    private Usuario usuario;
    private Proveedor proveedor; // Puede ser null en SALIDA o AJUSTE
    private TipoMovimiento tipoMovimiento;
    private int cantidad;
    private String motivo;

    public Transaccion() {}

    public Transaccion(Integer id, Producto producto, Usuario usuario, Proveedor proveedor,
                       TipoMovimiento tipoMovimiento, int cantidad, String motivo) {
        this.id = id;
        this.producto = producto;
        this.usuario = usuario;
        this.proveedor = proveedor;
        this.tipoMovimiento = tipoMovimiento;
        this.cantidad = cantidad;
        this.motivo = motivo;
    }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Proveedor getProveedor() { return proveedor; }
    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }

    public TipoMovimiento getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public void aplicarMovimiento() {
        switch (tipoMovimiento) {
            case ENTRADA:
                producto.aumentarStock(cantidad);
                break;
            case SALIDA:
                producto.disminuirStock(cantidad);
                break;
            case AJUSTE:
                producto.setStockActual(cantidad);
                break;
        }
    }

    @Override
    public String toString() {
        return tipoMovimiento + " - " + cantidad + " de " + producto.getNombre();
    }
}

