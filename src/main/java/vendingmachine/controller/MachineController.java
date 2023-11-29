package vendingmachine.controller;

import java.util.List;
import java.util.stream.Collectors;
import vendingmachine.domain.machine.Machine;
import vendingmachine.domain.machine.amount.Amount;
import vendingmachine.domain.machine.coin.Coin;
import vendingmachine.domain.machine.coin.CoinStorage;
import vendingmachine.domain.machine.coin.Filler;
import vendingmachine.domain.machine.product.Product;
import vendingmachine.domain.user.Balance;
import vendingmachine.domain.user.User;
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

        final User user = depositAmountOfUser();
        announcePurchaseProducts(user, machine);

        announceRefundChangesOfUser(user, machine);
    }


    private Machine depositAmountOfMachine() {
        return ExceptionHandler.getExceptionHandler(() -> {
            final Amount amount = new Amount(inputView.readAmountOfMachine());
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

    private User depositAmountOfUser() {
        return ExceptionHandler.getExceptionHandler(() -> {
            final Balance balance = new Balance(inputView.readAmountOfUser());
            return new User(balance);
        });
    }

    private void announcePurchaseProducts(final User user, final Machine machine) {
        while (isPossibleToPurchase(user, machine)) {
            announceCurrentAmountOfUser(user);
            final String product = purchaseProduct();
            user.purchase(product, machine);
        }
    }

    private void announceCurrentAmountOfUser(final User user) {
        outputView.printCurrentAmountOfUserMessage(user.getCurrentAmount());
    }

    private String purchaseProduct() {
        return ExceptionHandler.getExceptionHandler(inputView::readPurchaseProduct);
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
