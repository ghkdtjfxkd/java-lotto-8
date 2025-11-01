package lotto.common;

public class BusinessException extends IllegalArgumentException {
    protected BusinessException(String message) {
        super(message);
    }
}
