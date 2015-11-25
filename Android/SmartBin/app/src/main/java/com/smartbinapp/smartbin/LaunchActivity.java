package com.smartbinapp.smartbin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LaunchActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{

    private static final String TAB_FillLevels_TAG = "tab_FillLevels";
    private static final String TAB_Map_TAG = "tab_Map";
    private static final String TAB_Analaysis_TAG = "tab_Analysis";
    private FragmentTabHost mTabHost;

    private LoadBinsInfo reqbininfo =null;
    public static JSONArray bins=null;

    private BinsOfSearchedArea bininfoForArea = null;

    //private static String url_all_bins = "http://100.65.4.165/binapp/get_bins_radius.php?lat=12.9767&lon=77.5767&rad=20";
    private static final String url_all_bins = "http://192.168.0.4/binapp/get_bins_radius.php";
    private static final String url_serach_query = "http://192.168.0.4/binapp/get_for_serached_area.php";
    public static boolean data_for_search = false;

    private Context ctx;
    private Location mLastLocation;

    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    public static LatLng currentLocation=null;
    public static String searchedArea=null;


    // Google client to interact with Google API
    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ctx=this;


//        if(!isConnected(this))
//            buildDialog(this).show();
//        else {

        // First we need to check availability of play services
        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient();

            // we have internet connection, so it is save to connect to the internet here
            Log.d("GoogleAPi", " is available" );

            reqbininfo = new LoadBinsInfo();
            reqbininfo.execute();


//            while(bins==null) {
//                pDialog = new ProgressDialog(this);
//                pDialog.setMessage("Loading bins. Please wait...");
//                pDialog.setIndeterminate(false);
//                pDialog.setCancelable(false);
//                pDialog.show();
//            }
//
//            if(bins!=null && pDialog!=null)
//            {
//                pDialog.dismiss();
//            }



        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        /// add tab view
        InitView();




        EditText editText1 = (EditText) findViewById(R.id.SearchTextBox);
        editText1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                EditText myEditText = (EditText) v;

                if (keyCode == EditorInfo.IME_ACTION_SEARCH ||
                        keyCode == EditorInfo.IME_ACTION_DONE ||
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {

                    if (!event.isShiftPressed()) {

                        Log.d("EditText", myEditText.getText().toString() );
                        searchedArea=myEditText.getText().toString();

                        bininfoForArea=new BinsOfSearchedArea();
                        bininfoForArea.execute();



//                        Geocoder geo = new Geocoder(getBaseContext());
//                        List<Address> gotAddresses = null;
//                        try {
//                            gotAddresses = geo.getFromLocationName(myEditText.getText().toString(), 1);
//
//                            Address address = (Address) gotAddresses.get(0);
//
//                            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
//
//                            Log.d("GotAddress", address.getLatitude()+" "+ address.getLongitude());
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }

                        return true;
                    }

                }
                return false;
            }
        });

        // Loading bins in Background Thread

