import org.junit.*;

public class TestItemGroupingLineFormatter {

    @Test
    public void repeatTest() {
        String expected = "aaaaaaaa";

        String actual = ItemGroupingLineFormatter.repeat(8, "a");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void repeatNothingTest() {
        String expected = "";

        String actual = ItemGroupingLineFormatter.repeat(11,"");

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void itemLineTest() {
        String expected = "name:  a house\t\tseen:  9 times\n";

        String actual = ItemGroupingLineFormatter.itemLine(12, "a house", "name", 9);

        Assert.assertEquals(expected, actual);
    }

}
