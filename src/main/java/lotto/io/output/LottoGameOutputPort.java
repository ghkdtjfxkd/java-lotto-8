package lotto.io.output;

import lotto.common.BusinessException;
import lotto.io.output.dto.PurchasedLottoGamesResponse;

public interface LottoGameOutputPort {

    void print(PurchasedLottoGamesResponse response);
    void printTaskDivider();
    void printError(BusinessException exception);
}
