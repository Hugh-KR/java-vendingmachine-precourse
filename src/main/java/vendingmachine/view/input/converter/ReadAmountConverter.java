package vendingmachine.view.input.converter;

import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.amount.AmountExceptionStatus;

public class ReadAmountConverter {

    static final ReadAmountConverter READ_AMOUNT_CONVERTER = new ReadAmountConverter();

    private ReadAmountConverter() {
    }

    public static int convertAmount(final String amount) {
        return READ_AMOUNT_CONVERTER.parseAmount(amount);
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
