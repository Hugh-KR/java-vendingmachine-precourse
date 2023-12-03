package vendingmachine.domain.machine;

import java.util.List;
import vendingmachine.domain.machine.coin.Coin;
import vendingmachine.domain.machine.coin.CoinStorage;
import vendingmachine.domain.machine.product.Product;
import vendingmachine.domain.machine.product.ProductStorage;
import vendingmachine.domain.user.Balance;
import vendingmachine.domain.user.User;
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

    public void purchaseProduct(final Balance balance, final String productName) {
        Product product = productStorage.findOne(productName);
        product.sell(balance);
    }

    public void refund(final User user) {
        coinStorage.refund(user);
    }

    public boolean isPossibleToUseWith(final Balance balance) {
        return productStorage.isPossibleToUseWith(balance);
    }

    public boolean isAllProductSoldOut() {
        return productStorage.isAllSoldOut();
    }

    public List<CoinStorageDto> getCoinsOfMachine() {
        return coinStorage.getCoinsStatus();
    }

    public List<CoinStorageDto> getChangesOfMachine() {
        return coinStorage.getChangedCoinsStatus();
    }
}
