package com.busme_conductor.models.DAO;

import com.busme_conductor.interfaces.ConsultasBD;
import com.busme_conductor.models.DTO.Ruta;

import java.util.List;

public class RutaDAO implements ConsultasBD<Ruta> {
    @Override
    public boolean create(Ruta t) {
        return false;
    }

    @Override
    public boolean delete(Object key) {
        return false;
    }

    @Override
    public boolean update(Ruta t) {
        return false;
    }

    @Override
    public Ruta read(Object key) {
        return null;
    }

    @Override
    public List<Ruta> readAll() {
        return null;
    }
}
