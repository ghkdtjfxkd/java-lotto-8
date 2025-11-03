package lotto.util;

public final class InputChecks {

    private InputChecks() {
    }

    public static boolean isBlank(String input) {
        return input == null || input.isBlank();
    }

    public static boolean isNotNumeric(String input) {
        if (isBlank(input)) {
            return true;
        }
        return input.chars().anyMatch(c -> !Character.isDigit(c));
    }

    public static boolean hasRedundantLeadingZero(String input) {
        return input.length() > 1 && input.startsWith("0");
    }
}
