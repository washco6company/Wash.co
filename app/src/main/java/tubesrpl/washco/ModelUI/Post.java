package tubesrpl.washco.ModelUI;

import java.util.HashMap;
import java.util.Map;

public class Post {
    String id;
    private String userID;
    private String price;
    private String titlePost;
    private String address;
    private String imagePost;
    private String laundryname;
    private int favCount = 0;
    private Map<String, Boolean> fav = new HashMap<>();

    //Wajib kasih Constructor Kosong
    public Post() {
    }

    public Post(String id, String userID, String mImagePost, String titlePost, String laundryname, String address, String price, int favCount) {
        this.id = id;
        this.price = price;
        this.imagePost = mImagePost;
        this.laundryname = laundryname;
        this.titlePost = titlePost;
        this.address = address;
        this.userID = userID;
        this.favCount = favCount;
    }

    public String getImagePost() {
        return imagePost;
    }

    public String getPrice() {
        return price;
    }

    public String getUserID() {
        return userID;
    }

    public int getFavCount() {
        return favCount;
    }

    public String getId() {
        return id;
    }

    public String getTitlePost() {
        return titlePost;
    }

    public String getAddress() {
        return address;
    }

    public String getLaundryname() {
        return laundryname;
    }

}
