package homework1.task1;

import homework1.task3.Apple;
import homework1.task3.Fruit;
import homework1.task3.Orange;

import java.util.Arrays;

public class SwapArrayElements {

    public static void main(String[] args) {
        Object[] objArray = new Fruit[10];

        Arrays.fill(objArray, new Fruit());

        objArray[0] = new Apple();
        objArray[5] = new Orange();

        swapTwoArrayElements(objArray, 0, 5);
    }

    private static void swapTwoArrayElements(Object[] objArray, int index1, int index2) {
        Object tempObject = objArray[index1];
        objArray[index1] = objArray[index2];
        objArray[index2] = tempObject;
    }

}
