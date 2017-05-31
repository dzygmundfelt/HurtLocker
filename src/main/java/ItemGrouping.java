
public class ItemGrouping {

    private String name;
    private String[] prices;
    private int[] counts;
    private String nameRegex;

    ItemGrouping(Item item) {
        name = toFirstLetterUpperCase(item.getName());
        nameRegex =
    }

    String toFirstLetterUpperCase(String string) {
        char[] chars = string.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        for(int i = 1; i < chars.length; i++) {
            chars[i] = Character.toLowerCase(chars[i]);
        }
        return new String(chars);
    }

    String nameToRegex() {
        StringBuilder sb = new StringBuilder();
        char[] chars = name.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            sb.append(Character.toUpperCase(chars[i]));
            sb.append(Character.toLowerCase(chars[i]));
        }
    }

    int getTotalcount() {
        int count = 0;
        for(Integer i : counts) {
            count += i;
        }
        return count;
    }
}
