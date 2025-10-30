package lotto.domain.purchase;

class Money {

    private final int amount;

    private Money(int amount) {
        requirePositive(amount);
        this.amount = amount;
    }

    static Money from(String input) {
        return new Money(parseToInt(input));
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidPurchaseException(ErrorType.OUT_OF_INTEGER);
        }
    }

    private void requirePositive(int amount) {
        if (amount <= 0) {
            throw new InvalidPurchaseException(ErrorType.NOT_POSITIVE);
        }
    }

    PurchaseQuantity toLottoGames() {
        return PurchaseQuantity.from(amount);
    }

    int amount() {
        return amount;
    }
}
