package lotto.controller;

import lotto.application.service.LottoPurchaseService;
import lotto.io.input.LottoGameInputPort;
import lotto.io.input.dto.PurchaseLottoRequest;

public class LottoController {

    private final LottoGameInputPort inputPort;

    private final LottoPurchaseService purchaseService;

    public LottoController(LottoGameInputPort inputPort, LottoPurchaseService purchaseService) {
        this.inputPort = inputPort;
        this.purchaseService = purchaseService;
    }

    public void run() {
        PurchaseLottoRequest purchaseLottoRequest = inputPort.purchaseMoneyInput();

    }

    public void purchaseLotto() {
        PurchaseLottoRequest purchaseLottoRequest = inputPort.purchaseMoneyInput();
        purchaseService.purchaseLottoTicket(purchaseLottoRequest.rawMoneyInput());
    }
}
