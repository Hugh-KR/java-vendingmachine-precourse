package vendingmachine.utils;

public enum Delimiter {

    COMMA(","),
    SEMICOLON(";"),
    BAR("-"),
    BRACKET_LEFT("["),
    BRACKET_RIGHT("]");        ;

    private final String unit;

    Delimiter(final String unit) {
        this.unit = unit;
    }

    public static String[] splitWithSemiColon(final String target) {
        return target.split(SEMICOLON.unit);
    }

    public static String[] splitWithComma(final String target) {
        return target.split(COMMA.unit);
    }

    public static String formatWithBar(final String target) {
        return String.format(target, COMMA.unit);
    }

    public static boolean isEnclosedInBracket(final String target) {
        return target.charAt(0) == BRACKET_LEFT.unit.charAt(0)
                && target.charAt(target.length() - 1) == BRACKET_RIGHT.unit.charAt(0);
    }

    public static String removeBracket(final String target) {
        return target.substring(1, target.length() - 1);
    }
}
