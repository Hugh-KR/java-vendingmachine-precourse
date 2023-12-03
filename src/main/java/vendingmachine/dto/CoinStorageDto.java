package vendingmachine.dto;

public class CoinStorageDto {

    private final int coin;
    private final int count;


    public CoinStorageDto(final int coin, final int count) {
        this.coin = coin;
        this.count = count;
    }

    public int getCoin() {
        return coin;
    }

    public int getCount() {
        return count;
    }
}
