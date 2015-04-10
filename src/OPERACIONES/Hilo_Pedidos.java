package OPERACIONES;

import DAO.MesasDAO;
import DAO.MeserosDAO;
import DAO.PedidosDAO;
import static GUI.Principal.modelo;
import VO.MesasVO;
import VO.MeserosVO;
import VO.PedidosVO;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gebrail
 */
public class Hilo_Pedidos extends Thread {

    private Restaurante elpedidoese;
    private MeserosDAO meseros = new MeserosDAO();
    private MesasDAO mesas = new MesasDAO();
    private PedidosDAO pedidos = new PedidosDAO();
    String hora, minutos, segundos, ampm;
    int sumador = 0, sumador2 = 0;
    public static boolean congelar = true;

    public Hilo_Pedidos(Restaurante elpedidoese) {
        this.elpedidoese = elpedidoese;
    }

    public void run() {
        while (congelar) {
            LinkedList listaMeseros = meseros.listarMeseros();
            LinkedList listaMesas = mesas.listarMesas();

            for (Object listaMesa : listaMesas) {
                calcula();
                Calendar calendar = Calendar.getInstance();
                java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
                MesasVO mesa = (MesasVO) listaMesa;
                int datos[] = {664323, 3243223, 8787432, 5554, 123999};
                int meseroRandon = (int) Math.round(Math.random() * 4);
                for (Object listaMesero : listaMeseros) {
                    MeserosVO mesero = (MeserosVO) listaMesero;
                    if ((datos[meseroRandon] == mesero.getCedula()) && (mesero.getEstado().equals("Libre")) && (mesero.getContador() < 5) && (mesa.getEstado().equals("Libre"))) {
                        mesero.setContador(mesero.getContador() + 1);
                        if (meseros.modificarMesero(mesero)) {
                        }
                        mesa.setEstado("Ocupada");
                        if (mesas.modificarEstadoMesa(mesa)) {
                        }
                        PedidosVO pedido = new PedidosVO();
                        pedido.setId_mesa(mesa.getId());
                        pedido.setId_plato((int) ((Math.random() * 6) + 1));
                        pedido.setCedula_mesero(mesero.getCedula());
                        pedido.setFecha(ourJavaDateObject);
                        pedido.setEstado_pedido("En Proceso");
                        pedido.setHora_pedido(hora + ":" + minutos + ":" + segundos + " " + ampm);
                        if (pedidos.registrarPedido(pedido)) {

                        }
                        mostrar();
                        try {
                            Thread.sleep(10000);

                        } catch (InterruptedException ex) {
                            Logger.getLogger(Hilo_Pedidos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    } else if (mesero.getEstado().equals("Descansando")) {
                        mesero.setContador(mesero.getContador() + 1);
                        if (meseros.modificarMesero(mesero)) {

                        }

                    }
                }

            }
            elpedidoese.Controlar_Pedido();
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

}
