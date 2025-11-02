package lotto.io.output.dto;

import lotto.application.dto.ProfitRateDto;

public record ProfitRateResponse(double profitRate) {
    public static ProfitRateResponse of(ProfitRateDto profitRate) {
        return new ProfitRateResponse(profitRate.profitRate());
    }
}
