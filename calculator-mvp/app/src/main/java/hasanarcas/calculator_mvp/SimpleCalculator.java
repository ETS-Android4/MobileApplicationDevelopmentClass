package hasanarcas.calculator_mvp;

public class SimpleCalculator {
    private Integer operand1;
    private Integer operand2;
    private String operator;
    CalculatorListener listener;



    public SimpleCalculator(CalculatorListener listener) {
        this.listener = listener;
    }

    public void setOperand(int value){
        if(operand1 == null)
            operand1 = value;
        else
            operand2 = value;

        if(operator == null)
            return;

        if(operator.equals("+"))
            operand1 += operand2;
        else if(operator.equals("-"))
            operand1 -= operand2;

        listener.onResultCalculated(operand1);
    }

    public void setOperator(String operator){
        if(operator.equals("=")){
            operator = null;
            operand2 = null;
        }else
            this.operator = operator;

    }
    public void reset() {
        operand1 = null;
        operand2 = null;
        operator = null;
    }
}
