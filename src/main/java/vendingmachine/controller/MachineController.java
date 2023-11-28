package vendingmachine.controller;

import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.domain.machine.Machine;
import vendingmachine.domain.machine.amount.Amount;
import vendingmachine.domain.machine.coin.Coin;
import vendingmachine.domain.machine.coin.CoinStorage;
import vendingmachine.domain.machine.coin.Filler;
import vendingmachine.domain.machine.product.Product;
import vendingmachine.dto.CoinStorageDto;
import vendingmachine.dto.ProductDto;
import vendingmachine.service.Service;
import vendingmachine.view.input.InputView;
import vendingmachine.view.output.OutputView;

public class MachineController {

    private final Service service;
    private final InputView inputView;
    private final OutputView outputView;

    public MachineController(final Service service,
                             final InputView inputView,
                             final OutputView outputView) {
        this.service = service;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Machine machine = depositAmountOfMachine();
        announceDepositedCoins(machine);

        final List<Product> products = registerProductsOfMachine();
        machine.saveProducts(products);




    }


    private Machine depositAmountOfMachine() {
        return ExceptionHandler.getExceptionHandler(() -> {
            final Amount amount = new Amount(inputView.readAmount());
            final Filler filler = new Filler(amount.getAmount());

            List<Coin> coins = filler.getCoins();
            return new Machine(coins);
        });
    }

    private void announceDepositedCoins(final Machine machine) {
        final List<CoinStorageDto> coinsOfMachine = machine.getCoinsOfMachine();
        outputView.printCoinsOfMachineMessage();
        outputView.printCoinsOfMachine(coinsOfMachine);
    }

    private List<Product> registerProductsOfMachine() {
        return ExceptionHandler.getExceptionHandler(() -> {
            final List<ProductDto> productDtos = inputView.readProducts();
            return productDtos.stream().map(ProductDto::toEntity).toList();
        });
    }
}
