import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JERKSONParser {

    private static final String entryDividers = ";@^%*";
    private static final String[] fieldRegex = {"[nN][aA][mM][eE]","[pP][rR][iI][cC][eE]","[tT][yY][pP][eE]","[eE][xX][pP][iI][rR][aA][tT][iI][oO][nN]"};

    Receipt receipt;

    JERKSONParser() {
        receipt = new Receipt();
    }

    protected void parseInputToItems(String input) {
        Pattern p = Pattern.compile("([a-zA-Z0-9.@!;:%^&*(/]+)##");
        Matcher m = p.matcher(input);
        while(m.find()) {
            try {
                parseLine(m.group(1));
            } catch(LineFormatException lfe) {
                receipt.addError(m.group(1));
            }
        }
    }

    protected void parseLine(String line) throws LineFormatException {
        String[] itemFields = new String[fieldRegex.length];
        for(int i = 0; i < fieldRegex.length; i++) {
            Pattern p = Pattern.compile("(" + fieldRegex[i] + "):([a-zA-Z/.0-9]+)[" + entryDividers + "]?");
            Matcher m = p.matcher(line);
            if(!m.find()) {
                throw new LineFormatException();
            }
            itemFields[i] = m.group(2);
        }

        receipt.addItem(new Item(itemFields[0], itemFields[1], itemFields[2], itemFields[3]));
    }

}
