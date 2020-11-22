package homework6.task3;

public class AnotherArrayMethodEx {

    public static void main(String[] args) {
        int[] array = new int[]{5, 5, 5, 5, 5, 5, 4, 5, 5, 5, 5, 5};

        System.out.println(ifTheresOneOrFour(array));

    }

    public static boolean ifTheresOneOrFour(int[] array) {
        for (int i : array) {
            if (i == 1 || i == 4) {
                return true;
            }
        }
        return false;
    }

}
