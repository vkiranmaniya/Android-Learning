package com.example.vkira.navigationdrawer;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolBar;
    DrawerLayout mDrawer;
    ImageButton mImageMenuButton;
    NavigationView mNavigationView;
    FrameLayout mFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = findViewById(R.id.toolbar);

        mImageMenuButton = findViewById(R.id.showMenuButton);
        mDrawer = findViewById(R.id.Drawer);
        mNavigationView = findViewById(R.id.navigationDrawer);
        mFrameLayout = findViewById(R.id.FragmentDrawer);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawer.closeDrawer(Gravity.START);
                Toast.makeText(MainActivity.this, "Clicked: "+item.getTitle(), Toast.LENGTH_SHORT).show();
                Log.d("OptionMenu Event:" ,"Selected Option : "+item.getItemId());
                return true;
            }
        });

        mImageMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
        return true;
    }

    public boolean setFragment(Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction T = manager.beginTransaction();

        return true;
    }
}
