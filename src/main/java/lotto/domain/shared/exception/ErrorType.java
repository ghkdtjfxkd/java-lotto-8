package lotto.domain.shared.exception;

public enum ErrorType {

    // Lotto
    INVALID_NUMBER_COUNT("로또 번호는 6개여야 합니다."),
    DUPLICATES_EXIST("로또 번호는 중복된 숫자 없게 구성되어야 합니다."),
    OUT_OF_LOTTO_NUMBER_RANGE("로또 번호 범위 (1 ~ 45)를 벗어나는 숫자가 입력됐습니다."),
    ;

    private static final String ERROR_PREFIX = "[ERROR]: ";

    private final String description;

    ErrorType(String message) {
        this.description = message;
    }

    public String description() {
        return ERROR_PREFIX + description;
    }
}
