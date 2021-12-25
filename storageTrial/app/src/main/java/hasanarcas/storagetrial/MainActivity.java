package hasanarcas.storagetrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/*
public class MainActivity extends AppCompatActivity {
    GridView grid;
    ArrayList<Integer> myList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.grid);

        */
/*myList.add(R.drawable.ic_launcher_background);
        myList.add(R.drawable.common_google_signin_btn_text_dark_focused);*//*



        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();


        */
/*StorageReference image1 = storageRef.child("games/archvale.jpg");
        final long ONE_MEGABYTE = 1024 * 1024;
        image1.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                image.setImageBitmap(bitmap);
            }
        });
*//*


        ImageAdapter adapter = new ImageAdapter(this, myList);
        grid.setAdapter(adapter);



        */
/*FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        DocumentReference ref = firestore.collection("games").document("game_properties");
        ref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        String text = document.getString("content");
                        Scanner sc = new Scanner(text);
                        sc.useDelimiter(",");   //sets the delimiter pattern
                        while (sc.hasNext())  //returns a boolean value
                        {
                            Log.d("11111" , sc.next());  //find and returns the next complete token from this scanner
                        }

                    }
                    else{
                        Log.d("1111111", "no document..........");
                    }
                }
                else{
                    Log.d("11111111", "get failed with " + task.getException());
                }
            }
        });*//*


    }


}*/





public class MainActivity extends AppCompatActivity {
    ArrayList<String> imagelist;
    RecyclerView recyclerView;
    StorageReference root;
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
        listRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for(StorageReference file:listResult.getItems()){
                    file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            if(uri.toString().contains("demeo")){
                                imagelist.add(uri.toString());
                                Log.e("Itemvalue",uri.toString());
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