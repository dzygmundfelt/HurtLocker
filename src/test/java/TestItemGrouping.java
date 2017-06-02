import org.junit.*;

public class TestItemGrouping {

    @Test
    public void toFirstLetterUpperCaseTest() {
        String string = "abcdef";
        String expected = "Abcdef";

        String actual = ItemGrouping.toFirstLetterUpperCase(string);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void belongsInGroupTrueTest() {
        ItemGrouping grouping = new ItemGrouping(new Item("flour", "3.22", "food", "sometime"));
        Item item = new Item("FloUR", "3.11", "food", "eventually");

        boolean result = grouping.canAccept(item);

        Assert.assertTrue(result);
    }

    @Test
    public void belongsInGroupFalseTest() {
        ItemGrouping grouping = new ItemGrouping(new Item("flour", "3.22", "food", "sometime"));
        Item item = new Item("bread", "3.11", "food", "eventually");

        boolean result = grouping.canAccept(item);

        Assert.assertFalse(result);
    }

}
