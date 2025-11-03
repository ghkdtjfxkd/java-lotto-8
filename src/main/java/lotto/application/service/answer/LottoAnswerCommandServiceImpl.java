package lotto.application.service.answer;

import lotto.domain.answer.WinningCondition;
import lotto.domain.answer.LottoAnswerRepository;

public class LottoAnswerCommandServiceImpl implements LottoAnswerCommandService {

    private final LottoAnswerRepository lottoAnswerRepository;

    public LottoAnswerCommandServiceImpl(LottoAnswerRepository lottoAnswerRepository) {
        this.lottoAnswerRepository = lottoAnswerRepository;
    }

    @Override
    public void registerWinningNumbers(String winningNumbersInput) {
        lottoAnswerRepository.save(WinningCondition.from(winningNumbersInput));
    }

    @Override
    public void registerBonusNumber(String bonusNumberInput) {
        WinningCondition winningCondition = lottoAnswerRepository.getWinningCondition();
        lottoAnswerRepository.update(winningCondition.withBonus(bonusNumberInput));
    }
}
