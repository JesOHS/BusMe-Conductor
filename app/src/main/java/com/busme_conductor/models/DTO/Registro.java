package com.busme_conductor.models.DTO;

public class Registro {
    String id_unidad;
    String clave;

    public Registro(String id_unidad, String clave) {
        this.id_unidad = id_unidad;
        this.clave = clave;
    }

    public String getId_unidad() {
        return id_unidad;
    }

    public void setId_unidad(String id_unidad) {
        this.id_unidad = id_unidad;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
