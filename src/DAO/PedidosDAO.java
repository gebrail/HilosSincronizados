/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.conexion;
import VO.PedidosVO;
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
public class PedidosDAO {

    public PedidosDAO() {
    }

    public boolean registrarPedido(PedidosVO pedido) {
        conexion cn = null;
        PreparedStatement insert = null;
        try {
            cn = new conexion();
            boolean registro = false;
            insert = cn.getConnection().prepareStatement("INSERT INTO pedidos (id_mesa, id_plato, ced_mesero, fecha_pedi, estado_pedi, Hora_pedi) Values(?,?,?,?,?,?);");
            insert.setInt(1, pedido.getId_mesa());
            insert.setInt(2, pedido.getId_plato());
            insert.setInt(3, pedido.getCedula_mesero());
            insert.setDate(4, pedido.getFecha());
            insert.setString(5, pedido.getEstado_pedido());
            insert.setString(6, pedido.getHora_pedido());
            int r = insert.executeUpdate();
            if (r != 0) {
                registro = true;
            }
            return registro;
        } catch (SQLException ex) {
            Logger.getLogger(PedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(insert);
        }
    }

    public LinkedList listarPedidos() {
        conexion cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexion();
            select = cn.getConnection().prepareCall("SELECT* FROM pedidos;");
            rs = select.executeQuery();
            while (rs.next()) {
                PedidosVO pedidoVO = new PedidosVO();
                pedidoVO.setId_pedido(rs.getInt("id_pedi"));
                pedidoVO.setId_mesa(rs.getInt("id_mesa"));
                pedidoVO.setId_plato(rs.getInt("id_plato"));
                pedidoVO.setCedula_mesero(rs.getInt("ced_mesero"));
                pedidoVO.setFecha(rs.getDate("fecha_pedi"));
                pedidoVO.setEstado_pedido(rs.getString("estado_pedi"));
                pedidoVO.setHora_pedido(rs.getString("hora_pedi"));
                datos.add(pedidoVO);
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

    public boolean modificarpedido(PedidosVO pedidosxd) {
        conexion cn = null;
        PreparedStatement update = null;
        try {
            cn = new conexion();
            boolean modificar = false;
            update = cn.getConnection().prepareStatement("UPDATE pedidos SET estado_pedi=? WHERE id_pedi=?");
            update.setString(1, pedidosxd.getEstado_pedido());
            update.setInt(2, pedidosxd.getId_pedido());
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
     public LinkedList listarPedidos2() {
        conexion cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexion();
            select = cn.getConnection().prepareCall("SELECT* FROM pedidos where estado_pedi='En Proceso';");
            rs = select.executeQuery();
            while (rs.next()) {
                PedidosVO pedidoVO = new PedidosVO();
                pedidoVO.setId_pedido(rs.getInt("id_pedi"));
                pedidoVO.setId_mesa(rs.getInt("id_mesa"));
                pedidoVO.setId_plato(rs.getInt("id_plato"));
                pedidoVO.setCedula_mesero(rs.getInt("ced_mesero"));
                pedidoVO.setFecha(rs.getDate("fecha_pedi"));
                pedidoVO.setEstado_pedido(rs.getString("estado_pedi"));
                pedidoVO.setHora_pedido(rs.getString("hora_pedi"));
                datos.add(pedidoVO);
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
    public LinkedList listarPedidos3() {
        conexion cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexion();
            select = cn.getConnection().prepareCall("SELECT* FROM pedidos;");
            rs = select.executeQuery();
            while (rs.next()) {
                PedidosVO pedidoVO = new PedidosVO();
                pedidoVO.setId_pedido(rs.getInt("id_pedi"));
                pedidoVO.setId_mesa(rs.getInt("id_mesa"));
                pedidoVO.setId_plato(rs.getInt("id_plato"));
                pedidoVO.setCedula_mesero(rs.getInt("ced_mesero"));
                pedidoVO.setFecha(rs.getDate("fecha_pedi"));
                pedidoVO.setEstado_pedido(rs.getString("estado_pedi"));
                pedidoVO.setHora_pedido(rs.getString("hora_pedi"));
                datos.add(pedidoVO);
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

}

