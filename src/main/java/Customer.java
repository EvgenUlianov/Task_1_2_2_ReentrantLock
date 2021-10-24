import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Customer  extends Thread implements Runnable{

    private int priority;
    public Customer(int number) {
        super(String.format("Покупатель %d", number));
        priority = number;
    }

    @Override
    public void run() {
//        final int timeOut15 = 15_000;
        final int timeOut = 1500 + priority;
        String currentName = super.getName();
        CarShowroom carShowroom = CarShowroom.get();

        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("%s зашел в автосалон.\n", currentName);
        while (true) {
            boolean successful = false;
            carShowroom.lock();
            try {
                if(carShowroom.getNumberOfCars() != 0){
                    carShowroom.substractOneCar();
                    successful = true;
                }
                if (!successful) {
                    System.out.printf("Машин нет (%s)\n", currentName);
                    try {
                        carShowroom.await();
                    } catch (InterruptedException e) {
//                    e.printStackTrace();
                    }
                }
            } finally {
                carShowroom.unlock();
            }

            if (successful) {
                System.out.printf("%s уехал на новеньком авто.\n", currentName);
                return;
            }
        }
    }

}
