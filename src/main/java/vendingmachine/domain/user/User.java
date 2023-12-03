package vendingmachine.domain.user;

import java.util.List;
import vendingmachine.domain.machine.Machine;
import vendingmachine.domain.machine.coin.Coin;
import vendingmachine.dto.CoinStorageDto;

public class User {

    private final Balance balance;

    public User(final Balance balance) {
        this.balance = balance;
    }

    public boolean hasNotEnoughAmount(final Machine machine) {
        return machine.isPossibleToUseWith(balance);
    }

    public void purchase(final String productName, final Machine machine) {
        machine.purchaseProduct(this.balance, productName);
    }

    public void withdrawAmount(final int amount) {
        balance.withdraw(amount);
    }

    public boolean hasEnoughThan(final int amount) {
        return balance.isEnoughThan(amount);
    }

    public void saveRefundCoin(final Coin coin) {
        balance.save(coin);
    }

    public int getCurrentAmount() {
        return this.balance.getAmount();
    }

    public List<CoinStorageDto> getRefundCoinStatuses() {
        return this.balance.getRefundCoinStatuses();
    }

}
