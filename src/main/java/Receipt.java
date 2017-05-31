import java.util.ArrayList;

public class Receipt {
    ArrayList<Item> items;
    ArrayList<String> errors;
    ArrayList<ItemGrouping> groupings;

    Receipt() {
        items = new ArrayList<Item>();
        errors = new ArrayList<String>();
        groupings = new ArrayList<ItemGrouping>();
    }

    void addError(String string) {
        errors.add(string);
    }

    void addItem(Item item) {
        items.add(item);
    }

    /*
    This method puts the items in their groupings and prints everything.
     */
    String printSummary() {
        toGroupings();
        StringBuilder sb = new StringBuilder();
        for(ItemGrouping group: groupings) {
            sb.append(group.groupingToString());
        }
        sb.append("\n");
        sb.append(printErrors());
        return sb.toString();
    }

    String printErrors() {
        StringBuilder sb = new StringBuilder();
        sb.append("There were "  + errors.size() + " errors. Error lines listed below.\n");
        for(String error : errors) {
            sb.append(error);
            sb.append("\n");
        }
        return sb.toString();
    }

    private void toGroupings() {
        for(Item item : items) {
            if(!addToAGrouping(item)) {
                groupings.add(new ItemGrouping(item));
            }
        }
    }

    private boolean addToAGrouping(Item item) {
        for(ItemGrouping group : groupings) {
            if(group.belongsInGroup(item)) {
                group.addToGrouping(item);
                return true;
            }
        }
        return false;
    }

}
