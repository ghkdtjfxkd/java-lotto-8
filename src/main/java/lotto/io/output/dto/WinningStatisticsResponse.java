package lotto.io.output.dto;

import java.util.List;
import lotto.application.dto.WinningStatisticDto;

public record WinningStatisticsResponse(List<WinningStatisticDto> winningStatistics) {
    public static WinningStatisticsResponse from(List<WinningStatisticDto> statistics) {
        return new WinningStatisticsResponse(statistics);
    }
}
