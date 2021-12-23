package tr.edu.mu.ceng.mad.localdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText txtName;
    EditText txtSurname;
    EditText txtYear;
    Button btnInsert;
    Button btnSearch;
    TextView txtResult;

    MyRoomDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = findViewById(R.id.txtName);
        txtSurname = findViewById(R.id.txtSurname);
        txtYear = findViewById(R.id.txtYear);
        txtResult = findViewById(R.id.txtResult);
        btnInsert = findViewById(R.id.btnInsert);
        btnSearch = findViewById(R.id.btnSearch);

        db = Room.databaseBuilder(this, MyRoomDatabase.class, "myroomdatabase").build();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Student student = new Student();
                student.setName(txtName.getText().toString());
                student.setSurname(txtSurname.getText().toString());
                student.setYear(Integer.parseInt(txtYear.getText().toString()));

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StudentDAO stDAO = db.studentDAO();
                        stDAO.insert(student);
                    }
                });

                t.start();
            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        StudentDAO stDAO = db.studentDAO();
                        List<Student> students = stDAO.getAll();

                        Log.d("Query", students.toString());
                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtResult.setText(students.toString());
                            }
                        });

                    }
                });

                t.start();
            }
        });
    }
}