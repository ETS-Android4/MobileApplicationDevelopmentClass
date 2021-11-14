package hasanarcas.listviewexample1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterActivity extends AppCompatActivity {
    final List<Animal> animals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_adapter);
        animals.add(new Animal("Ant", R.mipmap.ic_ant));
        animals.add(new Animal("Bear", R.mipmap.ic_bear));
        animals.add(new Animal("Bird", R.mipmap.ic_bird));
        animals.add(new Animal("Cat", R.mipmap.ic_cat));
        animals.add(new Animal("Dog", R.mipmap.ic_dog));
        animals.add(new Animal("Donkey", R.mipmap.ic_donkey));
        animals.add(new Animal("Elephant", R.mipmap.ic_elephant));
        animals.add(new Animal("Giraffe", R.mipmap.ic_giraffe));
        animals.add(new Animal("Goat", R.mipmap.ic_goat));
        animals.add(new Animal("Monkey", R.mipmap.ic_monkey));
        animals.add(new Animal("Pig", R.mipmap.ic_pig));
        animals.add(new Animal("Rat", R.mipmap.ic_rat));
        animals.add(new Animal("Snake", R.mipmap.ic_snake));
        animals.add(new Animal("Spider", R.mipmap.ic_spider));

        final ListView listView = (ListView) findViewById(R.id.listview);
        AnimalAdapter adapter = new AnimalAdapter(this, animals);
        listView.setAdapter(adapter);
    }
}