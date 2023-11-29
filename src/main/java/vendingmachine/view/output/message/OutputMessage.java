package vendingmachine.view.output.message;

public enum OutputMessage {

    READ_AMOUNT_IN_POSSESSION_MESSAGE("자판기가 보유하고 있는 금액을 입력해 주세요."),
    READ_PRODUCT_MESSAGE("상품명과 가격, 수량을 입력해 주세요."),
    READ_DEPOSIT_AMOUNT_OF_USER_MESSAGE("투입 금액을 입력해 주세요."),
    READ_PRODUCTS_TO_BUY_MESSAGE("구매할 상품명을 입력해 주세요."),
    READ_CHANGE_MESSAGE("잔돈"),

    PRINT_COIN_IN_POSSESSION_MESSAGE("자판기가 보유한 동전"),
    PRINT_COIN_MESSAGE("%d원 - %d개"),
    PRINT_CURRENT_AMOUNT_OF_USER_MESSAGE("투입 금액: %d원");

    private final String message;

    OutputMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
