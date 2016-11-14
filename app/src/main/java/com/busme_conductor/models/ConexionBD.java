package com.busme_conductor.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConexionBD {
    /*
    * Pregunta si la instancia ya esta creada
    * para usar la misma y si no se crea
    * una nueva
    */
    private static ConexionBD instance; // Singleton
    private Connection cnn;

    /*
     * Constructor privado
     * para no crear instancias desde afuera
     */
    private ConexionBD() {
        String url = "jdbc:postgresql://ec2-23-23-226-24.compute-1.amazonaws.com/d7naf0g01olcpi";
        Properties props = new Properties();
        props.setProperty("user", "lhmukxzksrxdac");
        props.setProperty("password", "LD1-vOYp3VJ07QKfZ69UB0eXMm");
        props.setProperty("ssl", "true");
        try {
            Connection conn = DriverManager.getConnection(url, props);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * Unica forma de crear una conexion y que aplica el Singleton.
     * La palabra synchronized hace una lista de espera para que
     * si hay muchos usuarios, estos esperen su turno hasta que termine
     * el usuario que lo está usando
     */
    public synchronized static ConexionBD connect() {
        if (instance == null) {
            instance = new ConexionBD();
        }
        return instance;
    }

    public Connection getConnection() {
        return cnn;
    }

    public void closeConnection() {
        instance = null;
    }

}
