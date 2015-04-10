package DAO;

import Conexion.conexion;
import VO.MesasVO;
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
public class MesasDAO {

    public MesasDAO() {
    }
    
    public LinkedList listarMesas() {
        conexion cn = null;
        PreparedStatement select = null;
        ResultSet rs = null;
        LinkedList datos = new LinkedList();
        try {
            cn = new conexion();
            select = cn.getConnection().prepareCall("SELECT * FROM mesas;");
            rs = select.executeQuery();
            while (rs.next()) {
                MesasVO mesaVO = new MesasVO();
                mesaVO.setId(rs.getInt("id_mesa"));
                mesaVO.setNombre(rs.getString("nom_mesa"));
                mesaVO.setEstado(rs.getString("estado_mesa"));
                datos.add(mesaVO);
            }
            return datos;
        } catch (SQLException ex) {
            Logger.getLogger(MesasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return datos;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(select);
            cn.cerrarResultSet(rs);
        }
    }
    
    public boolean modificarEstadoMesa(MesasVO mesa) {
        conexion cn = null;
        PreparedStatement update = null;
        try {
            cn = new conexion();
            boolean modificar = false;
            update = cn.getConnection().prepareStatement("UPDATE mesas SET estado_mesa=? WHERE id_mesa= ?");
            update.setString(1, mesa.getEstado());
            update.setInt(2, mesa.getId());
            int r = update.executeUpdate();
            if (r != 0) {
                modificar = true;
            }
            return modificar;
        } catch (SQLException ex) {
            Logger.getLogger(MesasDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            cn.desconectar();
            cn.cerrarStatement(update);
        }
    }
}
