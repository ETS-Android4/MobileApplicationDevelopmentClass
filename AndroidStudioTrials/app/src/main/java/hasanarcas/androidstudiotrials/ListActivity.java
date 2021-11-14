package hasanarcas.androidstudiotrials;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private ListView animalsList;
    private Spinner citiesSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        animalsList = findViewById(R.id.animalsList);
        citiesSpinner = findViewById(R.id.citiesSpinner);

        ArrayList<String> animals = new ArrayList<>();
        animals.add("Monkey");
        animals.add("Rat");
        animals.add("Cow");
        animals.add("Cat");
        animals.add("Dog");
        ArrayAdapter<String> animalsAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                animals
        );
        animalsList.setAdapter(animalsAdapter);
        animalsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListActivity.this, animals.get(i) + " selected", Toast.LENGTH_SHORT).show();
            }
        });


        ArrayList<String> cities = new ArrayList<>();
        cities.add("Istanbul");
        cities.add("Monaco");
        cities.add("Milano");
        cities.add("Berlin");
        ArrayAdapter<String> citiesAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                cities
        );
        citiesSpinner.setAdapter(citiesAdapter);
        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListActivity.this, cities.get(i) +" selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(ListActivity.this, "Please select an Item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}