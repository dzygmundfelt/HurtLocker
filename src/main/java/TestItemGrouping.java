import org.junit.*;

public class TestItemGrouping {

    @Test
    public void toFirstLetterUpperCaseTest() {
        String string = "abcDeF";
        String expected = "Abcdef";

        String actual = ItemGrouping.toFirstLetterUpperCase(string);

        Assert.assertEquals(expected, actual);
    }

}
