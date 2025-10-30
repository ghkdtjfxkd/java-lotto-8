package lotto.common;

public class BusinessException extends RuntimeException {
    protected BusinessException(String message) {
        super(message);
    }
}
