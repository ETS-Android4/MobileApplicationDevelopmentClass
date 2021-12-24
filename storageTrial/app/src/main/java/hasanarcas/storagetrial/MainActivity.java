package hasanarcas.storagetrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    GridView grid;
    ArrayList<Integer> myList = new ArrayList<>();
    StorageReference txtFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.grid);

        /*myList.add(R.drawable.ic_launcher_background);
        myList.add(R.drawable.common_google_signin_btn_text_dark_focused);*/


        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        /*StorageReference image1 = storageRef.child("games/archvale.jpg");
        final long ONE_MEGABYTE = 1024 * 1024;
        image1.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                image.setImageBitmap(bitmap);
            }
        });
*/

        ImageAdapter adapter = new ImageAdapter(this, myList);
        grid.setAdapter(adapter);


        txtFile = storageRef.child("new_released_games_data.csv");

        final File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "names.txt");
        Log.d("11111111", file.getName());
        /*try {
            Scanner sc = new Scanner(file);
            sc.useDelimiter(",");
            while (sc.hasNext()){
                Log.d("11111111111111111111", sc.next());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            Log.e("111111111", "Couldn't download " + e);
        }*/



    }



}