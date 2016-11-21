package com.busme_conductor.models.DAO;

import com.busme_conductor.interfaces.ConsultasBD;
import com.busme_conductor.models.DTO.Registro;

import java.util.List;

public class RegistroDAO implements ConsultasBD<Registro> {


    @Override
    public boolean create(Registro t) {
        return false;
    }

    @Override
    public boolean delete(Object key) {
        return false;
    }

    @Override
    public boolean update(Registro t) {
        return false;
    }

    @Override
    public Registro read(Object key) {
        return null;
    }

    @Override
    public List<Registro> readAll() {
        return null;
    }
}
