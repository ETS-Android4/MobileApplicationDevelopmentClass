package hasanarcas.mytagram;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends BaseAdapter {
    List<Post> posts;
    LayoutInflater inflater;

    public PostAdapter(Activity activity, List<Post> posts) {
        this.posts = posts;
        inflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int i) {
        return posts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View rowView = inflater.inflate(R.layout.row,null);
        EditText txtMessage = rowView.findViewById(R.id.txtMessage);
        TextView txtLocation = rowView.findViewById(R.id.txtLocation);
        ImageView imageView = rowView.findViewById(R.id.imageView);

        Post post = posts.get(position);
        txtMessage.setText(post.getMessage());
        imageView.setImageBitmap(post.getImage());

        if(post.getLocation() != null){
            txtLocation.setText(post.getLocation().getLatitude() + " " + post.getLocation().getLongitude());
        }

        return rowView;
    }
}
