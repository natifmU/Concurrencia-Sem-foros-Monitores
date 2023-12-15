package Semaforo;
	
	class Proceso implements Runnable {
	    private Recurso recurso;
	    private int unidadesReserva;
	    private int unidadesLibera;

	    public Proceso(Recurso recurso, int unidadesReserva, int unidadesLibera) {
	        this.recurso = recurso;
	        this.unidadesReserva = unidadesReserva;
	        this.unidadesLibera = unidadesLibera;
	    }

	    @Override
	    public void run() {
	        recurso.reserva(unidadesReserva);
	        // Simula el trabajo
	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        recurso.libera(unidadesLibera);
	    }
	}
