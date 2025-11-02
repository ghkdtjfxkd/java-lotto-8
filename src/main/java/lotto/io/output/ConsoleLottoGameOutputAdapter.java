package lotto.io.output;

import java.util.List;
import lotto.common.BusinessException;
import lotto.io.output.dto.PurchasedLottoGamesResponse;
import lotto.io.output.dto.WinningStatisticsResponse;

public class ConsoleLottoGameOutputAdapter implements LottoGameOutputPort{

    private static final String TASK_DIVIDER = System.lineSeparator();

    @Override
    public void print(PurchasedLottoGamesResponse purchasedLotto) {
        System.out.println(purchasedLotto.quantity() + "개를 구매했습니다.");
        printGames(purchasedLotto);
    }

    @Override
    public void print(WinningStatisticsResponse response) {
        System.out.println(response.winningStatistics());
    }

    @Override
    public void printTaskDivider() {
        System.out.print(TASK_DIVIDER);
    }

    @Override
    public void printError(BusinessException exception) {
        System.out.println(exception.getMessage());
    }

    private void printGames(PurchasedLottoGamesResponse response) {
        response.lottoNumbers().stream()
                .map(this::sortedAscending)
                .forEach(System.out::println);
    }

    private List<Integer> sortedAscending(List<Integer> numbers) {
        return numbers.stream().sorted().toList();
    }
}
