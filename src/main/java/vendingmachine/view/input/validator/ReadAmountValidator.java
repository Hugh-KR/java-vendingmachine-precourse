package vendingmachine.view.input.validator;

import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.amount.AmountExceptionStatus;

public class ReadAmountValidator {

    static final ReadAmountValidator READ_AMOUNT_VALIDATOR = new ReadAmountValidator();

    private ReadAmountValidator() {
    }

    public static int validateAmount(final String amount) {
        return READ_AMOUNT_VALIDATOR.parseAmount(amount);
    }

    private int parseAmount(final String amount) {
        try {
            return Integer.parseInt(isNullAmount(amount));
        } catch (NumberFormatException e) {
            throw new CustomIllegalArgumentException(AmountExceptionStatus.READ_IS_NOT_NUMERIC);
        }
    }

    private String isNullAmount(final String amount) {
        try {
            return amount.trim();
        } catch (NullPointerException e) {
            throw new CustomIllegalArgumentException(AmountExceptionStatus.READ_IS_NULL);
        }
    }
}
