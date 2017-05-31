import java.util.ArrayList;

public class Receipt {
    Item[] items;
    private ArrayList<String> errors;

    void addError(String string) {
        errors.add(string);
    }



}
