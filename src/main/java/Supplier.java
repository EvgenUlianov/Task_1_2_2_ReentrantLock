import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Supplier extends Thread implements Runnable{
    public Supplier(String brand) {
        super(String.format("Производитель %s", brand));
    }

    @Override
    public void run() {
//        final int timeOut15 = 15_000;
        Random random = new Random();
        final int timeOut = 1000 + random.nextInt(1000);
        String currentName = super.getName();
        CarShowroom carShowroom = CarShowroom.get();

        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        carShowroom.lock();
        try {
            carShowroom.addOneCar();
            carShowroom.signalAll();
        } finally {
            carShowroom.unlock();
        }

        System.out.printf("%s произвел автомобиль.\n", currentName);
    }

}
