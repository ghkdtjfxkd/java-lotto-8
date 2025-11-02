package lotto.domain.lottoGame;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lotto.domain.shared.vo.Lotto;

public class LottoTicket {

    private final Deque<Lotto> unclassified;

    private LottoTicket(Deque<Lotto> lottoGames) {
        this.unclassified = lottoGames;
    }

    public static LottoTicket from(int purchaseQuantity, PickLottoNumbersStrategy strategy) {
        Deque<Lotto> lottoGames = Stream.generate(() ->Lotto.picks(strategy))
                .limit(purchaseQuantity)
                .collect(Collectors.toCollection(ArrayDeque::new));

        return new LottoTicket(lottoGames);
    }

    public List<Lotto> purchasedLottoGames() {
        return List.copyOf(unclassified);
    }
}
