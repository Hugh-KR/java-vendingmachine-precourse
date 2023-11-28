package vendingmachine.view.output.message;

public enum OutputMessage {

    READ_AMOUNT_IN_POSSESSION_MESSAGE("자판기가 보유하고 있는 금액을 입력해 주세요."),
    READ_PRODUCT_MESSAGE("상품명과 가격, 수량을 입력해 주세요."),
    READ_INSERT_AMOUNT_MESSAGE("투입 금액을 입력해 주세요."),
    READ_PRODUCTS_TO_BUY_MESSAGE("구매할 상품명을 입력해 주세요."),
    READ_CHANGE_MESSAGE("잔돈"),

    PRINT_COIN_IN_POSSESSION_MESSAGE("자판기가 보유한 동전"),
    PRINT_FIVE_HUNDRED_WON_COIN("500원 - %d개"),
    PRINT_ONE_HUNDRED_WON_COIN("100원 - %d개"),
    PRINT_FIFTY_WON_COIN("50원 - %d개"),
    PRINT_TEN_WON_COIN("10원 - %d개");

    private final String message;

    OutputMessage(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
