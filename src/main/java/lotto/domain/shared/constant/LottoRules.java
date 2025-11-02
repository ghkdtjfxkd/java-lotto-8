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
}
