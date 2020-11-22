package homework6.task2;

import java.util.Arrays;

public class SomeArrayMethodEx {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 4, 6, 234, 6, 345, 34, 3, 4, 3, 6};

        int[] newArray = returnAllAfterLastFour(array);

        for (Object obj : newArray) {
            System.out.println(obj);
        }

    }

    public static int[] returnAllAfterLastFour(int[] array) {
        int index = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 4) {
                index = i;
            }
        }
        return Arrays.copyOfRange(array, index + 1, array.length);
    }

}
