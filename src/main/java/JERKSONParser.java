import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JERKSONParser {

    private static final String entryDividers = ";@^%*";
    private static final String[] fieldRegex = {"[nN][aA][mM][eE]","[pP][rR][iI][cC][eE]","[tT][yY][pP][eE]","[eE][xX][pP][iI][rR][aA][tT][iI][oO][nN]"};

    private JERKSONParser() {}

    private static Matcher getMatcher(String pattern, String input) {
        Pattern p = Pattern.compile(pattern);
        return p.matcher(input);
    }

    protected static String parseInputToReceipt(String input) {
        List<Item> items = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        Matcher m = getMatcher("([a-zA-Z0-9.@!;:%^&*(/]+)##", input);
        while(m.find()) {
            try {
                items.add(parseLine(m.group(1)));
            } catch(LineFormatException lfe) {
                errors.add(m.group(1));
            }
        }
        return Receipt.printSummary(items, errors);
    }

    protected static Item parseLine(String line) throws LineFormatException {
        String[] itemFields = new String[fieldRegex.length];

        for(int i = 0; i < fieldRegex.length; i++) {
            Matcher m = getMatcher("(" + fieldRegex[i] + "):([a-zA-Z/.0-9]+)[" + entryDividers + "]?", line);
            if(!m.find()) {
                throw new LineFormatException();
            }
            itemFields[i] = m.group(2);
        }

        return new Item(itemFields[0], itemFields[1], itemFields[2], itemFields[3]);
    }

}
