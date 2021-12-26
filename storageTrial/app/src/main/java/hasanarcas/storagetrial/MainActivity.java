package hasanarcas.storagetrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> imagelist;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ImageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagelist=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerview);
        adapter=new ImageAdapter(imagelist,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        progressBar=findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);
        StorageReference listRef = FirebaseStorage.getInstance().getReference().child("new_released_games");
        //download(listRef, "demeo");

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        CollectionReference ref = firestore.collection("games");
        ref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for(QueryDocumentSnapshot document : task.getResult()){
                    //Log.d("11111111", "== >" + document.getData().get("name"));
                    String formattedName = ((String) document.getData().get("name")).replaceAll(" ", "-").replaceAll(":", "").replaceAll("'","");
                    Log.d("Name of the game:", "====>>" + formattedName);
                    //download(listRef, formattedName);
                }
            }
        });

    }

    public void download(StorageReference listRef, String formattedName){
        listRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for(StorageReference file:listResult.getItems()){
                    file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            if(uri.toString().contains(formattedName)){
                                imagelist.add(uri.toString());
                                Log.e("Itemvalue","" + uri.toString());
                            }

                        }
                    }).addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            recyclerView.setAdapter(adapter);
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            }
        });
    }
}