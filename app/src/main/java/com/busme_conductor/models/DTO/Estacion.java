package com.busme_conductor.models.DTO;

import org.postgis.PGgeometry;

public class Estacion {
    int idEstacion;
    String idRuta;
    PGgeometry geom;
    int ordenEstacion;
    boolean esTerminal;

    public Estacion(String idRuta, PGgeometry geom, int ordenEstacion, boolean esTerminal) {
        this.idRuta = idRuta;
        this.geom = geom;
        this.ordenEstacion = ordenEstacion;
        this.esTerminal = esTerminal;
    }

    public Estacion(int idEstacion, String idRuta, PGgeometry geom, int ordenEstacion, boolean esTerminal) {
        this.idEstacion = idEstacion;
        this.idRuta = idRuta;
        this.geom = geom;
        this.ordenEstacion = ordenEstacion;
        this.esTerminal = esTerminal;
    }

    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    public String getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public PGgeometry getGeom() {
        return geom;
    }

    public void setGeom(PGgeometry geom) {
        this.geom = geom;
    }

    public int getOrdenEstacion() {
        return ordenEstacion;
    }

    public void setOrdenEstacion(int ordenEstacion) {
        this.ordenEstacion = ordenEstacion;
    }

    public boolean isEsTerminal() {
        return esTerminal;
    }

    public void setEsTerminal(boolean esTerminal) {
        this.esTerminal = esTerminal;
    }
}
