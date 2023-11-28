package vendingmachine.domain.machine.amount.validator;

import vendingmachine.constant.Constant;
import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.amount.AmountExceptionStatus;

public class AmountValidator {

    private static final AmountValidator AMOUNT_VALIDATOR = new AmountValidator();

    private AmountValidator() {
    }

    public static void validateAmount(final int amount) {
        AMOUNT_VALIDATOR.validateAmountIsPositive(amount);
    }

    private void validateAmountIsPositive(final int amount) {
        if (!isPositive(amount)) {
            throw new CustomIllegalArgumentException(AmountExceptionStatus.AMOUNT_IS_NOT_POSITIVE);
        }
    }

    private boolean isPositive(final int amount) {
        return Constant.ZERO.getConstant() < amount;
    }

}
