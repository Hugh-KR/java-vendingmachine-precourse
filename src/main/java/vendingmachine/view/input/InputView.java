package vendingmachine.view.input;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.view.input.validator.ReadAmountValidator;
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
        return ReadAmountValidator.validateAmount(readLine());
    }




}
