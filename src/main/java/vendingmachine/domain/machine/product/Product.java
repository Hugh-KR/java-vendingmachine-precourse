package vendingmachine.domain.machine.product;

import vendingmachine.domain.user.Balance;

public class Product {

    private final String name;
    private final int price;
    private int quantity;

    public Product(final String name, final int price, final int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void sell(final Balance balance) {
        checkAmountIsEnough(balance.getAmount());
        decreaseQuantity();
        balance.withdraw(this.price);
    }

    private void checkAmountIsEnough(final int amount) {
        if (isNotEnoughAmount(amount)) {
            throw new IllegalArgumentException("구매 못함");
        }
    }

    public boolean isEnoughAmount(final int amount) {
        return this.price <= amount;
    }

    public boolean isNotEnoughAmount(final int amount) {
        return this.price > amount;
    }

    public boolean isSoldOut() {
        return this.quantity == 0;
    }

    public boolean isNotSoldOut() {
        return this.quantity > 0;
    }

    private void decreaseQuantity() {
        this.quantity--;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }
}
