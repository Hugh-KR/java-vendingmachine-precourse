package vendingmachine.exception.coin;

import vendingmachine.exception.ExceptionStatus;

public enum CoinExceptionStatus implements ExceptionStatus {

    COIN_IS_NOT_FOUND("찾을 수 없습니다.");

    private static final String MESSAGE_PREFIX = "일치하는 동전을 ";
    private static final String MESSAGE_SUFFIX = "다시 입력해 주세요.";

    private final String message;

    CoinExceptionStatus(final String message) {
        this.message = MESSAGE_PREFIX + message + MESSAGE_SUFFIX;
    }

    public String getMessage() {
        return this.message;
    }
}
