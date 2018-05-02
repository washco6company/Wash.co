package tubesrpl.washco;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tubesrpl.washco.ModelUI.User;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "Authentication Email";
    TextInputLayout mEmail, mPassword;
    Button mDaftar, mMasuk, mAdmin;

    String email, password;

    private FirebaseAuth mAuth;
    DatabaseReference databaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mEmail = findViewById(R.id.et_email);
        mPassword = findViewById(R.id.et_password);

        databaseUser = FirebaseDatabase.getInstance().getReference(MainActivity.table3);

        mMasuk = findViewById(R.id.btn_masuk);
        mMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmail.getEditText().getText().toString();
                password = mPassword.getEditText().getText().toString();

                if (email.equals("admin@mail.com")&&(password.equals("12345678"))) {
                    signInAdmin(email, password);
                }else if (validateForm()) {
                    signIn(email, password);
                } else {
                    Toast.makeText(LoginActivity.this, "Please Fill the Form",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        mDaftar = findViewById(R.id.btn_daftar);
        mDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mEmail.getEditText().getText().toString();
                password = mPassword.getEditText().getText().toString();

                if (validateForm()) {
                    createAccount(email, password);
                } else {
                    Toast.makeText(LoginActivity.this, "Please Fill the Form",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

//        mAdmin = (Button) findViewById(R.id.btn_admin);
//        mAdmin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                email = mEmail.getEditText().getText().toString();
//                password = mPassword.getEditText().getText().toString();
//
//                if (email.equals("admin@mail.com")&&(password.equals("12345678"))) {
//                    signInAdmin(email, password);
//                } else {
//                    Toast.makeText(LoginActivity.this, "You Are Not Admin",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            sendToMain();
        }

    }

    private void createAccount(final String email, final String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            String id = mAuth.getUid();
                            String[] username = email.split("@");
                            User user = new User(id, username[0], email);
                            databaseUser.child(id).setValue(user);
                            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void signIn(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(i);
                        } else {

                            Toast.makeText(LoginActivity.this, "Akun Belum Terdaftar",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void signInAdmin(String email, String password) {

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(LoginActivity.this, MainActivityAdmin.class);
                            startActivity(i);
                        } else {

                            Toast.makeText(LoginActivity.this, "You Are Not Admin",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private boolean validateForm() {
        boolean valid = false;

        valid = !email.isEmpty() && !password.isEmpty();
        return valid;

    }

    private void sendToMain() {
        Intent mainIntent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(mainIntent);
        finish();
    }


}
