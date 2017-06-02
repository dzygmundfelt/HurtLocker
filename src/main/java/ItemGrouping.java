import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class ItemGrouping {

    private String name;
    private Map<String, Integer> map;
    private int totalCount;
    private String nameRegex;

    ItemGrouping(Item item) {
        map = new TreeMap<>();
        map.put(item.getPrice(), 1);
        totalCount = 1;
        name = toFirstLetterUpperCase(item.getName().toLowerCase());
        setNameRegex();
    }

    public static String toFirstLetterUpperCase(String string) {
        String firstChar = new Character(string.charAt(0)).toString();
        return string.replaceFirst(firstChar, firstChar.toUpperCase());
    }

    private void setNameRegex() {
        StringBuilder sb = new StringBuilder();
        char[] chars = name.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            sb.append("[");
            if (chars[i] == 'o' || chars[i] == 'O') {
                sb.append(0);
            }
            sb.append(Character.toUpperCase(chars[i]));
            sb.append(Character.toLowerCase(chars[i]));
            sb.append("]");
        }
        nameRegex = sb.toString();
    }

    protected boolean canAccept(Item item) {
        return item.getName().length() == name.length()
                && Pattern.matches(nameRegex, item.getName());
    }

    protected void add(Item item) {
        Integer value = map.get(item.getPrice());
        if(value == null) {
            map.put(item.getPrice(), 1);
        } else {
            map.put(item.getPrice(), value + 1);
        }
        totalCount++;
    }

    private String[] getBorderChars() {
        String[] borderChars = new String[map.entrySet().size() + 1];
        borderChars[0] = "=";
        for(int i = 1; i < borderChars.length - 1; i++) {
            borderChars[i] = "-";
        }
        borderChars[borderChars.length - 1] = "";
        return borderChars;
    }

    protected String groupingToString(int columnWidth) {
        StringBuilder sb = new StringBuilder();
        String[] borderChars = getBorderChars();
        int i = 0;
        sb.append(ItemGroupingLineFormatter.itemLine(columnWidth, name, "name", totalCount));
        sb.append(ItemGroupingLineFormatter.printBarrier(columnWidth, borderChars[i]));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            i++;
            sb.append(ItemGroupingLineFormatter.itemLine(columnWidth, entry.getKey(), "price", entry.getValue()));
            sb.append(ItemGroupingLineFormatter.printBarrier(columnWidth, borderChars[i]));
        }
        return sb.toString();
    }
}