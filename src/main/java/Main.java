import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задача 2. Продвинутый автосалон");
        
        final int numberOfCostumers = 10;
        List<String> brands = new ArrayList<>();
        brands.add("Toyota");
        brands.add("Nissan");
        brands.add("BMW");
        brands.add("Audi");
        brands.add("Mercedes-Benz");
        brands.add("KIA");
        brands.add("Honda");
        brands.add("Hyundai");
        brands.add("Opel");
        brands.add("Volkswagen");

        final ExecutorService threadPool = Executors.newFixedThreadPool(numberOfCostumers + brands.size());

        //List<Runnable> treads = new ArrayList<>();
        for (int i = 0; i < numberOfCostumers; i++)
            threadPool.execute(new Customer(i + 1));
        for (String brand: brands)
            threadPool.execute(new Supplier(brand));

        final int timeOut15 = 15_000;
        try {
            Thread.sleep(timeOut15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.shutdownNow();

    }
}
