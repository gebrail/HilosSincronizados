package OPERACIONES;

import DAO.MesasDAO;
import VO.MesasVO;
import java.util.LinkedList;

/**
 *
 * @author gebrail
 */
public class Restaurante {

    private MesasDAO mesas = new MesasDAO();
    private boolean pedido_disponible = true;

    public synchronized void Producir_Pedido() {
        while (pedido_disponible == false) {
            try {
                wait(); // EL HILO CEDE EL MONITOR
            } catch (InterruptedException e) {
            }
        }
        int contadorxd = 0;
        LinkedList listaMesas3 = mesas.listarMesas();
        for (Object listaMesas4 : listaMesas3) {
            MesasVO mesasxd = (MesasVO) listaMesas4;
            if (mesasxd.getEstado().equals("Ocupada")) {
                contadorxd++;
            }
        }
        if (contadorxd != 15) {
            System.out.println("producir");
            pedido_disponible = false;
            notifyAll();
        }

    }

    public synchronized void Controlar_Pedido() {
        while (pedido_disponible == true) {

            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("controlar");
        pedido_disponible = true;
        notifyAll();

    }

}
