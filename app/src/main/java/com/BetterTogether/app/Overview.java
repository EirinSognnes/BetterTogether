package com.BetterTogether.app;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.BetterTogether.app.adapters.TabAdapter;

import DB.DatabaseThreadHandler;
import DB.SQLiteDB;
import JSONReader.ParsedPerson;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class Overview extends AppCompatActivity {

    private static final int MY_PERMISSIONS_READ_EXTERNAL = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        setSupportActionBar(findViewById(R.id.toolbar));
        ActionBar bar = getSupportActionBar();
        bar.hide();

        if (!(ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)){
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_READ_EXTERNAL);
        }

            TabAdapter tabAdapter = new TabAdapter
                    (getSupportFragmentManager(), new UserListFragment(), new GraphFragment());

        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(tabAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.bringToFront();
        tabLayout.setupWithViewPager(viewPager);
    }

    private void testAddToJSONFile() {
        ParsedPerson p = new ParsedPerson();
        p.setUsername("json");
        p.setFirstname("test");
        p.setLastname("write");
    }

    /*
    private void checkDB() {
        handler.getThreshold(RewardType.CAKE)
                .subscribe(reward -> Log.d("DATABASE-REWARD-CAKE-50"
                , Integer.toString(reward)));
    }*/


}
