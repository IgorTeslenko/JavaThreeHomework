import homework6.task3.AnotherArrayMethodEx;
import org.junit.Assert;
import org.junit.Test;

public class AnotherArrayMethodExTest {

    @Test
    public void testIfTheresOneOrFour() {
        Assert.assertTrue(AnotherArrayMethodEx.ifTheresOneOrFour(new int[]{1, 0, 2, 3, 4, 7, 1, 3, 4, 1, 2, 3, 5, 4, 6, 1, 2, 3, 5}));
        Assert.assertTrue(AnotherArrayMethodEx.ifTheresOneOrFour(new int[]{0, 8, 9, 2, 4, 6, 5, 0, 2, 4, 3, 7, 5, 6, 0}));
        Assert.assertTrue(AnotherArrayMethodEx.ifTheresOneOrFour(new int[]{2, 8, 7, 9, 4, 3, 5, 6, 2, 3, 6, 5, 2, 5, 6, 8}));
        Assert.assertFalse(AnotherArrayMethodEx.ifTheresOneOrFour(new int[]{0, 2, 3, 7, 3, 2, 3, 5, 6, 2, 3, 5, 2, 3, 7, 7, 7, 8}));
        Assert.assertFalse(AnotherArrayMethodEx.ifTheresOneOrFour(new int[]{8, 5, 3, 2, 7, 0, 9, 8, 7, 3, 7, 5, 7, 3, 2, 5, 0, 6, 3, 2, 7}));
    }

}
