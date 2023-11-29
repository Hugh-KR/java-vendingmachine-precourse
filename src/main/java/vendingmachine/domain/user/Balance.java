package vendingmachine.domain.user;

import java.util.List;
import vendingmachine.domain.machine.coin.Coin;
import vendingmachine.domain.machine.coin.CoinStorage;
import vendingmachine.dto.CoinStorageDto;

public class Balance {

    private CoinStorage coinStorage = new CoinStorage();
    private int amount;

    public Balance(final int amount) {
        this.amount = amount;
    }

    public void save(final Coin coin) {
        coinStorage.save(coin);
    }

    public void withdraw(final int amount) {
        this.amount -= amount;
    }

    public boolean isEnoughThan(final int amount) {
        return this.amount >= amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public List<CoinStorageDto> getRefundCoinStatuses() {
        return coinStorage.getChangedCoinsStatus();
    }
}
