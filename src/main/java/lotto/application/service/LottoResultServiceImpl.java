package lotto.application.service;

import java.util.List;
import lotto.application.dto.WinningStatisticDto;
import lotto.domain.lottoGame.LottoTicketRepository;
import lotto.domain.answer.LottoAnswerRepository;
import lotto.domain.purchase.PurchaseRepository;
import lotto.domain.result.LottoResult;
import lotto.domain.result.Profit;
import lotto.domain.shared.vo.Lotto;
import lotto.domain.shared.vo.LottoAnswer;

public class LottoResultServiceImpl implements LottoResultService {

    private final PurchaseRepository purchaseRepository;
    private final LottoTicketRepository lottoTicketRepository;
    private final LottoAnswerRepository lottoAnswerRepository;

    public LottoResultServiceImpl(PurchaseRepository purchaseRepository,
                                  LottoTicketRepository lottoTicketRepository,
                                  LottoAnswerRepository lottoAnswerRepository) {
        this.purchaseRepository = purchaseRepository;
        this.lottoTicketRepository = lottoTicketRepository;
        this.lottoAnswerRepository = lottoAnswerRepository;
    }

    public List<WinningStatisticDto> matchedResults() {
        LottoResult result = lottoResult();
        return result.scores().stream()
                .map(score -> WinningStatisticDto.from(score.rank(), score.winningCount()))
                .toList();
    }

    private LottoResult lottoResult() {
        List<Lotto> lottoGames = lottoTicketRepository.allLottoGames();
        LottoAnswer lottoAnswer = lottoAnswerRepository.getLottoAnswer();

        return LottoResult.from(lottoGames, lottoAnswer);
    }

    public double calculateProfitRate() {
        return Profit.from(lottoResult().scores()).rate(purchaseRepository.amount());
    }
}
