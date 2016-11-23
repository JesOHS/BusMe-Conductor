package com.busme_conductor.fragments;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.location.Location;
import android.util.Log;

import com.busme_conductor.R;
import com.busme_conductor.models.DAO.CamionDAO;
import com.busme_conductor.models.DTO.Camion;
import com.busme_conductor.models.DTO.Registro;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.postgis.PGgeometry;
import org.postgis.Point;

public class BusMeConductor extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    private Registro registro;
    double latitud = 0.0;
    double longitud = 0.0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        registro = getIntent().getExtras().getParcelable("Registro");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_me_conductor);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        miUbicacion();
    }

    private void agregarPosicion(double latitud, double longitud) {
        LatLng coordenadas = new LatLng(latitud, longitud);

        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null) {
            marcador.remove();
        }
        marcador = mMap.addMarker(new MarkerOptions()
                .position(coordenadas)
                .title("Yo")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.iconito)));
        if(count == 0){
            mMap.animateCamera(miUbicacion);
            count++;
        }
    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            latitud = location.getLatitude();
            longitud = location.getLongitude();
            // Objeto que se encarga de manejar las consultas a la BD
            CamionDAO camionDAO = new CamionDAO();
            // Obtiene un objeto camion de la base de datos
            Camion camion = camionDAO.read(registro.getId_unidad());
            Point point = new Point(latitud, longitud);
            // Crea un nuevo geom usando las coordenadas del punto actual
            PGgeometry geom = new PGgeometry(point);
            // Especifica el srid
            geom.getGeometry().setSrid(4326);
            // Modifica el geom(ubicacion) del camion
            camion.setGeom(geom);
            // Actualiza la base de datos
            camionDAO.update(camion);
            agregarPosicion(latitud, longitud);
        }
    }

    LocationListener locListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizarUbicacion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void miUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1500, 0, locListener);
    }
}
