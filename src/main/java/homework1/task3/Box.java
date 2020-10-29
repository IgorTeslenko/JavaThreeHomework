package homework1.task3;

import java.util.ArrayList;

public class Box {

    private final ArrayList<Fruit> box;

    public Box(ArrayList<Fruit> box) {
        this.box = box;
    }

    public void addFruit(Fruit fruit) {
        if (boxFruitMatch(fruit)) {
            this.box.add(fruit);
        } else {
            System.err.println("В коробке лежат фрукты другого типа");
        }
    }

    public float getWeight() {
        float boxWeight = 0;
        for (Fruit fruit : this.box) {
            boxWeight += fruit.getFruitWeight();
        }
        return boxWeight;
    }

    public boolean compare(Box box) {
        return this.getWeight() == box.getWeight();
    }

    public void mergeBoxes(Box box) {
        if (boxFruitMatch(box.box.get(0))) {
            box.box.addAll(this.box);
            this.box.clear();
        } else {
            System.err.println("В коробке лежат фрукты другого типа");
        }
    }

    //Мне очень не нравится реализация с проверкой типов фруктов в коробках, и я почти
    //уверен, что решать задачу нужно через создание ArrayList-ов с правильным типом
    //данных, но я так и не придумал, как это реализовать, выяснил только, что в
    // ArrayList<? extends Fruit> нельзя записывать никакие данные, а можно только читать.
    private boolean boxFruitMatch(Fruit fruit) {
        if (!this.box.isEmpty()) {
            return fruit.getFruitType().equals(this.box.get(0).getFruitType());
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Box{" +
                "box=" + box +
                '}';
    }
}
