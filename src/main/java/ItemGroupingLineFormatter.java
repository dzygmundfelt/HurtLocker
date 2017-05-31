import java.util.stream.Stream;
import static java.util.stream.Collectors.joining;

final class ItemGroupingLineFormatter {

    private ItemGroupingLineFormatter() {
    }

    static String itemLine(int columnWidth, String itemName, String label, int totalCount) {
        int width = columnWidth/2;
        return String.format("%-" + width + "s %" + width + "s\t\t%-" + width + "s %" + width + "s\n", label + ": ", itemName, "seen: ", totalCount + " times");
    }

    static String printBarrier(int columnWidth, String borderChar) {
        StringBuilder sb = new StringBuilder();
        sb.append(repeat(columnWidth, borderChar));
        sb.append("\t\t");
        sb.append(repeat(columnWidth, borderChar));
        sb.append("\n");
        return sb.toString();
    }

    static String repeat(int times, String borderChar) {
        return Stream.generate(() -> borderChar).limit(times).collect(joining());
    }
}