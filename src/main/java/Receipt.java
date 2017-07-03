import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private Receipt() {}

    protected static String printSummary(List<Item> items, List<String> errors) {
        List<ItemGrouping> groupings = placeItemsInGroupings(items);
        StringBuilder sb = new StringBuilder();
        for(ItemGrouping group: groupings) {
            sb.append(group.groupingToString(getMaxItemNameLength(items) + 8));
        }
        sb.append(printErrors(errors));
        return sb.toString();
    }

    private static int getMaxItemNameLength(List<Item> items) {
        int max = 0;
        for(Item item : items) {
            if(item.getName().length() > max) {
                max = item.getName().length();
            }
        }
        return max;
    }

    protected static String printErrors(List<String> errors) {
        StringBuilder sb = new StringBuilder();
        sb.append("There were "  + errors.size() + " errors. Error lines listed below.\n");
        for(String error : errors) {
            sb.append(error);
            sb.append("\n");
        }
        return sb.toString();
    }

    protected static List<ItemGrouping> placeItemsInGroupings(List<Item> items) {
        List<ItemGrouping> groupings = new ArrayList<>();
        for(Item item : items) {
            if(!addToAGrouping(item, groupings)) {
                groupings.add(new ItemGrouping(item));
            }
        }
        return groupings;
    }

    private static boolean addToAGrouping(Item item, List<ItemGrouping> groupings) {
        for(ItemGrouping group : groupings) {
            if(group.canAccept(item)) {
                group.add(item);
                return true;
            }
        }
        return false;
    }

}
