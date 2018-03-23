package com.dmsoftware.newsapp.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.dmsoftware.newsapp.R;
import com.dmsoftware.newsapp.fragments.HomeFragment;
import com.dmsoftware.newsapp.fragments.SettingsFragment;
import com.dmsoftware.newsapp.fragments.SourcesFragment;

public class NewsActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
          return navigateAccordingTo(item.getItemId());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigateAccordingTo(R.id.navigation_home);
    }

    private Fragment getFragments(int id) {
        switch (id) {
            case R.id.navigation_home: return new HomeFragment();
            case R.id.navigation_sources: return new SourcesFragment();
            case R.id.navigation_settings: return new SettingsFragment();
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private boolean navigateAccordingTo(int id) {
        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, getFragments(id))
                    .commit();
            return true;
        } catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
    }

    public void setFragmentToolbar(String toolbarTitle, boolean backIcon, final FragmentManager fragmentManager) {
        toolbar.setTitle(toolbarTitle);
        Integer icon;
        if(backIcon) {
            icon = R.drawable.ic_arrow_back_white_24dp;
            toolbar.setNavigationIcon(icon);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragmentManager.popBackStack();
                }
            });
        } else {
            toolbar.setNavigationIcon(null);
        }
    }
}
