package lotto.domain.purchase;

enum ErrorType {

    // Purchase
    BLANK("구매금액에 빈 입력은 올 수 없습니다.(BLANK)"),
    NOT_DIGIT("구매금액은 숫자만 입력해야 합니다."),

    // Money(purchase)
    OUT_OF_INTEGER("너무 큰 금액을 입력 하셨습니다. (int 범위를 넘어감)"),
    NOT_POSITIVE("구매 금액은 양수(0보다 큰 값)만 입력해야 합니다."),

    NOT_DIVISIBLE_BY_UNIT("구매 금액은 1000원 단위로 나누어 떨어져야 합니다.");
    private static final String ERROR_PREFIX = "[ERROR]: ";

    private final String description;

    ErrorType(String message) {
        this.description = message;
    }

    String description() {
        return ERROR_PREFIX + description;
    }
}
