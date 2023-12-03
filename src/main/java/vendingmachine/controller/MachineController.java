package vendingmachine.controller;

import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.domain.machine.Machine;
import vendingmachine.domain.machine.amount.Amount;
import vendingmachine.domain.machine.coin.Coin;
import vendingmachine.domain.machine.coin.Filler;
import vendingmachine.domain.machine.product.Product;
import vendingmachine.domain.user.Balance;
import vendingmachine.domain.user.User;
import vendingmachine.dto.CoinStorageDto;
import vendingmachine.dto.ProductDto;
import vendingmachine.exception.CustomIllegalArgumentException;
import vendingmachine.view.input.InputView;
import vendingmachine.view.output.OutputView;

public class MachineController {

    private final InputView inputView;
    private final OutputView outputView;

    public MachineController(final InputView inputView,
                             final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final Machine machine = initMachine();
        final User user = initUser();

        announcePurchaseProducts(user, machine);
        announceRefundChangesOfUser(user, machine);
    }

    private Machine initMachine() {
        return ExceptionHandler.getExceptionHandler(() -> {
            Machine machine = depositAmountOfMachine();
            announceDepositedCoins(machine);
            return registerProductsOfMachine(machine);
        });
    }

    private Machine depositAmountOfMachine() {
        return ExceptionHandler.getExceptionHandler(() -> {
            final Amount amount = new Amount(inputView.readAmountOfMachine());
            final Filler filler = new Filler(amount.getAmount());
            final List<Coin> coins = filler.getCoins();
            return new Machine(coins);
        });
    }

    private Machine registerProductsOfMachine(final Machine machine) {
        return ExceptionHandler.getExceptionHandler(() -> {
            final List<ProductDto> productDtos = inputView.readProducts();
            final List<Product> products = productDtos.stream()
                    .map(ProductDto::toEntity)
                    .collect(Collectors.toList());
            machine.saveProducts(products);
            return machine;
        });
    }

    private void announceDepositedCoins(final Machine machine) {
        final List<CoinStorageDto> coinsOfMachine = machine.getCoinsOfMachine();
        outputView.printCoinsOfMachineMessage();
        outputView.printCoinsOfMachine(coinsOfMachine);
    }

    private User initUser() {
        return ExceptionHandler.getExceptionHandler(() -> {
            final Balance balance = new Balance(inputView.readAmountOfUser());
            return new User(balance);
        });
    }

    private void announcePurchaseProducts(final User user, final Machine machine) {
        do {
            try {
                announceCurrentAmountOfUser(user);
                final String product = inputView.readPurchaseProduct();
                user.purchase(product, machine);
            } catch (CustomIllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (isPossibleToPurchase(user, machine));
    }

    private void announceCurrentAmountOfUser(final User user) {
        outputView.printCurrentAmountOfUserMessage(user.getCurrentAmount());
    }

    private boolean isPossibleToPurchase(final User user, final Machine machine) {
        return user.hasNotEnoughAmount(machine) || machine.isAllProductSoldOut();
    }

    private void announceRefundChangesOfUser(final User user, final Machine machine) {
        announceCurrentAmountOfUser(user);
        machine.refund(user);
        announceChangedCoins(user);
    }

    private void announceChangedCoins(final User user) {
        final List<CoinStorageDto> changesOfMachine = user.getRefundCoinStatuses();
        outputView.printChangesOfMachineMessage();
        outputView.printChangesOfMachine(changesOfMachine);
    }
}
