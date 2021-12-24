package hasanarcas.storagetrial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ImageAdapter extends BaseAdapter {
    Context context;
    ArrayList<Integer> list = new ArrayList<>();
    ImageView image;
    LayoutInflater inflater;

    public ImageAdapter(Context context, ArrayList<Integer> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.image_list, null);
        ImageView new_image = (ImageView) view.findViewById(R.id.image_list_image);
        new_image.setImageResource(list.get(i));
        return view;
    }
}
