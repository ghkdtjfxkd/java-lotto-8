package lotto.controller;

import java.util.List;
import lotto.application.dto.PurchasedLottoDto;
import lotto.application.dto.WinningStatisticDto;
import lotto.application.service.LottoAnswerService;
import lotto.application.service.LottoPurchaseService;
import lotto.application.service.LottoResultService;
import lotto.application.service.LottoResultServiceImpl;
import lotto.common.BusinessException;
import lotto.io.input.LottoGameInputPort;
import lotto.io.input.dto.BonusNumberRequest;
import lotto.io.input.dto.PurchaseLottoRequest;
import lotto.io.input.dto.WinningNumbersRequest;
import lotto.io.output.LottoGameOutputPort;
import lotto.io.output.dto.PurchasedLottoGamesResponse;
import lotto.io.output.dto.WinningStatisticsResponse;

public class LottoController {

    private final LottoGameInputPort inputPort;
    private final LottoGameOutputPort outputPort;

    private final LottoPurchaseService purchaseService;
    private final LottoAnswerService answerService;
    private final LottoResultService resultService;

    public LottoController(LottoGameInputPort inputPort,
                           LottoGameOutputPort outputPort,
                           LottoPurchaseService purchaseService,
                           LottoAnswerService answerService,
                           LottoResultService resultService) {
        this.inputPort = inputPort;
        this.outputPort = outputPort;
        this.purchaseService = purchaseService;
        this.answerService = answerService;
        this.resultService = resultService;
    }

    public void run() {
        execute(this::purchaseLotto);
        execute(this::checkingLottoGames);
        execute(this::registerWinningNumbers);
        execute(this::registerBonusNumber);
        execute(this::drawLottoGames);
    }

    private void execute(ExecutableTask task) {
        while (true) {
            try {
                task.execute();
                outputPort.printTaskDivider();
                return;
            } catch (BusinessException e) {
                outputPort.printTaskDivider();
                outputPort.printError(e);
            }
        }
    }

    private void purchaseLotto() {
        PurchaseLottoRequest request = inputPort.purchaseMoneyInput();
        purchaseService.purchaseLottoTicket(request.rawMoneyInput());
    }

    private void checkingLottoGames() {
        List<PurchasedLottoDto> lottoGames = purchaseService.LottoGames();
        PurchasedLottoGamesResponse purchasedResponse = PurchasedLottoGamesResponse.from(lottoGames);

        outputPort.print(purchasedResponse);
    }

    private void registerWinningNumbers() {
        WinningNumbersRequest request = inputPort.winningNumbersInput();
        answerService.registerWinningNumbers(request.rawWinningNumbersInput());
    }

    private void registerBonusNumber() {
        BonusNumberRequest request = inputPort.bonusNumberInput();
        answerService.registerBonusNumber(request.rawBonusNumberInput());
    }

    private void drawLottoGames() {
        List<WinningStatisticDto> winningStatistics = resultService.matchedResults();
        WinningStatisticsResponse winningStatisticsResponse = WinningStatisticsResponse.from(winningStatistics);

        outputPort.print(winningStatisticsResponse);
    }
}
