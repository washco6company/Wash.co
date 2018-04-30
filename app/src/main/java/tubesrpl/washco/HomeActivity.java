package tubesrpl.washco;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import tubesrpl.washco.Adapter.SwipeAdapter;

public class HomeActivity extends AppCompatActivity {
    ViewPager viewPager;
    SwipeAdapter adapter;
    private Button btnToLaundry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnToLaundry = findViewById(R.id.btn_toLaundry);

        viewPager = findViewById(R.id.view_pager);
        adapter = new SwipeAdapter(this);
        viewPager.setAdapter(adapter);

    }

    public void toLaundryList(View view) {
        Intent intent = new Intent(HomeActivity.this, MainActivity.class);
        startActivity(intent);
    }
}