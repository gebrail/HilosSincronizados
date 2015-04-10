package restaurantehilo;

import GUI.Principal;
import OPERACIONES.HiloControlar_Pedidos;
import OPERACIONES.Hilo_Pedidos;
import OPERACIONES.Restaurante;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author gebrail
 */
public class RestauranteHilo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException, JavaLayerException {
//        Principal obj = new Principal();// se lanza el principal con los dos hilos
//        obj.setLocationRelativeTo(null);
//        obj.setVisible(true);
        try {
            Principal miprincipal = new Principal();
            miprincipal.setLocationRelativeTo(null);
            miprincipal.setVisible(true);
            Restaurante hilosincronizado = new Restaurante();
            Hilo_Pedidos c = new Hilo_Pedidos(hilosincronizado);
            HiloControlar_Pedidos d = new HiloControlar_Pedidos(hilosincronizado);
            c.setPriority(5);
            d.setPriority(9);
            c.start();//lanza el hilo pedidos
            d.start();//lanza el hilo controlar
            FileInputStream fis;
            Player player;
            fis = new FileInputStream("src/Musica/musica.mp3");
            BufferedInputStream bis = new BufferedInputStream(fis);

            player = new Player(bis);
            player.play();
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
