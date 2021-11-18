package hasanarcas.reciclerviewtutorial;

import java.util.ArrayList;

public class Images {
    private String imageName;
    private int imageRate, imagePic;

    public Images(){}

    public Images(String imageName, int imageRate, int imagePic) {
        this.imageName = imageName;
        this.imageRate = imageRate;
        this.imagePic = imagePic;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getImageRate() {
        return imageRate;
    }

    public void setImageRate(int imageRate) {
        this.imageRate = imageRate;
    }

    public int getImagePic() {
        return imagePic;
    }

    public void setImagePic(int imagePic) {
        this.imagePic = imagePic;
    }

    static public ArrayList<Images> getData(){
        ArrayList<Images> imagesList = new ArrayList<Images>();
        String[] images = {"Weed 1", "Weed 2", "Walter White Labs"};
        int[] rates = {92, 97, 99};
        int[] pictures = {R.mipmap.weed1, R.mipmap.weed2, R.mipmap.ww};

        for(int i = 0; i < images.length; i++){
            Images image = new Images();
            image.setImageName(images[i]);
            image.setImageRate(rates[i]);
            image.setImagePic(pictures[i]);
            imagesList.add(image);
        }


        return imagesList;
    }
}
