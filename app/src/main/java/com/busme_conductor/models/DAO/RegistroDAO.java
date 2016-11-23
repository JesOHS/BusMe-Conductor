package com.busme_conductor.models.DAO;

import android.util.Log;

import com.busme_conductor.interfaces.ConsultasBD;
import com.busme_conductor.models.ConexionBD;
import com.busme_conductor.models.DTO.Registro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroDAO implements ConsultasBD<Registro> {
    private static final String SQL_INSERT = "INSERT INTO registros(clave, id_unidad) VALUES (?, ?)";
    private static final String SQL_DELETE = "DELETE FROM registros WHERE id_unidad = ?";
    private static final String SQL_UPDATE = "UPDATE registros SET clave = ? WHERE id_unidad = ?";
    private static final String SQL_READ = "SELECT * FROM registros WHERE id_unidad = ?";
    private static final String SQL_VALIDARLOGIN = "SELECT * FROM registros WHERE id_unidad = ? AND clave = ?";
    private static final String SQL_READALL = "SELECT * FROM registros";
    private static final ConexionBD conexion = ConexionBD.connect();

    @Override
    public boolean create(Registro t) {
        PreparedStatement ps;
        try {
            ps = conexion.getConexion().prepareStatement(SQL_INSERT);
            ps.setString(1, t.getClave());
            ps.setString(2, t.getId_unidad());
            if(ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try {
            ps = conexion.getConexion().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());
            if(ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Registro t) {
        PreparedStatement ps;
        try {
            ps = conexion.getConexion().prepareStatement(SQL_UPDATE);
            ps.setString(1, t.getClave());
            /* Esto no funcionara posiblemente se tenga que modificar
                la base de datos para agregar una columna donde venga la ruta
             */
            ps.setString(2, t.getId_unidad());
            if(ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        Log.i("DEBUG", "NO se encontro");
        return false;
    }

    @Override
    public Registro read(Object key) {
        PreparedStatement ps;
        ResultSet rs;
        Registro registro = null;
        try {
            Log.i("DEBUG", "" + (conexion.getConexion() == null));
            ps = conexion.getConexion().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while(rs.next()) {
                registro = new Registro(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return registro;
    }

    public Registro read(String clave, String idUnidad) {
        PreparedStatement ps;
        ResultSet rs;
        Registro registro = null;
        try {
            ps = conexion.getConexion().prepareStatement(SQL_VALIDARLOGIN);
            ps.setString(1, idUnidad);
            ps.setString(2, clave);
            rs = ps.executeQuery();
            while(rs.next()) {
                registro = new Registro(rs.getString(2), rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return registro;
    }

    @Override
    public List<Registro> readAll() {
        PreparedStatement ps;
        ResultSet rs;
        ArrayList<Registro> registros = new ArrayList<>();
        try {
            ps = conexion.getConexion().prepareStatement(SQL_READALL);
            rs = ps.executeQuery();
            while(rs.next()) {
                registros.add(new Registro(rs.getString(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexion.cerrarConexion();
        }
        return registros;
    }
}
