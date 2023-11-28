package vendingmachine;


import vendingmachine.controller.Controller;
import vendingmachine.service.Service;
import vendingmachine.view.input.InputView;
import vendingmachine.view.output.OutputView;

public class AppConfig {

    private static final AppConfig APP_CONFIG = new AppConfig();

    public final Controller controller;
    public final Service service;
    public final InputView inputView;
    public final OutputView outputView;

    private AppConfig() {
        this.outputView = initOutputView();
        this.inputView = initInputView();
        this.service = initGameService();
        this.controller = initController(service, inputView, outputView);
    }

    public static AppConfig getInstance() {
        return APP_CONFIG;
    }

    private OutputView initOutputView() {
        return new OutputView();
    }

    private InputView initInputView() {
        return new InputView();
    }

    private Service initGameService() {
        return new Service();
    }

    private Controller initController(final Service service,
                                      final InputView inputView,
                                      final OutputView outputView) {
        return new Controller(service, inputView, outputView);
    }
}
