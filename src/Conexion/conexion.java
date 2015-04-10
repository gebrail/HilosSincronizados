/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilson
 */
public class conexion {

    /*
     *
     * @author Wilson
     */
    private String login = "postgres";
    private String password = "tik0dcmg";
    private String url = "jdbc:postgresql://localhost:5432/Restaurante";
    private Connection conn;

    /*
     * Constructor de connexion
     */
    public conexion() {
        try {
            //Obtenemos el driver de para postgres
            Class.forName("org.postgresql.Driver");
            //Obtenemos la conexión
            conn = DriverManager.getConnection(url, login, password);
            if (!conn.isClosed()) {
              //  System.out.println("Conectado...!");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }

    /*
     * Permite retornar la conexión
     */
    public Connection getConnection() {
        return conn;
    }

    /*
     * Desconexion
     */
    public void desconectar() {
        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cerrarStatement(Statement... statements) {
        for (Statement statement : statements) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException ex) {
                    Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void cerrarResultSet(ResultSet... results) {
        for (ResultSet rs : results) {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
