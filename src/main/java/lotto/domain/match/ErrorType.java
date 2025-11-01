package lotto.domain.match;

enum ErrorType {

    BLANK("빈 입력은 올 수 없습니다.(BLANK)"),
    OUT_OF_INTEGER("int 범위를 넘어가는 숫자가 입력했습니다."),

    NOT_DIGITS_AND_DELIMITERS("당첨 번호는 구분자(\",\")와 숫자만 입력해야 합니다."),
    ;

    private static final String ERROR_PREFIX = "[ERROR]: ";

    private final String description;

    ErrorType(String message) {
        this.description = message;
    }

    String description() {
        return ERROR_PREFIX + description;
    }
}
