import java.util.LinkedList;
import java.util.regex.Pattern;

public class ItemGrouping {

    private String name;
    private LinkedList<String> prices;
    private LinkedList<Integer> counts;
    private String nameRegex;

    ItemGrouping(Item item) {
        prices = new LinkedList<String>();
        prices.add(item.getPrice());
        counts = new LinkedList<Integer>();
        counts.add(1);
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

    int getTotalcount() {
        int count = 0;
        for (Integer i : counts) {
            count += i;
        }
        return count;
    }

    boolean belongsInGroup(Item item) {
        return item.getName().length() == name.length()
                && Pattern.matches(nameRegex, item.getName());
    }

    void addToGrouping(Item item) {
        int index = prices.indexOf(item.getPrice());
        if (index == -1) {
            prices.add(item.getPrice());
            counts.add(1);
        } else {
            counts.set(index, counts.get(index) + 1);
        }
    }

    String groupingToString(int columnWidth) {
        StringBuilder sb = new StringBuilder();
        sb.append(ItemGroupingLineFormatter.itemLine(columnWidth, name, getTotalcount()));
        sb.append(ItemGroupingLineFormatter.printBarrier(columnWidth, "="));
        for (int i = 0; i < prices.size(); i++) {
            sb.append(ItemGroupingLineFormatter.itemLine(columnWidth, prices.get(i), counts.get(i)));
            sb.append(ItemGroupingLineFormatter.printBarrier(columnWidth, "-"));
        }
        return sb.toString();
    }
}