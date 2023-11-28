package vendingmachine.domain.machine.product;

public class Product {

    private final String name;
    private final int price;
    private final int quantity;

    public Product(final String name, final int price, final int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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
