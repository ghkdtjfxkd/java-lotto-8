package lotto.domain.lottoGame;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.shared.vo.Lotto;

public class LottoTicket {

    private final List<Lotto> unclassified;

    private LottoTicket(List<Lotto> lottoGames) {
        this.unclassified = lottoGames;
    }

    public static LottoTicket from(int purchaseQuantity, PickLottoNumbersStrategy strategy) {
        List<Lotto> lottoGames = Stream.generate(() -> Lotto.picks(strategy))
                .limit(purchaseQuantity)
                .toList();

        return new LottoTicket(lottoGames);
    }

    public List<Lotto> purchasedLottoGames() {
        return List.copyOf(unclassified);
    }
}
