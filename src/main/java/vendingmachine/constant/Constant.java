package vendingmachine.constant;

public enum Constant {

    ZERO(0),
    OPERAND(10);

    private final int constant;

    Constant(final int constant) {
        this.constant = constant;
    }

    public int getConstant() {
        return this.constant;
    }
}
