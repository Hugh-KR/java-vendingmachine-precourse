package vendingmachine.view.output;

import java.util.List;
import vendingmachine.dto.CoinStorageDto;
import vendingmachine.view.output.message.OutputMessage;

public class OutputView {

    public void printMessage(final String message) {
        System.out.println(message);
    }

    private void printMessage(final OutputMessage outputMessage) {
        this.printMessage(outputMessage.getMessage());
    }

    public void printReadAmountOfMachineMessage() {
        printMessage(OutputMessage.READ_AMOUNT_IN_POSSESSION_MESSAGE);
    }

    public void printCoinsOfMachineMessage() {
        printEmptyLine();
        printMessage(OutputMessage.PRINT_COIN_IN_POSSESSION_MESSAGE);
    }

    public void printCoinsOfMachine(final List<CoinStorageDto> coinStatuses) {
        for (CoinStorageDto coinStorageDto : coinStatuses) {
            printMessage(String.format(
                    OutputMessage.PRINT_COIN_MESSAGE.getMessage(),
                    coinStorageDto.getCoin(),
                    coinStorageDto.getCount()
            ));
        }
    }

    public void printReadProductsMessage() {
        printEmptyLine();
        printMessage(OutputMessage.READ_PRODUCT_MESSAGE);
    }

    public void printReadAmountOfUserMessage() {
        printEmptyLine();
        printMessage(OutputMessage.READ_DEPOSIT_AMOUNT_OF_USER_MESSAGE);
    }

    public void printCurrentAmountOfUserMessage(final int amount) {
        printEmptyLine();
        final String message = String.format(
                OutputMessage.PRINT_CURRENT_AMOUNT_OF_USER_MESSAGE.getMessage(),
                amount
        );
        printMessage(message);
    }

    public void printPurchaseProductMessage() {
        printMessage(OutputMessage.READ_PRODUCTS_TO_BUY_MESSAGE);
    }


    public void printChangesOfMachineMessage() {
        printMessage(OutputMessage.READ_CHANGE_MESSAGE);
    }

    public void printChangesOfMachine(final List<CoinStorageDto> coinStatuses) {
        for (CoinStorageDto coinStorageDto : coinStatuses) {
            printMessage(String.format(
                    OutputMessage.PRINT_COIN_MESSAGE.getMessage(),
                    coinStorageDto.getCoin(),
                    coinStorageDto.getCount()
            ));
        }
    }

    public void printEmptyLine() {
        System.out.println();
    }
}
