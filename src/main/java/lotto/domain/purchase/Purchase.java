package lotto.domain.purchase;

public class Purchase {

    private final Money money;

    private Purchase(String input) {
        requireValid(input);
        this.money = Money.from(input);
    }

    public static Purchase from(String input) {
        return new Purchase(input);
    }

    private void requireValid(String input) {
        requireNonBlank(input);
        requireNumeric(input);
    }

    private void requireNonBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new InvalidPurchaseException(ErrorType.BLANK);
        }
    }

    private void requireNumeric(String input) {
        for (char token : input.toCharArray()) {
            requireDigit(token);
        }
    }

    private void requireDigit(char token) {
        if (!Character.isDigit(token)) {
            throw new InvalidPurchaseException(ErrorType.NOT_DIGIT);
        }
    }

    public int quantity() {
        return money.toLottoGames().count();
    }

    public int amount() {
        return money.amount();
    }
}
