package hasanarcas.calculator_mvp;

import android.view.View;
import android.widget.Button;

import java.net.Inet4Address;
import java.util.Locale;

public class CalculatorPresenter implements CalculatorListener, View.OnClickListener {
    SimpleCalculator calculator;
    CalculatorView view;
    boolean operatorExpected = false;
    boolean isFirstDigit = true;

    public CalculatorPresenter(CalculatorView view){
        this.view = view;
        calculator = new SimpleCalculator(this);
    }

    @Override
    public void onResultCalculated(Integer operand1) {

    }

    @Override
    public void onClick(View v){
        Button btn = (Button) v;
        String symbol = btn.getText().toString();
        if (operatorExpected) {
            if (symbol.equals("+") || symbol.equals("-")) {
                calculator.setOperand(Integer.parseInt(view.getNumber()));
                calculator.setOperator(symbol);
                isFirstDigit = true;
                return;
            }else if(symbol.equals("=")) {
                calculator.setOperand(Integer.parseInt(view.getNumber()));
                calculator.setOperator(symbol);
                isFirstDigit = true;
                return;
            }
        }
        if(symbol.toLowerCase().equals("clear")){
            view.setNumber("");
            operatorExpected = false;
            isFirstDigit = true;
            calculator.reset();
        }else{
            if(isFirstDigit){
                view.setNumber(symbol);
                isFirstDigit = false;
                operatorExpected = true;
            }else{
                view.setNumber(view.getNumber() + symbol);
            }
        }
    }

}
