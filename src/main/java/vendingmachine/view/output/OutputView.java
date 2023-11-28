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

    public void printReadAmountMessage() {
        printMessage(OutputMessage.READ_AMOUNT_IN_POSSESSION_MESSAGE);
    }

    public void printCoinsOfMachineMessage() {
        printEmptyLine();
        printMessage(OutputMessage.PRINT_COIN_IN_POSSESSION_MESSAGE);
    }

    public void printCoinsOfMachine(final List<CoinStorageDto> coinsOfMachine) {
        for (CoinStorageDto coinStorageDto : coinsOfMachine) {
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
