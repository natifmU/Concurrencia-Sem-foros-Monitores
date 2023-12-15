package Semaforo;

public class Main {

	public static void main(String[] args) {
		 Recurso recurso = new Recurso(5);

		 Thread proceso1 = new Thread(new Proceso(recurso, 2, 1), "Proceso 1");
		 Thread proceso2 = new Thread(new Proceso(recurso, 3, 2), "Proceso 2");

		 proceso1.start();
		 proceso2.start();
		    }
	}
