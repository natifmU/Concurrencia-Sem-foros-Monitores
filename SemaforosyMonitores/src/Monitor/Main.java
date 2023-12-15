package Monitor;

public class Main {
    public static void main(String[] args) {
        Puente puente = new Puente();

        Thread coche1 = new Thread(new Coche(puente, "norte"), "Coche 1 (NORTE)");
        Thread coche2 = new Thread(new Coche(puente, "sur"), "Coche 2 (SUR)");

        coche1.start();
        coche2.start();
    }
}
