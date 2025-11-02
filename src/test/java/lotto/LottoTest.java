package lotto;

import java.util.stream.Stream;
import lotto.domain.shared.exception.ErrorType;
import lotto.domain.shared.exception.InvalidLottoNumbersException;
import lotto.domain.shared.vo.Lotto;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoTest {

    /**
     *  {@link Lotto} 검증 순서 <br>
     *
     * 숫자 갯수 검사(6개) -> 중복 검사 -> 숫자들 로또 범위 검사
     */

    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("올바르지 않은 로또 번호 개수일 때, 예외 메시지 테스트")
    @ParameterizedTest(name = "[{index}] 로또 번호: [{0}]")
    @MethodSource("provideWrongLottoNumbersCountLists")
    void wrong_lotto_number_count_exception_message_test(List<Integer> numbers) {
        AssertionsForClassTypes.assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(InvalidLottoNumbersException.class)
                .hasMessage(ErrorType.INVALID_NUMBER_COUNT.description());
    }

    @DisplayName("로또 번호에 중복된 숫자 존재 시, 예외 메시지 테스트")
    @ParameterizedTest(name = "[{index}] 로또 번호: {0}")
    @MethodSource("provideDuplicateNumbersList")
    void duplicate_number_exist_exception_message_test(List<Integer> numbers) {
        AssertionsForClassTypes.assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(InvalidLottoNumbersException.class)
                .hasMessage(ErrorType.DUPLICATES_EXIST.description());
    }

    @DisplayName("로또 번호 범위(1~45)를 넘어가는 숫자 존재 시, 예외 메시지 테스트")
    @ParameterizedTest(name = "[{index}] 로또 번호: {0}")
    @MethodSource("provideOutOfLottoNumbersList")
    void out_of_lotto_number_range_exception_message_test(List<Integer> numbers) {
        AssertionsForClassTypes.assertThatThrownBy(() -> new Lotto(numbers))
                .isInstanceOf(IllegalArgumentException.class)
                .isInstanceOf(InvalidLottoNumbersException.class)
                .hasMessage(ErrorType.OUT_OF_LOTTO_NUMBER_RANGE.description());
    }

    private static Stream<List<Integer>> provideWrongLottoNumbersCountLists() {
        return Stream.of(
                List.of(1, 2, 3),
                List.of(1, 2, 3, 4, 5, 6, 7)
        );
    }

    private static Stream<List<Integer>> provideDuplicateNumbersList() {
        return Stream.of(
                List.of(1, 1, 1, 1, 1, 1),
                List.of(1, 1, 2, 3, 4, 5)
        );
    }

    private static Stream<List<Integer>> provideOutOfLottoNumbersList() {
        return Stream.of(
                List.of(1, 2, 3, 4, 5, 46),
                List.of(0, 1, 2, 3, 4, 5)
        );
    }
}
