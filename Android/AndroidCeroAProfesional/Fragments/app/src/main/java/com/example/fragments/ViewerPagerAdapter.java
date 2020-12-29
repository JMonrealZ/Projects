package com.example.fragments;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import static com.example.fragments.MainActivity.TABMAIN;
import static com.example.fragments.MainActivity.TABDOS;

public class ViewerPagerAdapter extends FragmentPagerAdapter {

    private int numberTabs;

    public ViewerPagerAdapter(@NonNull FragmentManager fm, Context cxt, int tabsNumber){
        super(fm);
        this.numberTabs = tabsNumber;
    }

    //@NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position){
            case TABMAIN:
                return new FragmentUno();
            case TABDOS:
                return new FragmentDos();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberTabs;
    }
}
