package tubesrpl.washco.ModelUI;

public class ProfilePost {
    String id;
    private String userID;
    private String username;
    private String titlePost;
    private String post;
    private String imagePost;
    private String laundryname;

    //Wajib kasih Constructor Kosong
    public ProfilePost() {
    }

    public ProfilePost(String id, String userID, String mImagePost, String titlePost, String laundryname, String post) {
        this.id = id;
        this.imagePost = mImagePost;
        this.laundryname = laundryname;
        this.titlePost = titlePost;
        this.post = post;
        this.userID = userID;
    }

    public String getImagePost() {
        return imagePost;
    }

    public String getUserID() {
        return userID;
    }

    public String getId() {
        return id;
    }

    public String getTitlePost() {
        return titlePost;
    }

    public String getPost() {
        return post;
    }

    public String getLaundryname() {
        return laundryname;
    }
}
