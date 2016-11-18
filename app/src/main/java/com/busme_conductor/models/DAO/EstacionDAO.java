package com.busme_conductor.models.DAO;

import com.busme_conductor.interfaces.ConsultasBD;
import com.busme_conductor.models.ConexionBD;
import com.busme_conductor.models.DTO.Estacion;

import org.postgis.PGgeometry;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstacionDAO implements ConsultasBD<Estacion> {
    private static final String SQL_INSERT = "INSERT INTO estaciones(id_ruta, geom, orden_estacion, es_terminal) VALUES (?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM estaciones WHERE id_estacion = ?";
    private static final String SQL_UPDATE = "UPDATE estaciones SET id_ruta = ?, geom = ?, orden_estacion = ?, es_terminal = ? WHERE id_estacion = ?";
    private static final String SQL_READ = "SELECT * FROM estaciones WHERE id_estacion = ?";
    private static final String SQL_READALL = "SELECT * FROM estaciones";
    private static final String SQL_OBTENERCONRUTA = "SELECT * FROM estaciones WHERE id_ruta = ?";
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
        PreparedStatement ps;
        ResultSet rs;
        Estacion est = null;
        try {
            ps = conexion.getConnection().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while(rs.next()) {
                est = new Estacion(rs.getInt(1), rs.getString(2), (PGgeometry) rs.getObject(3), rs.getInt(4), rs.getBoolean(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.closeConnection();
        }
        return est;
    }

    @Override
    public List<Estacion> readAll() {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Estacion> estaciones = new ArrayList();
        try {
            ps = conexion.getConnection().prepareStatement(SQL_READALL);
            rs = ps.executeQuery();
            while(rs.next()) {
                estaciones.add(new Estacion(rs.getInt(1), rs.getString(2), (PGgeometry) rs.getObject(3), rs.getInt(4), rs.getBoolean(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.closeConnection();
        }
        return estaciones;
    }

    public List<Estacion> obtenerEstacionesDeLaRuta(Object key) {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Estacion> estaciones = new ArrayList();
        try {
            ps = conexion.getConnection().prepareStatement(SQL_OBTENERCONRUTA);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while(rs.next()) {
                estaciones.add(new Estacion(rs.getInt(1), rs.getString(2), (PGgeometry) rs.getObject(3), rs.getInt(4), rs.getBoolean(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.closeConnection();
        }
        return estaciones;
    }
}
