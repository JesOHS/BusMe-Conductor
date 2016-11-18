package com.busme_conductor.models.DTO;

import com.busme_conductor.models.DAO.EstacionDAO;

import java.util.List;

public class Ruta {
    String idRuta;
    List<Estacion> estaciones;

    public Ruta(String idRuta) {
        this.idRuta = idRuta;
        EstacionDAO estacionDAO = new EstacionDAO();
        estaciones = estacionDAO.obtenerEstacionesDeLaRuta(idRuta);
    }

    public Ruta(String idRuta, List<Estacion> estaciones) {
        this.idRuta = idRuta;
        this.estaciones = estaciones;
    }

    public String getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public List<Estacion> getEstaciones() {
        return estaciones;
    }

    public void setEstaciones(List<Estacion> estaciones) {
        this.estaciones = estaciones;
    }
}