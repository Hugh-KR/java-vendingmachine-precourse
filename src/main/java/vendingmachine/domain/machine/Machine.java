package vendingmachine.domain.machine;

import java.util.List;
import vendingmachine.domain.machine.coin.Coin;
import vendingmachine.domain.machine.coin.CoinStorage;
import vendingmachine.dto.CoinStorageDto;

public class Machine {

    private CoinStorage coinStorage = new CoinStorage();

    public Machine(final List<Coin> coins) {
        saveCoin(coins);
    }

    private void saveCoin(final List<Coin> coins) {
        coins.forEach(coinStorage::save);
    }

    public List<CoinStorageDto> getCoinsOfMachine() {
        return coinStorage.getCoinsStatus();
    }
}
