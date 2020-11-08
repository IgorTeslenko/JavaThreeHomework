package homework5;

import java.util.concurrent.CyclicBarrier;

public class Car implements Runnable {

    private static int CARS_COUNT = 0;

    private final Race race;
    private final int speed;
    private final String name;
    private final CyclicBarrier cb;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed, CyclicBarrier barrier) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cb = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.printf("%s готовится%n", this.name);
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.printf("%s готов%n", this.name);
            cb.await();
            cb.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            synchronized (MainClass.places) {
                MainClass.places.addCar(this);
            }

            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}