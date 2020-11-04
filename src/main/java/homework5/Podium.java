package homework5;

import java.util.ArrayList;
import java.util.List;

public class Podium {
    private final List<Car> arrangement;

    public Podium() {
        this.arrangement = new ArrayList<>();
    }

    public Car getWinner() {
        return arrangement.get(0);
    }

    public void addCar(Car car) {
        this.arrangement.add(car);
    }
}
