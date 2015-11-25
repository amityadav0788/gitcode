package com.smartbinapp.smartbin;

//import android.app.ActionBar;
import android.app.ActionBar;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class BinDetailsActivity1 extends AppCompatActivity  {

    private final static String TAG = "BinDetailsActivity";

    // Declaring our tabs and the corresponding fragments.
    android.support.v7.app.ActionBar.Tab weekTab, customTab;

    Fragment weekFragmentTab = new weekFregmentTab();
    Fragment customFragmentTab = new customFregmentTab();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        // The Action Bar is a window feature. The feature must be requested
//        // before setting a content view. Normally this is set automatically
//        // by your Activity's theme in your manifest. The provided system
//        // theme Theme.WithActionBar enables this for you. Use it as you would
//        // use Theme.NoTitleBar. You can add an Action Bar to your own themes
//        // by adding the element <item name="android:windowActionBar">true</item>
//        // to your style definition.
//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_bin_details1);

        // Set up the action bar.
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        Log.d(TAG, "hehehe in mainactivity actionbar " + actionBar.toString());
        // Screen handling while hiding ActionBar icon.
        actionBar.setDisplayShowHomeEnabled(false);

        // Screen handling while hiding Actionbar title.
        //actionBar.setDisplayShowTitleEnabled(false);

        // Creating ActionBar tabs.
        actionBar.setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_TABS);


        weekTab = actionBar.newTab().setText("Week");
        Log.v(TAG,"hehehe in mainactivity actionbar week");
        customTab= actionBar.newTab().setText("Custom");
        Log.v(TAG,"hehehe in mainactivity actionbar custom");

        // Setting tab listeners.
        //Fragment fragment=weekFragmentTab;
        weekTab.setTabListener(new TabListener(weekFragmentTab));
        Log.v(TAG, "hehehe in mainactivity actionbar week listner");
        customTab.setTabListener(new TabListener(customFragmentTab));
        Log.v(TAG, "hehehe in mainactivity actionbar custom tab listner");

        // Adding tabs to the ActionBar.
        actionBar.addTab(weekTab);
        Log.v(TAG, "hehehe in mainactivity actionbar add action bar");
        actionBar.addTab(customTab);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bin_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
//        // When the given tab is selected, switch to the corresponding page in
//        // the ViewPager.
//        //mViewPager.setCurrentItem(tab.getPosition());
//    }
//
//    @Override
//    public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
//    }
//
//    @Override
//    public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
//    }
}
