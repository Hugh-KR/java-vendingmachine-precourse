package vendingmachine.domain.machine.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import vendingmachine.domain.user.Balance;
import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.product.ProductExceptionStatus;

public class ProductStorage {
    private static final Map<String, Product> productStorage = new HashMap<>();

    public void save(final List<Product> products) {
        for (Product product : products) {
            String productName = product.getName();
            validateProductIsDuplicated(productName);
            productStorage.put(productName, product);
        }
    }

    private void validateProductIsDuplicated(final String productName) {
        if (isDuplicated(productName)) {
            throw new CustomIllegalArgumentException(ProductExceptionStatus.IS_DUPLICATED_PRODUCT);
        }
    }

    private boolean isDuplicated(final String productName) {
        return productStorage.containsKey(productName);
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
