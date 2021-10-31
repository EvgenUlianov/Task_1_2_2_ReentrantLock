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
        final int timeOut = 100 + random.nextInt(100);
        String currentName = super.getName();
        CarShowroom carShowroom = CarShowroom.get();

        CarShowroom.sleep(timeOut);
        //System.out.printf("%s зашел в автосалон.\n", currentName);

        carShowroom.addOneCar(currentName, timeOut);
   }

}
