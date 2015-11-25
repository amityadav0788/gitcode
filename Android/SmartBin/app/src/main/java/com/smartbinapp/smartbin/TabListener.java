package com.smartbinapp.smartbin;

/**
 * Created by Shilpa_Choudhary on 9/19/2015.
 */
import android.os.Bundle;
import android.app.Activity;
import android.app.ActionBar.Tab;
import android.support.v4.app.Fragment;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBar;


public class TabListener<T extends Fragment> implements ActionBar.TabListener {
    private Fragment fragment;

    // The contructor.
    public TabListener(Fragment fragment) {
        this.fragment = fragment;
    }

    // When a tab is tapped, the FragmentTransaction replaces
    // the content of our main layout with the specified fragment;
    // that's why we declared an id for the main layout.
//    @Override
//    public void onTabSelected(Tab tab, FragmentTransaction ft) {
//        //ft.replace(R.id.fillLevelsActivity, fragment);
//    }
//
//    // When a tab is unselected, we have to hide it from the user's view.
//    @Override
//    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
//        ft.remove(fragment);
//    }
//
//    // Nothing special here. Fragments already did the job.
//    @Override
//    public void onTabReselected(Tab tab, FragmentTransaction ft) {
//
//    }

    @Override
    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        ft.replace(R.id.binDetails, fragment);

    }

    @Override
    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
        ft.remove(fragment);

    }

    @Override
    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

    }
}
