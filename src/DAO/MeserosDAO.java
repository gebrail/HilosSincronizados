/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.conexion;
import VO.MeserosVO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wilson
 */
public class MeserosDAO {

    public MeserosDAO() {
    }

    public LinkedList listarMeseros() {
        conexion cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexion();
            select = cn.getConnection().prepareCall("SELECT* FROM meseros;");
            rs = select.executeQuery();
            while (rs.next()) {
                MeserosVO meseroVO = new MeserosVO();
                meseroVO.setCedula(rs.getInt("ced_mesero"));
                meseroVO.setEstado(rs.getString("estado_mesero"));
                meseroVO.setContador(rs.getInt("contmesas"));
                datos.add(meseroVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(MeserosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }

    public boolean modificarMesero(MeserosVO mesero) {
        conexion cn = null;
        PreparedStatement update = null;
        try {
            cn = new conexion();
            boolean modificar = false;
            update = cn.getConnection().prepareStatement("UPDATE meseros SET contmesas=?,estado_mesero=? WHERE ced_mesero=?");

            update.setInt(1, mesero.getContador());
            update.setString(2, mesero.getEstado());
            update.setInt(3, mesero.getCedula());
            int r = update.executeUpdate();
            if (r != 0) {
                modificar = true;
            }
            return modificar;
        } catch (SQLException ex) {
            Logger.getLogger(MeserosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(update);
        }
    }

    public LinkedList listarMeseros2() {
        conexion cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexion();
            select = cn.getConnection().prepareCall("SELECT* FROM meseros;");
            rs = select.executeQuery();
            while (rs.next()) {
                MeserosVO meseroVO = new MeserosVO();
                meseroVO.setCedula(rs.getInt("ced_mesero"));
                meseroVO.setEstado(rs.getString("estado_mesero"));
                meseroVO.setContador(rs.getInt("contmesas"));
                datos.add(meseroVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(MeserosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }
    public boolean modificarMesero2(MeserosVO mesero) {
        conexion cn = null;
        PreparedStatement update = null;
        try {
            cn = new conexion();
            boolean modificar = false;
            update = cn.getConnection().prepareStatement("UPDATE meseros SET contmesas=?,estado_mesero=? WHERE ced_mesero=?");

            update.setInt(1, mesero.getContador());
            update.setString(2, mesero.getEstado());
            update.setInt(3, mesero.getCedula());
            int r = update.executeUpdate();
            if (r != 0) {
                modificar = true;
            }
            return modificar;
        } catch (SQLException ex) {
            Logger.getLogger(MeserosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(update);
        }
    }
}
