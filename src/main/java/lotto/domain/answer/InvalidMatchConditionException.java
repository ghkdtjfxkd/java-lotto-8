package lotto.domain.answer;

import lotto.common.BusinessException;

class InvalidMatchConditionException extends BusinessException {
    public InvalidMatchConditionException(ErrorType error) {
        super(error.description());
    }
}
