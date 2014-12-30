
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.HashMap;
import java.util.concurrent.Semaphore;

public class Torre implements Runnable {

	private BlockingQueue <Avion> q = new ArrayBlockingQueue<Avion> (1);
	private HashMap <String,Avion> fPistas = new HashMap <String,Avion>();
	private Semaphore fPistasLibres;

	public Torre() {
		fPistasLibres = new Semaphore(2);
		fPistas.put("V", null);
		fPistas.put("W", null);
	}

	public void run() { 
		while(!Thread.interrupted()) {
			Avion a = devolverPrimerAvion();
			String p = decidirPista(a);
			Main.log("Torre: Avion numero " + a.getNumero() + " puede " +a.queHago() +" de pista " + p);
			a.asignarPista(p);
		}

	}

	public void notificar(Avion a, boolean despegando) {
		if(despegando) {
			Main.log("Avion " + a.getNumero() + " ya despego.");

		}else{
			Main.log("Avion " + a.getNumero() + " ya aterrizo.");
		}
		liberarPista(a);
	}

	private String decidirPista(Avion a) {
		try{
			fPistasLibres.acquire();
			for (HashMap.Entry<String,Avion> e : fPistas.entrySet()) {
				
	    		if(e.getValue()==null) {
	    			fPistas.put(e.getKey(),a);
	    			return e.getKey();
	    		}
			}
		}catch(Exception ex) {
			return ex.getMessage();
		}
		return "x";
 	}

 	private void liberarPista(Avion a) {
 		fPistas.put(a.getPista(),null);
 		fPistasLibres.release();
 	}

	public void meterAvionEnCola(Avion a, boolean despegando) {
		try{
			q.put(a);
			Main.log("Avion numero " + a.getNumero()  + " desea " + (despegando ? "despegar" : "aterrizar") + ".");
		}catch(Exception e) {

		}
	}

	public Avion devolverPrimerAvion() {


		try{
			return q.take(); 
		}catch(Exception e) {
			return null;
		}
		
		
	}
}