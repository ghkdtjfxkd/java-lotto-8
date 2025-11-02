package lotto.domain.answer;

import lotto.domain.shared.vo.LottoAnswer;

public interface LottoAnswerRepository {

    void save(WinningCondition winningCondition);

    void update(WinningCondition winningCondition);

    WinningCondition getWinningCondition();

    LottoAnswer getLottoAnswer();
}
