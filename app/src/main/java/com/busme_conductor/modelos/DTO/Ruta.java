package com.busme_conductor.modelos.DTO;

public class Ruta {
    String idRuta;
    String polilinea1;
    String polilinea2;

    public Ruta(String polilinea1) {
        this.polilinea1 = polilinea1;
    }

    public Ruta(String idRuta, String polilinea1, String polilinea2) {
        this.idRuta = idRuta;
        this.polilinea1 = polilinea1;
        this.polilinea2 = polilinea2;
    }

    public Ruta(String idRuta, String polilinea1) {
        this.idRuta = idRuta;
        this.polilinea1 = polilinea1;
    }

    public String getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(String idRuta) {
        this.idRuta = idRuta;
    }

    public String getPolilinea1() {
        return polilinea1;
    }

    public void setPolilinea1(String polilinea1) {
        this.polilinea1 = polilinea1;
    }
}