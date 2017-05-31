import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class ItemGrouping {

    private String name;
    private TreeMap<String, Integer> map;
    private int totalCount;
    private String nameRegex;

    ItemGrouping(Item item) {
        map = new TreeMap<>();
        map.put(item.getPrice(), 1);
        totalCount = 1;
        name = toFirstLetterUpperCase(item.getName());
        nameToRegex();
    }

    static String toFirstLetterUpperCase(String string) {
        char[] chars = string.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            chars[i] = Character.toLowerCase(chars[i]);
        }
        return new String(chars);
    }

    void nameToRegex() {
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

    String getNameRegex() {
        return nameRegex;
    }

    boolean belongsInGroup(Item item) {
        return item.getName().length() == name.length()
                && Pattern.matches(nameRegex, item.getName());
    }

    void addToGrouping(Item item) {
        Integer value = map.get(item.getPrice());
        if(value == null) {
            map.put(item.getPrice(), 1);
        } else {
            map.put(item.getPrice(), value + 1);
        }
        totalCount++;
    }

    String groupingToString(int columnWidth) {
        StringBuilder sb = new StringBuilder();
        sb.append(ItemGroupingLineFormatter.itemLine(columnWidth, name, totalCount));
        sb.append(ItemGroupingLineFormatter.printBarrier(columnWidth, "="));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append(ItemGroupingLineFormatter.itemLine(columnWidth, entry.getKey(), entry.getValue()));
            sb.append(ItemGroupingLineFormatter.printBarrier(columnWidth, "-"));
        }
        return sb.toString();
    }
}