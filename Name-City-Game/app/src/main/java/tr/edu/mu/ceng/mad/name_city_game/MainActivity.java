package tr.edu.mu.ceng.mad.name_city_game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtTile;
    private Button btnNormal;
    private Button btnAgainstTime;
    private Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTile = findViewById(R.id.txtTitle);
        btnNormal = findViewById(R.id.btnNormal);
        btnAgainstTime = findViewById(R.id.btnAgainstTime);
        btnExit = findViewById(R.id.btnExit);


        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NormalGameActivity.class);
                startActivity(intent);
            }
        });
    }
}