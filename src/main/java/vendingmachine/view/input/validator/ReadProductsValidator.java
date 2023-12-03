package vendingmachine.view.input.validator;

import java.util.Arrays;
import java.util.List;
import vendingmachine.dto.ProductDto;
import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.product.ProductExceptionStatus;
import vendingmachine.utils.Delimiter;

public class ReadProductsValidator {

    static final ReadProductsValidator READ_PRODUCTS_VALIDATOR = new ReadProductsValidator();

    private ReadProductsValidator() {
    }

    public static void validateProducts(final String name, final int price, final int quantity) {
        READ_PRODUCTS_VALIDATOR.validateNameIsEmpty(name);
        READ_PRODUCTS_VALIDATOR.validatePriceIsMoreThanMinimum(price);
        READ_PRODUCTS_VALIDATOR.validatePriceIsMultipleOfTen(price);
        READ_PRODUCTS_VALIDATOR.validateQuantityIsPositive(quantity);
    }

    private void validateNameIsEmpty(final String name) {
        if (name.isEmpty()) {
            throw new CustomIllegalArgumentException(ProductExceptionStatus.IS_WRONG_PRODUCT);
        }
    }

    private void validatePriceIsMoreThanMinimum(final int price) {
        if (price < 100) {
            throw new CustomIllegalArgumentException(ProductExceptionStatus.IS_WRONG_PRODUCT);
        }
    }

    private void validatePriceIsMultipleOfTen(final int price) {
        if (price % 10 != 0) {
            throw new CustomIllegalArgumentException(ProductExceptionStatus.IS_WRONG_PRODUCT);
        }
    }

    private void validateQuantityIsPositive(final int quantity) {
        if (quantity <= 0) {
            throw new CustomIllegalArgumentException(ProductExceptionStatus.IS_WRONG_PRODUCT);
        }
    }
}
