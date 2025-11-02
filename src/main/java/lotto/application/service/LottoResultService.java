package lotto.application.service;

import java.util.List;
import lotto.application.dto.ProfitRateDto;
import lotto.application.dto.WinningStatisticDto;

public interface LottoResultService {

    List<WinningStatisticDto> matchedResults();

    ProfitRateDto calculateProfitRate();
}
