package lotto.domain.shared.exception;

import lotto.common.BusinessException;

public class InvalidLottoNumbersException extends BusinessException {
    public InvalidLottoNumbersException(ErrorType error) {
        super(error.description());
    }
}
