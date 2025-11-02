package lotto.domain.lottoGame;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import lotto.infrastructure.strategy.QuickPicksStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LottoTicketTest {

    @DisplayName("구매 갯수 만큼 랜덤 로또 생성 테스트")
    @ParameterizedTest(name = "[{index}] 구매 갯수: [{0}]")
    @MethodSource("provideCorrectPurchaseCounts")
    void purchase_lotto_count_test(int purchaseCount) {
        PickLottoNumbersStrategy pickStrategy = new QuickPicksStrategy();

        LottoTicket purchased = LottoTicket.from(purchaseCount, pickStrategy);

        assertEquals(purchaseCount, purchased.purchasedLottoGames().size());
    }

    @DisplayName("동일한 숫자들로 이루어진 로또 허용 테스트")
    @ParameterizedTest(name = "[{index}] 동일한 로또 갯수: [{0}]")
    @MethodSource("provideCorrectPurchaseCounts")
    void allow_same_numbers_lotto_games_test(int purchaseCount) {
        PickLottoNumbersStrategy pickStrategy = new AlwaysSameNumbersStrategy();

        LottoTicket purchased = LottoTicket.from(purchaseCount, pickStrategy);

        assertEquals(purchaseCount, purchased.purchasedLottoGames().size());
    }

    private static Stream<Integer> provideCorrectPurchaseCounts() {
        return Stream.of(
                1,
                2,
                100
        );
    }
}
