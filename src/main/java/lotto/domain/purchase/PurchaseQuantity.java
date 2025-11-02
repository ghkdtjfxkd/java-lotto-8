package lotto.domain.purchase;

class PurchaseQuantity {

    private static final int LOTTO_PRICE = 1000;

    private final int count;

    private PurchaseQuantity(int money) {
        requireDivisibleByUnit(money);
        this.count = calculateCount(money);
    }

    static PurchaseQuantity from(int money) {
        requireDivisibleByUnit(money);
        return new PurchaseQuantity(money);
    }

    private static void requireDivisibleByUnit(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new InvalidPurchaseException(ErrorType.NOT_DIVISIBLE_BY_UNIT);
        }
    }

    private int calculateCount(int money) {
        return money / LOTTO_PRICE;
    }

    int count() {
        return this.count;
    }
}
