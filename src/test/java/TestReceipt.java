import org.junit.*;

public class TestReceipt {

    private Receipt receipt;

    @Before
    public void initialize() {
        receipt = new Receipt();
    }

    @Test
    public void printErrorsTest() {
        receipt.addError("here's one error");
        receipt.addError("and here's another");
        String expected = "There were 2 errors. Error lines listed below.\n" +
                "here's one error\n" +
                "and here's another\n";

        String actual = receipt.printErrors();

        Assert.assertEquals(expected, actual);
    }
}
