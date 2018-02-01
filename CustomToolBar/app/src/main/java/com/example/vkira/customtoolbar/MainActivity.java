package com.example.vkira.customtoolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    Animation mDeleteAnimation;
    ArrayList<Integer> mCountryList = new ArrayList<>();
    Toolbar mToolbar;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = findViewById(R.id.CustomToolbar);
        setSupportActionBar(mToolbar);

        mRecyclerView = findViewById(R.id.RecyclerView_MainActivity);

        int ImageID[] = {R.drawable.india ,R.drawable.afghanistan ,R.drawable.albania ,R.drawable.algeria ,
                R.drawable.andorra ,R.drawable.angola ,R.drawable.antigua ,R.drawable.argentina ,R.drawable.armenia ,R.drawable.australia ,
                R.drawable.austria ,R.drawable.bangladesh ,R.drawable.canada ,R.drawable.iceland ,R.drawable.liberia ,
                R.drawable.maldives ,R.drawable.mauritania ,R.drawable.montenegro ,R.drawable.morocco ,R.drawable.mozambique ,
                R.drawable.pakistan ,R.drawable.shrilanka ,R.drawable.spain ,R.drawable.sweden ,R.drawable.switzerland ,
                R.drawable.taiwan ,R.drawable.tajikistan ,R.drawable.uk ,R.drawable.usa ,R.drawable.zimbabwe};

        for(int image : ImageID){
            Country object = new Country(image);
        }



        mCountryList.addAll(Country.getmCountryImage());

        mLayoutManager = new GridLayoutManager(MainActivity.this , 2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CountryRecycler(MainActivity.this , mCountryList);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.profile:
                Toast.makeText(this, "Profile Option selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "Settings Option selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.contectus:
                Toast.makeText(this, "Contect-Us Option selected", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "No option selected", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    public void showCountryDialog(final View view){
        final AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(this);
                mAlertDialog.setIcon(view.getBackground());//Set Drawable icon here
                mAlertDialog.setTitle("Delete?");
                mAlertDialog.setMessage("The above shown icon is clicked from the list, do you want to remove it?");
                mAlertDialog.setCancelable(false);
                String mYes = "Yes" , mNo = "No";
                mAlertDialog.setPositiveButton(mYes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mDeleteAnimation = AnimationUtils.loadAnimation(MainActivity.this , R.anim.fab_close);
                        view.startAnimation(mDeleteAnimation);
                    }
                });
                mAlertDialog.setNegativeButton(mNo, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                mAlertDialog.show();
    }
}
