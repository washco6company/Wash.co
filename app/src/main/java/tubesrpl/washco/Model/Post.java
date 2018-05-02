package tubesrpl.washco.Model;

import com.google.firebase.database.Exclude;

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

    public Post(String id, int favCount) {
        this.id = id;
        this.favCount = favCount;
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


    @Exclude
    public Map<String, Object> toMap() {

        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", id);
        result.put("price", price);
        result.put("imagePost", imagePost);
        result.put("laundryname", laundryname);
        result.put("titlePost", titlePost);
        result.put("address", address);
        result.put("userID", userID);
        result.put("favCount", favCount);
        return result;
    }


}
