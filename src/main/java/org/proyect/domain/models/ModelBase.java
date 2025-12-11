package org.proyect.domain.models;

import java.time.LocalDateTime;

public abstract class ModelBase {

    protected Integer id;
    protected LocalDateTime fecha_creacion;
    protected LocalDateTime fecha_actualizacion;

    public ModelBase() {
        this.fecha_creacion = LocalDateTime.now();
        this.fecha_actualizacion = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getFechaCreacion() {
        return fecha_creacion;
    }

    public void setFechaCreacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fecha_actualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }
}
