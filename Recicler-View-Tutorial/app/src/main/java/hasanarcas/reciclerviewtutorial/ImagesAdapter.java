package hasanarcas.reciclerviewtutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesHolder> {

    private ArrayList<Images> imagesList;
    private Context context;
    private View.OnClickListener listener;

    public ImagesAdapter(ArrayList<Images> imagesList, Context context) {
        this.imagesList = imagesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ImagesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
        return new ImagesHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesHolder holder, int position) {
        Images images = imagesList.get(position);
        holder.setData(images);
    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    class ImagesHolder extends RecyclerView.ViewHolder{
        TextView imageName, imageRate;
        ImageView imagePic;

        public ImagesHolder(@NonNull View itemView) {
            super(itemView);
            imageName = (TextView) itemView.findViewById(R.id.image_item_TextViewImageName);
            imageRate = (TextView) itemView.findViewById(R.id.image_item_TextViewImageRate);
            imagePic = (ImageView) itemView.findViewById(R.id.image_item_imageView);

        }

        public void setData(Images images){
            this.imageName.setText(images.getImageName());
            this.imageRate.setText(String.valueOf(images.getImageRate()));
            this.imagePic.setBackgroundResource(images.getImagePic());
        }
    }

    public interface onItemClickListener{
        void onItemClick(Images images, int position);
    }

}
