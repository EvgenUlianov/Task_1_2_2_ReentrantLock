
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarShowroom {

    private final Lock lock;
    private final Condition condition;
    private static final boolean NEED_PRINT_STACK_TRACE_WHEN_SLEEP = true;

    private int numberOfCars;

    private CarShowroom(){
        numberOfCars = 0;
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    private static class Holder {
        public static final CarShowroom carShowroom = new CarShowroom();
    }

    public static CarShowroom get()  {
        return Holder.carShowroom;
    }

    public synchronized void addOneCar(String currentName, int timeOut) {
        try{
            lock.lock();
            sleep(timeOut);
            ++numberOfCars;
            condition.signalAll();
            System.out.printf("%s произвел автомобиль. В салоне теперь %d машин.\n", currentName, numberOfCars);

        } finally {
            lock.unlock();
        }
    }

    public synchronized void subtractOneCar(String currentName,int timeOut) {
        try{
            lock.lock();
            while (numberOfCars == 0){
                System.out.println("Машин нет");
                condition.await();
            }
            sleep(timeOut);
            --numberOfCars;
            System.out.printf("%s уехал на новеньком авто. В салоне теперь %d машин.\n", currentName, numberOfCars);
        } catch (InterruptedException e) {
            if (NEED_PRINT_STACK_TRACE_WHEN_SLEEP)
                e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void sleep(int timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            if (NEED_PRINT_STACK_TRACE_WHEN_SLEEP)
                e.printStackTrace();
        }
    }

}
