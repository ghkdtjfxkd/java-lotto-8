package lotto.application.service.result;

import java.util.List;
import lotto.application.dto.ProfitRateDto;
import lotto.application.dto.WinningStatisticDto;
import lotto.domain.purchase.PurchaseRepository;
import lotto.domain.result.LottoResultRepository;
import lotto.domain.result.Profit;
import lotto.domain.shared.vo.MatchedScore;

public class LottoResultQueryServiceImpl implements LottoResultQueryService {

    private final PurchaseRepository purchaseRepository;
    private final LottoResultRepository lottoResultRepository;

    public LottoResultQueryServiceImpl(PurchaseRepository purchaseRepository,
                                       LottoResultRepository lottoResultRepository) {
        this.purchaseRepository = purchaseRepository;
        this.lottoResultRepository = lottoResultRepository;
    }

    @Override
    public List<WinningStatisticDto> matchedResults() {
        return matchedScores().stream()
                .map(score -> WinningStatisticDto.from(score.rank(), score.winningCount()))
                .toList();
    }

    @Override
    public ProfitRateDto calculateProfitRate() {
        int purchaseAmount = purchaseRepository.amount();
        Profit profit = Profit.from(matchedScores());

        return new ProfitRateDto(profit.rateOf(purchaseAmount));
    }

    private List<MatchedScore> matchedScores() {
        return lottoResultRepository.getMatchedScores();
    }
}
