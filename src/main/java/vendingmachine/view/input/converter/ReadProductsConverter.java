package vendingmachine.view.input.converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.dto.ProductDto;
import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.exception.product.ProductExceptionStatus;
import vendingmachine.utils.Delimiter;
import vendingmachine.view.input.validator.ReadProductsValidator;

public class ReadProductsConverter {

    static final ReadProductsConverter READ_PRODUCTS_CONVERTER = new ReadProductsConverter();

    private ReadProductsConverter() {
    }

    public static List<ProductDto> convertProducts(final String products) {
        READ_PRODUCTS_CONVERTER.isEmptyProduct(products);
        return READ_PRODUCTS_CONVERTER.toDtos(products);
    }

    private List<ProductDto> toDtos(final String products) {
        final List<String> detail = READ_PRODUCTS_CONVERTER.splitWithSemiColon(products);
        return Collections.unmodifiableList(
                detail.stream()
                        .map(READ_PRODUCTS_CONVERTER::toDto)
                        .collect(Collectors.toList())
        );
    }

    private List<String> splitWithSemiColon(final String products) {
        return Collections.unmodifiableList(
                Arrays.stream(Delimiter.splitWithSemiColon(products))
                .map(String::trim)
                .collect(Collectors.toList())
        );
    }

    private ProductDto toDto(final String product) {
        List<String> detail = splitWithComma(isNullProduct(product));
        validateIsOutOfRange(detail);

        final String name = isNullProduct(detail.get(0));
        final int price = parseNumber(detail.get(1));
        final int quantity = parseNumber(detail.get(2));

        ReadProductsValidator.validateProducts(name, price, quantity);
        return new ProductDto(name, price, quantity);
    }

    private List<String> splitWithComma(final String product) {
        isEnclosedInBracket(product);

        final String removedProduct = Delimiter.removeBracket(product);
        return Collections.unmodifiableList(
                Arrays.stream(Delimiter.splitWithComma(removedProduct))
                        .map(String::trim)
                        .collect(Collectors.toList())
        );
    }

    private int parseNumber(final String detail) {
        try {
            return Integer.parseInt(isNullProduct(detail));
        } catch (NumberFormatException e) {
            throw new CustomIllegalArgumentException(ProductExceptionStatus.IS_WRONG_PRODUCT);
        }
    }

    private void validateIsOutOfRange(final List<String> detail) {
        if (isOutOfRange(detail)) {
            throw new CustomIllegalArgumentException(ProductExceptionStatus.IS_WRONG_PRODUCT);
        }
    }

    private boolean isOutOfRange(final List<String> detail) {
        return detail.size() != 3;
    }

    private void isEnclosedInBracket(final String product) {
        if (!Delimiter.isEnclosedInBracket(product)) {
            throw new CustomIllegalArgumentException(ProductExceptionStatus.IS_WRONG_PRODUCT);
        }
    }

    private String isNullProduct(final String products) {
        try {
            return products.trim();
        } catch (NullPointerException e) {
            throw new CustomIllegalArgumentException(ProductExceptionStatus.IS_WRONG_PRODUCT);
        }
    }

    private void isEmptyProduct(final String products) {
        if (isNullProduct(products).isEmpty()) {
            throw new CustomIllegalArgumentException(ProductExceptionStatus.IS_WRONG_PRODUCT);
        }
    }
}
