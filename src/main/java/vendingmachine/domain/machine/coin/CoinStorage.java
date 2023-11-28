package vendingmachine.domain.machine.coin;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import vendingmachine.dto.CoinStorageDto;

public class CoinStorage {

    private static final Map<Coin, Integer> coins = new LinkedHashMap<>();

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

    private CoinStorageDto toDto(final Coin coin, final int count) {
        return new CoinStorageDto(coin.getAmount(), count);
    }

    private boolean isEmptyCoin(final Coin coin) {
        return coins.get(coin) <= 0;
    }
}
