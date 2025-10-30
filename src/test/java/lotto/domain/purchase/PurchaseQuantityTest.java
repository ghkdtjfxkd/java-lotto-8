package lotto.domain.purchase;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PurchaseQuantityTest {

    private static final int LOTTO_PRICE = 1000;

    @DisplayName("1000으로 나누어 떨어지지 않는 금액 입력 시 예외 발생 테스트")
    @ParameterizedTest(name = "[{index}] 구매 금액: [{0}]")
    @MethodSource("provideNotDivisibleByLottoPriceMoneys")
    void wrong_purchase_money_exception_test(int money) {
        assertThrows(InvalidPurchaseException.class, () -> PurchaseQuantity.from(money));
    }

    @DisplayName("1000으로 나누어 떨어지지 않는 금액 입력 시 예외 메시지 테스트")
    @ParameterizedTest(name = "[{index}] 구매 금액: [{0}]")
    @MethodSource("provideNotDivisibleByLottoPriceMoneys")
    void wrong_purchase_money_exception_message_test(int money) {
        assertThatThrownBy(() -> PurchaseQuantity.from(money))
                .isInstanceOf(InvalidPurchaseException.class)
                .hasMessage(ErrorType.NOT_DIVISIBLE_BY_UNIT.description());
    }

    @DisplayName("구매 금액 변환 테스트")
    @ParameterizedTest(name = "[{index}] 구매 금액: [{0}]")
    @MethodSource("provideCorrectLottoPriceMoneys")
    void correct_purchase_money_test(int money) {
        PurchaseQuantity quantity = PurchaseQuantity.from(money);

        int expected = money / LOTTO_PRICE;
        assertEquals(expected, quantity.count());
    }

    private static Stream<Integer> provideNotDivisibleByLottoPriceMoneys() {
        return Stream.of(
                1,
                111,
                1001,
                111111111
        );
    }

    private static Stream<Integer> provideCorrectLottoPriceMoneys() {
        return Stream.of(
                1000,
                5000,
                4000,
                200000
        );
    }
}
