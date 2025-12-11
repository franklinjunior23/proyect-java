package org.proyect.domain.models;

public class Proveedor extends ModelBase {

    private String razonSocial;
    private String nombreContacto;
    private String correo;
    private String telefono;

    public Proveedor() {}

    public Proveedor(Integer id, String razonSocial, String nombreContacto, String correo, String telefono) {
        this.id = id;
        this.razonSocial = razonSocial;
        this.nombreContacto = nombreContacto;
        this.correo = correo;
        this.telefono = telefono;
    }

    public String getRazonSocial() { return razonSocial; }
    public void setRazonSocial(String razonSocial) { this.razonSocial = razonSocial; }

    public String getNombreContacto() { return nombreContacto; }
    public void setNombreContacto(String nombreContacto) { this.nombreContacto = nombreContacto; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String toString() {
        return razonSocial;
    }
}