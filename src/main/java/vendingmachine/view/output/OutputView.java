package vendingmachine.view.output;

import vendingmachine.view.output.message.OutputMessage;

public class OutputView {

    public void printMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final OutputMessage outputMessage) {
        this.printMessage(outputMessage.getMessage());
    }

    public void printReadAmountMessage() {
        printMessage(OutputMessage.READ_AMOUNT_IN_POSSESSION_MESSAGE);
    }

    public void printEmptyLine() {
        System.out.println();
    }
}
