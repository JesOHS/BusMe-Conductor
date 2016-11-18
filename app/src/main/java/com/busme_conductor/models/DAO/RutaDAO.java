package com.busme_conductor.models.DAO;

import com.busme_conductor.interfaces.ConsultasBD;
import com.busme_conductor.models.ConexionBD;
import com.busme_conductor.models.DTO.Ruta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RutaDAO implements ConsultasBD<Ruta> {
    private static final String SQL_INSERT = "INSERT INTO rutas(id_ruta) VALUES (?)";
    private static final String SQL_DELETE = "DELETE FROM rutas WHERE id_ruta = ?";
    private static final String SQL_UPDATE = "UPDATE rutas SET id_ruta = ? WHERE id_ruta = ?";
    private static final String SQL_READ = "SELECT * FROM rutas WHERE id_ruta = ?";
    private static final String SQL_READALL = "SELECT * FROM rutas";
    private static final ConexionBD conexion = ConexionBD.connect();

    @Override
    public boolean create(Ruta t) {
        PreparedStatement ps;
        try {
            ps = conexion.getConnection().prepareStatement(SQL_INSERT);
            ps.setString(1, t.getIdRuta());
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
    public boolean update(Ruta t) {
        PreparedStatement ps;
        try {
            ps = conexion.getConnection().prepareStatement(SQL_UPDATE);
            ps.setString(1, t.getIdRuta());
            /* Esto no funcionara posiblemente se tenga que modificar
                la base de datos para agregar una columna donde venga la ruta
             */
            ps.setString(2, t.getIdRuta());
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
    public Ruta read(Object key) {
        PreparedStatement ps;
        ResultSet rs;
        Ruta ruta = null;
        try {
            ps = conexion.getConnection().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while(rs.next()) {
                ruta = new Ruta(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.closeConnection();
        }
        return ruta;
    }

    @Override
    public List<Ruta> readAll() {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Ruta> rutas = new ArrayList<>();
        try {
            ps = conexion.getConnection().prepareStatement(SQL_READALL);
            rs = ps.executeQuery();
            while(rs.next()) {
                rutas.add(new Ruta(rs.getString(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.closeConnection();
        }
        return rutas;
    }

}