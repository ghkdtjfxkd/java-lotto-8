package lotto.application.service;

import lotto.domain.lottoGame.LottoTicketRepository;
import lotto.domain.match.WinningConditionRepository;

public class LottoMatchServiceImpl implements LottoMatchService {

    private final LottoTicketRepository lottoTicketRepository;
    private final WinningConditionRepository winningConditionRepository;

    public LottoMatchServiceImpl(LottoTicketRepository lottoTicketRepository,
                                 WinningConditionRepository winningConditionRepository) {
        this.lottoTicketRepository = lottoTicketRepository;
        this.winningConditionRepository = winningConditionRepository;
    }

    @Override
    public void registerWinningNumbers(String inputWinningNumber) {

    }
}
