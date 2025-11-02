package lotto.domain.answer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;
import lotto.common.TestFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningConditionTest {

    @DisplayName("입력이 `null`일 때, InvalidWinningConditionException 예외 발생 테스트")
    @Test
    void null_throws_InvalidPurchaseException_test() {
        Exception exception = assertThrows(InvalidWinningConditionException.class,
                () -> WinningCondition.from(null));

        assertThat(exception)
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(InvalidWinningConditionException.class)
                .isNotInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest(name = "[{index}] 입력: {1}")
    @MethodSource("provideOnlyBlanksInput")
    @DisplayName("입력이 비어있을 때(BLANK) 예외 메지시 테스트")
    void blank_input_exception_test(String input, String description) {
        assertThatThrownBy(() -> WinningCondition.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(InvalidWinningConditionException.class)
                .hasMessage(ErrorType.BLANK.description());
    }

    @ParameterizedTest(name = "[{index}] 입력: {1}")
    @MethodSource("provideContainedNonDigitsInput")
    @DisplayName("숫자와 구분자를 제외한 문자가 포함된 입력 시 예외 메지시 테스트")
    void not_numeric_or_delimiter_input_exception_test(String input, String description) {
        assertThatThrownBy(() -> WinningCondition.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(InvalidWinningConditionException.class)
                .hasMessage(ErrorType.NOT_DIGITS_OR_DELIMITER.description());
    }

    @ParameterizedTest(name = "[{index}] 입력: {0}")
    @MethodSource("provideCorrectInputs")
    @DisplayName("당첨 번호와 중복되는 보너스 문자를 입력했을 때 예외 테스트")
    void duplicate_bonus_number_input_exception_test(String input) {
        WinningCondition winningCondition = WinningCondition.from(input);

        String first = Arrays.stream(input.split(",")).findFirst().orElse("1");

        assertThatThrownBy(() -> winningCondition.withBonus(first))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(InvalidWinningConditionException.class)
                .hasMessage(ErrorType.DUPLICATES_EXIST.description());
    }

    @ParameterizedTest(name = "[{index}] 입력: {0}")
    @MethodSource("provideCorrectInputs")
    @DisplayName("정상적인 입력이라면, 예외 미발생 테스트")
    void correct_input_no_exception_test(String input) {
        assertThatNoException().isThrownBy(() -> WinningCondition.from(input));
    }

    private static Stream<Arguments> provideOnlyBlanksInput() {
        return TestFixtures.provideOnlyBlanksInput();
    }

    private static Stream<Arguments> provideContainedNonDigitsInput() {
        return TestFixtures.provideContainedNonDigitsInput();
    }

    // 1로 시작
    private static Stream<String> provideCorrectInputs() {
        return Stream.of(
                "1,2,3,4,5,6",
                "1,12,13,14,15,16"
        );
    }
}
