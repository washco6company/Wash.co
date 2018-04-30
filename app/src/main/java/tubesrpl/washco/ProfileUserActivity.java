package tubesrpl.washco;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.storage.StorageReference;

public class ProfileUserActivity extends AppCompatActivity {
    Button mAddPicProfile, mSaveProfile;
    DatabaseReference databaseFood;
    FirebaseAuth mAuth;

    private Uri imageUri;

    private StorageReference mStorage;
    Query databaseUser;

    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        mAuth = FirebaseAuth.getInstance();
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
    }

    public void addImage(View view) {

    }


    public void saveProfile(View view) {

    }
}
