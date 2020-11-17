import homework6.task2.SomeArrayMethodEx;
import org.junit.Assert;
import org.junit.Test;

public class SomeArrayMethodExTest {

    @Test
    public void testReturnAllAfterLastFour() {
        Assert.assertArrayEquals(new int[]{3, 6}, SomeArrayMethodEx.returnAllAfterLastFour(new int[]{1, 2, 3, 4, 6, 234, 6, 345, 34, 3, 4, 3, 6}));
        Assert.assertArrayEquals(new int[]{}, SomeArrayMethodEx.returnAllAfterLastFour(new int[]{1, 2, 3, 0, 9, 4, 5, 7, 1, 6, 4, 1, 2, 3, 4}));
        Assert.assertArrayEquals(new int[]{6, 1, 9, 8, 2, 3, 6, 5, 0, 5}, SomeArrayMethodEx.returnAllAfterLastFour(new int[]{3, 4, 6, 1, 9, 8, 2, 3, 6, 5, 0, 5}));
        Assert.assertArrayEquals(new int[]{3, 8, 5, 7, 6, 3}, SomeArrayMethodEx.returnAllAfterLastFour(new int[]{2, 4, 3, 5, 8, 3, 4, 2, 8, 5, 2, 4, 3, 8, 5, 7, 6, 3}));
    }

}
