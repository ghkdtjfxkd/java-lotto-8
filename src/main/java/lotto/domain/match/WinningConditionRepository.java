package lotto.domain.match;

public interface WinningConditionRepository {

    void save(WinningCondition winningCondition);

    void update(WinningCondition winningCondition);

    WinningCondition getWinningCondition();
}
