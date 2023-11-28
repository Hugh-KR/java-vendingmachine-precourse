package vendingmachine.dto;

import vendingmachine.domain.machine.product.Product;

public class ProductDto {

    private final String name;
    private final int price;
    private final int quantity;

    public ProductDto(final String name, final int price, final int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Product toEntity() {
        return new Product(name, price, quantity);
    }
}
