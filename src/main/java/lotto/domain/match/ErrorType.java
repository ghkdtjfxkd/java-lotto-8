package lotto.domain.match;

enum ErrorType {

    // WinningCondition
    BLANK("빈 입력은 올 수 없습니다.(BLANK)"),
    NOT_DIGITS_OR_DELIMITER("당첨 번호는 구분자(\",\")와 숫자만 입력해야 합니다."),
    DUPLICATES_EXIST("당첨 번호와 중복되는 보너스 번호를 입력하셨습니다."),

    // LottoNumberToken,
    OUT_OF_INTEGER("int 범위를 넘어가는 숫자가 입력됐습니다."),
    OUT_OF_LOTTO_NUMBER_RANGE("로또 번호 범위 (1 ~ 45)를 벗어나는 숫자가 입력됐습니다."),
    WHITE_SPACES_EXIST("구분자(\",\")와 구분자 사이에 공백 문자가 포함되어 있습니다."),
    NOT_DIGITS("로또 번호 입력은 숫자만 허용됩니다.");

    private static final String ERROR_PREFIX = "[ERROR]: ";

    private final String description;

    ErrorType(String message) {
        this.description = message;
    }

    String description() {
        return ERROR_PREFIX + description;
    }
}
