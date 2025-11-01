package lotto.application.service;

import lotto.domain.match.WinningCondition;
import lotto.domain.match.WinningConditionRepository;

public class LottoMatchServiceImpl implements LottoMatchService {

    private final WinningConditionRepository winningConditionRepository;

    public LottoMatchServiceImpl(WinningConditionRepository winningConditionRepository) {
        this.winningConditionRepository = winningConditionRepository;
    }

    @Override
    public void registerWinningNumbers(String winningNumbersInput) {
        winningConditionRepository.save(WinningCondition.from(winningNumbersInput));
    }

    @Override
    public void registerBonusNumber(String bonusNumberInput) {
        WinningCondition winningCondition = winningConditionRepository.getWinningCondition();
        winningConditionRepository.update(winningCondition.withBonus(bonusNumberInput));
    }
}
