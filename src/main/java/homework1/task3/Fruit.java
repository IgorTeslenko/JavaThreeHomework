package homework1.task3;

public class Fruit {

    private String fruitType;
    private float fruitWeight;

    public String getFruitType() {
        return fruitType;
    }

    public void setFruitType(String fruitType) {
        this.fruitType = fruitType;
    }

    public float getFruitWeight() {
        return this.fruitWeight;
    }

    public void setFruitWeight(float fruitWeight) {
        this.fruitWeight = fruitWeight;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "fruitType='" + fruitType + '\'' +
                '}';
    }
}
