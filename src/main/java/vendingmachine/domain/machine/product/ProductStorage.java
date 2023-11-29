package vendingmachine.domain.machine.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import vendingmachine.domain.user.Balance;

public class ProductStorage {
    private static final Map<String, Product> productStorage = new HashMap<>();

    public void save(final List<Product> products) {
        for (Product product : products) {
            String productName = product.getName();
            productStorage.put(productName, product);
        }
    }

    public Product findOne(final String productName) {
        return Optional.ofNullable(productStorage.get(productName))
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isPossibleToUseWith(final Balance balance) {
        return productStorage.values().stream()
                .filter(Product::isNotSoldOut)
                .anyMatch(product -> product.isEnoughAmount(balance.getAmount()));
    }

    public boolean isAllSoldOut() {
        return productStorage.values()
                .stream()
                .allMatch(Product::isSoldOut);
    }
}
