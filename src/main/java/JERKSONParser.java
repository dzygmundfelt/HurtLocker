import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JERKSONParser {

    private static final String entryDividers = ";@^%*";
    private static final String[] fieldRegex = {"[nN][aA][mM][eE]","[pP][rR][iI][cC][eE]","[tT][yY][pP][eE]","[eE][xX][pP][iI][rR][aA][tT][iI][oO][nN]"};

    Receipt receipt;

    JERKSONParser() {
        receipt = new Receipt();
    }

    void parseInputToItems(String input) {
        Pattern p = Pattern.compile("([a-zA-Z0-9.@!;:%^&*(/]+)##");
        Matcher m = p.matcher(input);
        while(m.find()) {
            parseLine(m.group(1));
        }
    }

    void parseLine(String line) {
        //try to parse {create new Item}, catch an error {incrementErrors}
        //while m.find() some pattern, examine pattern
        //


    }

    private String getFieldValue(int fRIndex, String line) {
        Pattern p = Pattern.compile("(" + fieldRegex[fRIndex] + "):(\\w+)?");
        Matcher m = p.matcher(line);
        if(m.find()) {
            return m.group(2);
        }
        return null;
    }

}
