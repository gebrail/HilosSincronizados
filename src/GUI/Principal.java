/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.MesasDAO;
import DAO.MeserosDAO;
import DAO.PedidosDAO;
import OPERACIONES.HiloControlar_Pedidos;
import OPERACIONES.Hilo_Pedidos;
import VO.MesasVO;
import VO.MeserosVO;
import VO.PedidosVO;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gebrail
 */
public final class Principal extends javax.swing.JFrame implements Runnable {

    public static DefaultTableModel modelo;
    private MeserosDAO meseros = new MeserosDAO();
    private PedidosDAO pedidos = new PedidosDAO();
    private MesasDAO mesas = new MesasDAO();
    String hora, minutos, segundos, ampm;
    Thread h1;
    Calendar calendario;
    String atributo = "Id";

    /**
     * Creates new form Principal
     */
    public Principal() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        initComponents();
        String filas[][] = {};
        String columnas[] = {"IdPedido", "IdMesa", "TIPOPLATO", "Cedulamesero", "fechapedido", "estado", "Hora"};
        jTable1.setOpaque(false);
        ((DefaultTableCellRenderer) jTable1.getDefaultRenderer(Object.class)).setOpaque(false);
        jScrollPane2.setOpaque(false);
        jScrollPane2.getViewport().setOpaque(false);
        jTable1.setShowGrid(false);
        modelo = new DefaultTableModel(filas, columnas);
        jTable1.setModel(modelo);
        h1 = new Thread((Runnable) this);
        h1.start();
        run();
        mostrar();

    }

    public void run() {
        Thread ct = Thread.currentThread();
        while (ct == h1) {
            calcula();
            Clock.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            LinkedList listameseros2 = meseros.listarMeseros2();
            for (int i = 0; i < listameseros2.size(); i++) {
                MeserosVO meseroxd = (MeserosVO) listameseros2.get(i);
                if ((meseroxd.getEstado().equals("Libre")) && (meseroxd.getContador() == 5)) {
                    meseroxd.setEstado("Descansando");
                    meseroxd.setContador(0);
                    if (meseros.modificarMesero2(meseroxd)) {
                        System.out.println("mesero descansando");
                    }
                } else if ((meseroxd.getEstado().equals("Descansando")) && (meseroxd.getContador() > 10)) {
                    meseroxd.setEstado("Libre");
                    meseroxd.setContador(0);
                    if (meseros.modificarMesero2(meseroxd)) {
                        //  System.out.println("Actualizo Mesero!!");
                    }
                }
            }
        }
    }

    public void calcula() {
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();

        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

//    public static void mostrar() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
//        con = new Conexion();
//        con.ConexionPostgres();
//        DefaultTableModel xd = new DefaultTableModel();
//        xd.addColumn("Id del pedido");
//        xd.addColumn("Id mesa");
//        xd.addColumn("Plato");
//        xd.addColumn("Cedula del mesero");
//        xd.addColumn("Fecha");
//        xd.addColumn("Estado");
//        xd.addColumn("HoritaXD");
//        jTable1.setModel(xd);
//        String unquery = "Select * From pedidos";
//        String datos[] = new String[7];
//        try {
//            ResultSet tr; //st.executeQuery(unquery);
//            tr = con.consultar(unquery);
//            while (tr.next()) {
//                String estadodelpedido = tr.getString(6);
//                if (estadodelpedido.equals("En Proceso")) {
//                    datos[0] = tr.getString("id_pedi");
//                    datos[1] = tr.getString("id_mesa");
//                    datos[2] = tr.getString("id_plato");
//                    datos[3] = tr.getString("ced_mesero");
//                    datos[4] = tr.getString("fecha_pedi");
//                    datos[5] = tr.getString("estado_pedi");
//                    datos[6] = tr.getString("hora_pedi");
//                    xd.addRow(datos);
//
//                }
//                jTable1.setModel(xd);
//
//            }
//
//        } catch (SQLException ex) {
//            Logger.getLogger(Principal.class
//                    .getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
    public void mostrar() {
        for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
        LinkedList listapedidosxd = pedidos.listarPedidos2();
        for (int a = 0; a < listapedidosxd.size(); a++) {
            PedidosVO elpedido = (PedidosVO) listapedidosxd.get(a);
            modelo.insertRow(a, new Object[]{});
            modelo.setValueAt(elpedido.getId_pedido(), a, 0);
            modelo.setValueAt(elpedido.getId_mesa(), a, 1);
            modelo.setValueAt(elpedido.getId_plato(), a, 2);
            modelo.setValueAt(elpedido.getCedula_mesero(), a, 3);
            DateFormat formato1 = DateFormat.getDateInstance();
            String sfecha = formato1.format(elpedido.getFecha());
            modelo.setValueAt(elpedido.getFecha(), a, 4);
            modelo.setValueAt(elpedido.getEstado_pedido(), a, 5);
            modelo.setValueAt(elpedido.getHora_pedido(), a, 6);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Clock = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bienvenido Al Sistema De Pedidos");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setSelectionBackground(new java.awt.Color(0, 204, 204));
        jTable1.setSelectionForeground(new java.awt.Color(0, 204, 204));
        jScrollPane2.setViewportView(jTable1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 200, 510, 120));

        jButton1.setFont(new java.awt.Font("Calibri", 1, 12)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Atender.png"))); // NOI18N
        jButton1.setText("Atender");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 360, -1, -1));

        jLabel3.setForeground(new java.awt.Color(0, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/relojfondo.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 220, 60));

        jLabel1.setFont(new java.awt.Font("Calibri", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Seleccione el cliente que debe de ser atendido: ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        Clock.setFont(new java.awt.Font("Segoe Print", 3, 24)); // NOI18N
        Clock.setForeground(new java.awt.Color(255, 255, 255));
        Clock.setText("LA HORA");
        getContentPane().add(Clock, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 180, 40));

        jButton2.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/vector_258_17-01-32.png"))); // NOI18N
        jButton2.setText("Terminar Simulación");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 360, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/gastromium.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 490));

        jMenuBar1.setBackground(java.awt.Color.darkGray);
        jMenuBar1.setForeground(new java.awt.Color(204, 255, 255));

        jMenu1.setBackground(java.awt.Color.darkGray);
        jMenu1.setForeground(new java.awt.Color(204, 255, 255));
        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/09.png"))); // NOI18N
        jMenu1.setText("Reportes");

        jMenuItem1.setBackground(java.awt.Color.darkGray);
        jMenuItem1.setForeground(new java.awt.Color(204, 255, 255));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/06-dish-32.png"))); // NOI18N
        jMenuItem1.setText("Total Pedidos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setBackground(java.awt.Color.darkGray);
        jMenuItem2.setForeground(new java.awt.Color(204, 255, 255));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/otrototal.png"))); // NOI18N
        jMenuItem2.setText("Total Ventas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setBackground(java.awt.Color.darkGray);
        jMenuItem3.setForeground(new java.awt.Color(204, 255, 255));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/14-chef-32.png"))); // NOI18N
        jMenuItem3.setText("Total Pedidos Por El Mesero");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setBackground(java.awt.Color.darkGray);
        jMenuItem4.setForeground(new java.awt.Color(204, 255, 255));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/platica.png"))); // NOI18N
        jMenuItem4.setText("Platos Mas Vendido y Cuanta Platica se hizo");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setBackground(java.awt.Color.darkGray);
        jMenu2.setForeground(new java.awt.Color(204, 255, 255));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/10.png"))); // NOI18N
        jMenu2.setText("Salir");

        jMenuItem7.setBackground(java.awt.Color.darkGray);
        jMenuItem7.setForeground(new java.awt.Color(204, 255, 255));
        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/salir.png"))); // NOI18N
        jMenuItem7.setText("Salir");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int i = jTable1.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "seleccione al cliente que no ha atendido que no ha atendido");
        } else {
            int a = (int) jTable1.getValueAt(i, 0);
            int c = (int) jTable1.getValueAt(i, 1);
            MesasVO mesasxd = new MesasVO();
            PedidosVO pedido = new PedidosVO();
            pedido.setId_pedido(a);
            pedido.setEstado_pedido("Despachado");
            mesasxd.setId(c);
            mesasxd.setEstado("Libre");
            if (pedidos.modificarpedido(pedido)) {
                JOptionPane.showMessageDialog(null, "Pedido # " + a + " Despachado");
            }
            if (mesas.modificarEstadoMesa(mesasxd)) {

                JOptionPane.showMessageDialog(null, "Mesa #: " + c + " Disponible");
            }
            mostrar();

            a = 0;
            c = 0;
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        Reporte2 reporte2 = new Reporte2(this, true);
        reporte2.setLocationRelativeTo(null);
        reporte2.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Reporte3 reporte3 = new Reporte3(this, true);
        reporte3.setLocationRelativeTo(null);
        reporte3.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        Reporte4 reporte456 = new Reporte4(this, true);
        reporte456.setLocationRelativeTo(null);
        reporte456.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Reporte1 reporte1 = new Reporte1(this, true);
        reporte1.setLocationRelativeTo(null);
        reporte1.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        System.exit(WIDTH);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       HiloControlar_Pedidos.congelar2=false;
       Hilo_Pedidos.congelar=false;
       
        JOptionPane.showMessageDialog(rootPane, "Simulacion Terminada");
        jButton1.setEnabled(false);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);

                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Principal.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(Principal.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Principal.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Clock;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
