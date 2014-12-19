import Avion;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.HashMap;

public class Torre implements Runnable {

	private BlockingQueue <Avion> q = new ArrayBlockingQueue<Avion> (1);
	private HashMap <String,Avion> pistas = new HashMap <>();

    	public void run() { 
    		while(true) {
    			Avion a = devolverPrimerAvion();
    			a.asignarPista()
    		}

    	}

    	public void meterAvionEnCola(Avion a) {
    		q.put(a);
    	}

    	public Avion devolverPrimerAvion() {

    		return q.take();
    		 
    	}
}