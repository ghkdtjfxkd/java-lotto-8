package lotto.application.service.result;

import java.util.List;
import lotto.application.dto.ProfitRateDto;
import lotto.application.dto.WinningStatisticDto;

public interface LottoResultQueryService {

    List<WinningStatisticDto> matchedResults();

    ProfitRateDto calculateProfitRate();
}
