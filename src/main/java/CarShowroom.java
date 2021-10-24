import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CarShowroom implements Lock, Condition {

    private Lock lock;
    private Condition condition;

    private int numberOfCars;

    private CarShowroom(){
        numberOfCars = 0;
        lock = new ReentrantLock();
        condition = newCondition();
    };

    private static class Holder {
        public static final CarShowroom сarShowroom = new CarShowroom();
    }

    public static CarShowroom get()  {
        return Holder.сarShowroom;
    }

    public int getNumberOfCars() {
        return numberOfCars;
    }

    public void addOneCar() {
        ++numberOfCars;
    }

    public boolean substractOneCar() {
        if (numberOfCars == 0)
            return false;

        --numberOfCars;
        return true;
    }

    @Override
    public void lock() {
        lock.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        lock.lockInterruptibly();
    }

    @Override
    public boolean tryLock() {
        return lock.tryLock();
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return lock.tryLock(time, unit);
    }

    @Override
    public void unlock() {
        lock.unlock();
    }

    @Override
    public Condition newCondition() {
        return lock.newCondition();
    }

    @Override
    public void await() throws InterruptedException {
        condition.await();
    }

    @Override
    public void awaitUninterruptibly() {
        condition.awaitUninterruptibly();
    }

    @Override
    public long awaitNanos(long nanosTimeout) throws InterruptedException {
        return condition.awaitNanos(nanosTimeout);
    }

    @Override
    public boolean await(long time, TimeUnit unit) throws InterruptedException {
        return condition.await(time, unit);
    }

    @Override
    public boolean awaitUntil(Date deadline) throws InterruptedException {
        return condition.awaitUntil(deadline);
    }

    @Override
    public void signal() {
        condition.signal();
    }

    @Override
    public void signalAll() {
        condition.signalAll();
    }

}
