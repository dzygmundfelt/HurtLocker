import org.junit.*;

public class FormatTesting {

    @Test
    public void printTest() {
        String expected = "";
        String actual = String.format("%-15s %15s %n", "name", "lastname");

        Assert.assertEquals(expected, actual);
    }

}
