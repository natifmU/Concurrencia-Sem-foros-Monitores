package Monitor;

class Coche implements Runnable {
    private Puente puente;
    private String direccion;

    public Coche(Puente puente, String direccion) {
        this.puente = puente;
        this.direccion = direccion;
    }

    @Override
    public void run() {
        if (direccion.equals("norte")) {
            puente.cruzarDesdeElNorte();
        } else if (direccion.equals("sur")) {
            puente.cruzarDesdeElSur();
        }

        // Simular el tiempo que está en el puente
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        puente.salirDelPuente();
    }
}