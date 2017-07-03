import org.apache.commons.io.IOUtils;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Main {

    public static void main(String[] args) throws Exception {
        String output = (new Main()).readRawDataToString();
        System.out.println(output);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("putthehurtinonthehurtlocker.txt"), "utf-8"))) {
            writer.write(JERKSONParser.parseInputToReceipt(output));
        }
    }

    private String readRawDataToString() throws Exception{
        ClassLoader classLoader = getClass().getClassLoader();
        return IOUtils.toString(classLoader.getResourceAsStream("RawData.txt"));
    }
}
