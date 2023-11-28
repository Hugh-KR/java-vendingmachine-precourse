package vendingmachine.view.input;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import vendingmachine.dto.ProductDto;
import vendingmachine.view.input.converter.ReadAmountConverter;
import vendingmachine.view.input.converter.ReadProductsConverter;
import vendingmachine.view.output.OutputView;

public class InputView {

    private final OutputView outputView;

    public InputView(final OutputView outputView) {
        this.outputView = outputView;
    }

    private String readLine() {
        return Console.readLine();
    }

    public int readAmount() {
        outputView.printReadAmountMessage();
        return ReadAmountConverter.convertAmount(readLine());
    }

    public List<ProductDto> readProducts() {
        outputView.printReadProductsMessage();
        return ReadProductsConverter.convertProducts(readLine());
    }




}
