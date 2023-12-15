package Semaforo;
import java.util.concurrent.Semaphore;

	class Recurso {
	    private int unidadesDisponibles;
	    private Semaphore semaforo;

	    public Recurso(int k) {
	        unidadesDisponibles = k;
	        semaforo = new Semaphore(1); // Inicializado a 1 para utilizar semáforo binario
	    }

	    public void reserva(int r) {
	        try {
	            semaforo.acquire(); // Bloquea el acceso al recurso
	            if (r <= unidadesDisponibles) {
	                unidadesDisponibles -= r;
	                System.out.println(Thread.currentThread().getName() + " reservó " + r + " unidades. Unidades disponibles: " + unidadesDisponibles);
	            } else {
	                System.out.println(Thread.currentThread().getName() + " no se puede reservar " + r + " unidades. No hay suficientes recursos disponibles.");
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } finally {
	            semaforo.release(); // Libera el acceso al recurso
	        }
	    }

	    public void libera(int l) {
	        try {
	            semaforo.acquire(); // Bloquea el acceso al recurso
	            unidadesDisponibles += l;
	            System.out.println(Thread.currentThread().getName() + " liberó " + l + " unidades. Unidades disponibles: " + unidadesDisponibles);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        } finally {
	            semaforo.release(); // Libera el acceso al recurso
	        }
	    }
	}
