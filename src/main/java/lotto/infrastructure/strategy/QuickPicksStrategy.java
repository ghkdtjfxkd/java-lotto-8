package lotto.infrastructure.strategy;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.domain.shared.constant.LottoRules;
import lotto.domain.lottoGame.PickLottoNumbersStrategy;

public class QuickPicksStrategy implements PickLottoNumbersStrategy {

    @Override
    public List<Integer> picks() {
        return Randoms.pickUniqueNumbersInRange(
                LottoRules.MIN_NUMBER.value(),
                LottoRules.MAX_NUMBER.value(),
                LottoRules.LOTTO_BALL_COUNT.value());
    }
}
