package homework5.obstacles;

import homework5.Car;

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car car) {
        try {
            System.out.printf("%s начал этап: %s%n", car.getName(), description);
            Thread.sleep(length / car.getSpeed() * 1000);
            System.out.printf("%s закончил этап: %s%n", car.getName(), description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}