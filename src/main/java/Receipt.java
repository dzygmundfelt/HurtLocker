import java.util.ArrayList;

public class Receipt {
    ArrayList<Item> items;
    ArrayList<String> errors;

    Receipt() {
        items = new ArrayList<Item>();
        errors = new ArrayList<String>();
    }

    void addError(String string) {
        errors.add(string);
    }

    void addItem(Item item) {
        items.add(item);
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

}
