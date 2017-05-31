import java.util.LinkedList;
import java.util.regex.Pattern;

public class ItemGrouping {

    private String name;
    private LinkedList<String> prices;
    private LinkedList<Integer> counts;
    private String nameRegex;

    ItemGrouping(Item item) {
        prices = new LinkedList<String>();
        counts = new LinkedList<Integer>();
        name = toFirstLetterUpperCase(item.getName());
        nameToRegex();
    }

    static String toFirstLetterUpperCase(String string) {
        char[] chars = string.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        for(int i = 1; i < chars.length; i++) {
            chars[i] = Character.toLowerCase(chars[i]);
        }
        return new String(chars);
    }

    void nameToRegex() {
        StringBuilder sb = new StringBuilder();
        char[] chars = name.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            sb.append("[");
            sb.append(Character.toUpperCase(chars[i]));
            sb.append(Character.toLowerCase(chars[i]));
            sb.append("]");
        }
        nameRegex = sb.toString();
    }

    int getTotalcount() {
        int count = 0;
        for(Integer i : counts) {
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
        if(index == -1) {
            prices.add(item.getPrice());
            counts.add(1);
        } else {
            counts.set(index, counts.get(index) + 1);
        }
    }

    String groupingToString() {
        StringBuilder sb = new StringBuilder();

        sb.append("name: ");
        sb.append(name);
        sb.append("\t\t");
        sb.append(seenXTimes(getTotalcount()));
        sb.append("============= \t \t =============\n");
        for(int i = 0; i < prices.size(); i++) {
            sb.append(printPrice(prices.get(i), counts.get(i)));
            sb.append("-------------\t\t -------------\n");
        }

        return sb.toString();
    }

    String seenXTimes(int number) {
        return "seen : " + number + " times\n";
    }

    String printPrice(String price, Integer integer) {
        return "price:\t" + price + "\t\t" + seenXTimes(integer);
    }
}
