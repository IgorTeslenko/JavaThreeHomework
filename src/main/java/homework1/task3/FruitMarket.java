package homework1.task3;


import java.util.ArrayList;

public class FruitMarket {

    public static void main(String[] args) {
        Box appleBox = new Box(new ArrayList<>());
        Box appleBox2 = new Box(new ArrayList<>());
        Box orangeBox = new Box(new ArrayList<>());
        Box orangeBox2 = new Box(new ArrayList<>());

        for (int i = 0; i <= 8; i++) {
            appleBox.addFruit(new Apple());
        }
        for (int i = 0; i <= 3; i++) {
            appleBox2.addFruit(new Apple());
        }
        for (int i = 0; i <= 5; i++) {
            orangeBox.addFruit(new Orange());
        }
        for (int i = 0; i <= 3; i++) {
            orangeBox2.addFruit(new Orange());
        }

        System.out.println(appleBox.compare(orangeBox));
        System.out.println("===============");
        appleBox.mergeBoxes(appleBox2);
        System.out.println(appleBox.toString());
        System.out.println(appleBox2.toString());
        System.out.println("===============");
        appleBox2.mergeBoxes(orangeBox);
        System.out.println("===============");
        appleBox2.addFruit(new Orange());

    }

}
