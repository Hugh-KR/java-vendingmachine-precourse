package vendingmachine.domain.machine.amount;

import vendingmachine.domain.machine.amount.validator.AmountValidator;

public class Amount {
    private final int amount;

    public Amount(final int amount) {
        AmountValidator.validateAmount(amount);
        this.amount = amount;
    }
}
