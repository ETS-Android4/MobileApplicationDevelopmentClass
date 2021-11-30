package hasanarcas.calculator_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CalculatorView
{
    CalculatorPresenter presenter;
    EditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new CalculatorPresenter(this);
        txt = findViewById(R.id.textView2);
        TableLayout table = findViewById(R.id.numPad);
        for (int i=0; i< table.getChildCount(); i++){
            TableRow row = (TableRow)table.getChildAt(i);
            for (int j=0; j< row.getChildCount(); j++){
                View view = row.getChildAt(j);
                view.setOnClickListener(presenter);
            }
        }
    }
    @Override
    public void setNumber(String result) {
        txt.setText(result);
    }
    @Override
    public String getNumber() {
        return txt.getText().toString();
    }
}