package com.busme_conductor.controladores;

import android.graphics.Color;
import android.os.AsyncTask;

import com.busme_conductor.fragments.BusMeConductor;
import com.busme_conductor.modelos.DAO.CamionDAO;
import com.busme_conductor.modelos.DAO.RutaDAO;
import com.busme_conductor.modelos.DTO.Camion;
import com.busme_conductor.modelos.DTO.Ruta;

import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;



public class Pintor extends AsyncTask<String, String, Void> {

    String encodedPolyline;
    String color;


    public Pintor(String color) {
        this.color=color;
    }



    @Override
    protected Void doInBackground(String... params) {
        RutaDAO rutaDAO = new RutaDAO();
        CamionDAO camionDAO= new CamionDAO();
        Camion camion = camionDAO.read(BusMeConductor.getRegistro().getId_unidad());
        Ruta ruta = rutaDAO.read(camion.getIdRuta());
        // Obtener la polilinea codificada de la bd
        encodedPolyline = rutaDAO.obtenerPolilinea(ruta.getIdRuta(), BusMeConductor.getRecorriendo());

        return null;
    }

    protected void onPostExecute(Void result) {
        //googleMap.clear();
        limpiarElementosDelMapa();

        pintarRuta();

    }

    private void limpiarElementosDelMapa() {
        if(BusMeConductor.getLinea() != null) {
            BusMeConductor.getLinea().remove();
        }
    }


    private void pintarRuta() {
        // Crear el objeto para agregar la polilinea
        PolylineOptions polylineOptions = new PolylineOptions();
        if (color=="rojo"){
            polylineOptions.color(Color.RED);

        }else if(color=="azul"){
            polylineOptions.color(Color.BLUE);
        }

        polylineOptions.width(20);
        polylineOptions.geodesic(true);
        // Agregar la polilinea decodificada con PolyUtil.decode()
        polylineOptions.addAll(PolyUtil.decode(encodedPolyline));
        Polyline linea = BusMeConductor.getmMap().addPolyline(polylineOptions);
        linea.setVisible(true);
        linea.setClickable(false);
        BusMeConductor.setLinea(linea);
    }





}