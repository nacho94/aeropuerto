import java.util.Random;
import java.util.Scanner;

public class Main {

	public static Random aleatorio = new Random();
	
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		int numAviones = sc.nextInt();
		int numHorasSimulacion = sc.nextInt();
		sc.close();
		
		Torre torre = new Torre();
		Thread hiloTorre = new Thread(torre);
		hiloTorre.start();
		
		Thread[] hilosAviones = new Thread[numAviones];		
		for (int i=0; i<numAviones;i++){
			hilosAviones[i] = new Thread(new Avion(i+1,torre));
			hilosAviones[i].start();
		}

		Thread.sleep(numHorasSimulacion);

		for (int i=0; i<numAviones;i++){
			hilosAviones[i].interrupt();
		}
		
		for (int i=0; i<numAviones;i++){
			hilosAviones[i].join();
		}
		
		hiloTorre.interrupt();
		hiloTorre.join();		
	}

}
