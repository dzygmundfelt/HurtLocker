final class ItemGroupingLineFormatter {

    private ItemGroupingLineFormatter() {
    }

    static String itemLine(int columnWidth, String itemName, int totalCount) {
        int width = columnWidth/2;
        return String.format("%-" + width + "s %" + width + "s\t\t%-" + width + "s %" + width + "s\n", "name: ", itemName, "seen: ", totalCount + " times");
    }

    static String printBarrier(int columnWidth, char character) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < columnWidth; i++) {
            sb.append(character);
        }
        sb.append("\t\t");
        for(int i = 0; i < columnWidth; i++) {
            sb.append(character);
        }
        sb.append("\n");
        return sb.toString();
    }
}