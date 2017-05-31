import org.junit.*;


public class TestJERKSONParser {

    JERKSONParser parser;

    @Before
    public void initialize() {
        parser = new JERKSONParser();
    }

    @Test (expected =  LineFormatException.class)
    public void lineNotValidTest() throws LineFormatException {
        String line = "name:9090;name";

        parser.parseLine(line);
    }

    @Test
    public void normalParseLineTest() {
        String line = "name:milk;price:3.35;type:food;expiration:5/31/17";
        Item expected = new Item("milk", 3.35, "food", "5/31/17");

        parser.parseLine(line);
        Item actual = parser.receipt.items[0];

        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void differentCapitalizationButValidParseLineTest() {
        String line = "naMe:milk;pRice:3.35;TYPe:food;exPIration:5/31/17";
        Item expected = new Item("milk", 3.35, "food", "5/31/17");

        parser.parseLine(line);
        Item actual = parser.receipt.items[0];

        Assert.assertTrue(expected.equals(actual));
    }

    @Test
    public void funkyPunctuationButValidParseLineTest() {
        String line = "name:milk@price:3.35%type:food^expiration:5/31/17";
        Item expected = new Item("milk", 3.35, "food", "5/31/17");

        parser.parseLine(line);
        Item actual = parser.receipt.items[0];

        Assert.assertTrue(expected.equals(actual));
    }
}
