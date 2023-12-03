package vendingmachine.exception.product;

import vendingmachine.exception.ExceptionStatus;

public enum ProductExceptionStatus implements ExceptionStatus {

    IS_WRONG_PRODUCT("잘못 입력 하셨습니다."),
    IS_DUPLICATED_PRODUCT("중복으로 입력 하셨습니다.");

    private static final String MESSAGE_PREFIX = "상품명을 ";
    private static final String MESSAGE_SUFFIX = "다시 입력해 주세요.";

    private final String message;

    ProductExceptionStatus(final String message) {
        this.message = MESSAGE_PREFIX + message + MESSAGE_SUFFIX;
    }

    public String getMessage() {
        return this.message;
    }
}
