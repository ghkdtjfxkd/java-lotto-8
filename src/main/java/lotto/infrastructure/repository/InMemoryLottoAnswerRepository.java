package lotto.infrastructure.repository;

import lotto.domain.answer.WinningCondition;
import lotto.domain.answer.LottoAnswerRepository;
import lotto.domain.shared.vo.LottoAnswer;

public class InMemoryLottoAnswerRepository implements LottoAnswerRepository {

    private WinningCondition winningCondition;

    @Override
    public void save(WinningCondition winningCondition) {
        this.winningCondition = winningCondition;
    }

    @Override
    public void update(WinningCondition winningCondition) {
        this.winningCondition = winningCondition;
    }

    @Override
    public WinningCondition getWinningCondition() {
        return winningCondition;
    }

    @Override
    public LottoAnswer getLottoAnswer() {
        return winningCondition.createAnswer();
    }
}
