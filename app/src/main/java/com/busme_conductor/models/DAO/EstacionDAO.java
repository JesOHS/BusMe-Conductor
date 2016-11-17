package com.busme_conductor.models.DAO;

import com.busme_conductor.interfaces.ConsultasBD;
import com.busme_conductor.models.ConexionBD;
import com.busme_conductor.models.DTO.Estacion;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class EstacionDAO implements ConsultasBD<Estacion> {
    private static final String SQL_INSERT = "INSERT INTO estaciones(id_ruta, geom, orden_estacion, es_terminal) VALUES (?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM estaciones WHERE id_estacion = ?";
    private static final String SQL_UPDATE = "UPDATE estaciones SET id_ruta = ?, geom = ?, orden_estacion = ?, es_terminal = ? WHERE id_estacion = ?";
    private static final String SQL_LEERRUTA = "SELECT * FROM estaciones WHERE id_ruta = ?";
    private static final ConexionBD conexion = ConexionBD.connect();

    @Override
    public boolean create(Estacion t) {
        PreparedStatement ps;
        try {
            ps = conexion.getConnection().prepareStatement(SQL_INSERT);
            ps.setString(1, t.getIdRuta());
            ps.setObject(2, t.getGeom());
            ps.setInt(3, t.getOrdenEstacion());
            ps.setBoolean(4, t.isEsTerminal());
            if(ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.closeConnection();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = conexion.getConnection().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if(ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.closeConnection();
        }
        return false;
    }

    @Override
    public boolean update(Estacion t) {
        PreparedStatement ps;
        try {
            ps = conexion.getConnection().prepareStatement(SQL_UPDATE);
            ps.setString(1, t.getIdRuta());
            ps.setObject(2, t.getGeom());
            ps.setInt(3, t.getOrdenEstacion());
            ps.setBoolean(4, t.isEsTerminal());
            ps.setInt(5, t.getIdEstacion());
            if(ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.closeConnection();
        }
        return false;
    }

    @Override
    public Estacion read(Object key) {
        return null;
    }

    @Override
    public List<Estacion> readAll() {
        return null;
    }
}
