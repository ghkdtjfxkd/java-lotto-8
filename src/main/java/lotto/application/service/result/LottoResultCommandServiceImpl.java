package lotto.application.service.result;

import java.util.List;
import lotto.domain.lottoGame.LottoTicketRepository;
import lotto.domain.answer.LottoAnswerRepository;
import lotto.domain.result.LottoResult;
import lotto.domain.result.LottoResultRepository;
import lotto.domain.shared.vo.Lotto;
import lotto.domain.shared.vo.LottoAnswer;

public class LottoResultCommandServiceImpl implements LottoResultCommandService {

    private final LottoTicketRepository lottoTicketRepository;
    private final LottoAnswerRepository lottoAnswerRepository;
    private final LottoResultRepository lottoResultRepository;

    public LottoResultCommandServiceImpl(LottoTicketRepository lottoTicketRepository,
                                         LottoAnswerRepository lottoAnswerRepository,
                                         LottoResultRepository lottoResultRepository) {
        this.lottoTicketRepository = lottoTicketRepository;
        this.lottoAnswerRepository = lottoAnswerRepository;
        this.lottoResultRepository = lottoResultRepository;
    }

    @Override
    public void registerLottoResult() {
        lottoResultRepository.save(lottoResult());
    }

    private LottoResult lottoResult() {
        List<Lotto> lottoGames = lottoTicketRepository.allLottoGames();
        LottoAnswer lottoAnswer = lottoAnswerRepository.getLottoAnswer();

        return LottoResult.from(lottoGames, lottoAnswer);
    }
}
