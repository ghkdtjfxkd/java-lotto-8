package lotto.domain.answer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.stream.Stream;
import lotto.common.TestFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoNumberTokenTest {

    @ParameterizedTest(name = "[{index}] 숫자 입력: {1}")
    @MethodSource("provideOnlyBlanksInputs")
    @DisplayName("입력이 비어있을 때(BLANK) 예외 메지시 출력 테스트")
    void blank_input_exception_test(String numberInput, String description) {
        assertThatThrownBy(() -> LottoNumberToken.from(numberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(InvalidWinningConditionException.class)
                .hasMessage(ErrorType.WHITE_SPACES_EXIST.description());
    }

    @ParameterizedTest(name = "[{index}] 숫자 입력: {1}")
    @MethodSource("provideContainedNonDigitsInput")
    @DisplayName("숫자를 제외한 문자가 포함된 입력 시 예외 메지시 출력 테스트")
    void not_numeric_or_delimiter_input_exception_test(String numberInput, String description) {
        assertThatThrownBy(() -> LottoNumberToken.from(numberInput))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(InvalidWinningConditionException.class)
                .hasMessage(ErrorType.NOT_DIGITS.description());
    }

    @DisplayName("로또 범위를 벗어나는 숫자 입력 시, 예외 메시지 테스트")
    @ParameterizedTest(name = "[{index}] 숫자 입력: [{0}]")
    @MethodSource("provideOutOfLottoRangeInputs")
    void non_positive_money_exception_message_test(String input) {
        assertThatThrownBy(() -> LottoNumberToken.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(InvalidWinningConditionException.class)
                .hasMessage(ErrorType.OUT_OF_LOTTO_NUMBER_RANGE.description());
    }

    private static Stream<Arguments> provideOnlyBlanksInputs() {
        return TestFixtures.provideOnlyBlanksInput();
    }

    private static Stream<Arguments> provideContainedNonDigitsInput() {
        return TestFixtures.provideContainedNonDigitsInput();
    }

    private static Stream<String> provideOutOfLottoRangeInputs() {
        return TestFixtures.provideOutOfLottoRangeInputs();
    }
}
