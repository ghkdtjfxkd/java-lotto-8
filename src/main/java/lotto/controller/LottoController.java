package lotto.controller;

import java.util.List;
import lotto.application.dto.ProfitRateDto;
import lotto.application.dto.PurchasedLottoDto;
import lotto.application.dto.WinningStatisticDto;
import lotto.application.service.answer.LottoAnswerCommandService;
import lotto.application.service.purchase.LottoPurchaseQueryService;
import lotto.application.service.purchase.LottoPurchaseCommandService;
import lotto.application.service.result.LottoResultQueryService;
import lotto.application.service.result.LottoResultCommandService;
import lotto.common.BusinessException;
import lotto.io.input.LottoGameInputPort;
import lotto.io.input.dto.BonusNumberRequest;
import lotto.io.input.dto.PurchaseLottoRequest;
import lotto.io.input.dto.WinningNumbersRequest;
import lotto.io.output.LottoGameOutputPort;
import lotto.io.output.dto.ProfitRateResponse;
import lotto.io.output.dto.PurchasedLottoGamesResponse;
import lotto.io.output.dto.WinningStatisticsResponse;

public class LottoController {

    private final LottoGameInputPort inputPort;
    private final LottoGameOutputPort outputPort;

    private final LottoPurchaseCommandService purchaseCommandService;
    private final LottoPurchaseQueryService purchaseQueryService;
    private final LottoAnswerCommandService answerCommandService;
    private final LottoResultCommandService resultCommandService;
    private final LottoResultQueryService resultQueryService;

    public LottoController(LottoGameInputPort inputPort,
                           LottoGameOutputPort outputPort,
                           LottoPurchaseCommandService purchaseCommandService,
                           LottoPurchaseQueryService purchaseQueryService,
                           LottoAnswerCommandService answerCommandService,
                           LottoResultCommandService resultCommandService,
                           LottoResultQueryService resultQueryService) {
        this.inputPort = inputPort;
        this.outputPort = outputPort;
        this.purchaseCommandService = purchaseCommandService;
        this.purchaseQueryService = purchaseQueryService;
        this.answerCommandService = answerCommandService;
        this.resultCommandService = resultCommandService;
        this.resultQueryService = resultQueryService;
    }

    public void run() {
        setupPhase();
        resultPhase();
    }

    private void setupPhase() {
        execute(this::purchaseLottoTicket);
        execute(this::displayPurchasedTickets);
        execute(this::registerWinningNumbers);
        execute(this::registerBonusNumber);
    }

    private void resultPhase() {
        registerLottoResult();
        displayWinningStatistics();
        displayProfitRate();
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

    private void purchaseLottoTicket() {
        PurchaseLottoRequest request = inputPort.purchaseMoneyInput();
        purchaseCommandService.purchaseLottoTicket(request.rawMoneyInput());
    }

    private void displayPurchasedTickets() {
        List<PurchasedLottoDto> lottoGames = purchaseQueryService.LottoGames();
        PurchasedLottoGamesResponse purchasedResponse = PurchasedLottoGamesResponse.from(lottoGames);

        outputPort.print(purchasedResponse);
    }

    private void registerWinningNumbers() {
        WinningNumbersRequest request = inputPort.winningNumbersInput();
        answerCommandService.registerWinningNumbers(request.rawWinningNumbersInput());
    }

    private void registerBonusNumber() {
        BonusNumberRequest request = inputPort.bonusNumberInput();
        answerCommandService.registerBonusNumber(request.rawBonusNumberInput());
    }

    private void registerLottoResult() {
        resultCommandService.registerLottoResult();
    }

    private void displayWinningStatistics() {
        List<WinningStatisticDto> winningStatistics = resultQueryService.matchedResults();
        WinningStatisticsResponse winningStatisticsResponse = WinningStatisticsResponse.from(winningStatistics);

        outputPort.print(winningStatisticsResponse);
    }

    private void displayProfitRate() {
        ProfitRateDto profitRateDto = resultQueryService.calculateProfitRate();
        ProfitRateResponse profitRateResponse = ProfitRateResponse.of(profitRateDto);

        outputPort.print(profitRateResponse);
    }
}
