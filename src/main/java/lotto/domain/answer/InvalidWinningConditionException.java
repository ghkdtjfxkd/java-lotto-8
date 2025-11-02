package lotto.domain.answer;

import lotto.common.BusinessException;

class InvalidWinningConditionException extends BusinessException {
    public InvalidWinningConditionException(ErrorType error) {
        super(error.description());
    }
}
