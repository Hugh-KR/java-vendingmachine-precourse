package vendingmachine.domain.machine;

import java.util.List;
import vendingmachine.domain.machine.coin.Coin;
import vendingmachine.domain.machine.coin.CoinStorage;
import vendingmachine.domain.machine.product.Product;
import vendingmachine.domain.machine.product.ProductStorage;
import vendingmachine.dto.CoinStorageDto;

public class Machine {

    private CoinStorage coinStorage = new CoinStorage();
    private ProductStorage productStorage = new ProductStorage();

    public Machine(final List<Coin> coins) {
        saveCoin(coins);
    }

    private void saveCoin(final List<Coin> coins) {
        coins.forEach(coinStorage::save);
    }

    public void saveProducts(final List<Product> products) {
        productStorage.save(products);
    }

    public List<CoinStorageDto> getCoinsOfMachine() {
        return coinStorage.getCoinsStatus();
    }


}
