package lotto.domain.match;


class NumberToken {

    private final int number;

    private NumberToken(int number) {
        this.number = number;
    }

    public static NumberToken from(String numberInput) {
        requireNumeric(numberInput);
        return new NumberToken(parseToInt(numberInput));
    }

    private static void requireNumeric(String input) {
        for (char token : input.toCharArray()) {
            requireDigit(token);
        }
    }

    private static void requireDigit(char token) {
        if (!Character.isDigit(token)) {
            throw new InvalidMatchConditionException(ErrorType.NOT_DIGITS_AND_DELIMITERS);
        }
    }

    private static int parseToInt(String numbersInput) {
        try {
            return Integer.parseInt(numbersInput);
        } catch (NumberFormatException e) {
            throw new InvalidMatchConditionException(ErrorType.OUT_OF_INTEGER);
        }
    }


    int value() {
        return number;
    }
}
