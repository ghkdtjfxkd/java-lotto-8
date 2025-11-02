package lotto.domain.result;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.lottoGame.PickLottoNumbersStrategy;
import lotto.domain.shared.constant.LottoRank;
import lotto.domain.shared.vo.Lotto;
import lotto.domain.shared.vo.LottoAnswer;
import lotto.infrastructure.strategy.QuickPicksStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ScoreBoardTest {

    private static final PickLottoNumbersStrategy RANDOM_PICKS = new QuickPicksStrategy();
    private static final int DEFAULT_VALUE = 0;

    @DisplayName("구매한 로또 갯수와 상관 없이, 당첨 순위는 고정되어 있어야 있어야 한다.")
    @ParameterizedTest(name = "[{index}] 로또 갯수 : [{1}] ")
    @MethodSource("provideRandomLottos")
    void winning_rank_test(List<Lotto> lottos, String description) {
        ScoreBoard scoreBoard = ScoreBoard.from(
                lottos,
                LottoAnswer.of(new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7)
        );

        assertEquals(LottoRank.getWinningRanks().size(), scoreBoard.matchedScores().size());
    }

    @DisplayName("구매한 로또가 당첨 순위에 해당한다면, 해당 당첨 갯수가 하나 증가해야한다.")
    @ParameterizedTest(name = "[{index}] 당첨 로또 : [{2}] ")
    @MethodSource("provideWinningRankLottos")
    void update_winning_rank_test(Lotto lotto, LottoAnswer answer, String description) {
        ScoreBoard scoreBoard = ScoreBoard.from(List.of(lotto), answer);

        LottoRank rank = LottoRank.from(answer.matchedCount(lotto), answer.bonusNumberMatches(lotto));

        int expected = DEFAULT_VALUE + 1;
        int actual = scoreBoard.matchedScores().get(rank);

        assertEquals(expected, actual);
    }

    private static Stream<Arguments> provideWinningRankLottos() {
        Lotto pickedLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        int pickedBonus = 7;

        LottoAnswer lottoAnswer = LottoAnswer.of(pickedLotto, pickedBonus);

        return Stream.of(
                Arguments.of(pickedLotto, lottoAnswer, "6개 일치"),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 7)), lottoAnswer, "5개 일치, 보너스 일치"),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 5, 8)), lottoAnswer, "5개 일치"),
                Arguments.of(new Lotto(List.of(1, 2, 3, 4, 9, 10)), lottoAnswer, "4개 일치"),
                Arguments.of(new Lotto(List.of(1, 2, 3, 9, 10, 11)), lottoAnswer, "3개 일치")
        );
    }

    private static Stream<Arguments> provideRandomLottos() {
        return Stream.of(
                Arguments.of(randomLottos(0), "0"),
                Arguments.of(randomLottos(1), "1"),
                Arguments.of(randomLottos(3), "3"),
                Arguments.of(randomLottos(10), "10")
        );
    }

    private static List<Lotto> randomLottos(int count) {
        return Stream.generate(() -> Lotto.picks(RANDOM_PICKS))
                .limit(count)
                .toList();
    }
}
