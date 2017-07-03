import org.junit.*;

public class TestJERKSONParser {

    @Test (expected =  LineFormatException.class)
    public void lineNotValidTest() throws LineFormatException {
        String line = "name:9090;name";

        JERKSONParser.parseLine(line);
    }

    @Test (expected = LineFormatException.class)
    public void emptyFieldParseLineTest() throws LineFormatException {
        String line = "name:;price:3.35;type:food;expiration:5/31/17";

        JERKSONParser.parseLine(line);
    }

    @Test (expected = LineFormatException.class)
    public void emptyStringParseLineTest() throws LineFormatException {
        String line = "";

        JERKSONParser.parseLine(line);
    }

    @Test
    public void normalParseLineTest() throws LineFormatException {
        String line = "name:milk;price:3.35;type:food;expiration:5/31/17";
        Item expected = new Item("milk", "3.35", "food", "5/31/17");

        Item actual = JERKSONParser.parseLine(line);

        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getExpiration(), actual.getExpiration());
    }

    @Test
    public void differentCapitalizationButValidParseLineTest() throws LineFormatException {
        String line = "naMe:milk;pRice:3.35;TYPe:food;exPIration:5/31/17";
        Item expected = new Item("milk", "3.35", "food", "5/31/17");

        Item actual = JERKSONParser.parseLine(line);

        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getExpiration(), actual.getExpiration());
    }

    @Test
    public void funkyPunctuationButValidParseLineTest() throws LineFormatException {
        String line = "name:milk@price:3.35%type:food^expiration:5/31/17";
        Item expected = new Item("milk", "3.35", "food", "5/31/17");

        Item actual = JERKSONParser.parseLine(line);

        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getType(), actual.getType());
        Assert.assertEquals(expected.getExpiration(), actual.getExpiration());
    }

}
