package tubesrpl.washco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

import tubesrpl.washco.Adapter.SwipeAdapter;

public class HomeActivity extends AppCompatActivity {
    ViewPager viewPager;
    SwipeAdapter adapter;
    private Button btnToLaundry;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();

        btnToLaundry = findViewById(R.id.btn_toLaundry);

        viewPager = findViewById(R.id.view_pager);
        adapter = new SwipeAdapter(this);
        viewPager.setAdapter(adapter);

    }

    //ketika menu dibuat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //method yang dijalankan ketika item di pilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //get item id
        int id = item.getItemId();

        if (id == R.id.logout) {
            mAuth.signOut();
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            
            finish();
        } else if (id == R.id.profile) {
            Intent intent = new Intent(HomeActivity.this, ProfileUserActivity.class);

            finish();
        }
        return true;
    }

    public void toLaundryList(View view) {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);

    }
}