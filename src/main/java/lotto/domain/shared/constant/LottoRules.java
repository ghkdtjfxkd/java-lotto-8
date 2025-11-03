package lotto.domain.shared.constant;

public enum LottoRules {

    MIN_NUMBER(1),
    MAX_NUMBER(45),

    LOTTO_BALL_COUNT(6);

    private final int value;

    LottoRules(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public static boolean isOutOfRange(int number) {
        return !(MIN_NUMBER.value() <= number && number <= MAX_NUMBER.value());
    }

    public static boolean isInvalidCount(int count) {
        return count != LOTTO_BALL_COUNT.value();
    }
}
