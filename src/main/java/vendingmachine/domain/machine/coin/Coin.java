package vendingmachine.domain.machine.coin;

import java.util.Arrays;
import java.util.List;
import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.ExceptionStatus;
import vendingmachine.exception.amount.AmountExceptionStatus;
import vendingmachine.exception.coin.CoinExceptionStatus;

public enum Coin {

    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public static Coin of(final int amount) {
        return Arrays.stream(Coin.values())
                .filter(coin -> coin.isEquals(amount))
                .findAny()
                .orElseThrow(() -> new CustomIllegalArgumentException(CoinExceptionStatus.COIN_IS_NOT_FOUND));
    }

    private boolean isEquals(final int amount) {
        return this.amount == amount;
    }

    public static List<Coin> getCoins(final int amount) {
        return Arrays.stream(Coin.values())
                .filter(coin -> coin.isBiggerThan(amount))
                .toList();
    }

    private boolean isBiggerThan(final int amount) {
        return this.amount <= amount;
    }

    public int getAmount() {
        return this.amount;
    }
}