//        allBinsInfo.setContext(LaunchActivity.this);
//        bins = allBinsInfo.getAllRegBins();


    }

    private void InitView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(setIndicator(LaunchActivity.this, mTabHost.newTabSpec(TAB_FillLevels_TAG),
                R.drawable.filllevel_tab_indicator), FillLevelContainer.class, null);
        mTabHost.addTab(setIndicator(LaunchActivity.this, mTabHost.newTabSpec(TAB_Map_TAG),
                R.drawable.map_tab_indicator), MapContainer.class, null);
        mTabHost.addTab(setIndicator(LaunchActivity.this, mTabHost.newTabSpec(TAB_Analaysis_TAG),
                R.drawable.analysis_tab_indicator), AnalysisContainer.class, null);


    }

    private TabHost.TabSpec setIndicator(Context ctx, TabHost.TabSpec spec, int resid) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.tab_item, null);
        ImageView img = (ImageView)v.findViewById(R.id.img_tab);
        img.setBackgroundResource(resid);
        return spec.setIndicator(v);
    }

    public boolean isConnected(Context context) {

        boolean haveConnectedWifi =false;
        boolean haveConnectedMobile=false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { // connected to the internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                haveConnectedWifi = true;
            else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
               haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;

    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet connection.");
        builder.setMessage("You have no internet connection");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        return builder;
    }

    @Override
    public void onBackPressed() {
        boolean isPopFragment=false;


        String currentTabTag = mTabHost.getCurrentTabTag();

        if(currentTabTag.equals(TAB_FillLevels_TAG)){
            isPopFragment=((BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_FillLevels_TAG)).popFragment();
        }
        else if(currentTabTag.equals(TAB_Map_TAG)){
            isPopFragment = ((BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_Map_TAG)).popFragment();

        }
        else if (currentTabTag.equals(TAB_Analaysis_TAG)) {
        isPopFragment = ((BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_Analaysis_TAG)).popFragment();
        }

        if (!isPopFragment) {
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_launch, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_refresh:
                doRefresh();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void doRefresh(){

        reqbininfo = new LoadBinsInfo();
        reqbininfo.execute();

//        String currentTabTag = mTabHost.getCurrentTabTag();
//
//        BaseContainerFragment currFrg = (BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(currentTabTag);
//
//        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.detach(currFrg);
//        ft.attach(currFrg);
//        ft.commit();

    }

    private void openSettings(){

    }

    /**
     * Creating google api client object
     * */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    /**
     * Method to display the location on UI
     * */
    private LatLng getCurrentLocation() {

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();

            Log.d("CurrentLocation:", "lat="+Double.toString(latitude)+" lon="+Double.toString(longitude));
            return new LatLng(latitude, longitude);
        }

        //coudn't find the location setting default value to 0 0
        return new LatLng(0,0);
    }


    /**
            * Google api callback methods
    */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i("Launch", "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    @Override
    public void onConnected(Bundle arg0) {

        // Once connected with google api, get the location
        currentLocation=getCurrentLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }



    /**
     * Method to verify google play services on the device
     * */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(), "This device is not supported.", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkPlayServices();
    }

    public static AlertDialog.Builder buildDialogAsSearched(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("BinInfo");
        builder.setMessage("Currently showing info for " + LaunchActivity.searchedArea + ". Please click on refresh to get Bins info around current location.");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        return builder;
    }


    class LoadBinsInfo extends AsyncTask<String, String, String> {

        private ProgressDialog pDialog;


        // JSON Node names
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_BINS = "bins";

        // Creating JSON Parser object
        JSONParser jParser = new JSONParser();
        JSONObject json = null;


        /***
         * Before starting background thread Show Progress Dialog
         ***/
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if(ctx!=null) {
                if (pDialog != null) {
                    pDialog = null;
                }
                pDialog = new ProgressDialog(ctx);
                pDialog.setMessage("Loading bins. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
                Log.d("Preexecute", " Launchactivity dialoag shown");
            }
            Log.d("Preexecute", " exiting");
        }

        /**
         * getting All bins from url
         */
        protected String doInBackground(String... args) {

            try {

                Log.d("doinbackground", " Launchactivity inside");

                if(currentLocation==null){
                    currentLocation=getCurrentLocation();

                }

                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                //params.add(new BasicNameValuePair("lat", Double.toString(currentLocation.latitude)));
                //params.add(new BasicNameValuePair("lon", Double.toString(currentLocation.longitude)));
                params.add(new BasicNameValuePair("lat", Double.toString(12.9767)));
                params.add(new BasicNameValuePair("lon", Double.toString(77.5767)));
                params.add(new BasicNameValuePair("rad", Integer.toString(20)));

                // getting JSON string from URL
                json = jParser.makeHttpRequest(url_all_bins, "GET", params);

                if (json != null) {

                    // Check your log cat for JSON reponse
                    Log.d("All bins: ", json.toString());

                    // Checking for SUCCESS TAG
                    int success = json.getInt(TAG_SUCCESS);

                    if (success == 1) { //bins found

                        // Getting Array of Bins
                        bins = json.getJSONArray(TAG_BINS);

                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;


        }

        /**
         * After completing background task Dismiss the progress dialog
         ***/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            Log.d("Postexecute", " Launchactivity entered");
            data_for_search=false;
            if(pDialog!=null && bins!=null )
                pDialog.dismiss();

//            BaseContainerFragment fillLevelFrg = (BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(TAB_FillLevels_TAG);
//            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            ft.detach(fillLevelFrg);
//            ft.attach(fillLevelFrg);
//            ft.commit();

            String currentTabTag = mTabHost.getCurrentTabTag();

            BaseContainerFragment currFrg = (BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(currentTabTag);

            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.detach(currFrg);
            ft.attach(currFrg);
            ft.commit();

            Log.d("Postexecute", " initializing view");
            Log.d("Postexecute", " after calling initview");

        }
    }

    class BinsOfSearchedArea extends AsyncTask<String, String, String> {

        private ProgressDialog pDialog;


        // JSON Node names
        private static final String TAG_SUCCESS = "success";
        private static final String TAG_BINS = "bins";

        // Creating JSON Parser object
        JSONParser jParser = new JSONParser();
        JSONObject json = null;


        /***
         * Before starting background thread Show Progress Dialog
         ***/
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if(ctx!=null) {
                if (pDialog != null) {
                    pDialog = null;
                }
                pDialog = new ProgressDialog(ctx);
                pDialog.setMessage("Loading bins information. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
                Log.d("Preexecute", " search area dialoag shown");
            }
            Log.d("Preexecute", " searcharea exiting");
        }

        /**
         * getting All bins from url
         */
        protected String doInBackground(String... args) {

            try {

                Log.d("doinbackground", " search query inside");

                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("area", searchedArea));

                // getting JSON string from URL
                json = jParser.makeHttpRequest(url_serach_query, "GET", params);

                if (json != null) {

                    // Check your log cat for JSON reponse
                    Log.d("All bins: ", json.toString());

                    // Checking for SUCCESS TAG
                    int success = json.getInt(TAG_SUCCESS);

                    if (success == 1) { //bins found

                        bins = new JSONArray();

                        // Getting Array of Bins
                        bins = json.getJSONArray(TAG_BINS);

                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;


        }

        /**
         * After completing background task Dismiss the progress dialog
         ***/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            Log.d("Postexecute", " searcharea entered");


            if(pDialog!=null && bins!=null )
                pDialog.dismiss();

            String currentTabTag = mTabHost.getCurrentTabTag();

            BaseContainerFragment currFrg = (BaseContainerFragment)getSupportFragmentManager().findFragmentByTag(currentTabTag);

            final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.detach(currFrg);
            ft.attach(currFrg);
            ft.commit();

            data_for_search=true;

            Log.d("Postexecute", " searched are initializing view");
            Log.d("Postexecute", " after calling search query");

        }
    }


}




//import com.couchbase.lite.Database;
//import com.couchbase.lite.LiveQuery;
//import com.couchbase.lite.Manager;
//import com.couchbase.lite.util.*;



//    //    couch internals
//    protected static Manager manager;
//    private Database database;
//    private LiveQuery liveQuery;

//    //public static final String SYNC_URL = "http://127.0.0.1:5984/"; //couch database
//
//    // url to get all products list
//    private static String url_all_products = "http://10.245.213.135/binapp/get_all_bins_pdo.php";
//
//    public static String TAG = "BinApp";

//all child activity should have this arrow
//getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        //button will be displayed in this layout
//        final LinearLayout llFillLevels = (LinearLayout) findViewById(R.id.fillLevelsActivity);
//
//        //Create four
//        for(int j=0;j<=10;j++) {
////            // Create LinearLayout
////            LinearLayout llLayout = new LinearLayout(this);
////            llLayout.setOrientation(LinearLayout.VERTICAL);
//
//            // Create Button
//            final Button btn = new Button(this);
//
//            // Give button an ID
//            btn.setId(j+1);
//            btn.setText("Bin " + j + 1);
//            // set the layoutParams on the button
//            btn.setLayoutParams(llFillLevels.getLayoutParams());
////            btn.setMinimumWidth(launch.getMeasuredWidth());
//
//            final int index = j;
//            // Set click listener for button
//            btn.setOnClickListener(new OnClickListener() {
//                public void onClick(View v) {
//
//                    android.util.Log.i("TAG", "index :" + index);
//
//                    Toast.makeText(getApplicationContext(), "Clicked Button Index :" + index, Toast.LENGTH_LONG).show();
//
//                }
//            });
//
//            //Add button to LinearLayout
//            //Add button to LinearLayout
//            llFillLevels.addView(btn);
//            //Add button to LinearLayout defined in XML
////            launch.addView(llLayout);
//        }


//        //to start couch db
//        try {
//            startCBLite();
//        } catch (Exception e) {
//            Toast.makeText(getApplicationContext(), "Error Initializing CBLIte, see logs for details", Toast.LENGTH_LONG).show();
//            com.couchbase.lite.util.Log.e(TAG, "Error initializing CBLite", e);
//        }

// addlistener for image button
//        addListenerOnImageButton((ImageButton) findViewById(R.id.btn_fill_levels));
//        addListenerOnImageButton((ImageButton) findViewById(R.id.btn_map));
//        addListenerOnImageButton((ImageButton) findViewById(R.id.btn_analysis));


//    public static List<BinInfo> BinList = new ArrayList<>();
//    public static JSONObject json =null;
//
//
//    // JSON Node names
//    private static final String TAG_SUCCESS = "success";
//    private static final String TAG_PRODUCTS = "bins";
//    private static final String TAG_PID = "ID";
//    private static final String TAG_NAME = "Fill";
//
//    // products JSONArray
//    public static JSONArray bins = null;
//    // Creating JSON Parser object
//    JSONParser jParser = new JSONParser();


//    public void addListenerOnImageButton(ImageButton imageButton) {
//
//        imageButton.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(getApplicationContext(), "ImageButton is clicked!", Toast.LENGTH_SHORT).show();
//                v.findViewById(R.id.btn_analysis).setBackgroundResource(R.drawable.button_analysis_white);
//
//            }
//        });
//
//
//
//        imageButton.bringToFront();
//        imageButton.setFocusable(true);
//        imageButton.setFocusableInTouchMode(true);
//        return;
//    }

//    protected void startCBLite() throws Exception {
//
//        Manager.enableLogging(TAG, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_SYNC_ASYNC_TASK, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_SYNC, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_QUERY, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_VIEW, com.couchbase.lite.util.Log.VERBOSE);
//        Manager.enableLogging(com.couchbase.lite.util.Log.TAG_DATABASE, com.couchbase.lite.util.Log.VERBOSE);
//
//        manager = new Manager(new AndroidContext(this), Manager.DEFAULT_OPTIONS);
//
//        //install a view definition needed by the application
//        database = manager.getDatabase(DATABASE_NAME);
//        com.couchbase.lite.View viewItemsByDate = database.getView(String.format("%s/%s", designDocName, byDateViewName));
//        viewItemsByDate.setMap(new Mapper() {
//            @Override
//            public void map(Map<String, Object> document, Emitter emitter) {
//                Object createdAt = document.get("created_at");
//                if (createdAt != null) {
//                    emitter.emit(createdAt.toString(), null);
//                }
//            }
//        }, "1.0");
//
//        initItemListAdapter();
//
//        startLiveQuery(viewItemsByDate);
//
//        startSync();
//
//    }
//
//    private void startSync() {
//
//        URL syncUrl;
//        try {
//            syncUrl = new URL(SYNC_URL);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
//
//        Replication pullReplication = database.createPullReplication(syncUrl);
//        pullReplication.setContinuous(true);
//
//        Replication pushReplication = database.createPushReplication(syncUrl);
//        pushReplication.setContinuous(true);
//
//        pullReplication.start();
//        pushReplication.start();
//
//        pullReplication.addChangeListener(this);
//        pushReplication.addChangeListener(this);
//
//    }


//    /**
//     * Background Async Task to Load all product by making HTTP Request
//     * */
//    class LoadAllProducts extends AsyncTask<String, String, String> {
//
//        // Progress Dialog
//        private ProgressDialog pDialog;

//        /**
//         * Before starting background thread Show Progress Dialog
//         * */
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            if(pDialog !=null)
//            {
//                pDialog = null;
//            }
//            pDialog = new ProgressDialog(LaunchActivity.this);
//            pDialog.setMessage("Loading products. Please wait...");
//            pDialog.setIndeterminate(false);
//            pDialog.setCancelable(false);
//            pDialog.show();
//        }
//
//        /**
//         * getting All products from url
//         * */
//        protected String doInBackground(String... args) {
//            // Building Parameters
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            // getting JSON string from URL
//            json = jParser.makeHttpRequest(url_all_products, "GET", params);
//            // Check your log cat for JSON reponse
//            //Log.d("All Products: ", json.toString());
//
//            try {
//                Log.d("hehehe", url_all_products.toString() + " --------- " + params.toString() + "---------- " + json.getString(TAG_SUCCESS));
//
//                // Checking for SUCCESS TAG
//                int success = json.getInt(TAG_SUCCESS);
//                Log.d("hehehe sucback",Integer.toString(success));
//                if (success == 1) {
//                    // products found
//                    // Getting Array of Products
//                    bins= json.getJSONArray(TAG_PRODUCTS);
//                    Log.d("hehehe i in doinback",Integer.toString(bins.length()));
//                    // looping through All Products
//
//                    BinList = new ArrayList<BinInfo>();
//                    for (int i = 0; i < bins.length(); i++) {
//                        JSONObject c = bins.getJSONObject(i);
//                        BinInfo bininfo = new BinInfo();
//
//                        // Storing each json item in variable
//                        String id = c.getString(TAG_PID);
//                        String fill = c.getString(TAG_NAME);
//
//                        Log.d("hehehe id inkground ",id);
//                        Log.d("hehehe round ", fill);
//                        // creating new HashMap
////                        HashMap<String, String> map = new HashMap<String, String>();
////
//                        // adding each child node to HashMap key => value
//                        //map.put(TAG_PID, id);
//                        //map.put(TAG_NAME, fill);
//
//                        bininfo.id =Integer.parseInt(id);
//                        bininfo.fillLevel=Double.parseDouble(fill);
//
//                        // adding HashList to ArrayList
//                        //productsList.add(map);
//                        BinList.add(bininfo);
//
//                        Log.d("just finLoad", BinList.toString());
//                    }
//                } else {
//                    // no products found
//                    // Launch Add New product Activity
//                    //Intent i = new Intent(getApplicationContext(),
//                    // NewProductActivity.class);
//                    // Closing all previous activities
//                    //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    // startActivity(i);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//            return null;
//        }
//
//        /**
//         * After completing background task Dismiss the progress dialog
//         * **/
//        protected void onPostExecute(String file_url) {
//            // dismiss the dialog after getting all products
//            pDialog.dismiss();
//            // updating UI from Background Thread
////            runOnUiThread(new Runnable() {
////                public void run() {
////                    /**
////                     * Updating parsed JSON data into ListView
////                     * */
////                    ListAdapter adapter = new SimpleAdapter(
////                            AllProductsActivity.this, productsList,
////                            R.layout.list_item, new String[] { TAG_PID,
////                            TAG_NAME},
////                            new int[] { R.id.id, R.id.fill });
////                    // updating listview
////                    setListAdapter(adapter);
////                }
////            });
//
//        }
//
//}




