class ItemGroupingLineFormatter {

    private ItemGroupingLineFormatter() {}

    static String topItemLine(int columnWidth, String itemName, int totalCount) {
        return String.format("%-" + (columnWidth)/2 + "s %" + columnWidth/2 + "s\t\t%-" + columnWidth/2 + "s %" + (columnWidth)/2 + "s\n", "name: ", itemName, "seen: ", totalCount + " times");
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

    static String printPriceLine(int columnWidth, String price, Integer integer) {
        return String.format("%-" + (columnWidth)/2 + "s %" + columnWidth/2 + "s\t\t%-" + columnWidth/2 + "s %" + (columnWidth)/2 + "s\n", "price: ", price, "seen: ", integer + " times");
    }
}