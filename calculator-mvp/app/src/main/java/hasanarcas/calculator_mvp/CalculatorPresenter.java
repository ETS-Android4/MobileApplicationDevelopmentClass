package hasanarcas.calculator_mvp;

import android.view.View;

public class CalculatorPresenter implements CalculatorListener{

    SimpleCalculator calculator;
    CalculatorView view;

    public CalculatorPresenter(CalculatorView view) {
        this.view = view;
        calculator = new SimpleCalculator(this);
    }

    @Override
    public int getResult() {
        return calculator.setResult();
    }

    @Override
    public void setOperand(String sOperand) {
        Integer operand = Integer.parseInt(sOperand);
        if(calculator.getOperand1()!=null){
            calculator.setOperand2(operand);
        }else
            calculator.setOperand1(operand);
    }

    @Override
    public void setOperator(String operator) {
        if(calculator.getOperand1()!=null){
            calculator.setOperator(operator);
        }
    }


}
