package vendingmachine.domain.machine.coin;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class Filler {

    private static final List<Coin> coins = new ArrayList<>();

    public Filler(final int amount) {
        toCoin(amount);
    }

    private void toCoin(int amount) {
        while (isAmountExist(amount)) {
            Coin coin = pickCoinInList(amount);
            coins.add(coin);
            amount -= coin.getAmount();
        }
    }

    private boolean isAmountExist(final int amount) {
        return amount > 0;
    }

    private Coin pickCoinInList(final int amount) {
        final List<Coin> coinsForPick = Coin.getCoins(amount);
        final List<Integer> numbers = coinsForPick.stream()
                .map(Coin::getAmount).toList();

        final int number = Randoms.pickNumberInList(numbers);
        return Coin.of(number);
    }

    public List<Coin> getCoins() {
        return this.coins;
    }
}
