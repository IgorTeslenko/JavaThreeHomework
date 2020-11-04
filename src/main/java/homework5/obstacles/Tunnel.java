package homework5.obstacles;

import homework5.Car;
import homework5.MainClass;

import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private final Semaphore smp;

    public Tunnel() {
        smp = new Semaphore(MainClass.CARS_COUNT / 2);
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car car) {
        try {
            if (smp.availablePermits() <= 0) {
                System.out.printf("%s подъехал к тоннелю и ждет%n", car.getName());
            }
            smp.acquire();
            System.out.printf("%s начал этап: %s%n", car.getName(), description);
            Thread.sleep(length / car.getSpeed() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.printf("%s закончил этап: %s%n", car.getName(), description);
            smp.release();
        }
    }
}
