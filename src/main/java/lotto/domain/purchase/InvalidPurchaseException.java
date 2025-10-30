package lotto.domain.purchase;

import lotto.common.BusinessException;

class InvalidPurchaseException extends BusinessException {
    public InvalidPurchaseException(ErrorType error) {
        super(error.description());
    }
}
