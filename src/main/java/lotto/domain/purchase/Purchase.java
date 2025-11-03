package lotto.domain.purchase;

import lotto.util.InputChecks;

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
        requireNoRedundantLeadingZero(input);
    }

    private void requireNonBlank(String input) {
        if (InputChecks.isBlank(input)) {
            throw new InvalidPurchaseException(ErrorType.BLANK);
        }
    }

    private void requireNumeric(String input) {
        if(InputChecks.isNotNumeric(input)) {
            throw new InvalidPurchaseException(ErrorType.NOT_DIGIT);
        }
    }

    private void requireNoRedundantLeadingZero(String input) {
        if(InputChecks.hasRedundantLeadingZero(input)) {
            throw new InvalidPurchaseException(ErrorType.REDUNDANT_LEADING_ZERO);
        }
    }

    public int quantity() {
        return money.toLottoGames().count();
    }

    public int amount() {
        return money.amount();
    }
}
