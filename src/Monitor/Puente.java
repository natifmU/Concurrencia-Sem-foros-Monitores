package Monitor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Puente {
    private boolean cocheEnElPuente;
    private int cochesEsperandoNorte;
    private int cochesEsperandoSur;

    private Lock lock;
    private Condition puedeCruzarNorte;
    private Condition puedeCruzarSur;

    public Puente() {
        cocheEnElPuente = false;
        cochesEsperandoNorte = 0;
        cochesEsperandoSur = 0;

        lock = new ReentrantLock();
        puedeCruzarNorte = lock.newCondition();
        puedeCruzarSur = lock.newCondition();
    }

    public void cruzarDesdeElNorte() {
        lock.lock();
        try {
            cochesEsperandoNorte++;
            while (cocheEnElPuente || cochesEsperandoSur > 0) {
                puedeCruzarNorte.await();
            }
            cochesEsperandoNorte--;
            cocheEnElPuente = true;
            System.out.println(Thread.currentThread().getName() + " CRUZANDO el puente desde el NORTE.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void cruzarDesdeElSur() {
        lock.lock();
        try {
            cochesEsperandoSur++;
            while (cocheEnElPuente || cochesEsperandoNorte > 0) {
                puedeCruzarSur.await();
            }
            cochesEsperandoSur--;
            cocheEnElPuente = true;
            System.out.println(Thread.currentThread().getName() + " CRUZANDO el puente desde el SUR.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void salirDelPuente() {
        lock.lock();
        try {
            cocheEnElPuente = false;
            System.out.println(Thread.currentThread().getName() + " SALIENDO del puente.");
            puedeCruzarNorte.signalAll();
            puedeCruzarSur.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

