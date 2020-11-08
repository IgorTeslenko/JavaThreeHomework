package homework5;

import homework5.obstacles.Road;
import homework5.obstacles.Tunnel;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MainClass {

    public static final int CARS_COUNT = 4;
    private static final CyclicBarrier cb = new CyclicBarrier(CARS_COUNT + 1);
    public static final Podium places = new Podium();

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cb);
        }

        for (Car car : cars) {
            new Thread(car).start();
        }

        cb.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");

        cb.await();
        cb.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        System.out.printf("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Победил %s%n", places.getWinner().getName());
    }
}
