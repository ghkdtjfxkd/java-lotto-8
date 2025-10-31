package lotto.domain.lottoGame;

import java.util.List;

@FunctionalInterface
public interface PickLottoNumbersStrategy {

    List<Integer> picks();
}
