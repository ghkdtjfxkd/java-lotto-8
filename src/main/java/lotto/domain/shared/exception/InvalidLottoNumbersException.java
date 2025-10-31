package lotto.domain.shared.exception;

class InvalidLottoNumbersException extends RuntimeException {
    public InvalidLottoNumbersException(String message) {
        super(message);
    }
}
