package homework1.task2;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayToArrayList {

    public static void main(String[] args) {
        String[] stringArr = new String[5];
        Integer[] integerArr = new Integer[5];

        Arrays.fill(stringArr, "Some String");
        Arrays.fill(integerArr, 10);

        ArrayList<String> strings = arrayToArraylist(stringArr);
        ArrayList<Number> numbers = arrayToArraylist(integerArr);
    }

    private static <T> ArrayList<T> arrayToArraylist(T[] objArr) {
        return new ArrayList<>(Arrays.asList(objArr));
    }

}
