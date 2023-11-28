package vendingmachine.utils;

import java.util.List;

public enum Delimiter {

    COMMA(","),
    SPACE(" "),
    BAR("-"),
    FORMAT("[%s]");

    private final String unit;

    Delimiter(final String unit) {
        this.unit = unit;
    }

    public static String[] splitWithComma(final String target) {
        return target.split(COMMA.unit);
    }

    public static String formatWithBar(final String target) {
        return String.format(target, COMMA.unit,  SPACE.unit);
    }

    public static String formatWithTarget(final String target) {
        return String.format(FORMAT.unit, target);
    }
}
