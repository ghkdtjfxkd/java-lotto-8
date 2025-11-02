package lotto.application.service;

import java.util.List;
import lotto.application.dto.WinningStatisticDto;
import lotto.domain.lottoGame.LottoTicketRepository;
import lotto.domain.answer.LottoAnswerRepository;
import lotto.domain.result.LottoResult;
import lotto.domain.shared.vo.Lotto;
import lotto.domain.shared.vo.LottoAnswer;

public class LottoResultServiceImpl implements LottoResultService {

    private final LottoTicketRepository lottoTicketRepository;
    private final LottoAnswerRepository lottoAnswerRepository;

    public LottoResultServiceImpl(LottoTicketRepository lottoTicketRepository,
                                  LottoAnswerRepository lottoAnswerRepository) {
        this.lottoTicketRepository = lottoTicketRepository;
        this.lottoAnswerRepository = lottoAnswerRepository;
    }

    public List<WinningStatisticDto> matchedResults() {
        List<Lotto> lottoGames = lottoTicketRepository.allLottoGames();
        LottoAnswer lottoAnswer = lottoAnswerRepository.getLottoAnswer();

        LottoResult result = LottoResult.from(lottoGames, lottoAnswer);
        return result.scores().stream()
                .map(score -> WinningStatisticDto.from(score.rank(), score.winningCount()))
                .toList();
    }

    private void calculatePrizes() {

    }
}
