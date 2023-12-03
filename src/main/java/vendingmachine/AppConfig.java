package vendingmachine;

import vendingmachine.controller.MachineController;
import vendingmachine.view.input.InputView;
import vendingmachine.view.output.OutputView;

public class AppConfig {

    private static final AppConfig APP_CONFIG = new AppConfig();

    public final MachineController machineController;
    public final InputView inputView;
    public final OutputView outputView;

    private AppConfig() {
        this.outputView = initOutputView();
        this.inputView = initInputView(outputView);
        this.machineController = initMachineController(inputView, outputView);
    }

    public static AppConfig getInstance() {
        return APP_CONFIG;
    }

    private OutputView initOutputView() {
        return new OutputView();
    }

    private InputView initInputView(final OutputView outputView) {
        return new InputView(outputView);
    }


    private MachineController initMachineController(final InputView inputView,
                                                    final OutputView outputView) {
        return new MachineController(inputView, outputView);
    }
}
