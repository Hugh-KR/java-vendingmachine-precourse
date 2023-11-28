package vendingmachine.exception.amount;

import vendingmachine.exception.ExceptionStatus;

public enum AmountExceptionStatus implements ExceptionStatus {

    READ_IS_NULL("NULL 은 입력할 수 없습니다."),
    READ_IS_NOT_NUMERIC("숫자만 입력할 수 있습니다."),

    AMOUNT_IS_NOT_POSITIVE("양의 정수만 입력할 수 있습니다.");

    private static final String MESSAGE_PREFIX = "자판기 보유 금액은 ";
    private static final String MESSAGE_SUFFIX = "다시 입력해 주세요.";

    private final String message;

    AmountExceptionStatus(final String message) {
        this.message = MESSAGE_PREFIX + message + MESSAGE_SUFFIX;
    }

    public String getMessage() {
        return this.message;
    }
}
