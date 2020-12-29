package com.example.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private int fragmentNumber;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewerPagerAdapter adapter;

    public static final int TABMAIN = 0;
    public static final int TABDOS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentNumber = 1;
        getSupportFragmentManager().beginTransaction().replace(R.id.vpContainer,new FragmentUno()).commit();

        setToolBar();
        setTablayout();
        setViewerPager();
        setListenerTabLayout(viewPager);
    }

    public void changeFragment(View view){
        if(fragmentNumber == 1) {
            fragmentNumber = 2;
            getSupportFragmentManager().beginTransaction().replace(R.id.vpContainer,new FragmentDos()).commit();
        }
        else {
            fragmentNumber = 1;
            getSupportFragmentManager().beginTransaction().replace(R.id.vpContainer,new FragmentUno()).commit();
        }
    }

    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Toolbar");
        setSupportActionBar(toolbar);
    }

    private void setTablayout() {
        tabLayout =  findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Uno"));
        tabLayout.addTab(tabLayout.newTab().setText("Dos"));
    }

    private void setViewerPager() {
        viewPager = findViewById(R.id.vpContainer);
        adapter = new ViewerPagerAdapter(getSupportFragmentManager(),this,tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setListenerTabLayout(final ViewPager viewPager){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}