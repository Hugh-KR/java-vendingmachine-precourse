package vendingmachine.domain.machine.coin;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import vendingmachine.domain.user.Balance;
import vendingmachine.domain.user.User;
import vendingmachine.dto.CoinStorageDto;

public class CoinStorage {

    private final Map<Coin, Integer> coins = new LinkedHashMap<>();

    public CoinStorage() {
        initStorage();
    }

    private void initStorage() {
        for (Coin coin : Coin.values()) {
            coins.put(coin, 0);
        }
    }

    public void save(final Coin coin) {
        coins.put(coin, coins.getOrDefault(coin, 0) + 1);
    }

    public List<CoinStorageDto> getCoinsStatus() {
        final List<CoinStorageDto> getCoinsStatus = new ArrayList<>();
        for (Map.Entry<Coin, Integer> entry : coins.entrySet()) {
            final Coin coin = entry.getKey();
            final int count = entry.getValue();
            getCoinsStatus.add(toDto(coin, count));
        }
        return getCoinsStatus;
    }

    public List<CoinStorageDto> getChangedCoinsStatus() {
        final List<CoinStorageDto> changedCoinsStatus = new ArrayList<>();
        for (Map.Entry<Coin, Integer> entry : coins.entrySet()) {
            final Coin coin = entry.getKey();
            if (isEmptyCoin(coin)) {
                continue;
            }
            final int count = entry.getValue();
            changedCoinsStatus.add(toDto(coin, count));
        }
        return changedCoinsStatus;
    }

    public void refund(final User user) {

        for (Coin coin : coins.keySet()) {
            if (coin.isBiggerThan(user.getCurrentAmount())) {
                continue;
            }
            if (isEmptyCoin(coin)) {
                continue;
            }
            decreaseCoinCount(user, coin);
        }
    }

    private void decreaseCoinCount(final User user, final Coin coin) {
        while (user.hasEnoughThan(coin.getAmount())) {
            if (isEmptyCoin(coin)) {
                break;
            }
            user.saveRefundCoin(coin);
            int count = coins.get(coin);
            coins.put(coin, count - 1);
            user.withdrawAmount(coin.getAmount());
        }
    }

    private CoinStorageDto toDto(final Coin coin, final int count) {
        return new CoinStorageDto(coin.getAmount(), count);
    }

    private boolean isEmptyCoin(final Coin coin) {
        return coins.get(coin) <= 0;
    }
}
