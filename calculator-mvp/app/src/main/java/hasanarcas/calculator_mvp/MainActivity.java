package hasanarcas.calculator_mvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CalculatorView {
    EditText txtNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalculatorPresenter presenter = new CalculatorPresenter(this);
        txtNum = findViewById(R.id.textView2);
        TableLayout table = findViewById(R.id.numPad);
        for (int i = 0; i < table.getChildCount(); i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            for(int j = 0; j<row.getChildCount();j++){
                Button btn = (Button) row.getChildAt(j);
                btn.setOnClickListener(presenter);
            }
        }
    }




    @Override
    public void setNumber(String number) {
        txtNum.setText(number);
        Toast.makeText(this, "aaaaa", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getNumber() {
        return txtNum.getText().toString();
    }
}