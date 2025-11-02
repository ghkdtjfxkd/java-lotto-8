package lotto.io.output;

import lotto.common.BusinessException;
import lotto.io.output.dto.ProfitRateResponse;
import lotto.io.output.dto.PurchasedLottoGamesResponse;
import lotto.io.output.dto.WinningStatisticsResponse;

public interface LottoGameOutputPort {

    void print(PurchasedLottoGamesResponse response);

    void print(WinningStatisticsResponse response);

    void print(ProfitRateResponse response);

    void printTaskDivider();

    void printError(BusinessException exception);
}
