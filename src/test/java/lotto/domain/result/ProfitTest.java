package lotto.domain.result;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.shared.constant.LottoRank;
import lotto.domain.shared.vo.MatchedScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ProfitTest {

    @DisplayName("구매 금액이 0일 때 예외 발생 테스트")
    @Test
    void profit_exception_test() {
        int zeroPurchaseAmount = 0;
        Profit profit = Profit.from(List.of(new MatchedScore(LottoRank.MISS, 1)));

        assertThrows(IllegalArgumentException.class, () -> profit.rateOf(zeroPurchaseAmount));
    }

    @DisplayName("구매 금액에 대한 수익률 계산 테스트")
    @ParameterizedTest(name = "[{index}] 예상 수익률 : {2}")
    @MethodSource("provideProfitRates")
    void profit_calculate_test(List<MatchedScore> matchedScores, int purchaseAmount, double expectedProfitRate) {
        Profit profit = Profit.from(matchedScores);

        double actualProfit = profit.rateOf(purchaseAmount);

        assertEquals(expectedProfitRate, actualProfit);
    }

    private static Stream<Arguments> provideProfitRates() {
        return Stream.of(
                Arguments.of(List.of(new MatchedScore(LottoRank.MISS, 8)), 8000, 0.0),
                Arguments.of(List.of(new MatchedScore(LottoRank.FIFTH, 1)), 1000, 500.0),
                Arguments.of(List.of(new MatchedScore(LottoRank.FIFTH, 1)), 5000, 100.0)
        );
    }
}
