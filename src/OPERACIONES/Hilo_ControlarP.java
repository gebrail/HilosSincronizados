package OPERACIONES;


/**
 *
 * @author Wilson
 */
public class Hilo_ControlarP extends Thread {
//
//    String putossegundos;
//    private Restaurante producirpedido;
//    private PedidosDAO pedidos = new PedidosDAO();
//
//    public Hilo_ControlarP(Restaurante producirpedido) {
//        this.producirpedido = producirpedido;
//    }
//
//    public void run() {
//        while (true) {
//            LinkedList listaPedidos = pedidos.listarPedidos();
//         //   for (Object listaPedido : listaPedidos) {
//            for (int i = 0; i < listaPedidos.size(); i++) {           
//                calcula();
//               PedidosVO pedid =(PedidosVO) listaPedidos.get(i);
//                String hora = pedid.getHora_pedido();
//                String hh = hora.substring(hora.length() - 5, hora.length());
//                String segundos = hh.substring(0, 2);
//                int segundospedido = 0;
//                int totaldemora = 0;
//                switch (pedid.getId_plato()) {
//                    case 1:
//                        segundospedido = 10;
//                        totaldemora = Integer.parseInt(segundos) + segundospedido;
//                        break;
//                    case 2:
//                        segundospedido = 5;
//                        totaldemora = Integer.parseInt(segundos) + segundospedido;
//                        break;
//                    case 3:
//                        segundospedido = 10;
//                        totaldemora = Integer.parseInt(segundos) + segundospedido;
//                        break;
//                    case 4:
//                        segundospedido = 15;
//                        totaldemora = Integer.parseInt(segundos) + segundospedido;
//                        break;
//                    case 5:
//                        segundospedido = 16;
//                        totaldemora = Integer.parseInt(segundos) + segundospedido;
//                        break;
//                    case 6:
//                        segundospedido = 15;
//                        totaldemora = Integer.parseInt(segundos) + segundospedido;
//                        break;
//                }
//                if ((Integer.parseInt(putossegundos) == totaldemora) && (pedid.getEstado_pedido().equals("En Proceso"))) {
//                    JOptionPane.showMessageDialog(null, "oiga atienda el pedido # " + pedid.getId_pedido());
//                }
//            }
//            producirpedido.Producir_Pedido();
//         }
//        
//    }
//
//    public void calcula() {
//        Calendar calendario = new GregorianCalendar();
//        Date fechaHoraActual = new Date();
//        calendario.setTime(fechaHoraActual);
//        putossegundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
//    }

}
