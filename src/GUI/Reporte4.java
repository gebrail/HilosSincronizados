/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DAO.PedidosDAO;
import VO.PedidosVO;
import java.util.LinkedList;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Wilson
 */
public class Reporte4 extends javax.swing.JDialog {

    private PedidosDAO pedidos = new PedidosDAO();
    public static long total1, total2, total3, total4, total5, total6, totalt1, totalt2, totalt3;
    public static int contador1, contador2, contador3, contador4, contador5, contador6, cantidad1, cantidad2, cantidad3;

    /**
     * Creates new form Reporte4
     */
    public Reporte4(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        mayorventa();

    }

    public void mayorventa() {
        LinkedList listapedidos4 = pedidos.listarPedidos();
        for (int i = 0; i < listapedidos4.size(); i++) {
            PedidosVO p1 = (PedidosVO) listapedidos4.get(i);
            int Tipo = p1.getId_plato();

            if (p1.getId_plato()== 1) {
                contador1++;
            } else if (p1.getId_plato() == 2) {
                contador2++;
            } else if (p1.getId_plato() == 3) {
                contador3++;
            } else if (p1.getId_plato() == 4) {
                contador4++;
            } else if (p1.getId_plato() == 5) {
                contador5++;
            } else if (p1.getId_plato() == 6) {
                contador6++;
            }

            total1 = 10000 * contador1;
            total2 = 7000 * contador2;
            total3 = 8000 * contador3;
            total4 = 9000 * contador4;
            total5 = 15000 * contador5;
            total6 = 20000 * contador6;
            totalt1 = total1 + total2;
            totalt2 = total3 + total4;
            totalt3 = total5 + total6;
            cantidad1 = contador1 + contador2;
            cantidad2 = contador3 + contador4;
            cantidad3 = contador5 + contador6;

            jTextField2.setText(String.valueOf(total1));
            jTextField3.setText(String.valueOf(total2));
            jTextField4.setText(String.valueOf(total3));
            jTextField5.setText(String.valueOf(total4));
            jTextField6.setText(String.valueOf(total5));
            jTextField7.setText(String.valueOf(total6));

            if ((contador1 > contador2) && (contador1 > contador3) && (contador1 > contador4) && (contador1 > contador5) && (contador1 > contador6)) {
                jTextField1.setText("HAMBUGRESA");
            } else if ((contador2 > contador1) && (contador2 > contador3) && (contador2 > contador4) && (contador2 > contador5) && (contador2 > contador6)) {
                jTextField1.setText("PERRO CALIENTE");
            } else if ((contador3 > contador1) && (contador3 > contador2) && (contador3 > contador4) && (contador3 > contador5) && (contador3 > contador6)) {
                jTextField1.setText("ALMUERZO X");
            } else if ((contador4 > contador1) && (contador4 > contador2) && (contador4 > contador3) && (contador4 > contador5) && (contador4 > contador6)) {
                jTextField1.setText("ALMUERZO Y");
            } else if ((contador5 > contador1) && (contador5 > contador2) && (contador5 > contador3) && (contador5 > contador4) && (contador5 > contador6)) {
                jTextField1.setText("CARNE ASADA");
            } else if ((contador6 > contador1) && (contador6 > contador2) && (contador6 > contador3) && (contador6 > contador4) && (contador6 > contador5)) {
                jTextField1.setText("CREEPS");
            }
            jTextField9.setText(String.valueOf(totalt1));
            jTextField10.setText(String.valueOf(totalt2));
            jTextField11.setText(String.valueOf(totalt3));

            if ((cantidad1 > cantidad2) && (cantidad1 > cantidad3)) {
                jTextField8.setText("Comidas Rápidas");
            } else if ((cantidad2 > cantidad1) && (cantidad2 > cantidad3)) {
                jTextField8.setText("Almuerzos");
            } else if ((cantidad3 > cantidad1) && (cantidad3 > cantidad2)) {
                jTextField8.setText("Otros");
            }

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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte Detallado");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel1.setForeground(java.awt.Color.cyan);
        jLabel1.setText("TOTAL $ HAMBUGRESA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 151, 34));

        jLabel2.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel2.setForeground(java.awt.Color.cyan);
        jLabel2.setText("TOTAL $ PERRO CALIENTE");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 151, 34));

        jLabel3.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabel3.setForeground(java.awt.Color.cyan);
        jLabel3.setText("El Plato Mas Vendido Fue");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 143, 43));

        jLabel4.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel4.setForeground(java.awt.Color.cyan);
        jLabel4.setText("TOTAL $ ALMUERZO X");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 151, 34));

        jLabel5.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel5.setForeground(java.awt.Color.cyan);
        jLabel5.setText("TOTAL $ ALMUERZO Y");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 151, 34));

        jLabel6.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel6.setForeground(java.awt.Color.cyan);
        jLabel6.setText("TOTAL $ CARNE ASADA");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 151, 34));

        jLabel7.setFont(new java.awt.Font("Calibri", 3, 12)); // NOI18N
        jLabel7.setForeground(java.awt.Color.cyan);
        jLabel7.setText("TOTAL $ CREEPS");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 151, 34));

        jLabel8.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabel8.setForeground(java.awt.Color.cyan);
        jLabel8.setText("Tipo De Plato Mas Vendido");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 160, 34));

        jLabel9.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabel9.setForeground(java.awt.Color.cyan);
        jLabel9.setText("Comidas Rápidas");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 100, 24));

        jLabel10.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabel10.setForeground(java.awt.Color.cyan);
        jLabel10.setText("Almuerzos");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 80, 24));

        jLabel11.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jLabel11.setForeground(java.awt.Color.cyan);
        jLabel11.setText("Otros");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 50, 24));

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 141, -1));

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 140, -1));

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 130, 140, -1));

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 140, -1));

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 140, -1));

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 140, -1));

        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 140, -1));

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 150, 20));

        jTextField9.setEditable(false);
        jTextField9.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        getContentPane().add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(466, 96, 159, -1));

        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        getContentPane().add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(466, 174, 159, -1));

        jTextField11.setEditable(false);
        jTextField11.setFont(new java.awt.Font("Calibri", 3, 14)); // NOI18N
        jTextField11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField11ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(466, 245, 159, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/restaurante-canoe.jpg"))); // NOI18N
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 640, 350));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField11ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

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
            java.util.logging.Logger.getLogger(Reporte4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reporte4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reporte4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reporte4.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Reporte4 dialog = new Reporte4(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
