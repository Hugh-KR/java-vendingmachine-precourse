package vendingmachine.domain.machine.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductStorage {
    private static final Map<String, Product> productStorage = new HashMap<>();

    public void save(final List<Product> products) {
        for (Product product : products) {
            String productName = product.getName();
            productStorage.put(productName, product);
        }
    }
}
