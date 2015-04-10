package OPERACIONES;

import DAO.PedidosDAO;
import VO.PedidosVO;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author Wilson
 */
public class HiloControlar_Pedidos extends Thread {

    String segundosv3;
    private Restaurante elpedidoese;
    private PedidosDAO pedidos = new PedidosDAO();
    public static boolean congelar2=true;

    public HiloControlar_Pedidos(Restaurante elpedidoese) {
        this.elpedidoese = elpedidoese;
    }

    public void run() {
        while (congelar2) {
            LinkedList listaPedidos = pedidos.listarPedidos3();
            for (Object listaPedido : listaPedidos) {
                calcula();
                PedidosVO pd = (PedidosVO) listaPedido;
                String hora = pd.getHora_pedido();
                String hh = hora.substring(hora.length() - 5, hora.length());
                String segundos = hh.substring(0, 2);
                int segundospedido = 0;
                int totaldemora = 0;
                int auxiliar = 0;
                switch (pd.getId_plato()) {
                    case 1:
                        segundospedido = 10;
                        totaldemora = Integer.parseInt(segundos) + segundospedido;
                        if (totaldemora == 60) {
                            totaldemora = segundospedido;
                        }
                        break;
                    case 2:
                        segundospedido = 5;
                        totaldemora = Integer.parseInt(segundos) + segundospedido;
                        if (totaldemora == 60) {
                            totaldemora = segundospedido;
                        }
                        break;
                    case 3:
                        segundospedido = 10;
                        totaldemora = Integer.parseInt(segundos) + segundospedido;
                        if (totaldemora == 60) {
                            totaldemora = segundospedido;
                        }
                        break;
                    case 4:
                        segundospedido = 15;
                        totaldemora = Integer.parseInt(segundos) + segundospedido;
                        if (totaldemora == 60) {
                            totaldemora = segundospedido;
                        }
                        break;
                    case 5:
                        segundospedido = 16;
                        totaldemora = Integer.parseInt(segundos) + segundospedido;
                        if (totaldemora == 60) {
                            totaldemora = segundospedido;
                        }
                        break;
                    case 6:
                        segundospedido = 15;
                        totaldemora = Integer.parseInt(segundos) + segundospedido;
                        if (totaldemora >= 60) {
                            totaldemora = segundospedido;
                        }
                        break;

                }

                if ((Integer.parseInt(segundosv3) == totaldemora) && (pd.getEstado_pedido().equals("En Proceso"))) {
                    JOptionPane.showMessageDialog(null, "Por Favor Atienda el pedido: " + pd.getId_pedido());

                }

            }
            elpedidoese.Producir_Pedido();
        }

    }

    public void calcula() {
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();
        calendario.setTime(fechaHoraActual);
        segundosv3 = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

}
