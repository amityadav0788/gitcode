package com.ngo.tabswipeapp;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.learn2crack.tab.R;

public class MainActivity extends FragmentActivity {
    ViewPager Tab;
    TabPagerAdapter TabAdapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabAdapter = new TabPagerAdapter(getSupportFragmentManager());

        Tab = (ViewPager)findViewById(R.id.action_settings);
        try {
            Tab.setOnPageChangeListener(
                    new ViewPager.SimpleOnPageChangeListener() {
                        @Override
                        public void onPageSelected(int position) {

                            actionBar = getActionBar();
                            actionBar.setSelectedNavigationItem(position);
                        }
                    });
            Tab.setAdapter(TabAdapter);
        }
        catch(Exception e) {
        Log.d("hehehe", "exception");
        }
        actionBar = getActionBar();
        //Enable Tabs on Action Bar
        try {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        }
        catch(Exception e) {
            Log.d("hehehe 2nd ", "exception");
        }
        ActionBar.TabListener tabListener = new ActionBar.TabListener(){

            @Override
            public void onTabReselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

                Tab.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(android.app.ActionBar.Tab tab,
                                        FragmentTransaction ft) {
                // TODO Auto-generated method stub

            }};
        //Add New Tab
        try {
            actionBar.addTab(actionBar.newTab().setText("Android").setTabListener(tabListener));
            actionBar.addTab(actionBar.newTab().setText("iOS").setTabListener(tabListener));
            actionBar.addTab(actionBar.newTab().setText("Windows").setTabListener(tabListener));
        }

        catch(Exception e) {
            Log.d("hehehe", "exception");
        }
    }

}
