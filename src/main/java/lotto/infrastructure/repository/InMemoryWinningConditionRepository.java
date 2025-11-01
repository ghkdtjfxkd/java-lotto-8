package lotto.infrastructure.repository;

import lotto.domain.match.WinningCondition;
import lotto.domain.match.WinningConditionRepository;

public class InMemoryWinningConditionRepository implements WinningConditionRepository {

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
}
