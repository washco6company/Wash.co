package tubesrpl.washco.Model;

public class Comment {

    String id;
    String laundryname;
    String comment;

    public Comment() {
    }

    public Comment(String id, String username, String comment) {
        this.id = id;
        this.laundryname = username;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public String getLaundryname() {
        return laundryname;
    }

    public String getComment() {
        return comment;
    }
}
