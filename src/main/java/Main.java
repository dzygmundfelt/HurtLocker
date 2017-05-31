import org.apache.commons.io.IOUtils;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Main {

    public String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        String result = IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
        return result;
    }

    public static void main(String[] args) throws Exception{
        String output = (new Main()).readRawDataToString();
        System.out.println(output);
        JERKSONParser parser = new JERKSONParser();
        parser.parseInputToItems(output);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("putthehurtinonthehurtlocker.txt"), "utf-8"))) {
            writer.write(parser.receipt.printSummary());
        }
    }
}
