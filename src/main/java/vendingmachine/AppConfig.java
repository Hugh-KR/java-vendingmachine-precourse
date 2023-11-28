package vendingmachine;


import vendingmachine.controller.MachineController;
import vendingmachine.service.Service;
import vendingmachine.view.input.InputView;
import vendingmachine.view.output.OutputView;

public class AppConfig {

    private static final AppConfig APP_CONFIG = new AppConfig();

    public final MachineController machineController;
    public final Service service;
    public final InputView inputView;
    public final OutputView outputView;

    private AppConfig() {
        this.outputView = initOutputView();
        this.inputView = initInputView(outputView);
        this.service = initGameService();
        this.machineController = initMachineController(service, inputView, outputView);
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

    private Service initGameService() {
        return new Service();
    }

    private MachineController initMachineController(final Service service,
                                             final InputView inputView,
                                             final OutputView outputView) {
        return new MachineController(service, inputView, outputView);
    }
}
