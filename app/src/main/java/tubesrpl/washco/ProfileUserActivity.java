package tubesrpl.washco;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.List;

import tubesrpl.washco.SetterGetter.User;

public class ProfileUserActivity extends AppCompatActivity {
    Button mAddPicProfile, mSaveProfile;
    DatabaseReference databaseFood;
    FirebaseAuth mAuth;
    List<User> userList;

    EditText username, email;
    ImageView userImage;

    private Uri imageUri;

    private StorageReference mStorage;
    Query databaseUser;

    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        mAuth = FirebaseAuth.getInstance();

        EditText username = (EditText) findViewById(R.id.et_username);
        EditText email = (EditText) findViewById(R.id.et_email);

        final ImageView userImage = (ImageView) findViewById(R.id.img_user);

        /*
        databaseUser = FirebaseDatabase.getInstance().getReference(MainActivity.table3);
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                System.out.println(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });
        */
        String currentUser = mAuth.getCurrentUser().getUid();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("User").child(currentUser);
        DatabaseReference emailUser = ref.child("email");
        DatabaseReference userName = ref.child("username");

//        email.setText(emailUser.getValue());
//        username.setText(String.valueOf(userName));

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                EditText username = (EditText) findViewById(R.id.et_username);
                EditText email = (EditText) findViewById(R.id.et_email);

                String emailUser = (String) dataSnapshot.child("email").getValue();
                String userName = (String) dataSnapshot.child("username").getValue();
                //int count = dataSnapshot.child(post.getId()).child("favCount").getValue(Integer.class)
                email.setText(String.valueOf(emailUser));
                username.setText(String.valueOf(userName));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }

        });

    }

//this.userList = userList;
//       username.setText(ref.get;
//        imageUri = null;
//        mAuth = FirebaseAuth.getInstance();
//
//        mStorage = FirebaseStorage.getInstance().getReference().child("profileimages");
//        mAuth = FirebaseAuth.getInstance();
//
//        mAddPicProfile = (Button) findViewById(R.id.btn_add_image_profile);
//        mAddPicProfile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
//            }
//        });


    public void addImage(View view) {

    }


    public void saveProfile(View view) {

    }
}
