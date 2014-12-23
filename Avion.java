import java.util.concurrent.Semaphore;

public class Avion implements Runnable {

	private int fNumero;
	private Torre fTorre;
	private Semaphore fSem;
	private String fPista;

	public Avion(int n, Torre t) {
		fNumero = n;
		fTorre = t;
		fSem = new Semaphore(0);
	}
	
	public void run() { //...
		while(true) {
			Main.log("Avion " + fNumero + " en el hangar.");
			// segun la practica 720 es el numero de minutos de 12 horas
			//que es lo que hay que esperar a milisegundos
			int esperar = Main.aleatorio.nextInt(720);
			// la espera tiene que estar entre 720 y 1440
			esperar+=720;
			Main.debug("esperando " + esperar + " milisegundos");
			sleep(esperar);

			fTorre.meterAvionEnCola(this,true);

			esperarTorre();
			Main.log("Avion numero " + fNumero + " despegando en pista " + fPista);
			sleep(10);
			fTorre.notificar(this,true);
			esperar = Main.aleatorio.nextInt(60);
			// la espera tiene que estar entre 60 y 120
			esperar+=60;
			Main.log("Avion " + fNumero + " en reconocimiento.");
			sleep(esperar);

			fTorre.meterAvionEnCola(this,false);
			esperarTorre();
			
			Main.log("Avion numero " + fNumero + " aterrizando en pista " + fPista);
			
			sleep(10);

			fTorre.notificar(this,false);
		}
	}

	public int getNumero() {
		return fNumero;
	}

	public String getPista() {
		return fPista;
	}

 	private void sleep(int n) {
		try{
			Thread.sleep(n);
		}catch(Exception e) {

		}
	}

	private void esperarTorre() {
		try{
			fSem.acquire();
		}catch(Exception e) {

		}
	}

	public void asignarPista(String pista) {
		fPista = pista;
		fSem.release();
	}


}