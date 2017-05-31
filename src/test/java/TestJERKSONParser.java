import org.junit.*;

public class TestJERKSONParser {

    private JERKSONParser parser;

    @Before
    public void initialize() {
        parser = new JERKSONParser();
    }

    @Test (expected =  LineFormatException.class)
    public void lineNotValidTest() throws LineFormatException {
        String line = "name:9090;name";

        parser.parseLine(line);
    }

    @Test (expected = LineFormatException.class)
    public void emptyFieldParseLineTest() throws LineFormatException {
        String line = "name:;price:3.35;type:food;expiration:5/31/17";

        parser.parseLine(line);
    }

    @Test (expected = LineFormatException.class)
    public void emptyStringParseLineTest() throws LineFormatException {
        String line = "";

        parser.parseLine(line);
    }

    @Test
    public void normalParseLineTest() throws LineFormatException {
        String line = "name:milk;price:3.35;type:food;expiration:5/31/17";
        Item expected = new Item("milk", "3.35", "food", "5/31/17");

        parser.parseLine(line);
        Item actual = parser.receipt.items.get(0);

        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getExpiration(), actual.getExpiration());
    }

    @Test
    public void differentCapitalizationButValidParseLineTest() throws LineFormatException {
        String line = "naMe:milk;pRice:3.35;TYPe:food;exPIration:5/31/17";
        Item expected = new Item("milk", "3.35", "food", "5/31/17");

        parser.parseLine(line);
        Item actual = parser.receipt.items.get(0);

        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getExpiration(), actual.getExpiration());
    }

    @Test
    public void funkyPunctuationButValidParseLineTest() throws LineFormatException {
        String line = "name:milk@price:3.35%type:food^expiration:5/31/17";
        Item expected = new Item("milk", "3.35", "food", "5/31/17");

        parser.parseLine(line);
        Item actual = parser.receipt.items.get(0);

        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getExpiration(), actual.getExpiration());
    }

    @Test
    public void parseValidInputTwoItemsTest() {
        String line = "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##";
        int expected = 2;

        parser.parseInputToItems(line);
        int actual = parser.receipt.items.size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseInputIncludesInvalidItemsSizeTest() {
        String line = "NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##";
        int expected = 3;

        parser.parseInputToItems(line);
        int actual = parser.receipt.items.size();

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void parseInputIncludesInvalidItemsErrorsSizeTest() {
        String line = "NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##naMe:;price:3.23;type:Food;expiration:1/04/2016##naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##";
        int expected = 1;

        parser.parseInputToItems(line);
        int actual = parser.receipt.errors.size();

        Assert.assertEquals(expected, actual);
    }
}
