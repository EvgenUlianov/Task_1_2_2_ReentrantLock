import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Customer extends Thread  implements Runnable{

    private int priority;
    public Customer(int number) {
        super(String.format("Покупатель %d", number));
        priority = number;
    }

    @Override
    public void run() {
//        final int timeOut15 = 15_000;
        Random random = new Random();
        final int timeOut = 100 + random.nextInt(100);
        //final int timeOut = 1700 + priority;
        String currentName = super.getName();
        CarShowroom carShowroom = CarShowroom.get();

        CarShowroom.sleep(timeOut);

        System.out.printf("%s зашел в автосалон.\n", currentName);
        carShowroom.subtractOneCar(currentName, timeOut);
    }

}
