package hasanarcas.calculator_mvp;

public class SimpleCalculator {
    private int operand1;
    private int operand2;
    private String operator;

    CalculatorListener listener;

    public SimpleCalculator(CalculatorListener listener) {
        this.listener = listener;
    }

    public int getOperand1() {
        return operand1;
    }

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
