package lotto.domain.lottoGame;

import java.util.List;

class AlwaysSameNumbersStrategy implements PickLottoNumbersStrategy{

    @Override
    public List<Integer> picks() {
        return List.of(1,2,3,4,5,6);
    }
}
