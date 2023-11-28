package vendingmachine;

import vendingmachine.controller.MachineController;

public class Application {
    private final MachineController machineController;

    public Application(final AppConfig appConfig) {
        this.machineController = appConfig.machineController;
    }

    public static void main(String[] args) {
        Application application = new Application(AppConfig.getInstance());
        application.run();
    }

    public void run() {
        machineController.run();
    }
}
