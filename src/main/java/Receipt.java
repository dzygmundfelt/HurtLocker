import java.util.ArrayList;
import java.util.List;

public class Receipt {
    List<Item> items;
    List<String> errors;
    List<ItemGrouping> groupings;

    Receipt() {
        items = new ArrayList<>();
        errors = new ArrayList<>();
        groupings = new ArrayList<>();
    }

    protected void addError(String string) {
        errors.add(string);
    }

    protected void addItem(Item item) {
        items.add(item);
    }

    /*
    This method puts the items in their groupings and prints everything.
     */
    protected String printSummary() {
        placeItemsInGroupings();
        int maxItemNameLength = getMaxItemNameLength();
        StringBuilder sb = new StringBuilder();
        for(ItemGrouping group: groupings) {
            sb.append(group.groupingToString(maxItemNameLength + 8));
        }
        sb.append(printErrors());
        return sb.toString();
    }

    private int getMaxItemNameLength() {
        int max = 0;
        for(Item item : items) {
            if(item.getName().length() > max) {
                max = item.getName().length();
            }
        }
        return max;
    }

    protected String printErrors() {
        StringBuilder sb = new StringBuilder();
        sb.append("There were "  + errors.size() + " errors. Error lines listed below.\n");
        for(String error : errors) {
            sb.append(error);
            sb.append("\n");
        }
        return sb.toString();
    }

    private void placeItemsInGroupings() {
        for(Item item : items) {
            if(!addToAGrouping(item)) {
                groupings.add(new ItemGrouping(item));
            }
        }
    }

    private boolean addToAGrouping(Item item) {
        for(ItemGrouping group : groupings) {
            if(group.canAccept(item)) {
                group.add(item);
                return true;
            }
        }
        return false;
    }

}
