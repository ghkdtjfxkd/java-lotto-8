package lotto.domain.match;

import lotto.common.BusinessException;

class InvalidMatchConditionException extends BusinessException {
    public InvalidMatchConditionException(ErrorType error) {
        super(error.description());
    }
}
