package hasanarcas.calculator_mvp;

import android.view.View;

public interface CalculatorListener extends View.OnClickListener{

    int getResult();
    void setOperand(String operand);
    void setOperator(String operator);

}
